/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.modelos;

import java.util.ArrayList;

/**
 *
 * @author Ernesto
 */
public class Usuario {

    private Integer idUsuario;
    private Rol rol;
    private String nombre;
    private String password;
    private String identificacionP;
    private ArrayList<Email> emails;
    private ArrayList<Telefono> telefonos;
    private boolean habilitado;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentificacionP() {
        return identificacionP;
    }

    public void setIdentificacionP(String identificacionP) {
        this.identificacionP = identificacionP;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean estaHabilitado) {
        this.habilitado = estaHabilitado;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public String getDescripcion() {
        return ("Usuario: " + getNombre() + ". ID: " + getIdUsuario());
    }

    @Override
    public String toString() {
        return ("ID Usuario: " + getIdUsuario()
                + "\nNombre: " + getNombre()
                + "\nRol: " + getRol().getNombre()
                + "\nIdentificación Personal: " + getIdentificacionP()
                + "\nEmail: " + getEmails().get(0).getEmail()
                + "\nTeléfono: " + getTelefonos().get(0).getTelefono()
                + "\nHabilitado: " + ((isHabilitado()) ? "Sí" : "No"));
    }
}
