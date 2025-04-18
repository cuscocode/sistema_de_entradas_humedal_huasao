/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_cliente;
import MODELO_1.m_precio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class precioDAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.getConexion();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID PRECIO ", "PRECIO DE ADULTO", " PRECIO DE NIÑO", "NOMBRE PRECIO"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarPrecio(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idprecio");
                registro[1] = rs.getString("precioadulto");
                registro[2] = rs.getString("precioinfaltil");
                registro[3] = rs.getString("nombre_precio");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar precio dao " + e);

            return null;
        }
    }

    public boolean insertar(m_precio omPrecio) {

        query = "call uspInsertarPrecio(?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setDouble("pprecioadulto", omPrecio.getPrecioadulto());
            cst.setDouble("pprecioinfaltil", omPrecio.getPrecioinfaltil());
            cst.setString("pnombre_precio", omPrecio.getNombre_precio());

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

            JOptionPane.showMessageDialog(null, "error en la clase precio Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_precio omPrecio) {

        query = "call uspModificarPrecio(?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidprecio", omPrecio.getIdprecio());
            cst.setDouble("pprecioadulto", omPrecio.getPrecioadulto());
            cst.setDouble("pprecioinfaltil", omPrecio.getPrecioinfaltil());
            cst.setString("pnombre_precio", omPrecio.getNombre_precio());
            

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
            JOptionPane.showMessageDialog(null, "error en la clase precio Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_precio omPrecio) {

        query = "call uspEliminarPrecio(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidprecio", omPrecio.getIdprecio());

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
            JOptionPane.showMessageDialog(null, "error en la clase precio Dao eliminar  " + e);

            return false;
        } finally {

        }

    }
}
