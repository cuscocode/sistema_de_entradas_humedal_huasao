/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

import CONTROLADOR_3.c_cliente;
import CONTROLADOR_3.c_informacion;
import CONTROLADOR_3.c_precio;
import CONTROLADOR_3.c_ventas;
import Componetes.GeneradorPdf;
import Componetes.Mensaje_Dialogo2;

import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;
import javax.swing.table.DefaultTableModel;

import Componetes.ManejoDeImagenes;
import Componetes.datos_usuario;
import com.itextpdf.text.pdf.PdfDocument;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author Elber Climaco
 */
public class pnl_ventas2 extends javax.swing.JPanel {

    DefaultTableModel modeloPreciosSeleccionados;

    DefaultTableModel modeloC = new DefaultTableModel();
    c_cliente omCliente = new c_cliente();

    DefaultTableModel modeloV;
    c_ventas omVentas = new c_ventas();

    DefaultTableModel modeloI;
    c_informacion omInfoGeneral = new c_informacion();

    DefaultTableModel modeloP;
    c_precio omPrecio = new c_precio();

    private ImageIcon imageicono;

    // ruta publica del pdf
    public static String direcionRuta = "";
    public static String pesopdf = "";
    public static String gmailEnvio = "";
    public File filedirec = new File("");
//      
    String validpdf = null;

    int valor = 0;

    JFileChooser file = new JFileChooser();
    String rutaguardar = "C:\\Users\\USER\\Documents\\";

    public pnl_ventas2() {
        initComponents();
        imageicono = new ImageIcon(this.getClass().getResource("/appapagar/image/logo1.png"));

        llenar_combo();
        llenar_campos("");
        listar_cliente("");

        rb_externo.setSelected(true);
        listarPrecios("");
    }

    public void llenar_combo() {
        try {
              for (int i = 0; i <= 10; i++) {
            cbo_adulto.addItem(String.valueOf(i));
            cbo_infantil.addItem(String.valueOf(i));

        }
        } catch (Exception e) {
        }
    }

    void llenar_campos(String dato) {
        try {
            modeloI = omInfoGeneral.ListarInformacion(dato);
            if (modeloI.getRowCount() > 0) {
                lb_nombreEntidad.setText(modeloI.getValueAt(0, 1).toString());
                lb_nombre_nogocio.setText(modeloI.getValueAt(0, 4).toString());
                lb_caja.setText(modeloI.getValueAt(0, 6).toString());

                lbl_nombre_negocio2.setText(modeloI.getValueAt(0, 1).toString());
                lbl_nombre_entidad2.setText(modeloI.getValueAt(0, 4).toString());
                lbl_boleteria.setText(modeloI.getValueAt(0, 6).toString());

                LocalTime horaActual = LocalTime.now();
                LocalDate fecha = LocalDate.now();

                lb_fecha.setText(fecha.toString());
                lb_hora.setText(horaActual.toString());
            } else {
                JOptionPane.showMessageDialog(null, "no se encontro la informacion de la empresa. registre la informacion de la empresa");
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al listar imformacion general");
        }
    }

    void listar_cliente(String dato) {
        try {
            modeloC = omCliente.ListarCliente(dato);
            //  tabla.setModel(modeloC);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar visitante");
        }
    }
//        double precioA=0, precio=0;

    void listarPrecios(String dato) {
        try {
            
            String[] titulos = {"id Precio", "precio adulto", "precio imfantil"};
            modeloPreciosSeleccionados = new DefaultTableModel(null, titulos);
            for (int i = 0; i < modeloPreciosSeleccionados.getRowCount(); i++) {
                modeloPreciosSeleccionados.removeRow(0);
            }
            cbo_precio.removeAllItems();
            modeloP = omPrecio.ListarPrecio(dato);

            if (modeloP.getRowCount() > 0) {
                if (rb_otro.isSelected()) {
                    for (int i = 0; i < modeloP.getRowCount(); i++) {
                        cbo_precio.addItem("PRECIO DE ADULTO: " + modeloP.getValueAt(i, 1) + "          PRECIO DE NIÑO: " + modeloP.getValueAt(i, 2));
                        String fila[] = {modeloP.getValueAt(i, 0).toString(), modeloP.getValueAt(i, 1).toString(), modeloP.getValueAt(i, 2).toString()};
                        modeloPreciosSeleccionados.addRow(fila);
                    }
                }

                if (rb_local.isSelected()) {
                    for (int i = 0; i < modeloP.getRowCount(); i++) {
                        if ("local".equalsIgnoreCase(modeloP.getValueAt(i, 3).toString())) {
                            cbo_precio.addItem("PRECIO DE ADULTO: " + modeloP.getValueAt(i, 1) + "          PRECIO DE NIÑO: " + modeloP.getValueAt(i, 2));
                            String fila[] = {modeloP.getValueAt(i, 0).toString(), modeloP.getValueAt(i, 1).toString(), modeloP.getValueAt(i, 2).toString()};
                            modeloPreciosSeleccionados.addRow(fila);

                        }
                    }
                }

                if (rb_externo.isSelected()) {
                    for (int i = 0; i < modeloP.getRowCount(); i++) {
                        if ("externo".equalsIgnoreCase(modeloP.getValueAt(i, 3).toString())) {
                            cbo_precio.addItem("PRECIO DE ADULTO: " + modeloP.getValueAt(i, 1) + "          PRECIO DE NIÑO: " + modeloP.getValueAt(i, 2));
                            String fila[] = {modeloP.getValueAt(i, 0).toString(), modeloP.getValueAt(i, 1).toString(), modeloP.getValueAt(i, 2).toString()};
                            modeloPreciosSeleccionados.addRow(fila);
                        }
                    }
                }

                if (rb_extranjero.isSelected()) {
                    for (int i = 0; i < modeloP.getRowCount(); i++) {
                        if ("extranjero".equalsIgnoreCase(modeloP.getValueAt(i, 3).toString())) {
                            cbo_precio.addItem("PRECIO DE ADULTO: " + modeloP.getValueAt(i, 1) + "          PRECIO DE NIÑO: " + modeloP.getValueAt(i, 2));
                            String fila[] = {modeloP.getValueAt(i, 0).toString(), modeloP.getValueAt(i, 1).toString(), modeloP.getValueAt(i, 2).toString()};
                            modeloPreciosSeleccionados.addRow(fila);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "no se encontraron los precios. registre los precios.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar los precios");
            System.out.println("error " + e);
        }
    }

    /* void recuperar_cliente() {
        int select = tabla.getSelectedRow();
        lb_id_cliente.setText(modeloC.getValueAt(select, 0).toString());
        lb_datos_cliente.setText(modeloC.getValueAt(select, 1).toString() + " " + modeloC.getValueAt(select, 0).toString());

    }*/
    void listar_venta(String dato) {
        try {
            modeloV = omVentas.ListarVenta(dato);
            //   tabla.setModel(modeloC);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar visitante");
        }
    }

    void insertarVenta() {
        try {
            llenar_campos("");
            if (radioButon.getSelection() == null || cbo_adulto.getSelectedItem().equals("0") && cbo_infantil.getSelectedItem().equals("0")) {

                JOptionPane.showMessageDialog(null, "complete los campos");
            } else {
                listarPrecios("");
                llenar_campos("");
                modeloP = omPrecio.ListarPrecio("");
                modeloI = omInfoGeneral.ListarInformacion("");

                String tipoVisitante = "";
                if (rb_otro.isSelected()) {
                    tipoVisitante = "Local";
                }
                if (rb_externo.isSelected()) {
                    tipoVisitante = "Externo";
                }
                if (rb_extranjero.isSelected()) {
                    tipoVisitante = "Extranjero";
                }

                omVentas.insertarVenta(Integer.parseInt(cbo_adulto.getSelectedItem().toString()), Integer.parseInt(cbo_infantil.getSelectedItem().toString()),
                        txt_dni.getText(), tipoVisitante, datos_usuario.getId_usuario(),
                        modeloI.getValueAt(0, 0).toString(), modeloPreciosSeleccionados.getValueAt(cbo_precio.getSelectedIndex(), 0).toString());

                mostrar_boleta();
                imprimirDirecto();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al insertar venta");
        }
    }

    public void mostrar_boleta() {
        try {

            //nombre negocio , nombre de la entidad, 
            modeloV = omVentas.ListarVenta("");
            int fila = modeloV.getRowCount() - 1;

            lb_codigo_Venta.setText(modeloV.getValueAt(fila, 0).toString());
            lb_tipo_visitante.setText(modeloV.getValueAt(fila, 9).toString());
            lb_cantida_adultos.setText(modeloV.getValueAt(fila, 1).toString());
            lb_cantidad_niños.setText(modeloV.getValueAt(fila, 2).toString());
            lb_total_visitantes.setText(modeloV.getValueAt(fila, 4).toString());
            lb_total_precios.setText(modeloV.getValueAt(fila, 5).toString());
            lb_operador.setText(modeloV.getValueAt(fila, 7).toString());

            Double precioTotalAdulto = Double.parseDouble(modeloV.getValueAt(fila, 1).toString()) * Double.parseDouble(modeloV.getValueAt(fila, 12).toString());
            Double precioTotalNiño = Double.parseDouble(modeloV.getValueAt(fila, 2).toString()) * Double.parseDouble(modeloV.getValueAt(fila, 13).toString());
            lb_precio_adultos.setText(precioTotalAdulto.toString());
            lb_precio_niños.setText(precioTotalNiño.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al mostrar datos en la boleta");
        }

    }

    public void guardarpdf(String ruta) {
        try {
            
        GeneradorPdf g = new GeneradorPdf();

        String correo = txtcorreo_enviar.getText();

        //regresion de datos
        gmailEnvio = correo;

        //Declaracion de datos que va atener el pdf
        String nombre_del_negocio = "";
        String nombre_entidad = "";
        String fecha = "";
        String hora = "";
        String codigo = "";
        String boleteria = "";
        String operador = "";
        String visitante = "";
        String cantidad_adulto = "";
        String cantidad_infantil = "";
        String precio_total_adulto = "";
        String precio_total_infantil = "";
        String nro_visitantes = "";
        String precio_total = "";
        String ruta_img = "";

        nombre_del_negocio = lbl_nombre_negocio2.getText();
        nombre_entidad = lbl_nombre_entidad2.getText();
        fecha = lb_fecha.getText();
        hora = lb_hora.getText();
        codigo = lb_codigo_Venta.getText();
        boleteria = lb_caja.getText();
        operador = lb_operador.getText();
        visitante = lb_tipo_visitante.getText();
        cantidad_adulto = lb_cantida_adultos.getText();
        cantidad_infantil = lb_cantidad_niños.getText();
        precio_total_adulto = lb_precio_adultos.getText();
        precio_total_infantil = lb_precio_niños.getText();
        nro_visitantes = lb_total_visitantes.getText();
        precio_total = lb_total_precios.getText();
        ruta_img = panel_img.getIcon().toString();
//                  panel_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/humedal_2.png")));

        g.generadorPDF1(
                nombre_del_negocio,
                nombre_entidad,
                fecha,
                hora,
                codigo,
                boleteria,
                operador,
                visitante,
                //cantidad
                cantidad_adulto,
                cantidad_infantil,
                precio_total_adulto,
                precio_total_infantil,
                nro_visitantes,
                precio_total,
                ruta, ruta_img);
        } catch (Exception e) {
        }
    }

    void mostrar_monto_cobrar() {

        try {
            listarPrecios("");
            double precioAdulto = Double.parseDouble(modeloPreciosSeleccionados.getValueAt(cbo_precio.getSelectedIndex(), 1).toString());
            double precioInfantil = Double.parseDouble(modeloPreciosSeleccionados.getValueAt(cbo_precio.getSelectedIndex(), 2).toString());

            double totalA = cbo_adulto.getSelectedIndex() * precioAdulto;
            double totalI = cbo_infantil.getSelectedIndex() * precioInfantil;

            lbl_total_cobrar.setText(totalA + totalI + "");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButon = new javax.swing.ButtonGroup();
        lbl_boleteria = new javax.swing.JLabel();
        btn_usuario2 = new rojerusan.RSMaterialButtonRound();
        txt_dni = new rojeru_san.RSMTextFull();
        lcodigo30 = new javax.swing.JLabel();
        lcodigo11 = new javax.swing.JLabel();
        lcodigo32 = new javax.swing.JLabel();
        lcodigo33 = new javax.swing.JLabel();
        lcodigo35 = new javax.swing.JLabel();
        cbo_infantil = new rojerusan.RSComboMetro();
        cbo_precio = new rojerusan.RSComboMetro();
        jPanel1 = new javax.swing.JPanel();
        lblImagen = new rojerusan.RSPanelCircleImage();
        lb_nombre_nogocio = new javax.swing.JLabel();
        lbl_nombre_negocio = new javax.swing.JLabel();
        lb_nombreEntidad = new javax.swing.JLabel();
        lcodigo26 = new javax.swing.JLabel();
        lbl_fecha1 = new rojeru_san.RSLabelFecha();
        lcodigo18 = new javax.swing.JLabel();
        lbl_hora = new javax.swing.JLabel();
        lblImagen1 = new rojerusan.RSPanelCircleImage();
        lbl_nombre_negocio1 = new javax.swing.JLabel();
        rSPanelGradiente3 = new rspanelgradiente.RSPanelGradiente();
        lb_total_precios = new javax.swing.JLabel();
        lblurl = new javax.swing.JLabel();
        lbl_nombre_negocio2 = new javax.swing.JLabel();
        panel_img = new jcMousePanel.jcMousePanel();
        lcodigo7 = new javax.swing.JLabel();
        lb_hora = new javax.swing.JLabel();
        lcodigo10 = new javax.swing.JLabel();
        lnlinfa = new javax.swing.JLabel();
        lcodigo13 = new javax.swing.JLabel();
        lcodigo14 = new javax.swing.JLabel();
        lb_precio_niños = new javax.swing.JLabel();
        lcodigo12 = new javax.swing.JLabel();
        lcodigo17 = new javax.swing.JLabel();
        lb_tipo_visitante = new javax.swing.JLabel();
        lb_fecha = new javax.swing.JLabel();
        lb_codigo_Venta = new javax.swing.JLabel();
        lb_caja = new javax.swing.JLabel();
        lb_operador = new javax.swing.JLabel();
        lcodigo23 = new javax.swing.JLabel();
        lb_cantidad_niños = new javax.swing.JLabel();
        lb_precio_adultos = new javax.swing.JLabel();
        lb_cantida_adultos = new javax.swing.JLabel();
        lcodigo29 = new javax.swing.JLabel();
        lblpass1 = new javax.swing.JLabel();
        lb_total_visitantes = new javax.swing.JLabel();
        lblpass3 = new javax.swing.JLabel();
        lbl_nombre_entidad2 = new javax.swing.JLabel();
        lcodigo31 = new javax.swing.JLabel();
        lcodigo36 = new javax.swing.JLabel();
        lbl_cod = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_guardar = new rojeru_san.complementos.RSButtonHover();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_enviar_correo = new rojeru_san.complementos.RSButtonHover();
        txtcorreo_enviar = new rojerusan.RSMetroTextFullPlaceHolder();
        rb_otro = new check.RB();
        rb_extranjero = new check.RB();
        lcodigo38 = new javax.swing.JLabel();
        cbo_adulto = new rojerusan.RSComboMetro();
        rb_externo = new check.RB();
        jPanel3 = new javax.swing.JPanel();
        lcodigo34 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbl_infantill = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbl_total_cobrar = new javax.swing.JLabel();
        lcodigo39 = new javax.swing.JLabel();
        rb_local = new check.RB();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(134, 156, 167)));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_boleteria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_boleteria.setForeground(new java.awt.Color(70, 187, 151));
        lbl_boleteria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_boleteria.setText("01");
        add(lbl_boleteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 190, 20));

        btn_usuario2.setBackground(new java.awt.Color(70, 187, 151));
        btn_usuario2.setBorder(null);
        btn_usuario2.setText("IMPRIMIR");
        btn_usuario2.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        btn_usuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario2ActionPerformed(evt);
            }
        });
        add(btn_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 480, 290, 50));

        txt_dni.setBackground(new java.awt.Color(46, 78, 114));
        txt_dni.setForeground(new java.awt.Color(70, 187, 151));
        txt_dni.setBordeColorFocus(new java.awt.Color(70, 187, 151));
        txt_dni.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_dni.setCaretColor(new java.awt.Color(70, 187, 151));
        txt_dni.setColorTransparente(true);
        txt_dni.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_dni.setPlaceholder(" *");
        txt_dni.setSoloNumeros(true);
        txt_dni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_dniFocusLost(evt);
            }
        });
        txt_dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dniActionPerformed(evt);
            }
        });
        txt_dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dniKeyTyped(evt);
            }
        });
        add(txt_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 250, 37));

        lcodigo30.setBackground(new java.awt.Color(102, 102, 102));
        lcodigo30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo30.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo30.setText("NUMERO :");
        add(lcodigo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 130, 20));

        lcodigo11.setBackground(new java.awt.Color(102, 102, 102));
        lcodigo11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo11.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo11.setText("BOLETERIA :");
        add(lcodigo11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 140, 20));

        lcodigo32.setBackground(new java.awt.Color(102, 102, 102));
        lcodigo32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo32.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo32.setText("VISITANTE :");
        add(lcodigo32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 140, 20));

        lcodigo33.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 18)); // NOI18N
        lcodigo33.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo33.setText("NUMERO DE VISITANTES ");
        add(lcodigo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 260, 20));

        lcodigo35.setBackground(new java.awt.Color(102, 102, 102));
        lcodigo35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo35.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo35.setText("NUMERO DE DNI :");
        add(lcodigo35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 180, 20));

        cbo_infantil.setBackground(new java.awt.Color(204, 204, 204));
        cbo_infantil.setForeground(new java.awt.Color(134, 156, 167));
        cbo_infantil.setColorArrow(new java.awt.Color(70, 187, 151));
        cbo_infantil.setColorBorde(new java.awt.Color(255, 255, 255));
        cbo_infantil.setColorFondo(new java.awt.Color(207, 216, 220));
        cbo_infantil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_infantilItemStateChanged(evt);
            }
        });
        cbo_infantil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_infantilActionPerformed(evt);
            }
        });
        add(cbo_infantil, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 220, -1));

        cbo_precio.setBackground(new java.awt.Color(204, 204, 204));
        cbo_precio.setForeground(new java.awt.Color(102, 102, 102));
        cbo_precio.setColorArrow(new java.awt.Color(255, 255, 255));
        cbo_precio.setColorBorde(new java.awt.Color(255, 255, 255));
        cbo_precio.setColorFondo(new java.awt.Color(207, 216, 220));
        add(cbo_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 330, -1));

        jPanel1.setBackground(new java.awt.Color(70, 187, 151));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagen.setColorBorde(new java.awt.Color(70, 187, 151));
        lblImagen.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/LogoOropesa.png"))); // NOI18N
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenMouseClicked(evt);
            }
        });
        lblImagen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 110, 110));

        lb_nombre_nogocio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_nombre_nogocio.setForeground(new java.awt.Color(255, 255, 255));
        lb_nombre_nogocio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nombre_nogocio.setText("DESCRIPCCION DEL LUGAR");
        jPanel1.add(lb_nombre_nogocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 380, 20));

        lbl_nombre_negocio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nombre_negocio.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombre_negocio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nombre_negocio.setText("SOFWARE 1.0");
        jPanel1.add(lbl_nombre_negocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 270, 50));

        lb_nombreEntidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_nombreEntidad.setForeground(new java.awt.Color(255, 255, 255));
        lb_nombreEntidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nombreEntidad.setText("Nobre de la Entidad");
        jPanel1.add(lb_nombreEntidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 380, 40));

        lcodigo26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo26.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo26.setText("FECHA :");
        jPanel1.add(lcodigo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 70, 20));

        lbl_fecha1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 100, 20));

        lcodigo18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo18.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo18.setText("HORA:");
        jPanel1.add(lcodigo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 50, 20));

        lbl_hora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_hora.setForeground(new java.awt.Color(255, 255, 255));
        lbl_hora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_hora.setText("11:23 p. m.");
        jPanel1.add(lbl_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 80, 20));

        lblImagen1.setColorBorde(new java.awt.Color(70, 187, 151));
        lblImagen1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/logo1.png"))); // NOI18N
        lblImagen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen1MouseClicked(evt);
            }
        });
        lblImagen1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(lblImagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 110, 110));

        lbl_nombre_negocio1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nombre_negocio1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombre_negocio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nombre_negocio1.setText("Municipalidad de Oropesa");
        jPanel1.add(lbl_nombre_negocio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 250, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, -1));

        rSPanelGradiente3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 78, 114)));
        rSPanelGradiente3.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente3.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_total_precios.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_total_precios.setForeground(new java.awt.Color(46, 78, 114));
        lb_total_precios.setText("00.00");
        rSPanelGradiente3.add(lb_total_precios, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 100, -1));

        lblurl.setBackground(new java.awt.Color(255, 255, 255));
        lblurl.setForeground(new java.awt.Color(46, 78, 114));
        lblurl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblurl.setText("cuscocode.com");
        lblurl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblurl.setName(""); // NOI18N
        lblurl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblurlMouseClicked(evt);
            }
        });
        rSPanelGradiente3.add(lblurl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 370, 20));

        lbl_nombre_negocio2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_nombre_negocio2.setForeground(new java.awt.Color(46, 78, 114));
        lbl_nombre_negocio2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nombre_negocio2.setText("Nombre del negocio");
        rSPanelGradiente3.add(lbl_nombre_negocio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 370, 30));

        panel_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/humedal_4.png"))); // NOI18N

        javax.swing.GroupLayout panel_imgLayout = new javax.swing.GroupLayout(panel_img);
        panel_img.setLayout(panel_imgLayout);
        panel_imgLayout.setHorizontalGroup(
            panel_imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        panel_imgLayout.setVerticalGroup(
            panel_imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        rSPanelGradiente3.add(panel_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10, 230, 190));

        lcodigo7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo7.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo7.setText("CANT");
        rSPanelGradiente3.add(lcodigo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 70, 20));

        lb_hora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_hora.setForeground(new java.awt.Color(46, 78, 114));
        lb_hora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_hora.setText("05:00 PM");
        rSPanelGradiente3.add(lb_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 100, 20));

        lcodigo10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo10.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo10.setText("COD :");
        rSPanelGradiente3.add(lcodigo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 70, 20));

        lnlinfa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lnlinfa.setForeground(new java.awt.Color(46, 78, 114));
        lnlinfa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lnlinfa.setText("NIÑO");
        rSPanelGradiente3.add(lnlinfa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 100, 20));

        lcodigo13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo13.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo13.setText("VISITANTE :");
        rSPanelGradiente3.add(lcodigo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 100, 20));

        lcodigo14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo14.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo14.setText("OPERADOR :");
        rSPanelGradiente3.add(lcodigo14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 100, 20));

        lb_precio_niños.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_precio_niños.setForeground(new java.awt.Color(46, 78, 114));
        lb_precio_niños.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_precio_niños.setText("0.00 ");
        rSPanelGradiente3.add(lb_precio_niños, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 100, 20));

        lcodigo12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo12.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo12.setText("CONCEPTO");
        rSPanelGradiente3.add(lcodigo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 90, 20));

        lcodigo17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo17.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo17.setText("FECHA :");
        rSPanelGradiente3.add(lcodigo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 70, 20));

        lb_tipo_visitante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_tipo_visitante.setForeground(new java.awt.Color(46, 78, 114));
        lb_tipo_visitante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_tipo_visitante.setText("EXTERNO / INTERNO");
        rSPanelGradiente3.add(lb_tipo_visitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 250, 20));

        lb_fecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_fecha.setForeground(new java.awt.Color(46, 78, 114));
        lb_fecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_fecha.setText("00-00-0000");
        rSPanelGradiente3.add(lb_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 100, 20));

        lb_codigo_Venta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_codigo_Venta.setForeground(new java.awt.Color(46, 78, 114));
        lb_codigo_Venta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_codigo_Venta.setText("REGIS32323");
        rSPanelGradiente3.add(lb_codigo_Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 100, 20));

        lb_caja.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_caja.setForeground(new java.awt.Color(46, 78, 114));
        lb_caja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_caja.setText("00");
        rSPanelGradiente3.add(lb_caja, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 100, 20));

        lb_operador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_operador.setForeground(new java.awt.Color(46, 78, 114));
        lb_operador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_operador.setText("SUB GERENCIA DE DESARROLLO");
        rSPanelGradiente3.add(lb_operador, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 250, 20));

        lcodigo23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo23.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo23.setText("ADULTO ");
        rSPanelGradiente3.add(lcodigo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 100, 20));

        lb_cantidad_niños.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_cantidad_niños.setForeground(new java.awt.Color(46, 78, 114));
        lb_cantidad_niños.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_cantidad_niños.setText("00");
        rSPanelGradiente3.add(lb_cantidad_niños, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 100, 20));

        lb_precio_adultos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_precio_adultos.setForeground(new java.awt.Color(46, 78, 114));
        lb_precio_adultos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_precio_adultos.setText("0.00");
        rSPanelGradiente3.add(lb_precio_adultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 100, 20));

        lb_cantida_adultos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_cantida_adultos.setForeground(new java.awt.Color(46, 78, 114));
        lb_cantida_adultos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_cantida_adultos.setText("00");
        rSPanelGradiente3.add(lb_cantida_adultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 100, 20));

        lcodigo29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo29.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo29.setText("TOTAL");
        rSPanelGradiente3.add(lcodigo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 70, 20));

        lblpass1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblpass1.setForeground(new java.awt.Color(46, 78, 114));
        lblpass1.setText("NRO DE VISITANTES :");
        rSPanelGradiente3.add(lblpass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 280, -1));

        lb_total_visitantes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_total_visitantes.setForeground(new java.awt.Color(46, 78, 114));
        lb_total_visitantes.setText("00");
        rSPanelGradiente3.add(lb_total_visitantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 40, -1));

        lblpass3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblpass3.setForeground(new java.awt.Color(46, 78, 114));
        lblpass3.setText("TOTAL: S/");
        rSPanelGradiente3.add(lblpass3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 130, -1));

        lbl_nombre_entidad2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nombre_entidad2.setForeground(new java.awt.Color(46, 78, 114));
        lbl_nombre_entidad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nombre_entidad2.setText("Nobre de la Entidad");
        rSPanelGradiente3.add(lbl_nombre_entidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 370, 40));

        lcodigo31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo31.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo31.setText("BOLETERIA :");
        rSPanelGradiente3.add(lcodigo31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 100, 20));

        lcodigo36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo36.setForeground(new java.awt.Color(46, 78, 114));
        lcodigo36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo36.setText("HORA:");
        rSPanelGradiente3.add(lcodigo36, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 50, 20));

        add(rSPanelGradiente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 370, 580));

        lbl_cod.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_cod.setForeground(new java.awt.Color(70, 187, 151));
        lbl_cod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_cod.setText("REGIS32323");
        add(lbl_cod, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 190, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_print_24px_1.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, -1, 30));

        btn_guardar.setBackground(new java.awt.Color(255, 255, 255));
        btn_guardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 216, 220)));
        btn_guardar.setForeground(new java.awt.Color(207, 216, 220));
        btn_guardar.setText("GUARDAR");
        btn_guardar.setColorHover(new java.awt.Color(70, 187, 151));
        btn_guardar.setColorText(new java.awt.Color(134, 156, 167));
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 260, 46));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 216, 220)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_new_message_48px.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 50, 50));

        btn_enviar_correo.setBackground(new java.awt.Color(70, 187, 151));
        btn_enviar_correo.setText("ENVIAR");
        btn_enviar_correo.setColorHover(new java.awt.Color(134, 156, 167));
        btn_enviar_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviar_correoActionPerformed(evt);
            }
        });
        jPanel2.add(btn_enviar_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 3, 100, -1));

        txtcorreo_enviar.setForeground(new java.awt.Color(134, 156, 167));
        txtcorreo_enviar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtcorreo_enviar.setBorderColor(new java.awt.Color(255, 255, 255));
        txtcorreo_enviar.setBotonColor(new java.awt.Color(70, 187, 151));
        txtcorreo_enviar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcorreo_enviar.setPhColor(new java.awt.Color(199, 211, 220));
        txtcorreo_enviar.setPlaceholder("@gmail.com");
        jPanel2.add(txtcorreo_enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 2, 310, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 480, 46));

        radioButon.add(rb_otro);
        rb_otro.setForeground(new java.awt.Color(70, 187, 151));
        rb_otro.setText("Otro");
        rb_otro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_otro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_otroActionPerformed(evt);
            }
        });
        add(rb_otro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, -1, -1));

        radioButon.add(rb_extranjero);
        rb_extranjero.setForeground(new java.awt.Color(70, 187, 151));
        rb_extranjero.setText("Extranjero");
        rb_extranjero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_extranjero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_extranjeroActionPerformed(evt);
            }
        });
        add(rb_extranjero, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        lcodigo38.setBackground(new java.awt.Color(102, 102, 102));
        lcodigo38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo38.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo38.setText("Precio S/ :");
        add(lcodigo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 170, 30));

        cbo_adulto.setBackground(new java.awt.Color(204, 204, 204));
        cbo_adulto.setForeground(new java.awt.Color(134, 156, 167));
        cbo_adulto.setColorArrow(new java.awt.Color(70, 187, 151));
        cbo_adulto.setColorBorde(new java.awt.Color(255, 255, 255));
        cbo_adulto.setColorFondo(new java.awt.Color(207, 216, 220));
        cbo_adulto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_adultoItemStateChanged(evt);
            }
        });
        cbo_adulto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_adultoActionPerformed(evt);
            }
        });
        add(cbo_adulto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 220, -1));

        radioButon.add(rb_externo);
        rb_externo.setForeground(new java.awt.Color(70, 187, 151));
        rb_externo.setText("Externo");
        rb_externo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_externo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_externoActionPerformed(evt);
            }
        });
        add(rb_externo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        jPanel3.setBackground(new java.awt.Color(227, 94, 106));

        lcodigo34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo34.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo34.setText("ADULTO :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lcodigo34, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lcodigo34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 220, 30));

        jPanel4.setBackground(new java.awt.Color(247, 204, 51));

        lbl_infantill.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_infantill.setForeground(new java.awt.Color(255, 255, 255));
        lbl_infantill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_infantill.setText("NIÑO :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_infantill, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_infantill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 220, 30));

        jPanel5.setBackground(new java.awt.Color(227, 94, 106));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_total_cobrar.setBackground(new java.awt.Color(102, 102, 102));
        lbl_total_cobrar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbl_total_cobrar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_total_cobrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_total_cobrar.setText("0.00");
        jPanel5.add(lbl_total_cobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, 50));

        lcodigo39.setBackground(new java.awt.Color(102, 102, 102));
        lcodigo39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo39.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo39.setText("MONTO A COBRAR");
        jPanel5.add(lcodigo39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 40));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 200, 90));

        radioButon.add(rb_local);
        rb_local.setForeground(new java.awt.Color(70, 187, 151));
        rb_local.setText("Local");
        rb_local.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_localActionPerformed(evt);
            }
        });
        add(rb_local, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseDragged

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void btn_usuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario2ActionPerformed

        insertarVenta();
//        imprimirDirecto();

    }//GEN-LAST:event_btn_usuario2ActionPerformed

    private void txt_dniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_dniFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dniFocusLost

    private void txt_dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dniActionPerformed

    private void txt_dniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyPressed

    }//GEN-LAST:event_txt_dniKeyPressed

    private void txt_dniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_dni.getText(), 8);
    }//GEN-LAST:event_txt_dniKeyTyped

    private void rB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rB1ActionPerformed

    private void lblImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenMouseClicked


    }//GEN-LAST:event_lblImagenMouseClicked

    private void lblurlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblurlMouseClicked
        try {
            Desktop.getDesktop().browse(URI.create("https://www.cuscocode.com/"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_lblurlMouseClicked

    void imprimirDirecto() {
        try {
            String nombrepdf = System.getProperty("user.dir") + "\\ticket.pdf";
//        String nombrepdf = "C:/Users/mr-robot/Documents/DOCUMENTO/blog.pdf";
//            String nombrepdf = "C:/Users/USER/Documents/aa.pdf";

            try {

                guardarpdf(nombrepdf);

            } catch (Exception e) {
                System.out.println("error:" + e);
            }

            File Archivo = new File(nombrepdf);
            PDDocument document = null;

            try {
                document = PDDocument.load(Archivo);
            } catch (IOException ex) {
                Logger.getLogger(pnl_ventas2.class.getName()).log(Level.SEVERE, null, ex);
            }

            PrinterJob job = PrinterJob.getPrinterJob();

            job.setPageable(new PDFPageable(document));
            try {
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(pnl_ventas2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al imprimir ticket\n" + e);
        }
    }


    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
//guardarpdf
        String nombrepdf = "Ticket0001";

        //validar si contiene "Ñ"
        String venies = nombrepdf;
        String cadenaNormalize = Normalizer.normalize(venies, Normalizer.Form.NFD);
        String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

        if (cadenaSinAcentos.trim().equals("")) {
            JOptionPane.showConfirmDialog(null, "Pon el nombre");
        } else {
            //almacenando la ruta donde se va a guardar
            int option = file.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File f = file.getSelectedFile();
                nombrepdf = (f.toString());

            }
            // contenido
            String ruta = nombrepdf;

            try {
                guardarpdf(ruta + ".pdf");

                JOptionPane.showMessageDialog(null, "PDF creado");
            } catch (Exception e) {
                System.out.println("error:" + e);
            }

            int visor = JOptionPane.showConfirmDialog(null, "Abrir el pdf");
            if (visor == 0) {
                try {
                    String url = nombrepdf + ".pdf";
                    ProcessBuilder p = new ProcessBuilder();
                    p.command("cmd.exe", "/c", url);
                    p.start();
                } catch (IOException ex) {
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                }

            }
        }


    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_enviar_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviar_correoActionPerformed

        JOptionPane.showInputDialog("No tiene acceso a internet");
        txtcorreo_enviar.setText("");

    }//GEN-LAST:event_btn_enviar_correoActionPerformed

    private void rb_extranjeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_extranjeroActionPerformed
        listarPrecios("");
        mostrar_monto_cobrar();
    }//GEN-LAST:event_rb_extranjeroActionPerformed

    private void rb_externoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_externoActionPerformed
        listarPrecios("");
        mostrar_monto_cobrar();
    }//GEN-LAST:event_rb_externoActionPerformed

    private void lblImagen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblImagen1MouseClicked


    private void cbo_adultoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_adultoActionPerformed
        mostrar_monto_cobrar();
    }//GEN-LAST:event_cbo_adultoActionPerformed

    private void cbo_infantilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_infantilActionPerformed
        mostrar_monto_cobrar();

    }//GEN-LAST:event_cbo_infantilActionPerformed

    private void rb_otroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_otroActionPerformed
        // TODO add your handling code here:
        listarPrecios("");
        mostrar_monto_cobrar();
    }//GEN-LAST:event_rb_otroActionPerformed

    private void rb_localActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_localActionPerformed
        // TODO add your handling code here:
        listarPrecios("");
        mostrar_monto_cobrar();
    }//GEN-LAST:event_rb_localActionPerformed

    private void cbo_adultoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_adultoItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbo_adultoItemStateChanged

    private void cbo_infantilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_infantilItemStateChanged

    }//GEN-LAST:event_cbo_infantilItemStateChanged
//frm_principal panel= new frm_principal();


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.RSButtonHover btn_enviar_correo;
    private rojeru_san.complementos.RSButtonHover btn_guardar;
    private rojerusan.RSMaterialButtonRound btn_usuario2;
    private rojerusan.RSComboMetro cbo_adulto;
    private rojerusan.RSComboMetro cbo_infantil;
    private rojerusan.RSComboMetro cbo_precio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lb_caja;
    private javax.swing.JLabel lb_cantida_adultos;
    private javax.swing.JLabel lb_cantidad_niños;
    private javax.swing.JLabel lb_codigo_Venta;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_hora;
    private javax.swing.JLabel lb_nombreEntidad;
    private javax.swing.JLabel lb_nombre_nogocio;
    private javax.swing.JLabel lb_operador;
    private javax.swing.JLabel lb_precio_adultos;
    private javax.swing.JLabel lb_precio_niños;
    private javax.swing.JLabel lb_tipo_visitante;
    private javax.swing.JLabel lb_total_precios;
    private javax.swing.JLabel lb_total_visitantes;
    private rojerusan.RSPanelCircleImage lblImagen;
    private rojerusan.RSPanelCircleImage lblImagen1;
    private javax.swing.JLabel lbl_boleteria;
    private javax.swing.JLabel lbl_cod;
    private rojeru_san.RSLabelFecha lbl_fecha1;
    private javax.swing.JLabel lbl_hora;
    private javax.swing.JLabel lbl_infantill;
    private javax.swing.JLabel lbl_nombre_entidad2;
    private javax.swing.JLabel lbl_nombre_negocio;
    private javax.swing.JLabel lbl_nombre_negocio1;
    private javax.swing.JLabel lbl_nombre_negocio2;
    private javax.swing.JLabel lbl_total_cobrar;
    private javax.swing.JLabel lblpass1;
    private javax.swing.JLabel lblpass3;
    private javax.swing.JLabel lblurl;
    private javax.swing.JLabel lcodigo10;
    private javax.swing.JLabel lcodigo11;
    private javax.swing.JLabel lcodigo12;
    private javax.swing.JLabel lcodigo13;
    private javax.swing.JLabel lcodigo14;
    private javax.swing.JLabel lcodigo17;
    private javax.swing.JLabel lcodigo18;
    private javax.swing.JLabel lcodigo23;
    private javax.swing.JLabel lcodigo26;
    private javax.swing.JLabel lcodigo29;
    private javax.swing.JLabel lcodigo30;
    private javax.swing.JLabel lcodigo31;
    private javax.swing.JLabel lcodigo32;
    private javax.swing.JLabel lcodigo33;
    private javax.swing.JLabel lcodigo34;
    private javax.swing.JLabel lcodigo35;
    private javax.swing.JLabel lcodigo36;
    private javax.swing.JLabel lcodigo38;
    private javax.swing.JLabel lcodigo39;
    private javax.swing.JLabel lcodigo7;
    private javax.swing.JLabel lnlinfa;
    private jcMousePanel.jcMousePanel panel_img;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private javax.swing.ButtonGroup radioButon;
    private check.RB rb_externo;
    private check.RB rb_extranjero;
    private check.RB rb_local;
    private check.RB rb_otro;
    private rojeru_san.RSMTextFull txt_dni;
    private rojerusan.RSMetroTextFullPlaceHolder txtcorreo_enviar;
    // End of variables declaration//GEN-END:variables
}
