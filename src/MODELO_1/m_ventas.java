/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO_1;

/**
 *
 * @author mr-robot
 */
public class m_ventas {

    String idventas;
    int nradultos, nrinfantil;
    String dni,tipo_visiatante;
    String fecha;
    int totalpersonas;
    Double montototal;
    String estado, idUsuario,idcliente, idinfo, idprecio;
    
    public m_ventas(){
    }

    public m_ventas(String idventas, int nradultos, int nrinfantil, String dni, String tipo_visiatante, String fecha, int totalpersonas, Double montototal, String estado, String idUsuario, String idcliente, String idinfo, String idprecio) {
        this.idventas = idventas;
        this.nradultos = nradultos;
        this.nrinfantil = nrinfantil;
        this.dni = dni;
        this.tipo_visiatante = tipo_visiatante;
        this.fecha = fecha;
        this.totalpersonas = totalpersonas;
        this.montototal = montototal;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idcliente = idcliente;
        this.idinfo = idinfo;
        this.idprecio = idprecio;
    }

    public String getIdventas() {
        return idventas;
    }

    public void setIdventas(String idventas) {
        this.idventas = idventas;
    }

    public int getNradultos() {
        return nradultos;
    }

    public void setNradultos(int nradultos) {
        this.nradultos = nradultos;
    }

    public int getNrinfantil() {
        return nrinfantil;
    }

    public void setNrinfantil(int nrinfantil) {
        this.nrinfantil = nrinfantil;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipo_visiatante() {
        return tipo_visiatante;
    }

    public void setTipo_visiatante(String tipo_visiatante) {
        this.tipo_visiatante = tipo_visiatante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotalpersonas() {
        return totalpersonas;
    }

    public void setTotalpersonas(int totalpersonas) {
        this.totalpersonas = totalpersonas;
    }

    public Double getMontototal() {
        return montototal;
    }

    public void setMontototal(Double montototal) {
        this.montototal = montototal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getIdinfo() {
        return idinfo;
    }

    public void setIdinfo(String idinfo) {
        this.idinfo = idinfo;
    }

    public String getIdprecio() {
        return idprecio;
    }

    public void setIdprecio(String idprecio) {
        this.idprecio = idprecio;
    }

    
}
