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
public class m_info_general {
     String idinfo ,razonsocial ,ruc ,areaE,nombrenegocio,direccion ,caja ,foto;
     
     public m_info_general(){
         
     }

    public m_info_general(String idinfo, String razonsocial, String ruc, String areaE, String nombrenegocio, String direccion, String caja, String foto) {
        this.idinfo = idinfo;
        this.razonsocial = razonsocial;
        this.ruc = ruc;
        this.areaE = areaE;
        this.nombrenegocio = nombrenegocio;
        this.direccion = direccion;
        this.caja = caja;
        this.foto = foto;
    }

    public String getIdinfo() {
        return idinfo;
    }

    public void setIdinfo(String idinfo) {
        this.idinfo = idinfo;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getAreaE() {
        return areaE;
    }

    public void setAreaE(String areaE) {
        this.areaE = areaE;
    }

    public String getNombrenegocio() {
        return nombrenegocio;
    }

    public void setNombrenegocio(String nombrenegocio) {
        this.nombrenegocio = nombrenegocio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
     
     
}
