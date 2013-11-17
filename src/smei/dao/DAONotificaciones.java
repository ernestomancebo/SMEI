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
import smei.modelos.Notificacion;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto
 */
public class DAONotificaciones {

    Connection conn = DBConnection.getConnection();
    PreparedStatement pstm;
    ResultSet rs;

    public boolean actualiarNotificacion(Notificacion n) {
        try {
            pstm = conn.prepareStatement("update notificaciones set contenido_personalizado = ?, titulo_personalizado = ?, "
                    + "habilitada = ? where idNotificacion = ?");
            pstm.setString(1, n.getContenidoPersonalizado());
            pstm.setString(2, n.getTituloPersonalizado());
            pstm.setBoolean(3, n.isHabilitada());
            pstm.setInt(4, n.getIdNotificacion());

            return (!pstm.execute());
        } catch (SQLException ex) {
            Logger.getLogger(DAONotificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Notificacion> getAllNotificaciones() {
        ArrayList<Notificacion> rv = new ArrayList<Notificacion>();
        try {
            pstm = conn.prepareStatement("select idNotificacion, nombre, contenido_por_defecto, contenido_personalizado, "
                    + "titulo_por_defecto, titulo_personalizado, habilitada from notificaciones");
            rs = pstm.executeQuery();

            while (rs.next()) {
                Notificacion n = new Notificacion();

                n.setIdNotificacion(rs.getInt("idNotificacion"));
                n.setNombre(rs.getString("nombre"));
                n.setContenidoPorDefecto(rs.getString("contenido_por_defecto"));
                n.setContenidoPersonalizado(rs.getString("contenido_personalizado"));
                n.setTituloPorDefecto(rs.getString("titulo_por_defecto"));
                n.setTituloPersonalizado(rs.getString("titulo_personalizado"));
                n.setHabilitada(rs.getBoolean("habilitada"));

                rv.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONotificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rv;
        }
    }

    /*
     public Notificacion getNotificacionByID(Integer id) {
     Notificacion n = new Notificacion();

     try {
     pstm = conn.prepareStatement("select idNotificacion, nombre, contenido_por_defecto, contenido_personalizado, "
     + "titulo_por_defecto, titulo_personalizado, habilitada from notificaciones where idNotificacion = ?");
     pstm.setInt(1, id);
     rs = pstm.executeQuery();

     while (rs.next()) {
     n.setIdNotificacion(rs.getInt("idNotificacion"));
     n.setNombre(rs.getString("nombre"));
     n.setContenidoPorDefecto("contenido_por_defecto");
     n.setContenidoPersonalizado(rs.getString("contenido_personalizado"));
     n.setTituloPorDefecto(rs.getString("titulo_por_defecto"));
     n.setTituloPersonalizado(rs.getString("titulo_personalizado"));
     n.setHabilitada(rs.getBoolean("habilitada"));
     }
     } catch (SQLException ex) {
     Logger.getLogger(DAONotificaciones.class.getName()).log(Level.SEVERE, null, ex);
     } finally {
     return n;
     }
     }
     */
    public static Object[][] crearTablaNotificaciones(List<Notificacion> notificaciones) {
        Object[][] rv = new Object[notificaciones.size()][];

        for (int i = 0; i < notificaciones.size(); i++) {
            rv[i] = GUIUtil.crearFilaNotificaciones(notificaciones.get(i));
        }

        return rv;
    }
}
