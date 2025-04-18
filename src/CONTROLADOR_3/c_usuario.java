/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import MODELO_1.*;
import DAO_2.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_usuario {

    usuario_DAO CPersonal = new usuario_DAO();
    m_usuario omPersonal = new m_usuario();

    public DefaultTableModel ListarUsuario(String Dato) {

        return CPersonal.listar(Dato);
    }
    //-----------------------------

    public boolean insertarUsuario(String tipoUsuario, String correo, String clave, String nombre, String apellidopaterno,
            String apellidomaterno, String dni, String telefono, String foto) {

        omPersonal.setTipoUsuario(tipoUsuario);
        omPersonal.setCorreo(correo);
        omPersonal.setClave(clave);
        omPersonal.setNombre(nombre);
        omPersonal.setApellidopaterno(apellidopaterno);
        omPersonal.setApellidomaterno(apellidomaterno);
        omPersonal.setDni(dni);
        omPersonal.setTelefono(telefono);
        omPersonal.setFoto(foto);

        return CPersonal.insertar(omPersonal);
    }

    public boolean modificarUsuario(String idUsuario, String tipoUsuario, String correo, String clave, String nombre,
            String apellidopaterno, String apellidomaterno, String dni, String telefono, String foto) {

        omPersonal.setIdUsuario(idUsuario);
        omPersonal.setTipoUsuario(tipoUsuario);
        omPersonal.setCorreo(correo);
        omPersonal.setClave(clave);
        omPersonal.setNombre(nombre);
        omPersonal.setApellidopaterno(apellidopaterno);
        omPersonal.setApellidomaterno(apellidomaterno);
        omPersonal.setDni(dni);
        omPersonal.setTelefono(telefono);
        omPersonal.setFoto(foto);

        return CPersonal.Modificar(omPersonal);

    }

    public boolean eliminarUsuario(String id_usuario) {

        omPersonal.setIdUsuario(id_usuario);
        return CPersonal.Eliminar(omPersonal);

    }

 

    public DefaultTableModel Iniciar_sesion(String correo, String clave) {

        return CPersonal.iniciar_sesion(correo, clave);
    }
}
