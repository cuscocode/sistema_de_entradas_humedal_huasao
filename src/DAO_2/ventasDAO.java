/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_cliente;
import MODELO_1.m_datos_borrados;
import MODELO_1.m_ventas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class ventasDAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.getConexion();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID VENTA ", "NR° DE ADULTOS", " NR° DE NIÑOS", "FECHA DE LA VENTA", "TOTAL DE PERSONAS", "MONTO TOTAL", "ESTADO",
            "DATOS DEL VENDEDOR", "DNI VISITANTE", "TIPO VISITANTE", "RAZON SOCIAL", "NOMBRE NEGOCIO", "PRECIO DE ADULTO", "PRECIO DE NIÑO"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_listar_ventas(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idventas");
                registro[1] = rs.getInt("nradultos");
                registro[2] = rs.getInt("nrinfantil");
                registro[3] = rs.getString("fecha");
                registro[4] = rs.getInt("totalpersonas");
                registro[5] = rs.getDouble("montototal");
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("usuario");
                registro[8] = rs.getString("dni");
                registro[9] = rs.getString("tipovisitante");
                registro[10] = rs.getString("razonsocial");
                registro[11] = rs.getString("nombrenegocio");
                registro[12] = rs.getDouble("precioadulto");
                registro[13] = rs.getDouble("precioinfaltil");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas dao " + e);

            return null;
        }
    }

    public boolean insertar(m_ventas omventas) {

        query = "call usp_Insertar_venta(?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setInt("pnradultos", omventas.getNradultos());
            cst.setInt("pnrinfantil", omventas.getNrinfantil());

            cst.setString("pdni", omventas.getDni());
            cst.setString("ptipovisitante", omventas.getTipo_visiatante());

            cst.setString("pidUsuario", omventas.getIdUsuario());
            cst.setString("pidinfo", omventas.getIdinfo());
            cst.setString("pidprecio", omventas.getIdprecio());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
            /* int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }*/

            // return true;
            return false;
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error en la clase venta Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Eliminar_venta(m_ventas omVentas) {

        query = "call uspEliminarVenta(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidVenta", omVentas.getIdventas());
            // cst.setString("pidcliente", omVentas.getIdcliente());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));
            }
            //============================
            /* int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }*/
            return false;
        } catch (Exception e) {
            //  JOptionPane.showMessageDialog(null, "error en la clase ventas Dao eliminar venta\n  " + e);

            return false;
        } finally {
        }

    }

    public boolean guardar_datos_borrados(m_datos_borrados omDatos) {

        query = "call uspInsertarDatosDeBorrados(?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pnombre", omDatos.getNombre());
            cst.setString("pdni", omDatos.getDni());
            cst.setInt("ptotalVentas", omDatos.getTotalVentas());
            cst.setDouble("pgananciaTotal", omDatos.getGananciaTotal());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));
            }
            //============================
            // int n = cst.executeUpdate();

            /* if (n != 0) {
                return true;
            } else {
                return false;
            }*/
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la clase ventas Dao datos \n  " + e);

            return false;
        } finally {

        }

    }

    //############################################################################### CONSULTAS
    public DefaultTableModel listar_venta_generales(String pDato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID VENTA", "VENDEDOR", "N° ADULTOS", "N° NIÑOS", "TOT. PERSONAS", "MONTO", "TIPO VISIT.", "FECHA"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_listar_ganacia_general(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", pDato);

            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idventas");
                registro[1] = rs.getString("usuario");
                registro[2] = rs.getDouble("nradultos");
                registro[3] = rs.getDouble("nrinfantil");
                registro[4] = rs.getInt("totalpersonas");
                registro[5] = rs.getDouble("montototal");
                registro[6] = rs.getString("tipovisitante");
                registro[7] = rs.getString("fecha");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar por ventas generales Dao " + e);

            return null;
        }
    }

    public DefaultTableModel listar_por_año(String pFecha_inicio, String pFecha_fin) {
        DefaultTableModel modelo;

        String[] titulos = {"VENDEDOR", "N° ADULTOS", "N° NIÑOS", "TOT. PERSONAS", "MONTO", "TIPO VISIT.", "FECHA"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_listar__por_año(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pFecha_inicio", pFecha_inicio);
            cst.setString("pfecha_fin", pFecha_fin);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("usuario");
                registro[1] = rs.getDouble("nradultos");
                registro[2] = rs.getDouble("nrinfantil");
                registro[3] = rs.getInt("totalpersonas");
                registro[4] = rs.getDouble("montototal");
                registro[5] = rs.getString("tipovisitante");
                registro[6] = rs.getString("fecha");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar por año de ventas dao " + e);

            return null;
        }
    }

    public DefaultTableModel listar_por_fecha(String pFecha_fecha) {
        DefaultTableModel modelo;

        String[] titulos = {"VENDEDOR", "N° ADULTOS", "N° NIÑOS", "TOT. PERSONAS", "MONTO", "TIPO VISIT.", "FECHA"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_listar__por_fecha(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pFecha", pFecha_fecha);

            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("usuario");
                registro[1] = rs.getDouble("nradultos");
                registro[2] = rs.getDouble("nrinfantil");
                registro[3] = rs.getInt("totalpersonas");
                registro[4] = rs.getDouble("montototal");
                registro[5] = rs.getString("tipovisitante");
                registro[6] = rs.getString("fecha");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar por fecha de ventas dao " + e);

            return null;
        }
    }

    public DefaultTableModel listar_datos_usuario(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"id_usuario ", "nombre",
            "apellido paterno", "apelliedo materno"};

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
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellidopaterno");
                registro[3] = rs.getString("apellidomaterno");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar datos usuario ventas dao " + e);

            return null;
        }
    }

    public DefaultTableModel listar_ventas_usuario(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID USUARIO ", "NOMBRES",
            "GANANCIAS", "VENTAS", "PERSONAS"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_filtrar_por_usuarios(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idUsuario");
                registro[1] = rs.getString("usuario");
                registro[2] = rs.getDouble("total_ganancias");
                registro[3] = rs.getString("total_ventas");
                registro[4] = rs.getString("total_personas");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas de los usuarios" + e);

            return null;
        }
    }

    public DefaultTableModel reporte_personal(String id_usuario) {
        DefaultTableModel modelo;

        String[] titulos = {"titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo", "titulo"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_listar_ventas_de_usuario(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pIdusuario", id_usuario);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idUsuario");
                registro[1] = rs.getString("usuario");
                registro[2] = rs.getString("dni");
                registro[3] = rs.getString("correo");
                registro[4] = rs.getString("telefono");
                registro[5] = rs.getDouble("total_ganancias");
                registro[6] = rs.getInt("total_ventas");
                registro[7] = rs.getInt("total_adultos");
                registro[8] = rs.getInt("total_niños");
                registro[9] = rs.getInt("total_personas");
                registro[10] = rs.getInt("clientes_locales");
                registro[11] = rs.getInt("clientes_externos");
                registro[12] = rs.getInt("clientes_extranjero");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar reportes perosonales DAO" + e);

            return null;
        }
    }

    public DefaultTableModel listar_ventas_hoy_usuario(String id_usuario, String fecha_hoy) {
        DefaultTableModel modelo;

        String[] titulos = {"ADULTOS ATENDIDOS", "NIÑOS ATENDIDOS",
            "VENTAS REALIZADAS", "GANANCIAS REALIZADAS"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_ganacias_de_hoy_usuario(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pIdusuario", id_usuario);
            cst.setString("pFecha", fecha_hoy);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("adultos_totales");
                registro[1] = rs.getString("niños_totales");
                registro[2] = rs.getInt("ventas_realizadas");
                registro[3] = rs.getString("ganancias_totales");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas de los usuarios" + e);

            return null;
        }
    }

    //=============================================PROCEDIMIENTO PARA BORRAR LAS VENTAS ==============================
    //=============================================PROCEDIMIENTO PARA BORRAR LAS VENTAS ==============================
    //=============================================PROCEDIMIENTO PARA BORRAR LAS VENTAS ==============================
    public DefaultTableModel listar_ventas_borrar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"ID VENTAS ", "NR ADULTOS",
            "NR NIÑOS", "FECHA VENDIDA", "NR PERSONAS", "MONTO", "ID USUARIO", "ID CLIENTE"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_listar_ventas_borrar(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idventas");
                registro[1] = rs.getString("nradultos");
                registro[2] = rs.getString("nrinfantil");
                registro[3] = rs.getString("fecha");
                registro[4] = rs.getString("totalpersonas");
                registro[5] = rs.getString("montototal");
                registro[6] = rs.getString("idUsuario");
                registro[7] = rs.getString("idcliente");
                // registro[4] = rs.getString("idinfo");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas B dao \n" + e);

            return null;
        }
    }

    public DefaultTableModel listar_venta_borrar_fecha(String pFecha_fecha) {
        DefaultTableModel modelo;

        String[] titulos = {"ID VENTAS ", "NR ADULTOS",
            "NR NIÑOS", "MONTO", "FECHA", "ID CLIENTE"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_venta_borrar_fecha(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pFecha", pFecha_fecha);

            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idventas");
                registro[1] = rs.getDouble("nradultos");
                registro[2] = rs.getDouble("nrinfantil");
                registro[3] = rs.getDouble("montototal");
                registro[4] = rs.getString("fecha");
                registro[5] = rs.getString("idcliente");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas borrar por fecha de ventas dao " + e);

            return null;
        }
    }

    public DefaultTableModel listar_venta_borrar_entre_fechas(String pFecha_inicio, String fecha_fin) {
        DefaultTableModel modelo;

        String[] titulos = {"ID VENTAS ", "NR ADULTOS",
            "NR NIÑOS", "MONTO", "FECHA", "ID CLIENTE"};

        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_venta_borrar_entre_fechas(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pFecha_inicio", pFecha_inicio);
            cst.setString("pfecha_fin", fecha_fin);

            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idventas");
                registro[1] = rs.getDouble("nradultos");
                registro[2] = rs.getDouble("nrinfantil");
                registro[3] = rs.getDouble("montototal");
                registro[4] = rs.getString("fecha");
                registro[5] = rs.getString("idcliente");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas borrar por entre fechas de ventas dao " + e);

            return null;
        }
    }

}
