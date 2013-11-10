/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import smei.modelos.Espacio;
import smei.modelos.Reserva;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto Mancebo T
 */
public class DAOEspacio {

    private Connection conn = DBConnection.getConnection();
    private PreparedStatement pstm;
    private ResultSet rs;

    public boolean insertarEspacio(Espacio espacio) {
        try {
            pstm = conn.prepareStatement(
                    "insert into "
                    + "espacios(nombre, capacidadDePersonas, habilitado, descripcion) "
                    + "values(?, ?, ?, ?)");

            pstm.setString(1, espacio.getNombre());
            pstm.setInt(2, espacio.getCapacidadDePersonas());
            pstm.setBoolean(3, espacio.isHabilitado());
            pstm.setString(4, espacio.getDescripcion());

            return pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspacio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean actualizarEspacio(Espacio espacio) {
        try {
            pstm = conn.prepareStatement(
                    "update espacios set "
                    + "nombre = ?, capacidadDePersonas = ?, habilitado = ?, descripcion = ? "
                    + "where idEspacio = ?");

            pstm.setString(1, espacio.getNombre());
            pstm.setInt(2, espacio.getCapacidadDePersonas());
            pstm.setBoolean(3, espacio.isHabilitado());
            pstm.setString(4, espacio.getDescripcion());
            pstm.setInt(5, espacio.getId());

            return (pstm.executeUpdate() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspacio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private boolean cambiarHabilitadoEspacio(List<Integer> idEspacios, final boolean habilitado) {
        try {
            pstm = conn.prepareCall("update espacios set habilitado = ? where idEspacio = ?");
            for (Integer s : idEspacios) {
                pstm.setBoolean(1, habilitado);
                pstm.setInt(2, s);
                if (pstm.executeUpdate() != 1) {
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspacio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deshabilitarEspacios(List<Integer> idEspacios) {
        return cambiarHabilitadoEspacio(idEspacios, false);
    }

    public boolean habilitarEspacios(List<Integer> idEspacios) {
        return cambiarHabilitadoEspacio(idEspacios, true);
    }

    public Espacio getEspacio() {
        Espacio espacio = new Espacio();
        espacio.setId(1);
        return espacio;
    }

    public Espacio getEspacioByID(Integer id) {
        Espacio e = new Espacio();

        try {
            pstm = conn.prepareCall("select idEspacio, nombre, capacidadDePersonas, habilitado, descripcion from espacios where idEspacio = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                e.setId(rs.getInt("idEspacio"));
                e.setNombre(rs.getString("nombre"));
                e.setCapacidadDePersonas(rs.getInt("capacidadDePersonas"));
                e.setHabilitado(rs.getBoolean("habilitado"));
                e.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return e;
        }
    }

    public ArrayList<Espacio> getAllEspacios() {
        ArrayList<Espacio> espacios = new ArrayList<Espacio>();

        try {
            pstm = conn.prepareCall("select idEspacio, nombre, capacidadDePersonas, habilitado, descripcion from espacios");
            rs = pstm.executeQuery();

            while (rs.next()) {
                Espacio e = new Espacio();

                e.setId(rs.getInt("idEspacio"));
                e.setNombre(rs.getString("nombre"));
                e.setCapacidadDePersonas(rs.getInt("capacidadDePersonas"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setHabilitado(rs.getBoolean("habilitado"));
                espacios.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return espacios;
        }
    }

    public ArrayList<Espacio> getEspaciosDisponiblesParaReserva(Reserva r) {
        ArrayList<Espacio> rv = new ArrayList<Espacio>();

        try {
            pstm = conn.prepareStatement("select e.idEspacio, e.nombre from espacios e "
                    + "where e.habilitado = ? and e.capacidadDePersonas >= ? and e.idEspacio not in ("
                    + "select r.idEspacio from reservaciones r where r.fechaInicio = ? and r.fechaFin = ? "
                    + "and idUsuario != ?)");

            pstm.setBoolean(1, true);
            pstm.setInt(2, r.getCantPersonas());
            pstm.setDate(3, new java.sql.Date(r.getFechaInicio().getTime()));
            pstm.setDate(4, new java.sql.Date(r.getFechaFin().getTime()));
            pstm.setInt(5, r.getUsuario().getIdUsuario());

            rs = pstm.executeQuery();

            while (rs.next()) {
                Espacio e = new Espacio();

                e.setId(rs.getInt("idEspacio"));
                e.setNombre(rs.getString("nombre"));

                rv.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEspacio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rv;
    }

    public static Object[][] crearTablaEspacio(List<Espacio> espacios) {
        Object[][] rv = new Object[espacios.size()][];

        for (int i = 0; i < espacios.size(); i++) {
            rv[i] = GUIUtil.crearFilaEspacio(espacios.get(i));
        }

        return rv;
    }
}
