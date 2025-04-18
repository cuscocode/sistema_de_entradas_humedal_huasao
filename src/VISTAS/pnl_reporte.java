/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

//import COMPONENTES.Mensaje_Dialogo2;
//import CONTROLADOR_3.c_usuario;
//import static VISTA.frm_principal.pnlMenu;
import CONTROLADOR_3.c_ventas;

import Componetes.GeneradorPdf_General;
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
import static VISTAS.pnl_ventas2.gmailEnvio;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
 * @author raul hacho cutipa
 */
public class pnl_reporte extends javax.swing.JPanel {

    DefaultTableModel modeloV;
    DefaultTableModel modeloU;
    c_ventas omVentas = new c_ventas();

    JFileChooser file = new JFileChooser();
    String rutaguardar = "C:\\Users\\USER\\Documents\\";

    private ImageIcon imageicono;
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    File archivo2;
    byte[] bytesImg;
    ManejoDeImagenes gestion = new ManejoDeImagenes();

    //------------------------------------------------
//private ImageIcon imageicono;
    //  frm_principal frmPrincipal = new frm_principal();
//    c_usuario omPersonal = new c_usuario();
    public pnl_reporte() {
        initComponents();
        imageicono = new ImageIcon(this.getClass().getResource("/appapagar/image/logo1.png"));
//        private ImageIcon imageicono;
        mostrar_fecha_hoy();
        mostrar_VentasUsuario("");
        listar_ventas_generales("");
    }

    public void mostrar_VentasUsuario(String datos) {
        try {
            modeloU = omVentas.ListarVentas_usuario(datos);
            tabla_usuarios.setModel(modeloU);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al mostrar usuario");
        }
    }

    public void mostrar_fecha_hoy() {
        LocalDate fecha = LocalDate.now();

        txt_date_por_fecha.setDatoFecha(new Date());
        txt_fecha_año.setText(fecha.getMonthValue() + "");
        txt_date_fecha_inicio.setDatoFecha(new Date());
    }

    public void listar_ventas_generales(String pDato) {
        try {
            modeloV = omVentas.ListarVentasGenerales(pDato);
            tabla_reportes.setModel(modeloV);
            //  JOptionPane.showMessageDialog(null, fechastring);
            mostrar_totales(modeloV);
            lb_avizos.setText("GANANCIAS GENERALES");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ganacias generales");
        }
    }

    public void filtrar_por_fecha() {
        try {
            if (txt_date_por_fecha.getDatoFecha() == null) {
                JOptionPane.showMessageDialog(null, "ingrese una fecha");
            } else {
                String fechastring = null;
                Date fecha = txt_date_por_fecha.getDatoFecha();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fechastring = f.format(fecha);

                modeloV = omVentas.listrarPorFecha(fechastring);
                tabla_reportes.setModel(modeloV);
                //  JOptionPane.showMessageDialog(null, fechastring);
                mostrar_totales(modeloV);
                lb_avizos.setText("GANANCIAS DEL: " + fechastring);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al filtrar por feha");
        }
    }

    public void filtrar_por_mes() {
        try {
            if (txt_fecha_año.getText().isEmpty() || Integer.parseInt(txt_fecha_año.getText()) < 1 || Integer.parseInt(txt_fecha_año.getText()) > 12) {
                JOptionPane.showMessageDialog(null, "ingrese un una feha valida");
            } else {
                int año = txt_Date_año_mes.getYear();
                int mes = Integer.parseInt(txt_fecha_año.getText());

                String fecha_inicio = año+ "-"+ mes + "-01";
                String fecha_fin = año + "-" + mes + "-" + dias_De_mes(mes, año);

                modeloV = omVentas.listrarPorAño(fecha_inicio, fecha_fin);
                tabla_reportes.setModel(modeloV);

                mostrar_totales(modeloV);
                lb_avizos.setText("GANAN. " + "MES:" + mes + " AÑO:" + año);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al filtrar por mes");
        }
    }

    public void filtrar_por_año() {
        try {
            String fechastring = null;
            int año = txt_date_año.getYear();

            String fecha_inicio = año + "-01-01";
            String fecha_fin = año + "-12-31";

            modeloV = omVentas.listrarPorAño(fecha_inicio, fecha_fin);
            tabla_reportes.setModel(modeloV);

            mostrar_totales(modeloV);
            lb_avizos.setText("GANANCIAS DEL: " + "AÑO:" + año);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al filtrar por año");
        }
    }

    public int dias_De_mes(int mes, int año) {
        int nrdias;
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 8 || mes == 10 || mes == 12) {
            nrdias = 31;
        } else {
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                nrdias = 30;
            } else {
                if (año % 4 == 0) {
                    nrdias = 29;
                } else {
                    nrdias = 28;
                }
            }
        }
        return nrdias;
    }

    public void mostrar_totales(DefaultTableModel modelo_de_ventas) {
        try {
            int col_niños, col_adul, col_ganan;
            if (modelo_de_ventas.getColumnCount() > 7) {
                col_adul = 2;
                col_niños = 3;
                col_ganan = 5;
            } else {
                col_adul = 1;
                col_niños = 2;
                col_ganan = 4;
            }

            //TOTAL DE ADULTOS VENDIDOS ATENDIDOS
            Double adultos_atenditos = 0.0;
            for (int i = 0; i < modelo_de_ventas.getRowCount(); i++) {
                adultos_atenditos = adultos_atenditos + Double.parseDouble(modelo_de_ventas.getValueAt(i, col_adul).toString());
            }
            lb_ganancias_adultos.setText("");
            lb_ganancias_adultos.setText(adultos_atenditos + "");

            //TOTAL DE NIÑOS VENDIDOS ATENDIDOS
            Double niños_atendidos = 0.0;
            for (int i = 0; i < modelo_de_ventas.getRowCount(); i++) {
                niños_atendidos = niños_atendidos + Double.parseDouble(modelo_de_ventas.getValueAt(i, col_niños).toString());
            }
            lb_ganancias_niños.setText("");
            lb_ganancias_niños.setText(niños_atendidos + "");

            //GANANCIAS GENERALES
            Double total_ganancias = 0.0;
            for (int i = 0; i < modelo_de_ventas.getRowCount(); i++) {
                total_ganancias = total_ganancias + Double.parseDouble(modelo_de_ventas.getValueAt(i, col_ganan).toString());
            }
            lb_total_ganacia.setText("");
            lb_total_ganacia.setText("S/ " + total_ganancias + "");

            //TOTAL DE VENTAS REALIZADAS
            lb_total_ventas.setText("");
            lb_total_ventas.setText(modelo_de_ventas.getRowCount() + " ventas realizadas");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al mostrar datos totales de las ventas");
        }
    }

    public void filtrar_entre_Dos_fechas() {
        try {

            if (txt_date_fecha_inicio.getDatoFecha() == null || txt_date_fecha_fin.getDatoFecha() == null) {
                JOptionPane.showMessageDialog(null, "complete las fecha para enpezar");
            } else {
                String fecha_inicio = null;
                Date fechaini = txt_date_fecha_inicio.getDatoFecha();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                fecha_inicio = f.format(fechaini);

                String fecha_fin = null;
                Date fechafin = txt_date_fecha_fin.getDatoFecha();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                fecha_fin = s.format(fechafin);

                modeloV = omVentas.listrarPorAño(fecha_inicio, fecha_fin);
                tabla_reportes.setModel(modeloV);
                mostrar_totales(modeloV);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al filtrar entre dos fechas");
        }
    }

    String fecha_seleccionada_loader = "";

    //imrimir
    void imprimirDirecto() {
        try {
//        String nombrepdf = "C:/Users/OLIPC/Desktop/Documents/aa.pdf";
//        String nombrepdf = "C:/Users/mr-robot/Documents/DOCUMENTO/blog.pdf";
            String nombrepdf = "C:/Users/USER/Documents/ssss.pdf";

            try {

                guardarpdf_pc(nombrepdf);

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

    public void guardarpdf_pc(String ruta) {
        GeneradorPdf_General g = new GeneradorPdf_General();

        //Declaracion de datos que va atener el pdf
        String fechaActual = "";
        String fechaDMA = "";
        String totalAdultos = "";
        String Totalinfantil = "";
        String GananciaTotal = "";

        //asignamos los valores del variable
        fechaActual = lbl_fecha.getFecha().toString();
        fechaDMA = lbl_fecha_seleccionada.getText();
        totalAdultos = lb_ganancias_adultos.getText();
        Totalinfantil = lb_ganancias_niños.getText();
        GananciaTotal = lb_total_ganacia.getText();

        //enviamos la funcion con los datos variables
        g.GeneradorPdf_General(
                fechaActual,
                fechaDMA,
                totalAdultos,
                Totalinfantil,
                GananciaTotal,
                ruta);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButon = new javax.swing.ButtonGroup();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        rSPanelGradiente1 = new rspanelgradiente.RSPanelGradiente();
        jpanel_n = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lcodigo6 = new javax.swing.JLabel();
        lb_total_ganacia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lb_avizos = new javax.swing.JLabel();
        lcodigo4 = new javax.swing.JLabel();
        lcodigo5 = new javax.swing.JLabel();
        lb_ganancias_niños = new javax.swing.JLabel();
        lb_ganancias_adultos = new javax.swing.JLabel();
        lcodigo2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuarios = new rojerusan.RSTableMetro();
        jPanel7 = new javax.swing.JPanel();
        lb_total_ventas = new javax.swing.JLabel();
        lcodigo27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_modeficar = new rojerusan.RSMaterialButtonRound();
        jcMousePanel11 = new jcMousePanel.jcMousePanel();
        jPanel4 = new javax.swing.JPanel();
        lcodigo11 = new javax.swing.JLabel();
        btn_ = new rojerusan.RSButtonHover();
        lcodigo24 = new javax.swing.JLabel();
        lcodigo25 = new javax.swing.JLabel();
        lcodigo26 = new javax.swing.JLabel();
        btn_3 = new rojerusan.RSButtonHover();
        btn_4 = new rojerusan.RSButtonHover();
        btn_5 = new rojerusan.RSButtonHover();
        txt_date_fecha_inicio = new rojeru_san.componentes.RSDateChooser();
        txt_Date_año_mes = new rojeru_san.componentes.RSYearDate();
        txt_date_año = new rojeru_san.componentes.RSYearDate();
        lbl_fecha_seleccionada = new javax.swing.JLabel();
        lcodigo13 = new javax.swing.JLabel();
        lcodigo14 = new javax.swing.JLabel();
        txt_date_por_fecha = new rojeru_san.componentes.RSDateChooser();
        txt_date_fecha_fin = new rojeru_san.componentes.RSDateChooser();
        jPanel6 = new javax.swing.JPanel();
        lcodigo3 = new javax.swing.JLabel();
        lcodigo15 = new javax.swing.JLabel();
        lcodigo16 = new javax.swing.JLabel();
        txt_fecha_año = new LIB.JTexfieldPH_FielTex();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_reportes = new rojerusan.RSTableMetro();
        rSPanelGradiente5 = new rspanelgradiente.RSPanelGradiente();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new LIB.JTexfieldPH_FielTex();
        lbl_fecha = new rojeru_san.RSLabelFecha();
        lcodigo9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
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

        rSPanelShadow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente1.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel_n.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lcodigo6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo6.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo6.setText("GANANCIA TOTAL");

        lb_total_ganacia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_total_ganacia.setForeground(new java.awt.Color(51, 51, 51));
        lb_total_ganacia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_total_ganacia.setText(" S/ 00.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lcodigo6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_total_ganacia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_total_ganacia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBackground(new java.awt.Color(227, 94, 106));

        lb_avizos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_avizos.setForeground(new java.awt.Color(255, 255, 255));
        lb_avizos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_avizos.setText("GANANCIAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_avizos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_avizos, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        lcodigo4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo4.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo4.setText("Adultos Atendidos");

        lcodigo5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo5.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo5.setText("Niños Atendidos");

        lb_ganancias_niños.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_ganancias_niños.setForeground(new java.awt.Color(153, 153, 153));
        lb_ganancias_niños.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_ganancias_niños.setText("0");

        lb_ganancias_adultos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_ganancias_adultos.setForeground(new java.awt.Color(153, 153, 153));
        lb_ganancias_adultos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_ganancias_adultos.setText("0");

        javax.swing.GroupLayout jpanel_nLayout = new javax.swing.GroupLayout(jpanel_n);
        jpanel_n.setLayout(jpanel_nLayout);
        jpanel_nLayout.setHorizontalGroup(
            jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpanel_nLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel_nLayout.createSequentialGroup()
                        .addComponent(lcodigo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_ganancias_adultos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel_nLayout.createSequentialGroup()
                        .addComponent(lcodigo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_ganancias_niños, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jpanel_nLayout.setVerticalGroup(
            jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_nLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_ganancias_adultos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_ganancias_niños, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rSPanelGradiente1.add(jpanel_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 290, 200));

        lcodigo2.setFont(new java.awt.Font("techno overload BRK", 0, 48)); // NOI18N
        lcodigo2.setForeground(new java.awt.Color(227, 94, 106));
        lcodigo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo2.setText(" Reporte de las ventas");
        rSPanelGradiente1.add(lcodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 510, 50));

        tabla_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, ""},
                {null, null, ""},
                {null, null, null}
            },
            new String [] {
                "Precio id", "Precio Adulto", "Precio Niño"
            }
        ));
        tabla_usuarios.setAltoHead(25);
        tabla_usuarios.setColorBackgoundHead(new java.awt.Color(70, 187, 151));
        tabla_usuarios.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla_usuarios.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla_usuarios.setColorFilasBackgound2(new java.awt.Color(172, 224, 208));
        tabla_usuarios.setColorFilasForeground1(new java.awt.Color(131, 209, 185));
        tabla_usuarios.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tabla_usuarios.setColorSelBackgound(new java.awt.Color(50, 105, 125));
        tabla_usuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_usuarios.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_usuarios.setGridColor(new java.awt.Color(255, 255, 255));
        tabla_usuarios.setGrosorBordeFilas(0);
        tabla_usuarios.setMultipleSeleccion(false);
        tabla_usuarios.setSelectionBackground(new java.awt.Color(227, 94, 106));
        tabla_usuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tabla_usuariosMouseMoved(evt);
            }
        });
        tabla_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_usuariosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_usuariosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_usuarios);

        rSPanelGradiente1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 490, 160));

        jPanel7.setBackground(new java.awt.Color(70, 187, 151));

        lb_total_ventas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_total_ventas.setForeground(new java.awt.Color(255, 255, 255));
        lb_total_ventas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_total_ventas.setText("Lista de ventas realizadas");

        lcodigo27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo27.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo27.setText("Lista de ventas realizadas");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 341, Short.MAX_VALUE)
                .addComponent(lb_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(lcodigo27, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(321, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_total_ventas, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lcodigo27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        rSPanelGradiente1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 550, 40));

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        rSPanelGradiente1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 160, 4));

        btn_modeficar.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar.setBorder(null);
        btn_modeficar.setText("IMPRIMIR");
        btn_modeficar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficarActionPerformed(evt);
            }
        });
        rSPanelGradiente1.add(btn_modeficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 190, 50));

        jcMousePanel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_new_ticket_48px.png"))); // NOI18N

        javax.swing.GroupLayout jcMousePanel11Layout = new javax.swing.GroupLayout(jcMousePanel11);
        jcMousePanel11.setLayout(jcMousePanel11Layout);
        jcMousePanel11Layout.setHorizontalGroup(
            jcMousePanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );
        jcMousePanel11Layout.setVerticalGroup(
            jcMousePanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        rSPanelGradiente1.add(jcMousePanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 280, 35, 30));

        jPanel4.setBackground(new java.awt.Color(227, 94, 106));

        lcodigo11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo11.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo11.setText("FECHA  A SELECCIONAR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lcodigo11, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lcodigo11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        rSPanelGradiente1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 490, 40));

        btn_.setBackground(new java.awt.Color(70, 187, 151));
        btn_.setText("VER TOTAL");
        btn_.setColorHover(new java.awt.Color(153, 153, 153));
        btn_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActionPerformed(evt);
            }
        });
        rSPanelGradiente1.add(btn_, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 220, -1));

        lcodigo24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo24.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo24.setText("REPORTE DE HOY");
        rSPanelGradiente1.add(lcodigo24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 140, 30));

        lcodigo25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo25.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo25.setText("REPORTE DE MENSUAL");
        rSPanelGradiente1.add(lcodigo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 140, 30));

        lcodigo26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo26.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo26.setText("REPORTE DE ANUAL");
        rSPanelGradiente1.add(lcodigo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 150, 30));

        btn_3.setBackground(new java.awt.Color(70, 187, 151));
        btn_3.setText("VER TOTAL");
        btn_3.setColorHover(new java.awt.Color(153, 153, 153));
        btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_3ActionPerformed(evt);
            }
        });
        rSPanelGradiente1.add(btn_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 140, -1));

        btn_4.setBackground(new java.awt.Color(70, 187, 151));
        btn_4.setText("VER TOTAL");
        btn_4.setColorHover(new java.awt.Color(153, 153, 153));
        btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_4ActionPerformed(evt);
            }
        });
        rSPanelGradiente1.add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 140, -1));

        btn_5.setBackground(new java.awt.Color(70, 187, 151));
        btn_5.setText("VER TOTAL");
        btn_5.setColorHover(new java.awt.Color(153, 153, 153));
        btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_5ActionPerformed(evt);
            }
        });
        rSPanelGradiente1.add(btn_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 140, -1));

        txt_date_fecha_inicio.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_date_fecha_inicio.setColorButtonHover(new java.awt.Color(70, 187, 151));
        txt_date_fecha_inicio.setColorForeground(new java.awt.Color(153, 153, 153));
        txt_date_fecha_inicio.setPlaceholder("seleccionar fecha");
        rSPanelGradiente1.add(txt_date_fecha_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 220, -1));

        txt_Date_año_mes.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_Date_año_mes.setColorButtonHover(new java.awt.Color(70, 187, 151));
        rSPanelGradiente1.add(txt_Date_año_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 100, -1));

        txt_date_año.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_date_año.setColorButtonHover(new java.awt.Color(70, 187, 151));
        rSPanelGradiente1.add(txt_date_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 140, -1));

        lbl_fecha_seleccionada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_fecha_seleccionada.setForeground(new java.awt.Color(102, 102, 102));
        lbl_fecha_seleccionada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_fecha_seleccionada.setText("...");
        rSPanelGradiente1.add(lbl_fecha_seleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 480, -1));

        lcodigo13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo13.setForeground(new java.awt.Color(102, 102, 102));
        lcodigo13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo13.setText("Fecha Año/Mes/Dia");
        rSPanelGradiente1.add(lcodigo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, -1, -1));

        lcodigo14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo14.setForeground(new java.awt.Color(102, 102, 102));
        lcodigo14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo14.setText("Mes  - Año");
        rSPanelGradiente1.add(lcodigo14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 120, -1));

        txt_date_por_fecha.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_date_por_fecha.setColorButtonHover(new java.awt.Color(70, 187, 151));
        txt_date_por_fecha.setColorForeground(new java.awt.Color(153, 153, 153));
        txt_date_por_fecha.setPlaceholder("seleccionar fecha");
        rSPanelGradiente1.add(txt_date_por_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 140, -1));

        txt_date_fecha_fin.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_date_fecha_fin.setColorButtonHover(new java.awt.Color(70, 187, 151));
        txt_date_fecha_fin.setColorForeground(new java.awt.Color(153, 153, 153));
        txt_date_fecha_fin.setPlaceholder("seleccionar fecha");
        rSPanelGradiente1.add(txt_date_fecha_fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 220, -1));

        jPanel6.setBackground(new java.awt.Color(227, 94, 106));

        lcodigo3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo3.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo3.setText("Reportes Entre Fechas ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lcodigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lcodigo3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        rSPanelGradiente1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 220, 40));

        lcodigo15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo15.setForeground(new java.awt.Color(102, 102, 102));
        lcodigo15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo15.setText(" Año");
        rSPanelGradiente1.add(lcodigo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 110, -1));

        lcodigo16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo16.setForeground(new java.awt.Color(102, 102, 102));
        lcodigo16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo16.setText("Fecha de Inicio");
        rSPanelGradiente1.add(lcodigo16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 110, -1));

        txt_fecha_año.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txt_fecha_año.setForeground(new java.awt.Color(153, 153, 153));
        txt_fecha_año.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha_año.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_fecha_año.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_fecha_año.setPhColor(new java.awt.Color(255, 255, 255));
        txt_fecha_año.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_fecha_añoKeyReleased(evt);
            }
        });
        rSPanelGradiente1.add(txt_fecha_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 40, 40));

        tabla_reportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, ""},
                {null, null, ""},
                {null, null, null}
            },
            new String [] {
                "Precio id", "Precio Adulto", "Precio Niño"
            }
        ));
        tabla_reportes.setAltoHead(25);
        tabla_reportes.setColorBackgoundHead(new java.awt.Color(70, 187, 151));
        tabla_reportes.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla_reportes.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla_reportes.setColorFilasBackgound2(new java.awt.Color(172, 224, 208));
        tabla_reportes.setColorFilasForeground1(new java.awt.Color(131, 209, 185));
        tabla_reportes.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tabla_reportes.setColorSelBackgound(new java.awt.Color(50, 105, 125));
        tabla_reportes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_reportes.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_reportes.setGridColor(new java.awt.Color(255, 255, 255));
        tabla_reportes.setGrosorBordeFilas(0);
        tabla_reportes.setMultipleSeleccion(false);
        tabla_reportes.setSelectionBackground(new java.awt.Color(227, 94, 106));
        tabla_reportes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tabla_reportesMouseMoved(evt);
            }
        });
        tabla_reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_reportesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_reportesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_reportes);

        rSPanelGradiente1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 550, 160));

        rSPanelGradiente5.setBackground(new java.awt.Color(255, 197, 0));
        rSPanelGradiente5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        rSPanelGradiente5.setColorPrimario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente5.setColorSecundario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente5.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_search_30px.png"))); // NOI18N
        jLabel1.setToolTipText("");
        rSPanelGradiente5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        rSPanelGradiente1.add(rSPanelGradiente5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 40, 40));

        txt_buscar.setBackground(new java.awt.Color(2, 210, 185));
        txt_buscar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(255, 255, 255)));
        txt_buscar.setForeground(new java.awt.Color(255, 255, 255));
        txt_buscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_buscar.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_buscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_buscar.setPhColor(new java.awt.Color(255, 255, 255));
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        rSPanelGradiente1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 220, 40));

        lbl_fecha.setForeground(new java.awt.Color(2, 210, 185));
        lbl_fecha.setFont(new java.awt.Font("Roboto Bold", 1, 36)); // NOI18N
        rSPanelGradiente1.add(lbl_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 210, 40));

        lcodigo9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo9.setForeground(new java.awt.Color(102, 102, 102));
        lcodigo9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo9.setText("Fecha Final");
        rSPanelGradiente1.add(lcodigo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 110, -1));

        rSPanelShadow1.add(rSPanelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1150, 570));

        add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuariosMouseClicked
        //

    }//GEN-LAST:event_tabla_usuariosMouseClicked

    private void tabla_usuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuariosMousePressed


    }//GEN-LAST:event_tabla_usuariosMousePressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseDragged

    private void tabla_usuariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuariosMouseMoved
        // TODO add your handling code here:
        //   Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
    }//GEN-LAST:event_tabla_usuariosMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved


    private void btn_modeficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficarActionPerformed

//        String nombrepdf = "C:/Users/USER/Documents/aa.pdf";
        String nombrepdf = "C:/Users/OLIPC/Desktop/Documents/Reporte.pdf";

        try {
            guardarpdf_pc(nombrepdf);
        } catch (Exception e) {
            System.out.println("error:" + e);
        }
        try {
            String url = nombrepdf;
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btn_modeficarActionPerformed

    private void btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_3ActionPerformed

        // TODO add your handling code here:
        filtrar_por_año();
        lbl_fecha_seleccionada.setText("AÑO : " + txt_date_año.getYear() + "");
    }//GEN-LAST:event_btn_3ActionPerformed

    private void btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_5ActionPerformed
        // TODO add your handling code here:
        filtrar_por_mes();

        lbl_fecha_seleccionada.setText("MES : " + txt_fecha_año.getText() + " - AÑO : " + txt_Date_año_mes.getYear());
    }//GEN-LAST:event_btn_5ActionPerformed

    private void btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_4ActionPerformed

        // TODO add your handling code here:
        filtrar_por_fecha();
        Date h = txt_date_por_fecha.getDatoFecha();

        Date fecha = txt_date_por_fecha.getDatoFecha();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        lbl_fecha_seleccionada.setText(f.format(fecha));

    }//GEN-LAST:event_btn_4ActionPerformed

    private void txt_fecha_añoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecha_añoKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_fecha_añoKeyReleased

    private void btn_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActionPerformed
        // TODO add your handling code here:
        filtrar_entre_Dos_fechas();

        Date fecha = txt_date_fecha_inicio.getDatoFecha();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");

        Date fecha2 = txt_date_fecha_fin.getDatoFecha();
        SimpleDateFormat f2 = new SimpleDateFormat("dd-MM-yyyy");

        lbl_fecha_seleccionada.setText("\n FECHA DE INICIO : " + f.format(fecha) + "\nHASTA : " + f2.format(fecha2));

    }//GEN-LAST:event_btn_ActionPerformed

    private void tabla_reportesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_reportesMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_reportesMouseMoved

    private void tabla_reportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_reportesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_reportesMouseClicked

    private void tabla_reportesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_reportesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_reportesMousePressed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
        mostrar_VentasUsuario(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonHover btn_;
    private rojerusan.RSButtonHover btn_3;
    private rojerusan.RSButtonHover btn_4;
    private rojerusan.RSButtonHover btn_5;
    private rojerusan.RSMaterialButtonRound btn_modeficar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private jcMousePanel.jcMousePanel jcMousePanel11;
    private javax.swing.JPanel jpanel_n;
    private javax.swing.JLabel lb_avizos;
    private javax.swing.JLabel lb_ganancias_adultos;
    private javax.swing.JLabel lb_ganancias_niños;
    private javax.swing.JLabel lb_total_ganacia;
    private javax.swing.JLabel lb_total_ventas;
    private rojeru_san.RSLabelFecha lbl_fecha;
    private javax.swing.JLabel lbl_fecha_seleccionada;
    private javax.swing.JLabel lcodigo11;
    private javax.swing.JLabel lcodigo13;
    private javax.swing.JLabel lcodigo14;
    private javax.swing.JLabel lcodigo15;
    private javax.swing.JLabel lcodigo16;
    private javax.swing.JLabel lcodigo2;
    private javax.swing.JLabel lcodigo24;
    private javax.swing.JLabel lcodigo25;
    private javax.swing.JLabel lcodigo26;
    private javax.swing.JLabel lcodigo27;
    private javax.swing.JLabel lcodigo3;
    private javax.swing.JLabel lcodigo4;
    private javax.swing.JLabel lcodigo5;
    private javax.swing.JLabel lcodigo6;
    private javax.swing.JLabel lcodigo9;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.ButtonGroup radioButon;
    private rojerusan.RSTableMetro tabla_reportes;
    private rojerusan.RSTableMetro tabla_usuarios;
    private rojeru_san.componentes.RSYearDate txt_Date_año_mes;
    private LIB.JTexfieldPH_FielTex txt_buscar;
    private rojeru_san.componentes.RSYearDate txt_date_año;
    private rojeru_san.componentes.RSDateChooser txt_date_fecha_fin;
    private rojeru_san.componentes.RSDateChooser txt_date_fecha_inicio;
    private rojeru_san.componentes.RSDateChooser txt_date_por_fecha;
    private LIB.JTexfieldPH_FielTex txt_fecha_año;
    // End of variables declaration//GEN-END:variables
}
