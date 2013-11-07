/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ernesto
 */
public class DBConnection {

    public Connection getConnection() {
        Connection rv = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            rv = DriverManager.getConnection("jdbc:mysql://localhost/smei?"
                    + "user=test&password=test");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("GOOD!");
        return rv;
    }
}
