/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import java.util.ArrayList;
import java.util.List;
import smei.modelos.Email;
import smei.modelos.Rol;
import smei.modelos.Telefono;
import smei.modelos.Usuario;
import smei.util.Util;

/**
 *
 * @author Ernesto
 */
public class DAOUsuario {

    public void insertarUsuario(Usuario usuario) {
        System.out.println("Usuario insertado");
    }

    public void actualizarUsuario(Usuario usuario) {
        System.out.println("Usuario " + usuario.getIdUsuario() + " actualizado");
    }

    public void deshabilitarUsuarios(List<String> idUsuarios) {
        for (String s : idUsuarios) {
            System.out.println(s + " deshabilitado");
        }
    }

    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        return usuario;
    }

    public Usuario getUsuarioByID(Integer id) {
        Usuario u = new Usuario();

        Email e = new Email(id + "@ee.com");
        ArrayList<Email> email = new ArrayList<Email>();
        email.add(e);

        Telefono t = new Telefono("000-000-000" + id);
        ArrayList<Telefono> telefono = new ArrayList<Telefono>();
        telefono.add(t);

        u.setIdUsuario(new Integer((int) id));
        u.setNombre("u" + id);
        u.setIdentificacionP("0000-000" + id);
        u.setPassword(Util.generarClaveDeUsuario(u));
        u.setEmails(email);
        u.setTelefonos(telefono);
        u.setRol(new Rol("adm"));
        u.setEstaHabilitado((id % 2 == 0) ? true : false);

        return u;
    }

    public Object[][] getAllUsuarios() {
        Object[][] rv;

        List<Usuario> usuarios = new ArrayList<Usuario>();

        for (byte i = 0; i < 5; i++) {
            Usuario u = new Usuario();

            Email e = new Email(i + "@ee.com");
            ArrayList<Email> email = new ArrayList<Email>();
            email.add(e);

            Telefono t = new Telefono("000-000-000" + i);
            ArrayList<Telefono> telefono = new ArrayList<Telefono>();
            telefono.add(t);

            u.setIdUsuario(new Integer((int) i));
            u.setNombre("u" + i);
            u.setEmails(email);
            u.setTelefonos(telefono);
            u.setRol(new Rol("adm"));
            u.setEstaHabilitado((i % 2 == 0) ? true : false);

            usuarios.add(u);
        }

        rv = new Object[usuarios.size()][];

        for (byte i = 0; i < usuarios.size(); i++) {
            rv[i] = Util.crearFilaUsuario(usuarios.get(i));
        }

//        rv = new Object[][]{
//            {false, 4, null, null, null, null},
//            {false, 56, null, null, null, null},
//            {false, 3, null, null, null, null},
//            {false, 1, null, null, null, null}
//        };

        return rv;
    }

    public boolean cambiarContrasena(String contrasena) {
        System.out.println("Nueva contrasena: " + contrasena);
        return true;
    }
}
