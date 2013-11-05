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
    private int capacidadDePersonas;
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

    public int getCapacidadDePersonas() {
        return capacidadDePersonas;
    }

    public void setCapacidadDePersonas(int capacidadDePersonas) {
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
}