/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import smei.dao.DAOUsuario;
import smei.dao.DBConnection;
import smei.gui.espacios.MaestroEspacios;
import smei.gui.espacios.VisualizadorDeEspacios;
import smei.gui.historico.MaestroTendencia;
import smei.gui.notificaciones.MaestroNotificaciones;
import smei.gui.reservas.MaestroReservas;
import smei.gui.reservas.VisualizadorDeReservas;
import smei.gui.usuarios.MaestroUsuarios;
import smei.gui.usuarios.ModificarContrasena;
import smei.gui.usuarios.VisualizadorDeUsuarios;
import smei.modelos.Rol;
import smei.modelos.Usuario;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto
 */
public class Principal extends javax.swing.JFrame {

    private JInternalFrame activeFrame = null;
    private static Principal instance = new Principal();
    private IniciarSesion iniciarSesion;
    private Usuario usuario;

    public static Principal getInstance() {
        return instance;
    }

    /**
     * Creates new form Principal
     */
    private Principal() {
        initComponents();
        initializeValues();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskPaneReporte = new com.l2fprod.common.swing.JTaskPane();
        taskPaneReserva = new com.l2fprod.common.swing.JTaskPaneGroup();
        jLinkBtnRegReserva = new com.l2fprod.common.swing.JLinkButton();
        jLinkBtnBuscarReserva = new com.l2fprod.common.swing.JLinkButton();
        taskPaneEspacio = new com.l2fprod.common.swing.JTaskPaneGroup();
        jLinkBtnBuscarEspacio = new com.l2fprod.common.swing.JLinkButton();
        jLinkBtnRegEspacio = new com.l2fprod.common.swing.JLinkButton();
        jTaskPaneGroup3 = new com.l2fprod.common.swing.JTaskPaneGroup();
        jLinkBtnTendReser = new com.l2fprod.common.swing.JLinkButton();
        taskPaneUsuario = new com.l2fprod.common.swing.JTaskPaneGroup();
        jLinkBtnBuscarUsu = new com.l2fprod.common.swing.JLinkButton();
        jLinkBtnRegUsu = new com.l2fprod.common.swing.JLinkButton();
        jLinkBtnModContr = new com.l2fprod.common.swing.JLinkButton();
        taskPaneNotificaciones = new com.l2fprod.common.swing.JTaskPaneGroup();
        jLinkBtnModNotif = new com.l2fprod.common.swing.JLinkButton();
        jLinkUsuario = new com.l2fprod.common.swing.JLinkButton();
        PrincipalDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnArchivo = new javax.swing.JMenu();
        mnCerrarSesion = new javax.swing.JMenuItem();
        mnSalir = new javax.swing.JMenuItem();
        mnAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taskPaneReporte.setBackground(new java.awt.Color(240, 240, 240));
        taskPaneReporte.setMinimumSize(new java.awt.Dimension(201, 380));
        taskPaneReporte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        taskPaneReserva.setTitle("Reservas");
        taskPaneReserva.setMaximumSize(new java.awt.Dimension(172, 86));
        taskPaneReserva.setMinimumSize(new java.awt.Dimension(172, 86));

        jLinkBtnRegReserva.setText("Registro Reserva");
        jLinkBtnRegReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnRegReservaActionPerformed(evt);
            }
        });

        jLinkBtnBuscarReserva.setText("Buscar Reservas");
        jLinkBtnBuscarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnBuscarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskPaneReservaLayout = new javax.swing.GroupLayout(taskPaneReserva.getContentPane());
        taskPaneReserva.getContentPane().setLayout(taskPaneReservaLayout);
        taskPaneReservaLayout.setHorizontalGroup(
            taskPaneReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLinkBtnRegReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLinkBtnBuscarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        taskPaneReservaLayout.setVerticalGroup(
            taskPaneReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskPaneReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLinkBtnRegReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLinkBtnBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        taskPaneReporte.add(taskPaneReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        taskPaneEspacio.setTitle("Espacios");

        jLinkBtnBuscarEspacio.setText("Buscar Espacios");
        jLinkBtnBuscarEspacio.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnBuscarEspacio.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnBuscarEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnBuscarEspacioActionPerformed(evt);
            }
        });

        jLinkBtnRegEspacio.setText("Registrar Espacio");
        jLinkBtnRegEspacio.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnRegEspacio.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnRegEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnRegEspacioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskPaneEspacioLayout = new javax.swing.GroupLayout(taskPaneEspacio.getContentPane());
        taskPaneEspacio.getContentPane().setLayout(taskPaneEspacioLayout);
        taskPaneEspacioLayout.setHorizontalGroup(
            taskPaneEspacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLinkBtnBuscarEspacio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLinkBtnRegEspacio, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        taskPaneEspacioLayout.setVerticalGroup(
            taskPaneEspacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskPaneEspacioLayout.createSequentialGroup()
                .addComponent(jLinkBtnRegEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLinkBtnBuscarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        taskPaneReporte.add(taskPaneEspacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 176, 239, -1));

        jTaskPaneGroup3.setTitle("Reportes");

        jLinkBtnTendReser.setText("Generar Reportes");
        jLinkBtnTendReser.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnTendReser.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnTendReser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnTendReserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jTaskPaneGroup3Layout = new javax.swing.GroupLayout(jTaskPaneGroup3.getContentPane());
        jTaskPaneGroup3.getContentPane().setLayout(jTaskPaneGroup3Layout);
        jTaskPaneGroup3Layout.setHorizontalGroup(
            jTaskPaneGroup3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLinkBtnTendReser, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        jTaskPaneGroup3Layout.setVerticalGroup(
            jTaskPaneGroup3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLinkBtnTendReser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        taskPaneReporte.add(jTaskPaneGroup3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 306, 239, -1));

        taskPaneUsuario.setTitle("Usuario");

        jLinkBtnBuscarUsu.setText("Buscar Usuarios");
        jLinkBtnBuscarUsu.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnBuscarUsu.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnBuscarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnBuscarUsuActionPerformed(evt);
            }
        });

        jLinkBtnRegUsu.setText("Registrar Usuario");
        jLinkBtnRegUsu.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnRegUsu.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnRegUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnRegUsuActionPerformed(evt);
            }
        });

        jLinkBtnModContr.setText("Modificar Contraseña");
        jLinkBtnModContr.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnModContr.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnModContr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnModContrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskPaneUsuarioLayout = new javax.swing.GroupLayout(taskPaneUsuario.getContentPane());
        taskPaneUsuario.getContentPane().setLayout(taskPaneUsuarioLayout);
        taskPaneUsuarioLayout.setHorizontalGroup(
            taskPaneUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLinkBtnBuscarUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLinkBtnRegUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLinkBtnModContr, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        taskPaneUsuarioLayout.setVerticalGroup(
            taskPaneUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskPaneUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLinkBtnRegUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLinkBtnBuscarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLinkBtnModContr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        taskPaneReporte.add(taskPaneUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 392, 239, -1));

        taskPaneNotificaciones.setTitle("Notificaciones");

        jLinkBtnModNotif.setText("Modificar Notificaciones");
        jLinkBtnModNotif.setMaximumSize(new java.awt.Dimension(112, 22));
        jLinkBtnModNotif.setMinimumSize(new java.awt.Dimension(112, 22));
        jLinkBtnModNotif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLinkBtnModNotifActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskPaneNotificacionesLayout = new javax.swing.GroupLayout(taskPaneNotificaciones.getContentPane());
        taskPaneNotificaciones.getContentPane().setLayout(taskPaneNotificacionesLayout);
        taskPaneNotificacionesLayout.setHorizontalGroup(
            taskPaneNotificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLinkBtnModNotif, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        taskPaneNotificacionesLayout.setVerticalGroup(
            taskPaneNotificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskPaneNotificacionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLinkBtnModNotif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        taskPaneReporte.add(taskPaneNotificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 545, 239, -1));

        jLinkUsuario.setText("USUARIO");
        taskPaneReporte.add(jLinkUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 239, -1));

        PrincipalDesktopPane.setBackground(getBackground());

        mnArchivo.setText("Archivo");

        mnCerrarSesion.setText("Cerrar sesion");
        mnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCerrarSesionActionPerformed(evt);
            }
        });
        mnArchivo.add(mnCerrarSesion);

        mnSalir.setText("Salir");
        mnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSalirActionPerformed(evt);
            }
        });
        mnArchivo.add(mnSalir);

        jMenuBar1.add(mnArchivo);

        mnAyuda.setText("Ayuda");
        jMenuBar1.add(mnAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(taskPaneReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrincipalDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PrincipalDesktopPane)
                    .addComponent(taskPaneReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JDesktopPane getPrincipalDesktopPane() {
        return PrincipalDesktopPane;
    }

    private void initializeValues() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension.width * 9 / 10, dimension.height * 9 / 10);
        setLocation(dimension.width * 1 / 20, dimension.height * 1 / 20);
//        getInstance().setIconImage(new ImageIcon(getClass().getResource("/resources/icons/schedule-48.png")));
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;
        if (u != null) {
            jLinkUsuario.setText("Bienvenido, " + u.getNombre());
//            manejarContenidoPorRoles(u.getRol());
        }
    }

    private void manejarContenidoPorRoles(Rol r) {

        switch (r.getIdRol()) {
            case 1:
                jLinkBtnRegReserva.setVisible(true);
                jLinkBtnRegEspacio.setVisible(true);
                jLinkBtnBuscarUsu.setVisible(true);
                jLinkBtnRegUsu.setVisible(true);

                taskPaneNotificaciones.setVisible(true);
                taskPaneUsuario.setVisible(true);
                taskPaneReporte.setVisible(true);
                break;

            case 2:

                //Links para registrar reserva y espacio
                jLinkBtnRegReserva.setVisible(true);
                jLinkBtnRegEspacio.setVisible(true);
                //Links para manejo de usuario
                jLinkBtnBuscarUsu.setVisible(false);
                jLinkBtnRegUsu.setVisible(false);

                //Modulos a los que no esta permitido
                taskPaneNotificaciones.hide();
//                taskPaneReporte.hide();
                break;

            case 3:
                //Links para registrar reserva y espacio
                jLinkBtnRegReserva.setVisible(false);
                jLinkBtnRegEspacio.setVisible(false);
                //Links para manejo de usuario
                jLinkBtnBuscarUsu.setVisible(false);
                jLinkBtnRegUsu.setVisible(false);

                //Modulos a los que no esta permitido
                taskPaneNotificaciones.hide();
                taskPaneReporte.hide();
                break;

            default:
                JOptionPane.showMessageDialog(rootPane, "Rol " + r.getNombre() + " desconocido");
                break;
        }
    }

    private void jLinkBtnModNotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnModNotifActionPerformed
        activeFrame = MaestroNotificaciones.getInstance();
        GUIUtil.limpiarContenido(activeFrame);
        agregarMaestroInternalFrame("Actualizar Notificaciones");
    }//GEN-LAST:event_jLinkBtnModNotifActionPerformed

    private void jLinkBtnRegReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnRegReservaActionPerformed
        activeFrame = MaestroReservas.getInstance();
        ((MaestroReservas) activeFrame).limpiarData();
        // Asignando usuario
        ((MaestroReservas) activeFrame).setUsuario(usuario);
        GUIUtil.limpiarContenido(activeFrame);
        agregarMaestroInternalFrame("Registrar Reservacion");
    }//GEN-LAST:event_jLinkBtnRegReservaActionPerformed

    private void jLinkBtnRegEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnRegEspacioActionPerformed
        activeFrame = MaestroEspacios.getInstance();
        ((MaestroEspacios) activeFrame).limpiarData();
        // Asignando usuario
        ((MaestroEspacios) activeFrame).setUsuario(usuario);
        GUIUtil.limpiarContenido(activeFrame);
        agregarMaestroInternalFrame("Registrar Espacio");
    }//GEN-LAST:event_jLinkBtnRegEspacioActionPerformed

    private void jLinkBtnBuscarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnBuscarReservaActionPerformed
        activeFrame = VisualizadorDeReservas.getInstance();
        // Asignando usuario
        ((VisualizadorDeReservas) activeFrame).setUsuario(usuario);
        agregarVisualizadorInternalFrame("Buscar Reserva");

    }//GEN-LAST:event_jLinkBtnBuscarReservaActionPerformed

    private void jLinkBtnBuscarEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnBuscarEspacioActionPerformed
        activeFrame = VisualizadorDeEspacios.getInstance();
        // Asignando usuario
        ((VisualizadorDeEspacios) activeFrame).setUsuario(usuario);
        agregarVisualizadorInternalFrame("Buscar Espacio");
    }//GEN-LAST:event_jLinkBtnBuscarEspacioActionPerformed

    private void jLinkBtnRegUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnRegUsuActionPerformed
        activeFrame = MaestroUsuarios.getInstance();
        ((MaestroUsuarios) activeFrame).limpiarData();
        // Asignando usuario
        ((MaestroUsuarios) activeFrame).setUsuario(usuario);
        GUIUtil.limpiarContenido(activeFrame);
        agregarMaestroInternalFrame("Registrar Usuario");
    }//GEN-LAST:event_jLinkBtnRegUsuActionPerformed

    private void jLinkBtnBuscarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnBuscarUsuActionPerformed
        activeFrame = VisualizadorDeUsuarios.getInstance();
        // Asignando usuario
        ((VisualizadorDeUsuarios) activeFrame).setUsuario(usuario);
        agregarMaestroInternalFrame("Buscar Usuario");
    }//GEN-LAST:event_jLinkBtnBuscarUsuActionPerformed

    private void jLinkBtnModContrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnModContrActionPerformed
        setUsuario(new DAOUsuario().getUsuarioByID(1));

        activeFrame = ModificarContrasena.getInstance();
        // Asignando usuario
        ((ModificarContrasena) activeFrame).setUsuario(usuario);
        agregarMaestroInternalFrame("Modificar Contraseña");
    }//GEN-LAST:event_jLinkBtnModContrActionPerformed

    private void jLinkBtnTendReserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLinkBtnTendReserActionPerformed
        activeFrame = MaestroTendencia.getInstance();
        agregarMaestroInternalFrame("Reportes");
    }//GEN-LAST:event_jLinkBtnTendReserActionPerformed

    private void mnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCerrarSesionActionPerformed
        this.setVisible(false);
        this.setUsuario(null);
        iniciarSesion = IniciarSesion.getInstance();
        iniciarSesion.setVisible(true);
        iniciarSesion.limpiarData();
    }//GEN-LAST:event_mnCerrarSesionActionPerformed

    private void mnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSalirActionPerformed
        try {
            DBConnection.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.exit(0);
        }
    }//GEN-LAST:event_mnSalirActionPerformed

    private void agregarMaestroInternalFrame(String titulo) {
        GUIUtil.addFrameToDesktopPanel(PrincipalDesktopPane, activeFrame);
        GUIUtil.asignarTitulo(activeFrame, titulo);
        GUIUtil.habilitarEdicion(activeFrame);
        GUIUtil.deshabilitarBtnModificar(activeFrame);
    }

    private void agregarVisualizadorInternalFrame(String titulo) {
        GUIUtil.addFrameToDesktopPanel(PrincipalDesktopPane, activeFrame);
        GUIUtil.asignarTitulo(activeFrame, titulo);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                instance = new Principal();
                getInstance().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane PrincipalDesktopPane;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnBuscarEspacio;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnBuscarReserva;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnBuscarUsu;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnModContr;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnModNotif;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnRegEspacio;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnRegReserva;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnRegUsu;
    private com.l2fprod.common.swing.JLinkButton jLinkBtnTendReser;
    private com.l2fprod.common.swing.JLinkButton jLinkUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private com.l2fprod.common.swing.JTaskPaneGroup jTaskPaneGroup3;
    private javax.swing.JMenu mnArchivo;
    private javax.swing.JMenu mnAyuda;
    private javax.swing.JMenuItem mnCerrarSesion;
    private javax.swing.JMenuItem mnSalir;
    private com.l2fprod.common.swing.JTaskPaneGroup taskPaneEspacio;
    private com.l2fprod.common.swing.JTaskPaneGroup taskPaneNotificaciones;
    private com.l2fprod.common.swing.JTaskPane taskPaneReporte;
    private com.l2fprod.common.swing.JTaskPaneGroup taskPaneReserva;
    private com.l2fprod.common.swing.JTaskPaneGroup taskPaneUsuario;
    // End of variables declaration//GEN-END:variables
}
