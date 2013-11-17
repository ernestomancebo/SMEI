/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.modelos;

import java.util.Date;

/**
 *
 * @author Ernesto
 */
public class Reserva {

    private Integer id;
    private Usuario usuario;
    private Espacio espacio;
    private int cantPersonas;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaInicio;
    private Date fechaFin;
    private EstadoReservacion estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoReservacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoReservacion estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return ("Reservación: " + getId()
                + "\nUsuario: " + getUsuario().getNombre()
                + "\nLugar: " + getEspacio().getNombre()
                + "\nCantidad de Personas: " + getCantPersonas()
                + "\nDescripción: " + getDescripcion()
                + "\nFecha: "
                + "\nEstado de la reserva: " + getEstado().getNombre());
    }
    /*    
     private Integer id;
     private Usuario usuario;
     private Espacio espacio;
     private int cantPersonas;
     private String descripcion;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private Date fechaInicio;
     private Date fechaFin;
     private EstadoReservacion estado;
     */
}
