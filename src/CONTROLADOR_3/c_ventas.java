/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.ventasDAO;
import MODELO_1.m_datos_borrados;
import MODELO_1.m_ventas;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mr-robot
 */
public class c_ventas {

    ventasDAO cVentaDao = new ventasDAO();
    m_ventas omVenta = new m_ventas();
    m_datos_borrados omDatos=new m_datos_borrados();

    public DefaultTableModel ListarVenta(String Dato) {

        return cVentaDao.listar(Dato);
    }
    //-----------------------------

    public boolean insertarVenta(int nradultos, int nrinfantil, String dni, String tipo_visitante,
            String idUsuario, String idinfo, String idprecio) {

        omVenta.setNradultos(nradultos);
        omVenta.setNrinfantil(nrinfantil);
        omVenta.setDni(dni);
        omVenta.setTipo_visiatante(tipo_visitante);
        omVenta.setIdUsuario(idUsuario);
        omVenta.setIdinfo(idinfo);
        omVenta.setIdprecio(idprecio);

        return cVentaDao.insertar(omVenta);
    }

    public DefaultTableModel ListarVentasGenerales(String Dato) {

        return cVentaDao.listar_venta_generales(Dato);
    }

    public DefaultTableModel listrarPorFecha(String Dato) {
        return cVentaDao.listar_por_fecha(Dato);
    }

    public DefaultTableModel listrarPorAño(String pFecha_inicio, String pFecha_fin) {
        return cVentaDao.listar_por_año(pFecha_inicio, pFecha_fin);
    }

    public boolean EliminarVenta(String id_venta) {
        omVenta.setIdventas(id_venta);
        return cVentaDao.Eliminar_venta(omVenta);
    }
    
    public boolean guardarDatosBorrado(String nombre, String dni, int totalVentas, Double gananciaTotal) {

        omDatos.setNombre(nombre);
        omDatos.setDni(dni);
        omDatos.setTotalVentas(totalVentas);
        omDatos.setGananciaTotal(gananciaTotal);
        
        return cVentaDao.guardar_datos_borrados(omDatos);

    }

    public DefaultTableModel ListarDatos_usuario(String Dato) {

        return cVentaDao.listar_datos_usuario(Dato);
    }

    public DefaultTableModel ListarVentas_usuario(String Dato) {

        return cVentaDao.listar_ventas_usuario(Dato);
    }

    public DefaultTableModel ListarReporte_personal(String Id_usuario) {

        return cVentaDao.reporte_personal(Id_usuario);
    }

    public DefaultTableModel ListarReporte_personal_ventas_hoy(String Id_usuario, String fecha_hoy) {

        return cVentaDao.listar_ventas_hoy_usuario(Id_usuario, fecha_hoy);
    }

    
    
      //PROCEDIMIENTOS PARA BORRAR
    public DefaultTableModel ListarVentaBorrar(String dato) {

        return cVentaDao.listar_ventas_borrar(dato);
    }
    
    public DefaultTableModel listrarVentaBorrarFecha(String Dato) {
        return cVentaDao.listar_venta_borrar_fecha(Dato);
    }
    
    
    public DefaultTableModel listrarPorEntreFechas(String pFecha_inicio, String pFecha_fin) {
        return cVentaDao.listar_venta_borrar_entre_fechas(pFecha_inicio, pFecha_fin);
    }
}
