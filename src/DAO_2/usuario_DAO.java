/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_usuario;
import java.awt.Frame;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class usuario_DAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.getConexion();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"id_usuario ", "tipo usuario", "correo", "nombre",
            "apellido paterno", "apelliedo materno", "dni", "telefono", "foto"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarRegistroUsuario(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idUsuario");
                registro[1] = rs.getString("tipoUsuario");
                registro[2] = rs.getString("correo");
               // registro[3] = rs.getString("clave");
                registro[3] = rs.getString("nombre");

                registro[4] = rs.getString("apellidopaterno");
                registro[5] = rs.getString("apellidomaterno");
                registro[6] = rs.getString("dni");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("foto");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar usuario dao " + e);

            return null;
        }
    }

    public boolean insertar(m_usuario omPersonal) {

        query = "call uspInsertarRegistroUsuario(?,?,?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("ptipoUsuario", omPersonal.getTipoUsuario());
            cst.setString("pcorreo", omPersonal.getCorreo());
            cst.setString("pclave", omPersonal.getClave());
            cst.setString("pnombre", omPersonal.getNombre());
            cst.setString("papellidopaterno", omPersonal.getApellidopaterno());

            cst.setString("papellidomaterno", omPersonal.getApellidomaterno());
            cst.setString("pdni", omPersonal.getDni());
            cst.setString("ptelefono", omPersonal.getTelefono());
            cst.setString("pfoto", omPersonal.getFoto());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
            int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error en la clase Usuario Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_usuario omPersonal) {

        query = "call uspModificarRegistroUsuario(?,?,?,?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidUsuario", omPersonal.getIdUsuario());
            cst.setString("ptipoUsuario", omPersonal.getTipoUsuario());
            cst.setString("pcorreo", omPersonal.getCorreo());
            cst.setString("pclave", omPersonal.getClave());
            cst.setString("pnombre", omPersonal.getNombre());
            cst.setString("papellidopaterno", omPersonal.getApellidopaterno());

            cst.setString("papellidomaterno", omPersonal.getApellidomaterno());
            cst.setString("pdni", omPersonal.getDni());
            cst.setString("ptelefono", omPersonal.getTelefono());
            cst.setString("pfoto", omPersonal.getFoto());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
            int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la clase Usuario Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_usuario omPersonal) {

        query = "call uspEliminarRegistroUsuario(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidUsuario", omPersonal.getIdUsuario());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));
            }
            //============================
            int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la clase Usuario Dao eliminar  " + e);

            return false;
        } finally {

        }

    }

    // ===========================================================================================00
    public DefaultTableModel iniciar_sesion(String PCorreo, String Pclave) {
        DefaultTableModel modelo;

        String[] titulos = {"acceso", "idusuario"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_iniciar_sesion(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pcorreo", PCorreo);
            cst.setString("pclave", Pclave);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {

                // recibimos los datos del procedimiento iniciar sesion
                registro[0] = rs.getString("acceso");
                registro[1] = rs.getString("pidusuario");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "error en la clase iniciar sesion  Dao " + e);
            return null;
        }
    }
}
