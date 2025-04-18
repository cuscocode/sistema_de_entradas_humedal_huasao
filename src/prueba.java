/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rgrau
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            String ruta= System.getProperty("user.dir")+"/Reporte_personal.pdf";

            System.out.println(ruta);   
        } catch (Exception e) {
        }
    }

}
