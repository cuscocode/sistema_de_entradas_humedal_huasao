/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CONEXION {


    public String db = "db_humedal";
    public String url = "jdbc:mysql://localhost:3306/" + db; /// jdbc:mysql://localhost:3306/
    public String user = "root";
    public String pass = "";
    
    static Connection link = null;

    public CONEXION() {

    }

    public void conectar() {
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "error de coneccion con la base de datos _clase_coneccion " + e);
        }
    }
    
    public Connection getConexion(){
        if (link==null) {
            conectar();
        }
        return link;
    }
    
    public void cerrarConeccion(){
        //link.close();
    }

}
