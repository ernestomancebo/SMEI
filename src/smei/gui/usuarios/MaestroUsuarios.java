/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.usuarios;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import smei.dao.DAOUsuario;
import smei.modelos.Email;
import smei.modelos.Rol;
import smei.modelos.Telefono;
import smei.modelos.Usuario;
import smei.util.Util;

/**
 *
 * @author Ernesto Mancebo T
 */
public class MaestroUsuarios extends javax.swing.JInternalFrame {

    private static MaestroUsuarios instancia = new MaestroUsuarios();
    private Usuario usuario;
    private DAOUsuario daoUsuario = new DAOUsuario();

    /**
     * Creates new form MaestroUsuarios
     */
    private MaestroUsuarios() {
        initComponents();
        initializeComponets();
    }

    public static MaestroUsuarios getInstance() {
        return instancia;
    }

    public void initializeComponets() {
//        jCheckBox1.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setSize(287, 266);
        btnModificar.setLocation(btnAceptar.getLocation());
    }

    public boolean llenarUsuario() {

        String validar = Util.validarCampos(getInstance());
        if (!validar.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Verificar " + validar);
            return false;
        }

        ArrayList<Email> correos = new ArrayList<Email>();
        correos.add(new Email(txtCorreo.getText()));

        ArrayList<Telefono> telefono = new ArrayList<Telefono>();
        telefono.add(new Telefono(txtTelefono.getText()));

//        Rol rol = new Rol();
//        rol.setNombre(cmbRol.getSelectedItem().toString());
        //Es nuevo
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setNombre(txtNombre.getText());
        usuario.setIdentificacionP(txtIdentificacion.getText());
        usuario.setEmails(correos);
        usuario.setTelefonos(telefono);
//        usuario.setRol(rol);
        usuario.setHabilitado(chkHabilitado.isSelected());

        return true;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        cmbRol = new javax.swing.JComboBox();
        chkHabilitado = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel3.setText("Identificación:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel4.setText("Rol:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel5.setText("Habilitado:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        txtNombre.setName("Nombre"); // NOI18N
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 130, -1));

        txtIdentificacion.setName("Identificacion"); // NOI18N
        getContentPane().add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 130, -1));

        getContentPane().add(cmbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 130, -1));

        chkHabilitado.setToolTipText("");
        chkHabilitado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHabilitadoActionPerformed(evt);
            }
        });
        getContentPane().add(chkHabilitado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 75, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 70, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 75, -1));

        txtCorreo.setName("Correo"); // NOI18N
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 130, -1));

        jLabel7.setText("Correo:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel8.setText("Teléfono:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        txtTelefono.setName("Telefono"); // NOI18N
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarDataFromID(Usuario usuario) {
        this.usuario = usuario;
        llenarCamposFromUsuario(usuario);
        Util.asignarTitulo(getInstance(), "Usuario " + usuario.getIdUsuario());
    }

    public void llenarCamposFromUsuario(Usuario u) {
        txtNombre.setText(u.getNombre());
        txtIdentificacion.setText(u.getIdentificacionP());
        txtCorreo.setText(u.getEmails().get(0).getEmail());
        txtTelefono.setText(u.getTelefonos().get(0).getTelefono());
        cmbRol.setSelectedItem(u.getRol().getNombre());
        chkHabilitado.setSelected(u.isHabilitado());
    }
    private void chkHabilitadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHabilitadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHabilitadoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Util.deshabilitarBtnModificar(getInstance());
        //        Util.habilitarBtnSalir(getInstance());
        Util.habilitarEdicion(getInstance());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Util.limpiarContenido(getInstance());
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (usuario == null) {
            Util.limpiarContenido(getInstance());
        } else {
            llenarCamposFromUsuario(usuario);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (llenarUsuario()) {
            if (usuario.getIdUsuario() != null) {
                daoUsuario.actualizarUsuario(usuario);
                usuario = null;
            } else {
                usuario.setPassword(Util.generarClaveDeUsuario(usuario));
                daoUsuario.insertarUsuario(usuario);
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkHabilitado;
    private javax.swing.JComboBox cmbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
