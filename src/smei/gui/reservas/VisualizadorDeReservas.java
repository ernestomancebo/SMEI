/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui.reservas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import smei.dao.DAOReservas;
import smei.modelos.Reserva;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto Mancebo T
 */
public class VisualizadorDeReservas extends javax.swing.JInternalFrame {

    /**
     * Creates new form VisualizadorDeReservas
     */
    private static VisualizadorDeReservas instancia = new VisualizadorDeReservas();
    private final Object[] columnHeaders = {"", "ID Reservacion", "Usuario", "Fecha", "Hora Inicio - Fin", "Lugar", "Estado"};
    private final Class[] columnsTypes = {java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
    DAOReservas daoReservas = new DAOReservas();
    ArrayList<Reserva> modeloReserva;
    private boolean esConsulta;

    private VisualizadorDeReservas() {
        initComponents();
        initializeValues();
    }

    public static VisualizadorDeReservas getInstance() {
        return instancia;
    }

    public void initializeValues() {

        tblVisualizarReserva.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblVisualizarReserva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    if (target.getSelectedColumn() != 0) {
                        MaestroReservas activeFrame = MaestroReservas.getInstance();
                        
                        GUIUtil.addFrameToDesktopPanel(getInstance().getDesktopPane(), activeFrame);
                        GUIUtil.deshabilitarEdicion(activeFrame);
                        if (esConsulta) {
                            GUIUtil.habilitarBtnSalir(activeFrame);
                        } else {
                            GUIUtil.habilitarBtnModificar(activeFrame);
                        }
                        
                        activeFrame.cargarData(modeloReserva.get(target.getSelectedRow()));
                    }
                }
            }
        });

        //Add table Model

        cargarTablaReservas();
    }

    private void cargarTablaReservas() {
        GUIUtil.setMultiPuporseModelToTable(tblVisualizarReserva,
                daoReservas.crearTablaReserva(modeloReserva = daoReservas.getReservas()), columnHeaders, columnsTypes);
    }

    public boolean setConsulta(boolean esConsulta) {
        return this.esConsulta = esConsulta;
    }

    public boolean esConsulta() {
        return esConsulta;
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
        txtBuscarReserva = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVisualizarReserva = new javax.swing.JTable();
        btnCancelarReserva = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnExportarReserva = new javax.swing.JButton();

        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jLabel1.setText("Buscar:");

        tblVisualizarReserva.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVisualizarReserva.setToolTipText("");
        jScrollPane1.setViewportView(tblVisualizarReserva);

        btnCancelarReserva.setText("Cancelar Reservación");
        btnCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarReservaActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnExportarReserva.setText("Exportar Reservación");
        btnExportarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelarReserva)
                            .addComponent(btnExportarReserva))
                        .addGap(18, 18, 18)
                        .addComponent(btnAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnExportarReserva)
                    .addComponent(btnCancelarReserva))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarReservaActionPerformed
        ArrayList<Integer> values = getIdReservasSeleccionadas();
        if (!values.isEmpty()) {
            daoReservas.deshabilitarReservas(values);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Favor seleccione al menos una reservacion para cancelar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelarReservaActionPerformed

    private void btnExportarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarReservaActionPerformed
        ArrayList<Integer> values = getIdReservasSeleccionadas();
        if (!values.isEmpty()) {
            System.out.println("Exportando " + values);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Favor seleccione al menos una reservacion para exportar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarReservaActionPerformed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        cargarTablaReservas();
    }//GEN-LAST:event_formPropertyChange

    private ArrayList<Integer> getIdReservasSeleccionadas() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (Integer i : GUIUtil.getIndexOfSelectedRows(tblVisualizarReserva, 0)) {
            values.add(modeloReserva.get(i).getId());
        }
        return values;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelarReserva;
    private javax.swing.JButton btnExportarReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVisualizarReserva;
    private javax.swing.JTextField txtBuscarReserva;
    // End of variables declaration//GEN-END:variables
}
