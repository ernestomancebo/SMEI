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

    public void insertarReserva(Reserva reserva) {
        System.out.println("Reserva " + reserva.getId() + " insertado");
    }

    public void actualizarReserva(Reserva reserva) {
        System.out.println("Reserva " + reserva.getId() + " actualizado");
    }

    public void deshabilitarReservas(List<Integer> idReservas) {
        for (Integer s : idReservas) {
            System.out.println(s + " deshabilitado");
        }
    }

    public Reserva getReserva() {
        Reserva reserva = new Reserva();
        reserva.setId(1);
        return reserva;
    }

    public Reserva getReservaByID(Integer id) {
        Reserva e = new Reserva();

        e.setId(new Integer((int) id));

        return e;
    }

    public ArrayList<Reserva> getReservas() {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        try {
            pstm = conn.prepareStatement("select r.idReservacion, u.idUsuario, u.nombre, e.idEspacio, e.nombre, r.cantidadDePersonas, "
                    + "r.descripcion, r.fechaInicio, r.fechaFin, r.idEstado, es.nombre from reservaciones r, espacios e, usuario u, estados_reservaciones es "
                    + "where u.idUsuario = r.idUsuario and e.idEspacio = r.idEspacio and es.idEstado = r.idEstado");
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
