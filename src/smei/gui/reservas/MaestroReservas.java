/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.reservas;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import smei.dao.DAOEspacio;
import smei.dao.DAOReservas;
import smei.modelos.Espacio;
import smei.modelos.Reserva;
import smei.modelos.Usuario;
import smei.util.GUIUtil;
import smei.util.Util;

/**
 *
 * @author Ernesto
 */
public final class MaestroReservas extends javax.swing.JInternalFrame {

    private static MaestroReservas instancia = new MaestroReservas();
    private DAOReservas daoReserva = new DAOReservas();
    private ArrayList<Espacio> modeloEspacios;
    private Reserva reserva;

    /**
     * Creates new form MaestroReservas
     */
    public MaestroReservas() {
        initComponents();
        initComponentsValues();
    }

    public static MaestroReservas getInstance() {
//        setReservaToNull();
        return instancia;
    }

    public void initComponentsValues() {
        // SpinnerModel modeloHora = new SpinnerNumberModel(1, 1, 12, 1);
        spnHoraI.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        spnHoraF.setModel(new SpinnerNumberModel(1, 1, 12, 1));

        // SpinnerModel modeloMint = new SpinnerNumberModel(1, 0, 59, 5);
        spnMinutoI.setModel(new SpinnerNumberModel(0, 0, 59, 5));
        spnMinutoF.setModel(new SpinnerNumberModel(0, 0, 59, 5));

        String[] tandaList = {"AM", "PM"};
        // SpinnerListModel modelotanda = new SpinnerListModel(tandaList);
        spnTandaI.setModel(new SpinnerListModel(tandaList));
        spnTandaF.setModel(new SpinnerListModel(tandaList));

        //Aceptar y Modificar deben estar en el mismo lugar!
        btnModificar.setLocation(btnAceptar.getLocation());

        GUIUtil.setCalendarChooserAfterToday(dChooserFecha.getJCalendar());

        agregarEventoSpinners(this);

        this.setSize(380, 400);
    }

    private void agregarEventoSpinners(Container con) {
        for (Component c : con.getComponents()) {
            if (c instanceof JSpinner) {
                ((JSpinner) c).addChangeListener(new javax.swing.event.ChangeListener() {
                    @Override
                    public void stateChanged(javax.swing.event.ChangeEvent evt) {
                        llenarCMBEspaciosEnVivo();
                    }
                });
            } else if (c instanceof Container) {
                agregarEventoSpinners((Container) c);
            }
        }
    }

    private boolean llenarReserva() {
        String validar = GUIUtil.validarCampos(getInstance());

        if (!validar.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Verificar " + validar.replace('_', ' '));
            return false;
        }

        if (dChooserFecha.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "Verificar fecha");
            return false;
        }

        Date fInicio = Util.crearDateConHora(dChooserFecha.getDate(), Util.buildHoraFromStrings(
                String.valueOf(spnHoraI.getValue()), String.valueOf(spnMinutoI.getValue()), (String) spnTandaI.getValue()));
        Date fFin = Util.crearDateConHora(dChooserFecha.getDate(), Util.buildHoraFromStrings(
                String.valueOf(spnHoraF.getValue()), String.valueOf(spnMinutoF.getValue()), (String) spnTandaF.getValue()));

        if (fInicio.getTime() <= Calendar.getInstance().getTime().getTime()) {
            JOptionPane.showMessageDialog(rootPane, "La hora de inicio debe de ser posterior a la actual");
            return false;
        }

        if (fInicio.getTime() >= fFin.getTime()) {
            JOptionPane.showMessageDialog(rootPane, "La hora fin debe ser posterior a la de inicio");
            return false;
        }

//        if (cbbLugar.getSelectedIndex() == -1) {
//            JOptionPane.showMessageDialog(rootPane, "Debe de seleccionar un espacio a reservar");
//            return false;
//        }
        //Es nuevo
        if (reserva == null) {
            reserva = new Reserva();
        }

        //Modificar
        Usuario u = new Usuario();
        u.setIdUsuario(1);

        Espacio e = new Espacio();
        e.setId(modeloEspacios.get(cbbLugar.getSelectedIndex()).getId());

        reserva.setUsuario(u);
        reserva.setEspacio(e);

        //OK
        reserva.setFechaInicio(fInicio);
        reserva.setFechaFin(fFin);
        reserva.setCantPersonas(Integer.valueOf(txtCantPersonas.getText()));
        reserva.setDescripcion(txtDesc.getText());

        return true;
    }

    public void cargarData(Reserva reserva) {
        this.reserva = reserva;

        llenarCamposFromReserva(reserva);
        GUIUtil.asignarTitulo(getInstance(), "Reserva " + reserva.getId());

        if (!this.reserva.getEstado().getNombre().equals("Pendiente")) {
            btnModificar.setVisible(false);
            btnModificar.setEnabled(false);
        } else {
            btnModificar.setVisible(true);
            btnModificar.setEnabled(true);
        }
    }

    private void llenarCamposFromReserva(Reserva r) {
        String[] hInicio = Util.getHoraDesdeFecha(r.getFechaInicio());
        String[] hFin = Util.getHoraDesdeFecha(r.getFechaFin());

        dChooserFecha.setDate(r.getFechaInicio());

        //Hora de inicio
        spnHoraI.setValue(new Integer(hInicio[0]));
        spnMinutoI.setValue(new Integer(hInicio[1]));
        spnTandaI.setValue(hInicio[2]);

        //Hora fin
        spnHoraF.setValue(new Integer(hFin[0]));
        spnMinutoF.setValue(new Integer(hFin[1]));
        spnTandaF.setValue(hFin[2]);

        txtCantPersonas.setText(String.valueOf(r.getCantPersonas()));
        txtDesc.setText(r.getDescripcion());

        //Llenando Combo Box
        llenarCMBEspaciosFromReserva(reserva);
        cbbLugar.setSelectedIndex(getEspacioIndex(r));
    }

    private void llenarCMBEspaciosFromReserva(Reserva r) {
        //If reserva == null; 

        modeloEspacios = new DAOEspacio().getEspaciosDisponiblesParaReserva(r);
        String[] values = new String[modeloEspacios.size()];

        for (int i = 0; i < modeloEspacios.size(); i++) {
            values[i] = modeloEspacios.get(i).getNombre();
        }

        cbbLugar.setModel(new DefaultComboBoxModel(values));
    }

    private void llenarCMBEspaciosEnVivo() {
        Reserva r = new Reserva();

        Date fecha = dChooserFecha.getDate();
        Integer cant = null;

        if (!txtCantPersonas.getText().trim().isEmpty()) {
            cant = Integer.valueOf(txtCantPersonas.getText());
        }

        if (fecha == null || cant == null) {
            return;
        }

        Usuario u = new Usuario();
        u.setIdUsuario(1);

        r.setUsuario(u);
        r.setFechaInicio(Util.crearDateConHora(fecha, Util.buildHoraFromStrings(
                String.valueOf(spnHoraI.getValue()), String.valueOf(spnMinutoI.getValue()), (String) spnTandaI.getValue())));
        r.setFechaFin(Util.crearDateConHora(fecha, Util.buildHoraFromStrings(
                String.valueOf(spnHoraF.getValue()), String.valueOf(spnMinutoF.getValue()), (String) spnTandaF.getValue())));
        r.setCantPersonas(cant);

        llenarCMBEspaciosFromReserva(r);
    }

    private int getEspacioIndex(Reserva r) {
        final int id = r.getEspacio().getId();

        for (int i = 0; i < modeloEspacios.size(); i++) {
            if (modeloEspacios.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

//    public static void setReservaToNull() {
//        reserva = null;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbbLugar = new javax.swing.JComboBox();
        dChooserFecha = new com.toedter.calendar.JDateChooser();
        txtCantPersonas = new javax.swing.JTextField();
        spnHoraF = new javax.swing.JSpinner();
        spnMinutoF = new javax.swing.JSpinner();
        spnTandaF = new javax.swing.JSpinner();
        spnTandaI = new javax.swing.JSpinner();
        spnMinutoI = new javax.swing.JSpinner();
        spnHoraI = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(38, 33));
        setPreferredSize(new java.awt.Dimension(380, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbLugar.setName(""); // NOI18N
        getContentPane().add(cbbLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 183, 186, -1));

        dChooserFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dChooserFechaPropertyChange(evt);
            }
        });
        getContentPane().add(dChooserFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 38, 186, -1));

        txtCantPersonas.setName("Cantidad_de_Personas"); // NOI18N
        txtCantPersonas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantPersonasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantPersonasKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 145, 64, -1));
        getContentPane().add(spnHoraF, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 114, 37, -1));
        getContentPane().add(spnMinutoF, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 114, 37, -1));
        getContentPane().add(spnTandaF, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 114, 50, -1));
        getContentPane().add(spnTandaI, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 76, 50, -1));
        getContentPane().add(spnMinutoI, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 76, 37, -1));
        getContentPane().add(spnHoraI, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 76, 37, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Descripción:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 218, 115, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Lugar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 183, 115, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cantidad de Personas:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 115, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 38, 115, -1));

        txtDesc.setColumns(20);
        txtDesc.setLineWrap(true);
        txtDesc.setRows(5);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setName("Descripcion"); // NOI18N
        txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDesc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 221, 186, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Hora Inicio:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, 115, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Hora Fin:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 114, 115, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 80, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 75, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 75, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        GUIUtil.deshabilitarBtnModificar(getInstance());
//        GUIUtil.habilitarBtnSalir(getInstance());
        GUIUtil.habilitarEdicion(getInstance());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (reserva == null) {
            GUIUtil.limpiarContenido(getInstance());
        } else {
            GUIUtil.limpiarContenido(getInstance());
            llenarCamposFromReserva(reserva);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCantPersonasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantPersonasKeyTyped
        Util.aceptaSoloNumeros(evt, evt.getKeyChar());
    }//GEN-LAST:event_txtCantPersonasKeyTyped

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (llenarReserva()) {
            if (reserva.getId() == null) {
                daoReserva.insertarReserva(reserva);
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea ingresar una nueva reserva?", "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    GUIUtil.limpiarContenido(getInstance());
                    return;
                }
            } else {
                daoReserva.actualizarReserva(reserva);
                reserva = null;
            }
            GUIUtil.limpiarContenido(getInstance());
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        GUIUtil.limpiarContenido(getInstance());
        reserva = null;
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void dChooserFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dChooserFechaPropertyChange
        llenarCMBEspaciosEnVivo();
    }//GEN-LAST:event_dChooserFechaPropertyChange

    private void txtCantPersonasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantPersonasKeyReleased
        llenarCMBEspaciosEnVivo();
    }//GEN-LAST:event_txtCantPersonasKeyReleased

    private void txtDescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyTyped
        Util.limitaLongitud(evt, txtDesc.getText(), 200);
    }//GEN-LAST:event_txtDescKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbbLugar;
    private com.toedter.calendar.JDateChooser dChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnHoraF;
    private javax.swing.JSpinner spnHoraI;
    private javax.swing.JSpinner spnMinutoF;
    private javax.swing.JSpinner spnMinutoI;
    private javax.swing.JSpinner spnTandaF;
    private javax.swing.JSpinner spnTandaI;
    private javax.swing.JTextField txtCantPersonas;
    private javax.swing.JTextArea txtDesc;
    // End of variables declaration//GEN-END:variables
}
