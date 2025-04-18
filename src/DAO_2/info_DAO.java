/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_info_general;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class info_DAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.getConexion();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID INFORMACION", "RAZON SOCIAL", " RUC DE LA EMPRESA", "AREA ", "NOMBRE DEL NEGOCIO", "DIRECCION", "CAJA", "FOTO DEL NEGOCIO"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarInfomacion(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idinfo");
                registro[1] = rs.getString("razonsocial");
                registro[2] = rs.getString("ruc");
                registro[3] = rs.getString("areaE");
                registro[4] = rs.getString("nombrenegocio");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("caja");
                registro[7] = rs.getString("foto");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar informacio general dao " + e);

            return null;
        }
    }

    public boolean insertar(m_info_general omImformacion) {

        query = "call uspInsertarInfo(?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("prazonsocial", omImformacion.getRazonsocial());
            cst.setString("pruc", omImformacion.getRuc());
            cst.setString("pareaE", omImformacion.getAreaE());
            cst.setString("pnombrenegocio", omImformacion.getNombrenegocio());
            cst.setString("pdireccion", omImformacion.getDireccion());
            cst.setString("pcaja", omImformacion.getCaja());
            cst.setString("pfoto", omImformacion.getFoto());
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

            JOptionPane.showMessageDialog(null, "error en la clase informacion general Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_info_general omImformacion) {

        query = "call uspModificarInformacio(?,?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidinfo", omImformacion.getIdinfo());
           cst.setString("prazonsocial", omImformacion.getRazonsocial());
            cst.setString("pruc", omImformacion.getRuc());
            cst.setString("pareaE", omImformacion.getAreaE());
            cst.setString("pnombrenegocio", omImformacion.getNombrenegocio());
            cst.setString("pdireccion", omImformacion.getDireccion());
            cst.setString("pcaja", omImformacion.getCaja());
            cst.setString("pfoto", omImformacion.getFoto());

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
            JOptionPane.showMessageDialog(null, "error en la clase informacion general Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_info_general omimformacion) {

        query = "call uspEliminarInformacion(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidinfo", omimformacion.getIdinfo());

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
            JOptionPane.showMessageDialog(null, "error en la clase informacion general Dao eliminar  " + e);

            return false;
        } finally {

        }

    }
}
