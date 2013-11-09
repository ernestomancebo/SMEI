/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import smei.modelos.Usuario;

/**
 *
 * @author Ernesto
 */
public class Util {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern CEDULA_PATTERN = Pattern.compile("^[0-9]{3}-[0-9]{7}-[0-9]{1}$");
    private static final Pattern MATRICULA_PATTERN = Pattern.compile("^[0-9]{4}-[0-9]{4}$");
    private static final Pattern TELEFONO_PATTERN = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");

    public static void aceptaSoloNumeros(KeyEvent evt, char c) {
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }

    }

    public static boolean validateStringWithPattern(String strToValidate, Pattern pattern) {
        return strToValidate.split(pattern.toString()).length == 0;
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

    public static String[] getHoraDeFecha(Date d) {
        String[] rv ;//= new String[3];

        rv = new SimpleDateFormat("hh:mm a").format(d).split("");

        rv[0] = String.valueOf(d.getHours());
        rv[0] = String.valueOf(d.getMinutes());
        rv[0] = String.valueOf(d.getHours());
        return rv;
    }
}
