/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.historico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRException;
import smei.dao.DBConnection;
import smei.util.ReportGenerator;

/**
 *
 * @author Ernesto
 */
public class MaestroTendencia extends javax.swing.JInternalFrame {

    /**
     * Creates new form MaestroTendencia
     */
    private static MaestroTendencia instancia = new MaestroTendencia();
    private Map<String, String> mapeoReservaciones;
    private Map<String, Object> mapeoParametros;
    private Connection conn = DBConnection.getConnection();
    private PreparedStatement pstm;
    private ResultSet rs;
    private ReportGenerator reporte;

    private MaestroTendencia() {
        initComponents();
        initializeValues();
    }

    public static MaestroTendencia getInstance() {
        return instancia;
    }

    public void initializeValues() {
        //Registrando el grupo de Entidades
        grpEntidades.add(rbtnReserva);
        grpEntidades.add(rbtnEspacio);
        grpEntidades.add(rbtnUsuario);

        //Registrando el grupo de Tipo de reporte
        grpTipo.add(rbtnRegistro);
        grpTipo.add(rbtnTendencia);

        //Seleccionando las opciones 
        rbtnReserva.setSelected(true);
        rbtnRegistro.setSelected(true);

        mapeoReservaciones = new HashMap<String, String>();

        mapeoReservaciones.put("Canceladas", "CANCELADA");
        mapeoReservaciones.put("Completadas", "COMPLETADA");
        mapeoReservaciones.put("Pendientes", "PENDIENTE");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "Archivos Excel (*.xlsx)";
            }

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".xlsx");
                }
            }
        });
        this.setSize(297, 193);

    }

    private void cambiarEstadoCheckBoxes(boolean b) {
        chkCompletada.setVisible(b);
        chkPendiente.setVisible(b);
        chkCancelada.setVisible(b);

        rbtnTendencia.setVisible(!b);
    }

    private void cambiarEstadoAntiguedad(boolean b) {
        spnTiempo.setVisible(b);
        lblMes.setVisible(b);
    }

    private void esconderCheckBoxes() {
        cambiarEstadoCheckBoxes(false);
    }

    private void mostrarCheckBoxes() {
        cambiarEstadoCheckBoxes(true);
    }

    private void esconderAntiguedad() {
        cambiarEstadoAntiguedad(false);
    }

    private void mostrarAntiguedad() {
        cambiarEstadoAntiguedad(true);
    }

    private void verificarSiMuestraCheckBoxes() {
        if (rbtnReserva.isSelected()) {
            rbtnRegistro.setSelected(true);
            mostrarCheckBoxes();
        } else {
            esconderCheckBoxes();
        }
    }

    private void verificarSiMuestraAntiguedad() {
        if ((!rbtnReserva.isSelected() && rbtnTendencia.isSelected()) || rbtnReserva.isSelected()) {
            mostrarAntiguedad();
        } else {
            esconderAntiguedad();
        }
    }

//    private void 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpEntidades = new javax.swing.ButtonGroup();
        grpTipo = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        rbtnReserva = new javax.swing.JRadioButton();
        rbtnEspacio = new javax.swing.JRadioButton();
        rbtnUsuario = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        rbtnRegistro = new javax.swing.JRadioButton();
        rbtnTendencia = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        chkCompletada = new javax.swing.JCheckBox();
        chkPendiente = new javax.swing.JCheckBox();
        chkCancelada = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        spnTiempo = new javax.swing.JSpinner();
        lblMes = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rbtnReserva.setText("Reservaciones");
        rbtnReserva.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnReservaStateChanged(evt);
            }
        });
        getContentPane().add(rbtnReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        rbtnEspacio.setText("Espacios");
        getContentPane().add(rbtnEspacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 7, -1, -1));

        rbtnUsuario.setText("Usuarios");
        getContentPane().add(rbtnUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 7, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, 260, 10));

        rbtnRegistro.setText("Histórico");
        rbtnRegistro.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnRegistroStateChanged(evt);
            }
        });
        getContentPane().add(rbtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, -1, -1));

        rbtnTendencia.setText("Tendencia");
        rbtnTendencia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnTendenciaStateChanged(evt);
            }
        });
        getContentPane().add(rbtnTendencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 49, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 79, 260, 10));

        chkCompletada.setText("Completadas");
        getContentPane().add(chkCompletada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 91, -1, -1));

        chkPendiente.setText("Pendientes");
        getContentPane().add(chkPendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 91, -1, -1));

        chkCancelada.setText("Canceladas");
        getContentPane().add(chkCancelada, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 91, -1, -1));

        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 132, -1, -1));

        jButton2.setText("Limpiar");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 132, 71, -1));

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 132, 71, -1));

        spnTiempo.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        spnTiempo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTiempoStateChanged(evt);
            }
        });
        getContentPane().add(spnTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        lblMes.setText("Mes");
        getContentPane().add(lblMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnRegistroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnRegistroStateChanged
        verificarSiMuestraCheckBoxes();
        verificarSiMuestraAntiguedad();
    }//GEN-LAST:event_rbtnRegistroStateChanged

    private void rbtnReservaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnReservaStateChanged
        verificarSiMuestraCheckBoxes();
        verificarSiMuestraAntiguedad();
        if (rbtnReserva.isSelected()) {
            rbtnRegistro.setText("Histórico");
        } else {
            rbtnRegistro.setText("Registro");
        }
    }//GEN-LAST:event_rbtnReservaStateChanged

    private void generarReporteReservaciones(String ruta) {
        final String reporteSeleccionado = "Registro_Reservas";
        ArrayList<InputStream> fis = new ArrayList<InputStream>();
        ArrayList<String> estados = new ArrayList<String>();
        String titulo = new String();
        mapeoParametros = new HashMap<String, Object>();
        reporte = new ReportGenerator();

        try {
            estados.add((chkCompletada.isSelected()) ? chkCompletada.getText() : null);
            estados.add((chkPendiente.isSelected()) ? chkPendiente.getText() : null);
            estados.add((chkCancelada.isSelected()) ? chkCancelada.getText() : null);
            estados.removeAll(Collections.singleton(null));

            mapeoParametros.put("periodo", String.valueOf(spnTiempo.getValue()));
            mapeoParametros.put("idEstado1", new String());
            mapeoParametros.put("idEstado2", new String());
            mapeoParametros.put("idEstado3", new String());

            fis.add(new FileInputStream(new File("resources/reports/"
                    + reporteSeleccionado + ".jrxml")));

            pstm = conn.prepareCall("select idEstado from estados_reservaciones where nombre = ?");
            for (byte i = 1; i < (estados.size() + 1); i++) {
                pstm.setString(1, estados.get(i - 1));
                rs = pstm.executeQuery();
                while (rs.next()) {
                    mapeoParametros.put("idEstado" + i, rs.getString("idEstado"));
                }
                titulo = titulo + estados.get(i - 1);
                if ((estados.size() - i) > 1) {
                    titulo = titulo + ", ";
                } else if ((estados.size() - i) == 1) {
                    titulo = titulo + " y ";
                }
            }
            mapeoParametros.put("reporte", titulo);

            reporte.printExcelReport(fis, ruta, mapeoParametros, conn);
        } catch (SQLException ex) {
            Logger.getLogger(MaestroTendencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(MaestroTendencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaestroTendencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarReporteEntidades(String reporteSeleccionado, String ruta) {
        try {
            ArrayList<InputStream> fis = new ArrayList<InputStream>();
            mapeoParametros = new HashMap<String, Object>();
            mapeoParametros.put("periodo", String.valueOf(spnTiempo.getValue()));
            reporte = new ReportGenerator();

            fis.add(new FileInputStream(new File("resources/reports/"
                    + reporteSeleccionado + ".jrxml")));

            reporte.printExcelReport(fis, ruta, mapeoParametros, conn);

        } catch (JRException ex) {
            Logger.getLogger(MaestroTendencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaestroTendencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        getInstance().setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void rbtnTendenciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnTendenciaStateChanged
        verificarSiMuestraAntiguedad();
    }//GEN-LAST:event_rbtnTendenciaStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final String[] archivosJasper = {"Tendencia_Espacios", "Registro_Espacios",
            "Tendencia_Usuarios", "Registro_Usuarios"};
        String archivo;

        if (rbtnReserva.isSelected()) {
            archivo = "Historico Reserva";
        } else if (rbtnEspacio.isSelected()) {
            archivo = (rbtnTendencia.isSelected() ? archivosJasper[0] : archivosJasper[1]);
        } else {
            archivo = (rbtnTendencia.isSelected() ? archivosJasper[2] : archivosJasper[3]);
        }

        fileChooser.setSelectedFile(new File(archivo.replace("_", " ")));
        if (fileChooser.showSaveDialog(getInstance()) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();

            if (!ruta.endsWith(".xlsx")) {
                ruta += ".xlsx";
            }

            if (new File(ruta).exists()) {
                if (JOptionPane.showConfirmDialog(rootPane, "El archivo " + new File(ruta).getName() + " ya existe\n"
                        + "¿Desea sobreescribirlo?", "Aviso", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }

            if (rbtnReserva.isSelected()) {
                generarReporteReservaciones(ruta);
            } else if (rbtnEspacio.isSelected()) {
                generarReporteEntidades(archivo, ruta);
            } else {
                generarReporteEntidades(archivo, ruta);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void spnTiempoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTiempoStateChanged
        if ((Integer) spnTiempo.getValue() > 1) {
            lblMes.setText("Meses");
        } else {
            lblMes.setText("Mes");
        }
    }//GEN-LAST:event_spnTiempoStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkCancelada;
    private javax.swing.JCheckBox chkCompletada;
    private javax.swing.JCheckBox chkPendiente;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.ButtonGroup grpEntidades;
    private javax.swing.ButtonGroup grpTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblMes;
    private javax.swing.JRadioButton rbtnEspacio;
    private javax.swing.JRadioButton rbtnRegistro;
    private javax.swing.JRadioButton rbtnReserva;
    private javax.swing.JRadioButton rbtnTendencia;
    private javax.swing.JRadioButton rbtnUsuario;
    private javax.swing.JSpinner spnTiempo;
    // End of variables declaration//GEN-END:variables
}
