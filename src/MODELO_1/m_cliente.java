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
public class m_cliente {

    String idcliente, dni, tipovisitante;

    public m_cliente() {

    }

    public m_cliente(String idcliente, String dni, String tipovisitante) {
        this.idcliente = idcliente;
        this.dni = dni;
        this.tipovisitante = tipovisitante;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipovisitante() {
        return tipovisitante;
    }

    public void setTipovisitante(String tipovisitante) {
        this.tipovisitante = tipovisitante;
    }

}
