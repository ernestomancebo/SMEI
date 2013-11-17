/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import com.sun.org.apache.xerces.internal.util.DOMUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ernesto
 */
public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {

        if (conn == null) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream("connection.properties"));

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("host") + "/" + prop.getProperty("db") + "?"
                        + "user=" + prop.getProperty("user")
                        + "&password=" + prop.getProperty("password"));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                conn = null;
            } catch (IOException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                conn = null;
            }
        }

        return conn;
    }
}
