/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componetes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author mr-robot
 */
public class funciones_fotos {

    public funciones_fotos() {
    }

    static Path AlmacenamientofotosRuta, ubicacion;

    public static void crearCarpetaAlmacenar(String nombreCarpeta) {
        try {
            File direcctorio = new File(nombreCarpeta);
            direcctorio.mkdir();
            AlmacenamientofotosRuta = Paths.get(direcctorio.getAbsolutePath());
            ubicacion = Paths.get(direcctorio.getAbsolutePath());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al crear carpera para almacenar fotos");
        }
    }

    public static String copiarfoto(String rutaImagenCoperar, String dni) {
        try {
            //creando nombre para la foto
            LocalDate fechaactual = LocalDate.now();
            String NombreImagen = fechaactual + "--" + dni + ".png";

            //CREANDO DIRECTORIO PARA ALMACENAR LAS IMAGENES
            //estara dentro del proyecto
            Path origen = Paths.get(rutaImagenCoperar);

            AlmacenamientofotosRuta = ubicacion;

            Path copiar = Files.copy(origen, ubicacion.resolve(NombreImagen), StandardCopyOption.REPLACE_EXISTING); //copear dentro del proyecto

            return NombreImagen;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, " ocurrio un error al subir foto \n" + e);

            return null;
        }
    }

    public static void eliminar_foto(String nombrefoto) {
        try {
            File archivo = new File(AlmacenamientofotosRuta + "\\", nombrefoto);

            if (archivo.exists()) {
                archivo.delete();
            } else {
                JOptionPane.showMessageDialog(null, "el no se encuentra la ubicacion de la foto");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ocurrio un error al eliminar la foto");
        }
    }

    public static String getRecuperarFotoMostrar(String nombreFoto) {
        try {
            return AlmacenamientofotosRuta + "\\" + nombreFoto;
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al recuperar ruta");
            return null;
        }
    }
    
    
    

}
