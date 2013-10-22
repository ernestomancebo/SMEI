/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.reservas;

import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import smei.util.Util;

/**
 *
 * @author Ernesto
 */
public final class MaestroReservas extends javax.swing.JInternalFrame {
    
    private static MaestroReservas instancia = new MaestroReservas();

    /**
     * Creates new form MaestroReservas
     */
    public MaestroReservas() {
        initComponents();
        initComponentsValues();
    }
    
    public static MaestroReservas getInstance() {
        return instancia;
    }
    
    public void initComponentsValues() {
        // SpinnerModel modeloHora = new SpinnerNumberModel(1, 1, 12, 1);
        spnHoraI.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        spnHoraF.setModel(new SpinnerNumberModel(1, 1, 12, 1));

        // SpinnerModel modeloMint = new SpinnerNumberModel(1, 0, 59, 5);
        spnMinutoI.setModel(new SpinnerNumberModel(0, 0, 59, 5));
        spnMinutoF.setModel(new SpinnerNumberModel(0, 0, 59, 5));
        
        String[] tandaList = {"a.m.", "p.m."};
        // SpinnerListModel modelotanda = new SpinnerListModel(tandaList);
        spnTandaI.setModel(new SpinnerListModel(tandaList));
        spnTandaF.setModel(new SpinnerListModel(tandaList));

        //Aceptar y Modificar deben estar en el mismo lugar!
        btnModificar.setLocation(btnAceptar.getLocation());
        
        this.setSize(380, 400);
    }

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
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(38, 33));
        setPreferredSize(new java.awt.Dimension(380, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(cbbLugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 183, 186, -1));
        getContentPane().add(dChooserFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 38, 186, -1));
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
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 221, 186, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Hora Inicio:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, 115, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Hora Fin:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 114, 115, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 335, -1, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 335, 75, -1));

        btnAceptar.setText("Aceptar");
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 335, 75, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 335, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Util.deshabilitarBtnModificar(getInstance());
        Util.habilitarEdicion(getInstance());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Util.limpiarContenido(getInstance());
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Util.limpiarContenido(getInstance());
    }//GEN-LAST:event_btnLimpiarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
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