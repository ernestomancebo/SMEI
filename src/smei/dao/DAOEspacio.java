/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.dao;

import java.util.ArrayList;
import java.util.List;
import smei.modelos.Espacio;
import smei.util.Util;

/**
 *
 * @author Ernesto Mancebo T
 */
public class DAOEspacio {

    public void insertarEspacio(Espacio espacio) {
        System.out.println("Espacio " + espacio.getId() + " insertado");
    }

    public void actualizarEspacio(Espacio espacio) {
        System.out.println("Espacio " + espacio.getId() + " actualizado");
    }

    public void deshabilitarEspacios(List<String> idEspacios) {
        for (String s : idEspacios) {
            System.out.println(s + " deshabilitado");
        }
    }

    public Espacio getEspacio() {
        Espacio espacio = new Espacio();
        espacio.setId(1);
        return espacio;
    }

    public Espacio getEspacioByID(Integer id) {
        Espacio e = new Espacio();

        e.setId(new Integer((int) id));
        e.setNombre("e" + id);
        e.setCapacidadDePersonas(id);
        e.setDescripcion("D" + id);
        e.setHabilitado((id % 2 == 0));

        return e;
    }

    public Object[][] getAllEspacios() {
        Object[][] rv;

        List<Espacio> espacios = new ArrayList<Espacio>();

        for (byte i = 0; i < 5; i++) {
            Espacio e = new Espacio();

            e.setId(new Integer((int) i));
            e.setNombre("e" + i);
            e.setCapacidadDePersonas(i);
            e.setDescripcion("D" + i);
            e.setHabilitado((i % 2 == 0));

            espacios.add(e);
        }

        rv = new Object[espacios.size()][];

        for (byte i = 0; i < espacios.size(); i++) {
            rv[i] = Util.crearFilaEspacio(espacios.get(i));
        }

        return rv;
    }

}
