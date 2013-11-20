/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import smei.dao.DAOUsuario;
import smei.modelos.Email;
import smei.modelos.Usuario;
import smei.util.Util;

/**
 *
 * @author Ernesto
 */
public class IniciarSesion extends javax.swing.JFrame {

    /**
     * Creates new form IniciarSesion
     */
    private static IniciarSesion instancia = new IniciarSesion();
    private DAOUsuario daoUsuario = new DAOUsuario();
    private Usuario usuario;
    private Principal principal;
    
    private IniciarSesion() {
        initComponents();
        initializeComponents();
    }
    
    public static IniciarSesion getInstance() {
        return instancia;
    }
    
    private void initializeComponents() {
        btnPassword.setBorderPainted(false);
        btnPassword.setOpaque(false);
        btnPassword.setBackground(Color.lightGray);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
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
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        btnPassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        txtUsuario.setName("Usuario"); // NOI18N

        txtPassword.setName("Contraseña"); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setText("SMEI");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnPassword.setText("Olvide mi contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEntrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPassword)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPassword)
                    .addComponent(btnEntrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarSesion() {
        String validar = (txtUsuario.getText().isEmpty() ? txtUsuario.getName() : "");
        
        if (validar.isEmpty()) {
            validar = (new String(txtPassword.getPassword()).isEmpty() ? txtPassword.getName() : "");
        }
        
        if (!validar.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Validar " + validar);
            return;
        }
        
        final String valor = txtUsuario.getText();
        usuario = new Usuario();
        usuario.setPassword(new String(txtPassword.getPassword()));
        if (Util.validarEmail(valor)) {
            ArrayList<Email> email = new ArrayList<Email>();
            email.add(new Email(valor));
            
            usuario.setEmails(email);
            usuario.setIdUsuario(null);
            usuario.setIdentificacionP(null);
        } else if (Util.validarMatricula(valor)) {
            usuario.setEmails(null);
            usuario.setIdUsuario(null);
            usuario.setIdentificacionP(valor);
        } else if (Util.validarSoloDigito(valor)) {
            usuario.setEmails(null);
            usuario.setIdUsuario(Integer.valueOf(valor));
            usuario.setIdentificacionP(null);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Fallo en combinación usuario/contraseña");
            return;
        }
        
        usuario = daoUsuario.getUsuarioPorLogin(usuario);
        usuario.setPassword(new String(txtPassword.getPassword()));
        
        if (usuario.getIdUsuario() == null) {
            JOptionPane.showMessageDialog(rootPane, "Fallo en combinación usuario/contraseña");
            return;
        }
        
        this.setVisible(false);
        principal = Principal.getInstance();
        principal.setVisible(true);
        principal.setUsuario(usuario);
    }
    
    public void setUsuario(Usuario u) {
        this.usuario = u;
    }
    
    public void limpiarData() {
        setUsuario(null);
        txtUsuario.setText("");
        txtPassword.setText("");
        txtUsuario.requestFocus();
    }
    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_txtPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                instancia = new IniciarSesion();
                instancia.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
