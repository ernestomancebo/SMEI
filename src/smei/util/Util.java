/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.JTextComponent;
import smei.modelos.Usuario;

/**
 *
 * @author Ernesto
 */
public class Util {

    public enum patrones {
    }
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern CEDULA_PATTERN = Pattern.compile("^[0-9]{3}-[0-9]{7}-[0-9]{1}$");
    private static final Pattern MATRICULA_PATTERN = Pattern.compile("^[0-9]{4}-[0-9]{4}$");
    private static final Pattern TELEFONO_PATTERN = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");

    public static void limpiarContenido(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JSpinner) {
                SpinnerModel modelo = ((JSpinner) c).getModel();
                if (modelo instanceof SpinnerNumberModel) {
                    ((JSpinner) c).setValue(((SpinnerNumberModel) modelo).getMinimum());
                } else if (modelo instanceof SpinnerListModel) {
                    ((JSpinner) c).setValue(((SpinnerListModel) modelo).getList().get(0));
                }
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).removeAllItems();
            } else if (c instanceof JTextComponent) {
                ((JTextComponent) c).setText("");
            } else if (c instanceof Container) {
                limpiarContenido((Container) c);
            }
        }
    }

    public static void modificarEdicion(Container container, boolean editar) {
        for (Component c : container.getComponents()) {
            if (c instanceof JSpinner) {
                c.setEnabled(editar);
            } else if (c instanceof JComboBox) {
                c.setEnabled(editar);
            } else if (c instanceof JCheckBox) {
                c.setEnabled(editar);
            } else if (c instanceof JTextComponent) {
                c.setEnabled(editar);
            } else if (c instanceof Container) {
                modificarEdicion((Container) c, editar);
            }
        }
    }

    public static void habilitarEdicion(Container container) {
        modificarEdicion(container, true);
    }

    public static void deshabilitarEdicion(Container container) {
        modificarEdicion(container, false);
    }

    public static void modificarBtnMod(Container container, boolean esVisible) {
        for (Component c : container.getComponents()) {
            if (c instanceof JButton) {
                if (((JButton) c).getText().equals("Modificar")) {
                    ((JButton) c).setVisible(esVisible);
                    ((JButton) c).setEnabled(esVisible);
                } else {
                    ((JButton) c).setVisible(!esVisible);
                    ((JButton) c).setEnabled(!esVisible);
                }
            } else if (c instanceof Container) {
                modificarBtnMod((Container) c, esVisible);
            }
        }
    }

    public static void modificarBtnSalir(Container container, boolean esVisible) {
        for (Component c : container.getComponents()) {
            if (c instanceof JButton) {
                if (((JButton) c).getText().equals("Salir")) {
                    ((JButton) c).setVisible(esVisible);
                    ((JButton) c).setEnabled(esVisible);
                }
            } else if (c instanceof Container) {
                modificarBtnSalir((Container) c, esVisible);
            }
        }
    }

    public static void habilitarBtnModificar(Container container) {
        modificarBtnMod(container, true);
        habilitarBtnSalir(container);
    }

    public static void deshabilitarBtnModificar(Container container) {
        modificarBtnMod(container, false);
        habilitarBtnSalir(container);
    }

    public static void habilitarBtnSalir(Container container) {
        modificarBtnSalir(container, true);
    }

    public static void asignarTitulo(Container container, String titulo) {
        if (container instanceof JInternalFrame) {
            ((JInternalFrame) container).setTitle(titulo);
        }
    }

    public static void setMultiPuporseModelToTable(JTable tabla, final Object[][] values, final Object[] columnHeaders, final Class[] columnsTypes) {
        tabla.setModel(new javax.swing.table.DefaultTableModel(values, columnHeaders) {
            Class[] types = columnsTypes;

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column != 0) {
                    return false;
                }
                return true;
            }
        });
    }

    public static void addFrameToDesktopPanel(JDesktopPane desktopPane, JInternalFrame frameToAdd) {
        try {
            for (JInternalFrame frame : desktopPane.getAllFrames()) {
                if (frame.equals(frameToAdd)) {
                    frameToAdd.setVisible(true);
                    frameToAdd.toFront();
                    frameToAdd.repaint();
                    frameToAdd.setSelected(true);
                    return;
                }
            }

            Dimension desktopSize = desktopPane.getSize();
            Dimension jInternalFrameSize = frameToAdd.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            frameToAdd.setLocation(width, height);

            desktopPane.add(frameToAdd);
            frameToAdd.setVisible(true);
            frameToAdd.toFront();
            frameToAdd.repaint();
            frameToAdd.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void aceptaSoloNumeros(KeyEvent evt, char c) {
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }

    public static ArrayList<String> getIDsFromSelectedRows(JTable tabla, int booleanIndex, int idIndex) {
        ArrayList<String> rv = new ArrayList<String>();
        for (byte i = 0; i < tabla.getRowCount(); i++) {
            if ((Boolean) tabla.getValueAt(i, booleanIndex)) {
                rv.add(String.valueOf(tabla.getValueAt(i, idIndex)));
            }
        }
        return rv;
    }

    public static ImageIcon loadIcon(String strPath) {
        return new ImageIcon();
    }

    public static boolean validateStringWithPattern(String strToValidate, Pattern pattern) {
        if (strToValidate.split(pattern.toString()).length == 0) {
            return true;
        }
        return false;
    }

    public static String validarCampos(Container container) {
        String rv = new String();
        String value;
        String txtName;

        for (Component c : container.getComponents()) {
            if (c instanceof JTextComponent) {
                value = ((JTextComponent) c).getText();
                txtName = ((JTextComponent) c).getName();

                if (!value.trim().isEmpty()) {
                    if (txtName.equals("Correo")) {
                        if (!validarEmail(value)) {
                            rv = txtName;
                        }
                    } else if (txtName.equals("Telefono")) {
                        if (!validarTelefono(value)) {
                            rv = txtName;
                        }
                    } else if (txtName.equals("Identificacion")) {
                        if (!validarMatricula(value)) {
                            rv = txtName;
                        }
                    }

                    if (!rv.isEmpty()) {
                        ((JTextComponent) c).selectAll();
                        ((JTextComponent) c).requestFocus();
                        return rv;
                    }
                } else {
                    ((JTextComponent) c).selectAll();
                    ((JTextComponent) c).requestFocus();
                    return txtName;
                }
            } else if (c instanceof Container) {
                if (!(rv = validarCampos((Container) c)).isEmpty()) {
                    return rv;
                }
            }
        }
        return rv;
    }

    public static String generarClaveDeUsuario(Usuario usuario) {
        final String nombre = usuario.getNombre();
        final String identificacion = usuario.getIdentificacionP();
        final String rv = (nombre.substring(0, 1).toUpperCase()
                + identificacion.substring(identificacion.length() - 4, identificacion.length())
                + nombre.substring(nombre.length() - 1, nombre.length()).toLowerCase());
        System.out.println(rv);
        return rv;
    }

    public static Object[] crearFilaUsuario(Usuario usuario) {
        return new Object[]{
            false, usuario.getIdUsuario(), usuario.getNombre(),
            usuario.getEmails().get(0).getEmail(), usuario.getRol().getNombre(), (usuario.isEstaHabilitado()) ? "Sí" : "No"};
    }

    public static boolean validarEmail(String email) {
        return validateStringWithPattern(email, EMAIL_PATTERN);
    }

    public static boolean validarCedula(String cedula) {
        return validateStringWithPattern(cedula, CEDULA_PATTERN);
    }

    public static boolean validarMatricula(String matricula) {
        return validateStringWithPattern(matricula, MATRICULA_PATTERN);
    }

    public static boolean validarTelefono(String telefono) {
        return validateStringWithPattern(telefono, TELEFONO_PATTERN);
    }
    /*
     public static BufferedImage rotate(BufferedImage image, double angle) {
     double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
     int w = image.getWidth(), h = image.getHeight();
     int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
     GraphicsConfiguration gc = getDefaultConfiguration();
     BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
     Graphics2D g = result.createGraphics();
     g.translate((neww - w) / 2, (newh - h) / 2);
     g.rotate(angle, w / 2, h / 2);
     g.drawRenderedImage(image, null);
     g.dispose();
     return result;
     }
     */
}
