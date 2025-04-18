/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_cliente;
import MODELO_1.m_usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class cliente_DAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.getConexion();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID CLIENTE ", "DNI", " TIPO DE VISITANTE"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarCliente(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idcliente");
                registro[1] = rs.getString("dni");
                registro[2] = rs.getString("tipovisitante");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar cliente dao " + e);

            return null;
        }
    }

    public boolean insertar(m_cliente omCliente) {

        query = "call uspInsertarCliente(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pdni", omCliente.getDni());
            cst.setString("ptipovisitante", omCliente.getTipovisitante());

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

            JOptionPane.showMessageDialog(null, "error en la clase cliente Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_cliente omCliente) {

        query = "call uspModificarCliente(?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidcliente", omCliente.getIdcliente());
            cst.setString("pdni", omCliente.getDni());
            cst.setString("ptipovisitante", omCliente.getTipovisitante());

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
            JOptionPane.showMessageDialog(null, "error en la clase cliente Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_cliente omCliente) {

        query = "call uspEliminarCliente(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidcliente", omCliente.getIdcliente());

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
            JOptionPane.showMessageDialog(null, "error en la clase cliente Dao eliminar  " + e);

            return false;
        } finally {

        }

    }

}
