/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.modelos;

/**
 *
 * @author Ernesto
 */
public class Notificacion {

    private int idNotificacion;
    private String nombre;
    private String contenidoPorDefecto;
    private String contenidoPersonalizado;
    private String tituloPorDefecto;
    private String tituloPersonalizado;
    private boolean habilitada;

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenidoPorDefecto() {
        return contenidoPorDefecto;
    }

    public void setContenidoPorDefecto(String contenidoPorDefecto) {
        this.contenidoPorDefecto = contenidoPorDefecto;
    }

    public String getContenidoPersonalizado() {
        return contenidoPersonalizado;
    }

    public void setContenidoPersonalizado(String contenidoPersonalizado) {
        this.contenidoPersonalizado = contenidoPersonalizado;
    }

    public String getTituloPorDefecto() {
        return tituloPorDefecto;
    }

    public void setTituloPorDefecto(String tituloPorDefecto) {
        this.tituloPorDefecto = tituloPorDefecto;
    }

    public String getTituloPersonalizado() {
        return tituloPersonalizado;
    }

    public void setTituloPersonalizado(String tituloPersonalizado) {
        this.tituloPersonalizado = tituloPersonalizado;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    @Override
    public String toString() {
        return ("ID Notificacion: " + idNotificacion
                + "\nNombre: " + nombre
                + "\nTitulo por Defecto: " + tituloPorDefecto
                + "\nTitulo Personalizado: " + tituloPersonalizado
                + "\nContenido por Defecto: " + contenidoPorDefecto
                + "\nContenido Personalizado: " + contenidoPersonalizado
                + "\nHabilitada: " + (habilitada ? "Sí" : "No"));

    }
//       private int idNotificacion;
//    private String nombre;
//    private String contenidoPorDefecto;
//    private String contenidoPersonalizado;
//    private String tituloPorDefecto;
//    private String tituloPersonalizado;
//    private boolean habilitada;
}
