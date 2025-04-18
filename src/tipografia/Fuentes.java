/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipografia;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author Elber
 */
public class Fuentes {     
    private Font font = null;
    //tipos de fuentes declarados
    public String font_time1 = "digital-7 (italic).ttf";
    public String font_time2 = "digital-7 (mono italic).ttf";
    public String font_time3 = "digital-7 (mono).ttf";
    public String font_time4 = "digital-7.ttf";
    
        public Font fuente( String fontName, int estilo, float tamanio)
    {
         try {
            //Se carga la fuente
            InputStream is =  getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);            
        }
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }
}

