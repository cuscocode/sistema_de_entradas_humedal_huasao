/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componetes;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author ANONYMOUS
 */
public class clase_impresora {

    public void eliminar_cola_de_impreciones() {
        try {
            File archivo = new File("ejecutar.bat");
            FileWriter fw = new FileWriter(archivo);
            System.out.println(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("del %systemroot%\\System32\\spool\\printers\\* /q");
            bw.close();
            Desktop.getDesktop().open(archivo);
            // funcionesAnimaciones.mostrarAlertaBien("error", "se las colas de impreciones ");
            System.out.println("se limpiaron las colas de impreciones ");
        } catch (Exception e) {
            // funcionesAnimaciones.mostrarAlertaError("error", "error al para cola de impreciones");
        }
    }
}
