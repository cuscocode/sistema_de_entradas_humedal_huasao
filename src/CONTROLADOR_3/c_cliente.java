/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.cliente_DAO;
import MODELO_1.m_cliente;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class c_cliente {

    cliente_DAO cClienteDAO = new cliente_DAO();
    m_cliente omCliente = new m_cliente();

    public DefaultTableModel ListarCliente(String Dato) {

        return cClienteDAO.listar(Dato);
    }
    //-----------------------------

    public boolean insertarCliente(String dni, String tipovisitante) {

        omCliente.setDni(dni);
        omCliente.setTipovisitante(tipovisitante);

        return cClienteDAO.insertar(omCliente);
    }

    public boolean modificarCliente(String idcliente, String dni, String tipovisitante) {

       omCliente.setIdcliente(idcliente);
       omCliente.setDni(dni);
       omCliente.setTipovisitante(tipovisitante);
       
        return cClienteDAO.Modificar(omCliente);
    }

    public boolean eliminarCliente(String id_cliente) {

        omCliente.setIdcliente(id_cliente);
        return cClienteDAO.Eliminar(omCliente);

    }

    
}
