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
public class m_datos_borrados {
   int idBorrados ;
String nombre ,dni ;
int totalVentas ;
Double gananciaTotal ;



public m_datos_borrados(){
    
}

    public m_datos_borrados(int idBorrados, String nombre, String dni, int totalVentas, Double gananciaTotal) {
        this.idBorrados = idBorrados;
        this.nombre = nombre;
        this.dni = dni;
        this.totalVentas = totalVentas;
        this.gananciaTotal = gananciaTotal;
    }

    public int getIdBorrados() {
        return idBorrados;
    }

    public void setIdBorrados(int idBorrados) {
        this.idBorrados = idBorrados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(Double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }




}
