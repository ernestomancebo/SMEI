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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import smei.modelos.Espacio;
import smei.modelos.EstadoReservacion;
import smei.modelos.Reserva;
import smei.modelos.Usuario;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto Mancebo T
 */
public class DAOReservas {

    Connection conn = DBConnection.getConnection();
    PreparedStatement pstm;
    ResultSet rs;

    public boolean insertarReserva(Reserva reserva) {
        try {
            int idEstado = 0;
            pstm = conn.prepareStatement("select idEstado from estados_reservaciones where upper(nombre) = 'PENDIENTE'");
            rs = pstm.executeQuery();

            if (rs.next()) {
                idEstado = rs.getInt(1);
            }

            pstm = conn.prepareStatement("insert into reservaciones"
                    + "(idUsuario, idEspacio, fechaCreacion, fechaModificacion, fechaInicio, fechaFin, cantidadDePersonas, descripcion, idEstado) "
                    + "values(?, ?, sysdate(), sysdate(), ?, ?, ?, ?, ?)");
            pstm.setInt(1, reserva.getUsuario().getIdUsuario());
            pstm.setInt(2, reserva.getEspacio().getId());
            pstm.setTimestamp(3, new Timestamp(reserva.getFechaInicio().getTime()));
            pstm.setTimestamp(4, new Timestamp(reserva.getFechaFin().getTime()));
            pstm.setInt(5, reserva.getCantPersonas());
            pstm.setString(6, reserva.getDescripcion());
            pstm.setInt(7, idEstado);

            return pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOReservas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return false;
        }
    }

    public boolean actualizarReserva(Reserva reserva) {
        try {
            pstm = conn.prepareStatement("update reservaciones set "
                    + "idEspacio = ?, fechaModificacion = ?, fechaInicio = ?, "
                    + "fechaFin = ?, cantidadDePersonas = ?, descripcion = ? "
                    + "where idReservacion = ?");

            pstm.setInt(1, reserva.getEspacio().getId());
            pstm.setTimestamp(2, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(3, new Timestamp(reserva.getFechaInicio().getTime()));
            pstm.setTimestamp(4, new Timestamp(reserva.getFechaFin().getTime()));
            pstm.setInt(5, reserva.getCantPersonas());
            pstm.setString(6, reserva.getDescripcion());
            pstm.setInt(7, reserva.getId());

            return (pstm.executeUpdate() == 2);
        } catch (SQLException ex) {
            Logger.getLogger(DAOReservas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return false;
        }
    }

    public boolean deshabilitarReservas(List<Integer> idReservas) {
        try {
            int idCancelado = 0;
            pstm = conn.prepareStatement("select idEstado from estados_reservaciones where upper(nombre) = 'CANCELADA'");
            rs = pstm.executeQuery();

            if (rs.next()) {
                idCancelado = rs.getInt(1);
            }

            pstm = conn.prepareStatement("update reservaciones set idEstado = ? where idReservacion = ? "
                    + "AND idEstado = (select idEstado from estados_reservaciones where upper(nombre) = 'PENDIENTE')");
            pstm.setInt(1, idCancelado);

            for (Integer s : idReservas) {
                pstm.setInt(2, s);
                if ((pstm.executeUpdate() != 2)) {
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOReservas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<Reserva> getReservas() {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        try {
            pstm = conn.prepareStatement("select r.idReservacion, u.idUsuario, u.nombre, e.idEspacio, e.nombre, r.cantidadDePersonas, "
                    + "r.descripcion, r.fechaInicio, r.fechaFin, r.idEstado, es.nombre from reservaciones r, espacios e, usuario u, estados_reservaciones es "
                    + "where u.idUsuario = r.idUsuario and e.idEspacio = r.idEspacio and es.idEstado = r.idEstado order by r.idEstado desc,  r.fechaInicio desc");
            rs = pstm.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString(3));

                Espacio e = new Espacio();
                e.setId(rs.getInt("idEspacio"));
                e.setNombre(rs.getString(5));

                EstadoReservacion es = new EstadoReservacion();
                es.setIdEstado(rs.getInt("idEstado"));
                es.setNombre(rs.getString(11));

                Reserva r = new Reserva();
                r.setId(rs.getInt("idReservacion"));
                r.setCantPersonas(rs.getInt("cantidadDePersonas"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setFechaInicio(new Date(rs.getTimestamp("fechaInicio").getTime()));
                r.setFechaFin(new Date(rs.getTimestamp("fechaFin").getTime()));
                r.setEstado(es);
                r.setUsuario(u);
                r.setEspacio(e);

                reservas.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }

    public Object[][] crearTablaReserva(List<Reserva> reservas) {
        Object[][] rv = new Object[reservas.size()][];

        for (byte i = 0; i < reservas.size(); i++) {
            rv[i] = GUIUtil.crearFilaReserva(reservas.get(i));
        }

        return rv;
    }
}