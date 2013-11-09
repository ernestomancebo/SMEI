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
import java.util.logging.Level;
import java.util.logging.Logger;
import smei.modelos.Rol;

/**
 *
 * @author Ernesto
 */
public class DAORol {

    Connection conn = DBConnection.getConnection();
    PreparedStatement pstm;
    ResultSet rs;

    public ArrayList<Rol> getRoles() {
        ArrayList<Rol> rv = new ArrayList<Rol>();
        
        try {
            pstm = conn.prepareStatement("select idRol, nombre from rol");
            rs = pstm.executeQuery();

            while (rs.next()) {
                Rol r = new Rol();

                r.setIdRol(rs.getInt("idRol"));
                r.setNombre(rs.getString("nombre"));

                rv.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rv;
        }
    }
    
}
