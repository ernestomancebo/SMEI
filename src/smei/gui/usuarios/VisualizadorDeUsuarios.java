/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.usuarios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import smei.dao.DAOUsuario;
import smei.modelos.Usuario;
import smei.util.GUIUtil;
import smei.util.Mail;

/**
 *
 * @author Ernesto Mancebo T
 */
public class VisualizadorDeUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form VisualizadorDeUsuarios
     */
    private static VisualizadorDeUsuarios instancia = new VisualizadorDeUsuarios();
    private final Object[] columnHeaders = {"", "ID Usuario", "Nombre", "Correo", "Rol", "Esta Habilitado"};
    private final Class[] columnsTypes = {java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
    private DAOUsuario daoUsuario = new DAOUsuario();
    private ArrayList<Usuario> modeloUsuario;
    private boolean esConsulta;
    private Usuario usuario;

    private VisualizadorDeUsuarios() {
        initComponents();
        initializeValues();
    }

    public static VisualizadorDeUsuarios getInstance() {
        return instancia;
    }

    public void initializeValues() {

        this.setSize(726, 374);

        tblVisualizarUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblVisualizarUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    if (target.getSelectedColumn() != 0) {
                        MaestroUsuarios activeFrame = MaestroUsuarios.getInstance();
                        activeFrame.limpiarData();
                        activeFrame.setUsuario(usuario);
                        activeFrame.cargarDataFromUsuario(daoUsuario.getUsuarioByID(
                                modeloUsuario.get(target.getSelectedRow()).getIdUsuario()));
                        GUIUtil.addFrameToDesktopPanel(getInstance().getDesktopPane(), activeFrame);
                        GUIUtil.deshabilitarEdicion(activeFrame);
                        if (esConsulta) {
                            GUIUtil.habilitarBtnSalir(activeFrame);
                        } else {
                            GUIUtil.habilitarBtnModificar(activeFrame);
                        }
                    }
                }
            }
        });

        //Add table Model
        cargarTabla();
    }

    private void cargarTabla() {
        GUIUtil.setMultiPuporseModelToTable(tblVisualizarUsuario,
                daoUsuario.crearTablaUsuario(modeloUsuario = daoUsuario.getAllUsuarios()), columnHeaders, columnsTypes);
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVisualizarUsuario = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chkDeshabilitar = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(726, 374));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setText("Deshabilitar Usuario");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 298, -1, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(629, 298, -1, -1));

        tblVisualizarUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVisualizarUsuario.setToolTipText("");
        jScrollPane1.setViewportView(tblVisualizarUsuario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, 690, 238));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 11, 189, -1));

        jLabel1.setText("Buscar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        chkDeshabilitar.setSelected(true);
        chkDeshabilitar.setText("Deshabilitar");
        chkDeshabilitar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDeshabilitar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkDeshabilitarPropertyChange(evt);
            }
        });
        getContentPane().add(chkDeshabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ArrayList<Integer> values = new ArrayList<Integer>();

        for (int i : GUIUtil.getIndexOfSelectedRows(tblVisualizarUsuario, 0)) {
            values.add(modeloUsuario.get(i).getIdUsuario());

            ArrayList<String> a = new ArrayList<String>();
            a.add(modeloUsuario.get(i).getEmails().get(0).getEmail());

            Mail.getInstance().sendAMail(Mail.TipoEmail.ACTUALIZAR_USUARIO, usuario,
                    modeloUsuario.get(i).toString(), a);
        }

        if (!values.isEmpty()) {
            if (chkDeshabilitar.isSelected()) {
                daoUsuario.deshabilitarUsuarios(values);
            } else {
                daoUsuario.habilitarUsuarios(values);
            }
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Favor seleccione al menos un usuario para "
                    + ((chkDeshabilitar.isSelected()) ? "deshabilitar" : "habilitar"),
                    "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        cargarTabla();
    }//GEN-LAST:event_formPropertyChange

    private void chkDeshabilitarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkDeshabilitarPropertyChange
        if (chkDeshabilitar.isSelected()) {
            btnEliminar.setText("Deshabilitar Usuario");
        } else {
            btnEliminar.setText("Habilitar Usuario");
        }
        btnEliminar.setSize(127, 23);
    }//GEN-LAST:event_chkDeshabilitarPropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JCheckBox chkDeshabilitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVisualizarUsuario;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
