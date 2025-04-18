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

/**
 *
 * @author SOPORTESS
 */
public class GeneradorPdf {

    private Font FuenteBold = new Font(Font.FontFamily.UNDEFINED, 7, Font.UNDERLINE);
    private Font Fuentenegrita = new Font(Font.FontFamily.UNDEFINED, 7, Font.BOLD);
    private Font FuenteNormal = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);
    private Font FuenteItalic = new Font(Font.FontFamily.COURIER, 7, Font.ITALIC);

    public void generadorPDF1(
            String nombre_del_negocio,
            //DATOS DE LOS PARTICIPANTES
            String nombre_entidad,
            String fecha,
            String hora,
            String codigo,
            String boleteria,
            String operador,
            String visitante,
            //datos del precio y cantidad
            String cantidad_adulto,
            String cantidad_infantil,
            String precio_total_adulto,
            String precio_total_infantil,
            String nro_visitantes,
            String precio_total,
            String salida,
            String ruta_img) {
        try {
            //tamanio de la hoja -margenes - top -- footer
            Document document = new Document(PageSize.A7, 20, 20, -10, 0);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();

//         Image imagen = Image.getInstance(ruta_img);
//         
//          imagen.scaleAbsoluteWidth(80f);
//          imagen.scaleAbsoluteHeight(80f);
//          imagen.setAlignment(55);
//          
//          document.add(imagen);
            document.add(getnegrita(nombre_entidad));
            document.add(getInfo(nombre_del_negocio));
//        document.add(new Paragraph(Chunk.NEWLINE)); 

//        document.add(new Paragraph(Chunk.NEWLINE)); 
//-----------------------------*
            Font negrita = new Font(FontFamily.UNDEFINED, 7, Font.BOLD, BaseColor.BLACK);
            Chunk redText = new Chunk("FECHA : ", negrita);

            Font blue = new Font(FontFamily.UNDEFINED, 7, Font.NORMAL, BaseColor.BLACK);
            Chunk blueText = new Chunk(fecha + "      ", blue);
//            document.add(new Paragraph(Chunk.NEWLINE));

            Font normal = new Font(FontFamily.UNDEFINED, 7, Font.NORMAL, BaseColor.BLACK);
            Chunk greenText = new Chunk("HORA : ", negrita);
            Chunk dni = new Chunk(hora + "\n", normal);

            Chunk dnic = new Chunk("CODIGO :", negrita);
            Chunk d = new Chunk(codigo + "\n", normal);

            Chunk d2 = new Chunk("BOLETERIA : ", negrita);
            Chunk d3 = new Chunk(boleteria + "\n", normal);

            Chunk darren = new Chunk("OPERADOR : ", negrita);
            Chunk d5 = new Chunk(operador + "\n", normal);

            Chunk d6 = new Chunk("CONCEPTO       CANTIDAD         TOTAL  \n", negrita);
            Chunk d8 = new Chunk("ADULTO :              ", negrita);
            Chunk d10 = new Chunk(cantidad_adulto + "                       " + precio_total_adulto + "\n", normal
            );
             Chunk d12 = new Chunk("NIÑO :                    ", negrita);
            Chunk dprope = new Chunk(cantidad_infantil + "                       " + precio_total_infantil + "\n", normal);

            Chunk d14 = new Chunk("NRO DE VISITANTES : ", negrita);

            Chunk p11 = new Chunk(nro_visitantes + "      ", normal);
            Chunk p12 = new Chunk("TOTAL : S / ", negrita);
            Chunk p13 = new Chunk(precio_total + "\n", negrita);

            Paragraph p2 = new Paragraph();
            p2.setAlignment(Element.ALIGN_JUSTIFIED);

            p2.add(redText);
            p2.add(blueText);
            p2.add(greenText);
            p2.add(dni);

            p2.add(dnic);
            p2.add(d);
//            p2.add(d2);
//            p2.add(d3);
            p2.add(darren);
            p2.add(d5);
            p2.add(d6);
            p2.add(d8);
            p2.add(d10);
            p2.add(d12);
            p2.add(dprope);
            p2.add(d14);

//            document.add(new Paragraph(Chunk.NEWLINE)); 
//            document.add(getnegrita("GGGGGGGGGGGGGGGGGG"));
            p2.add(p11);
            p2.add(p12);
            p2.add(p13);

            document.add(p2);

            document.add(getFooter("cuscocode.com"));

            document.close();
        } catch (Exception e) {

        }

    }
//    private Paragraph  getHeader(String texto)  {
//    Paragraph p =new Paragraph();
//    Chunk c=new Chunk();
//    p.setAlignment(Element.ALIGN_CENTER);
//    c.append(texto);
//    c.setFont(Fuentenegrita);
//    p.add(c);
//    return p;
//    }

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
