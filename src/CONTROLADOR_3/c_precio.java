/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.precioDAO;
import MODELO_1.m_precio;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class c_precio {

    precioDAO cPrecioDAO = new precioDAO();
    m_precio omPrecio = new m_precio();

    public DefaultTableModel ListarPrecio(String Dato) {

        return cPrecioDAO.listar(Dato);
    }
    //-----------------------------

    public boolean insertarPrecio(Double precioadulto, Double precioinfaltil, String nombre_precio) {
        omPrecio.setPrecioadulto(precioadulto);
        omPrecio.setPrecioinfaltil(precioinfaltil);
        omPrecio.setNombre_precio(nombre_precio);

        return cPrecioDAO.insertar(omPrecio);
    }

    public boolean modificarPrecio(String idprecio, Double precioadulto, Double precioinfaltil, String nombre_precio) {

        omPrecio.setIdprecio(idprecio);
        omPrecio.setPrecioadulto(precioadulto);
        omPrecio.setPrecioinfaltil(precioinfaltil);
        omPrecio.setNombre_precio(nombre_precio);
        
        return cPrecioDAO.Modificar(omPrecio);
    }

    public boolean eliminarPrecio(String id_precio) {

        omPrecio.setIdprecio(id_precio);
        return cPrecioDAO.Eliminar(omPrecio);
    }

}
