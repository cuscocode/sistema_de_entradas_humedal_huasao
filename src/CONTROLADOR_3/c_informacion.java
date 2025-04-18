/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.cliente_DAO;
import DAO_2.info_DAO;
import MODELO_1.m_cliente;
import MODELO_1.m_info_general;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class c_informacion {

    info_DAO cInformacionDAO = new info_DAO();
    m_info_general omImformacion = new m_info_general();

    public DefaultTableModel ListarInformacion(String Dato) {

        return cInformacionDAO.listar(Dato);
    }
    //-----------------------------

    public boolean insertarInformacion(String razonsocial, String ruc, String areaE, String nombrenegocio, String direccion, String caja, String foto) {

        omImformacion.setRazonsocial(razonsocial);
        omImformacion.setRuc(ruc);
        omImformacion.setAreaE(areaE);
        omImformacion.setNombrenegocio(nombrenegocio);
        omImformacion.setDireccion(direccion);
        omImformacion.setCaja(caja);
        omImformacion.setFoto(foto);

        return cInformacionDAO.insertar(omImformacion);
    }

    public boolean modificarInformacion(String idinfo, String razonsocial, String ruc, String areaE, String nombrenegocio, String direccion, String caja, String foto) {

        omImformacion.setIdinfo(idinfo);
        omImformacion.setRazonsocial(razonsocial);
        omImformacion.setRuc(ruc);
        omImformacion.setAreaE(areaE);
        omImformacion.setNombrenegocio(nombrenegocio);
        omImformacion.setDireccion(direccion);
        omImformacion.setCaja(caja);
        omImformacion.setFoto(foto);

        return cInformacionDAO.Modificar(omImformacion);
    }

    public boolean eliminarInformacion(String id_info) {

        omImformacion.setIdinfo(id_info);
        return cInformacionDAO.Eliminar(omImformacion);

    }
}
