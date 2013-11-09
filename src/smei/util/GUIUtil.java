/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

import com.toedter.calendar.JCalendar;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import smei.modelos.Espacio;
import smei.modelos.Reserva;
import smei.modelos.Usuario;

/**
 *
 * @author Ernesto
 */
public class GUIUtil {

    public static void limpiarContenido(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JSpinner) {
                SpinnerModel modelo = ((JSpinner) c).getModel();
                if (modelo instanceof SpinnerNumberModel) {
                    ((JSpinner) c).setValue(((SpinnerNumberModel) modelo).getMinimum());
                } else if (modelo instanceof SpinnerListModel) {
                    ((JSpinner) c).setValue(((SpinnerListModel) modelo).getList().get(0));
                }
            } //            else if (c instanceof JComboBox) {
            //                ((JComboBox) c).removeAllItems();
            //            }
            else if (c instanceof JTextComponent) {
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
                return column == 0;
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
            Logger.getLogger(GUIUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Integer> getIndexOfSelectedRows(JTable tabla, final int booleanIndex) {
        ArrayList<Integer> rv = new ArrayList<Integer>();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if ((Boolean) tabla.getValueAt(i, booleanIndex)) {
                rv.add(i);
            }
        }
        return rv;
    }

    public static ImageIcon loadIcon(String strPath) {
        return new ImageIcon();
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
                        if (!Util.validarEmail(value)) {
                            rv = txtName;
                        }
                    } else if (txtName.equals("Telefono")) {
                        if (!Util.validarTelefono(value)) {
                            rv = txtName;
                        }
                    } else if (txtName.equals("Identificacion")) {
                        if (!Util.validarMatricula(value)) {
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

    public static JCalendar setCalendarChooserAfterToday(JCalendar calendar) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 2);
        calendar.setSelectableDateRange(new Date(), new Date(c.getTime().getYear(), c.getTime().getMonth(), c.getTime().getDate()));

        PropertyChangeListener calendarChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = ((JCalendar) evt.getSource()).getDate();
            }
        };

        calendar.addPropertyChangeListener("calendar", calendarChangeListener);

        return calendar;
    }

    public static Object[] crearFilaUsuario(Usuario usuario) {
        return new Object[]{
            false, usuario.getIdUsuario(), usuario.getNombre(),
            usuario.getEmails().get(0).getEmail(), usuario.getRol().getNombre(), (usuario.isHabilitado()) ? "Sí" : "No"};
    }

    public static Object[] crearFilaEspacio(Espacio espacio) {
        return new Object[]{
            false, espacio.getNombre(), espacio.getCapacidadDePersonas(),
            espacio.getDescripcion(), (espacio.isHabilitado()) ? "Sí" : "No"};
    }

    public static Object[] crearFilaReserva(Reserva reserva) {
        return new Object[]{};
    }
}
