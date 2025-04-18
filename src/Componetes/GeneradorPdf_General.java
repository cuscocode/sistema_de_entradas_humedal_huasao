package Componetes;
import Componetes.Numero_Letras;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;

import java.io.FileOutputStream;
import java.sql.Array;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;

/**
 *
 * @author SOPORTESS
 */
public class GeneradorPdf_General {
    
      JFileChooser file = new JFileChooser();
//    String rutaguardar = "C:\\Users\\USER\\Documents\\";
            String nombrepdf = "C:/Users/OLIPC/Desktop/Documents/aa.pdf";
//        String nombrepdf = "C:/Users/mr-robot/Documents/DOCUMENTO/blog.pdf";

    private Font FuenteBold = new Font(Font.FontFamily.UNDEFINED, 7, Font.UNDERLINE);
    private Font Fuentenegrita = new Font(Font.FontFamily.UNDEFINED, 8, Font.BOLD);
    private Font FuenteNormal = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);
    private Font FuenteItalic = new Font(Font.FontFamily.COURIER, 7, Font.ITALIC);

    public void GeneradorPdf_General(
            String fechaActual,
            String fechaDMA, 
            String totalAdultos, 
            String Totalinfantil, 
            String GananciaTotal,
            String salida) {
        try {
            //tamanio de la hoja -margenes - top -- footer
            Document document = new Document(PageSize.A7, 20, 20, -10, 0);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();

            document.add(getnegrita("Humedal - Huasao"));
            document.add(getInfo("Reporte de ventas")); 
            
            document.add(getfecha(fechaActual));

            
//        document.add(new Paragraph(Chunk.NEWLINE)); 
//-----------------------------*
            Font negrita = new Font(FontFamily.UNDEFINED, 7, Font.BOLD, BaseColor.BLACK);

            Font blue = new Font(FontFamily.UNDEFINED, 7, Font.NORMAL, BaseColor.BLACK);
         
//          document.add(new Paragraph(Chunk.NEWLINE));

            Font normal = new Font(FontFamily.UNDEFINED, 7, Font.NORMAL, BaseColor.BLACK);
            Chunk greenText = new Chunk("REPORTES DE : ", negrita);
            Chunk dni = new Chunk(fechaDMA + "\n", normal);
            //fecha entre dos
           
            
             Chunk d12 = new Chunk("CANT. DE VISITANTES - NIÑOS : ", negrita);
            Chunk dprope = new Chunk(Totalinfantil+ "\n", normal);

            Chunk d14 = new Chunk("CANT. DE VISITANTES - ADULTOS : ", negrita);
            Chunk p11 = new Chunk(totalAdultos + "\n", normal);
            
            Chunk p12 = new Chunk("GANANCIA TOTAL : ", negrita);
            Chunk p13 = new Chunk(GananciaTotal + "\n", normal);

            Paragraph p2 = new Paragraph();
            p2.setAlignment(Element.ALIGN_JUSTIFIED);

          
            p2.add(greenText);
            p2.add(dni);
            p2.add(d12);
            p2.add(dprope);
            p2.add(d14);
            p2.add(p11);
            p2.add(p12);
            p2.add(p13);

            document.add(p2);

            document.add(getFooter("cuscocode.com"));

            document.close();
        } catch (Exception e) {
        }
    }

    private Paragraph getnegrita(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(Fuentenegrita);
        p.add(c);
        return p;
    }

    private Paragraph getInfo(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(Fuentenegrita);
        p.add(c);
        return p;
    }
    
     private Paragraph getfecha(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(Fuentenegrita);
        p.add(c);
        return p;
    }

    private Paragraph getFooter(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(Fuentenegrita);
        p.add(c);
        return p;
    }

    private Paragraph[] getne(String texto, String txt) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(texto.replaceAll("\\n\\r", ""));
        c.setFont(FuenteNormal);
        p.add(c);

        Paragraph p2 = new Paragraph();
        Chunk c2 = new Chunk();
        p2.setAlignment(Element.ALIGN_JUSTIFIED);
        c2.append(txt);
        c2.setFont(Fuentenegrita);
        p2.add(c2);

//    double[] serie=new double[n];
        Paragraph d[] = {p, p2};

        return d;

    }

}
