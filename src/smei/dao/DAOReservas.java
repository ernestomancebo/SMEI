/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import java.util.ArrayList;
import java.util.List;
import smei.modelos.Reserva;
import smei.util.GUIUtil;

/**
 *
 * @author Ernesto Mancebo T
 */
public class DAOReservas {

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

    public ArrayList<Reserva> getAllReservas() {

        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        for (byte i = 0; i < 5; i++) {
            Reserva e = new Reserva();

            e.setId(new Integer((int) i));

            reservas.add(e);
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
