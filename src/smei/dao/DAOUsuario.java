/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private Connection conn = DBConnection.getConnection();
    private PreparedStatement pstm;
    private ResultSet rs;

    public boolean insertarUsuario(Usuario usuario) {
        try {
            pstm = conn.prepareStatement(
                    "insert into "
                    + "usuario(nombre, password, idRol, identificacion, email, telefono, habilitado) "
                    + "values(?, ?, ?, ?, ?, ?, ?)");

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getPassword());
            pstm.setInt(3, 2);
            pstm.setString(4, usuario.getIdentificacionP());
            pstm.setString(5, usuario.getEmails().get(0).getEmail());
            pstm.setString(6, usuario.getTelefonos().get(0).getTelefono());
            pstm.setBoolean(7, usuario.isHabilitado());

            return pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
        try {
            pstm = conn.prepareStatement(
                    "update usuario set "
                    + "nombre = ?, password = ?, idRol = ?, identificacion = ?, email = ?, telefono = ?, habilitado = ? "
                    + "where idUsuario = ?");

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getPassword());
            pstm.setInt(3, 2);
            pstm.setString(4, usuario.getIdentificacionP());
            pstm.setString(5, usuario.getEmails().get(0).getEmail());
            pstm.setString(6, usuario.getTelefonos().get(0).getTelefono());
            pstm.setBoolean(7, usuario.isHabilitado());
            pstm.setInt(8, usuario.getIdUsuario());

            return pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deshabilitarUsuarios(List<Integer> idUsuarios, boolean habilitado) {
        try {
            pstm = conn.prepareCall("update usuario set habilitado = ? where idUsuario = ?");
            for (Integer s : idUsuarios) {
                pstm.setBoolean(1, habilitado);
                pstm.setInt(2, s);
                if (!pstm.execute()) {
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Usuario getUsuarioByID(Integer id) {
        Usuario u = new Usuario();

        try {
            pstm = conn.prepareCall("select idUsuario, r.nombre, u.nombre, password, identificacion, email, telefono, habilitado from usuario u, rol r where u.idRol = r.idRol and idUsuario = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Email e = new Email(rs.getString("email"));
                ArrayList<Email> email = new ArrayList<Email>();
                email.add(e);

                Telefono t = new Telefono(rs.getString("telefono"));
                ArrayList<Telefono> telefono = new ArrayList<Telefono>();
                telefono.add(t);

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString(3));
                u.setEmails(email);
                u.setTelefonos(telefono);
                u.setIdentificacionP(rs.getString("identificacion"));
                u.setRol(new Rol(rs.getString(2)));
                u.setHabilitado(rs.getBoolean("habilitado"));
                u.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return u;
        }
    }

    public ArrayList<Usuario> getAllUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            pstm = conn.prepareCall("select idUsuario, r.nombre, u.nombre, email, telefono, habilitado from usuario u, rol r where u.idRol = r.idRol");
            rs = pstm.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();

                Email e = new Email(rs.getString("email"));
                ArrayList<Email> email = new ArrayList<Email>();
                email.add(e);

                Telefono t = new Telefono(rs.getString("telefono"));
                ArrayList<Telefono> telefono = new ArrayList<Telefono>();
                telefono.add(t);

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString(3));
                u.setEmails(email);
                u.setTelefonos(telefono);
                u.setRol(new Rol(rs.getString(2)));
                u.setHabilitado(rs.getBoolean("habilitado"));

                usuarios.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return usuarios;
        }
    }

    public static Object[][] crearTablaUsuario(List<Usuario> usuarios) {
        Object[][] rv = new Object[usuarios.size()][];

        for (byte i = 0; i < usuarios.size(); i++) {
            rv[i] = Util.crearFilaUsuario(usuarios.get(i));
        }
        return rv;
    }

    public boolean cambiarContrasena(Usuario usuario) {
        try {
            System.out.println(usuario.getPassword() + "" + usuario.getIdUsuario());
            pstm = conn.prepareCall("update usuario set password = ? where idUsuario = ?");
            pstm.setString(1, usuario.getPassword());
            pstm.setInt(2, usuario.getIdUsuario());

            return (pstm.executeUpdate() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
