/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.espacios;

import javax.swing.JOptionPane;
import smei.dao.DAOEspacio;
import smei.modelos.Espacio;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto Mancebo T
 */
public class MaestroEspacios extends javax.swing.JInternalFrame {

    private static MaestroEspacios instancia = new MaestroEspacios();
    private Espacio espacio;
    private DAOEspacio daoEspacio = new DAOEspacio();

    /**
     * Creates new form MaestroEspacios
     */
    private MaestroEspacios() {
        initComponents();
        initializeValues();
    }

    public static MaestroEspacios getInstance() {
        return instancia;
    }

    public void initializeValues() {
        this.setSize(323, 274);
        btnModificar.setLocation(btnAceptar.getLocation());
    }

    public boolean llenarEspacio() {
        String validar = GUIUtil.validarCampos(getInstance());

        if (!validar.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Verificar " + validar);
            return false;
        }

        //Es nuevo
        if (espacio == null) {
            espacio = new Espacio();
        }

        espacio.setNombre(txtNombre.getText());
        espacio.setCapacidadDePersonas(Integer.valueOf(txtLimiteP.getText()));
        espacio.setDescripcion(txtDescripcion.getText());
        espacio.setHabilitado(chkHabilitado.isSelected());

        return true;
    }

    public void cargarDataFromID(Espacio espacio) {
        this.espacio = espacio;
        llenarCamposFromEspacio(espacio);
        GUIUtil.asignarTitulo(getInstance(), "Espacio " + espacio.getId());
    }

    public void llenarCamposFromEspacio(Espacio e) {
        txtNombre.setText(e.getNombre());
        txtLimiteP.setText(String.valueOf(e.getCapacidadDePersonas()));
        txtDescripcion.setText(e.getDescripcion());
        chkHabilitado.setSelected(e.isHabilitado());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtLimiteP = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        chkHabilitado = new javax.swing.JCheckBox();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Límite de Personas:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel2.setText("Nombre de Espacio:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setText("Descripción:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        txtLimiteP.setName(" "); // NOI18N
        txtLimiteP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLimitePActionPerformed(evt);
            }
        });
        txtLimiteP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLimitePKeyTyped(evt);
            }
        });
        getContentPane().add(txtLimiteP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 76, -1));

        txtNombre.setName(" "); // NOI18N
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 166, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setName(" "); // NOI18N
        jScrollPane1.setViewportView(txtDescripcion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 70, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 75, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 75, -1));

        jLabel4.setText("Habilitado:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));
        getContentPane().add(chkHabilitado, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        GUIUtil.limpiarContenido(getInstance());
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        GUIUtil.limpiarContenido(getInstance());
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        GUIUtil.deshabilitarBtnModificar(getInstance());
//        GUIUtil.habilitarBtnSalir(getInstance());
        GUIUtil.habilitarEdicion(getInstance());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (llenarEspacio()) {
            if (espacio.getId() != null) {
                daoEspacio.actualizarEspacio(espacio);
                espacio = null;
            } else {
                daoEspacio.insertarEspacio(espacio);
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtLimitePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLimitePKeyTyped
        GUIUtil.aceptaSoloNumeros(evt, evt.getKeyChar());
    }//GEN-LAST:event_txtLimitePKeyTyped

    private void txtLimitePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLimitePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLimitePActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkHabilitado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtLimiteP;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
