/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.modelos;

/**
 *
 * @author Ernesto
 */
public class Espacio {

    private Integer id;
    private String nombre;
    private Integer capacidadDePersonas;
    private String descripcion;
    private boolean habilitado;

    public Espacio() {
    }

    public Espacio(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacidadDePersonas() {
        return capacidadDePersonas;
    }

    public void setCapacidadDePersonas(Integer capacidadDePersonas) {
        this.capacidadDePersonas = capacidadDePersonas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return ("Espacio: " + getNombre()
                + "\nID Espacio: " + getId()
                + "\nCapacidad de Personas: " + getCapacidadDePersonas()
                + "\nDescripción: " + getDescripcion()
                + "\nHabilitado: " + ((isHabilitado()) ? "Sí" : "No"));
    }
}
