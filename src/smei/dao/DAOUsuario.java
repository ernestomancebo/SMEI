/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import java.util.List;
import smei.modelos.Usuario;

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

    public Usuario getUsuario(Integer id) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        return usuario;
    }
}
