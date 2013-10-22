/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Ernesto
 */
public class Util {

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

    public static void modificarBtnMod(Container container, boolean esModificar) {
        for (Component c : container.getComponents()) {
            if (c instanceof JButton) {
                if (((JButton) c).getText().equals("Modificar")) {
                    ((JButton) c).setVisible(esModificar);
                    ((JButton) c).setEnabled(esModificar);
                } else {
                    ((JButton) c).setVisible(!esModificar);
                    ((JButton) c).setEnabled(!esModificar);
                }
            } else if (c instanceof Container) {
                modificarBtnMod((Container) c, esModificar);
            }
        }
    }

    public static void habilitarBtnModificar(Container container) {
        modificarBtnMod(container, true);
    }

    public static void deshabilitarBtnModificar(Container container) {
        modificarBtnMod(container, false);
    }

    public static void asignarTitulo(Container container, String titulo) {
        if (container instanceof JInternalFrame) {
            ((JInternalFrame) container).setTitle(titulo);
        }
    }

    public static MouseAdapter getDoubleClickedRow() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                }
            }
        };
    }

    public static DefaultTableModel addCheckBoxesToTable(Object[][] values, final String[] columns) {
        return new javax.swing.table.DefaultTableModel(values, columns);
    }

    public static void addFrameToDesktopPanel(JDesktopPane desktopPane, JInternalFrame frameToAdd) {

        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame.equals(frameToAdd)) {
                frameToAdd.setVisible(true);
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
    }
}
