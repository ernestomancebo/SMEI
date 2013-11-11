/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static boolean aceptaSoloNumeros(KeyEvent evt, char c) {
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            return true;
        }
        return false;
    }

    public static void limitaLongitud(KeyEvent evt, String str, int l) {
        if (str.length() == l) {
            evt.consume();
        }
    }

    public static void aceptaSoloTelefono(KeyEvent evt, char c) {
        if (c != '-' && aceptaSoloNumeros(evt, c)) {
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

    public static String[] getHoraDesdeFecha(Date d) {
        return new SimpleDateFormat("hh:mm a").format(d).split("(\\s|\\:)");
    }

    public static String[] getHoraDesdeString(String h) {
        return h.split("(\\s|\\:)");
    }

    public static String buildHoraFromStrings(String s1, String s2, String s3) {
        s1 = String.format("%2s", s1).replace(' ', '0');
        s2 = String.format("%2s", s2).replace(' ', '0');

        return (s1 + ":" + s2 + " " + ((s3 != null) ? s3 : "")).trim();
    }

    public static Date crearDateConHora(Date d, String t) {
        Calendar cal = Calendar.getInstance();

        //Asignando Fecha
        cal.setTime(d);

        //Asignando Hora
        String[] hora = Util.getHoraDesdeString(t);
        cal.set(Calendar.HOUR, Integer.valueOf(hora[0]));
        cal.set(Calendar.MINUTE, Integer.valueOf(hora[1]));
        cal.set(Calendar.AM_PM, (hora[2].equals("AM") ? Calendar.AM : Calendar.PM));

        return cal.getTime();
    }

    public static String getFechaFromDate(Date d) {
        return new SimpleDateFormat("MM-dd-yyyy").format(d);
    }

    public static String getHoraFromDate(Date d) {
        return new SimpleDateFormat("hh:mm a").format(d);
    }
}
