/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componetes;

/**
 *
 * @author mr-robot
 */
public class datos_usuario {
    public static String id_usuario="",tipo_usuario="",nombre="",apellidoPaterno="",apellidoMaterno="",dni="";
    
    public datos_usuario(){
     
    }

    public static String getId_usuario() {
        return id_usuario;
    }

    public static void setId_usuario(String id_usuario) {
        datos_usuario.id_usuario = id_usuario;
    }

    public static String getTipo_usuario() {
        return tipo_usuario;
    }

    public static void setTipo_usuario(String tipo_usuario) {
        datos_usuario.tipo_usuario = tipo_usuario;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        datos_usuario.nombre = nombre;
    }

    public static String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public static void setApellidoPaterno(String apellidoPaterno) {
        datos_usuario.apellidoPaterno = apellidoPaterno;
    }

    public static String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public static void setApellidoMaterno(String apellidoMaterno) {
        datos_usuario.apellidoMaterno = apellidoMaterno;
    }

    public static String getDni() {
        return dni;
    }

    public static void setDni(String dni) {
        datos_usuario.dni = dni;
    }
    
    
}
