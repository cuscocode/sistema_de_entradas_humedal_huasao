/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

//import COMPONENTES.Mensaje_Dialogo2;
//import CONTROLADOR_3.c_usuario;
//import static VISTA.frm_principal.pnlMenu;
import CONTROLADOR_3.c_cliente;
import CONTROLADOR_3.c_ventas;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import rojerusan.RSDateChooser;

/**
 *
 * @author raul hacho cutipa
 */
public class pnl_Otros extends javax.swing.JPanel {

    DefaultTableModel modeloV, modeloBorar;
    private ImageIcon imageicono;
    c_ventas omVentas = new c_ventas();
    //------------------------------------------------

    //  frm_principal frmPrincipal = new frm_principal();
//    c_usuario omPersonal = new c_usuario();
    public pnl_Otros() {
        initComponents();
        listar_ventas("");
        imageicono = new ImageIcon(this.getClass().getResource("/appapagar/image/logo1.png"));

//        private ImageIcon imageicono;
//AGREGAR TITULOS A LA TABLA
        String[] titulos = {"ID VENTAS ", "NR ADULTOS",
            "NR NIÑOS", "MONTO", "FECHA", "ID CLIENTE"};
        modeloBorar = new DefaultTableModel();
        modeloBorar.setColumnIdentifiers(titulos);
        tabla_borrar.setModel(modeloBorar);
    }

    void listar_ventas(String datos) {
        try {
            modeloV = omVentas.ListarVentaBorrar(datos);
            tabla_ventas.setModel(modeloV);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar ventas");
        }
    }

    void copiar_fila() {
        try {
            int col_id, col_adulto, col_niño, col_monto, col_fecha, col_id_cliente;
            if (modeloV.getColumnCount() <= 6) {
                col_id = 0;
                col_adulto = 1;
                col_niño = 2;
                col_monto = 3;
                col_fecha = 4;
                col_id_cliente = 5;
            } else {
                col_id = 0;
                col_adulto = 1;
                col_niño = 2;
                col_monto = 5;
                col_fecha = 3;
                col_id_cliente = 7;
            }

            int fila = tabla_ventas.getSelectedRow();
            if (fila >= 0) {

                //que no se repitan las filas 
                Boolean exite = false;
                for (int i = 0; i < modeloBorar.getRowCount(); i++) {
                    if (modeloBorar.getValueAt(i, 0).toString().equals(modeloV.getValueAt(fila, 0))) {
                        exite = true;
                    }
                }

                if (exite == false) {
                    String[] registro = new String[6];
                    registro[0] = modeloV.getValueAt(fila, col_id).toString();
                    registro[1] = modeloV.getValueAt(fila, col_adulto).toString();
                    registro[2] = modeloV.getValueAt(fila, col_niño).toString();
                    registro[3] = modeloV.getValueAt(fila, col_monto).toString();
                    registro[4] = modeloV.getValueAt(fila, col_fecha).toString();
                    registro[5] = modeloV.getValueAt(fila, col_id_cliente).toString();

                    modeloBorar.addRow(registro);
                    tabla_borrar.setModel(modeloBorar);
                    mostrar_totales(modeloBorar);
                } else {
                    JOptionPane.showMessageDialog(null, "esta venta ya se encuentra en la tabla para borar \n seleccione otra venta");
                }
            } else {
                JOptionPane.showMessageDialog(null, "seleccione una fila");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al pasar a la otra fila\n" + e);
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

                modeloV = omVentas.listrarVentaBorrarFecha(fechastring);
                tabla_ventas.setModel(modeloV);
                //  mostrar_totales(modeloBorar);
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

                String fecha_inicio = año + "-" + mes + "-01";
                String fecha_fin = año + "-" + mes + "-" + dias_De_mes(mes, año);

                modeloV = omVentas.listrarPorEntreFechas(fecha_inicio, fecha_fin);
                tabla_ventas.setModel(modeloV);
                //  mostrar_totales(modeloBorar);
                /* mostrar_totales(modeloV);
                lb_avizos.setText("GANAN. " + "MES:" + mes + " AÑO:" + año);*/
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

            modeloV = omVentas.listrarPorEntreFechas(fecha_inicio, fecha_fin);
            tabla_ventas.setModel(modeloV);
            mostrar_totales(modeloBorar);

            /*  mostrar_totales(modeloV);
            lb_avizos.setText("GANANCIAS DEL: " + "AÑO:" + año);*/
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

            //GANANCIAS GENERALES
            Double total_ganancias = 0.0;
            for (int i = 0; i < modelo_de_ventas.getRowCount(); i++) {
                total_ganancias = total_ganancias + Double.parseDouble(modelo_de_ventas.getValueAt(i, 3).toString());
            }
            lb_ganancias.setText("");
            lb_ganancias.setText(+total_ganancias + "");

            //TOTAL DE VENTAS REALIZADAS
            lb_ventas.setText("");
            lb_ventas.setText(modelo_de_ventas.getRowCount() + "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al mostrar datos totales de las ventas");
        }
    }

    void eliminar() {
        try {
            modeloBorar = (DefaultTableModel) tabla_borrar.getModel();
            if (modeloBorar.getRowCount() >= 0) {
                for (int i = 0; i <modeloBorar.getRowCount(); i++) {
                    omVentas.EliminarVenta(modeloBorar.getValueAt(i, 0).toString());
                    System.out.println("venta eliminada");
                }

                guardar_datos_borrados();
                listar_ventas("");
                limpiar_tabla_borrar();
                mostrar_totales(modeloBorar);

            } else {

                JOptionPane.showMessageDialog(null, "selecione para eliminar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al eliminar las ventas");
        }
    }

    void guardar_datos_borrados() {
        try {

            if (modeloBorar.getRowCount() >= 0) {
                String nombre = datos_usuario.getNombre() + " " + datos_usuario.getApellidoPaterno() + " " + datos_usuario.getApellidoMaterno();
                omVentas.guardarDatosBorrado(nombre, datos_usuario.getDni(), Integer.parseInt(lb_ventas.getText()), Double.parseDouble(lb_ganancias.getText()));
            } else {

                JOptionPane.showMessageDialog(null, "selecione para eliminar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al eliminar las ventas");
        }
    }

    void limpiar_tabla_borrar() {
        try {
            modeloBorar = new DefaultTableModel();
            tabla_borrar.setModel(modeloBorar);
            String[] titulos = {"ID VENTAS ", "NR ADULTOS",
                "NR NIÑOS", "MONTO", "FECHA", "ID CLIENTE"};
            modeloBorar = new DefaultTableModel();
            modeloBorar.setColumnIdentifiers(titulos);
            tabla_borrar.setModel(modeloBorar);
            mostrar_totales(modeloBorar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al limoiar tabla ");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButon = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        lcodigo2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_modeficar1 = new rojerusan.RSMaterialButtonRound();
        jPanel4 = new javax.swing.JPanel();
        lcodigo11 = new javax.swing.JLabel();
        lcodigo24 = new javax.swing.JLabel();
        lcodigo25 = new javax.swing.JLabel();
        lcodigo26 = new javax.swing.JLabel();
        btn_3 = new rojerusan.RSButtonHover();
        btn_4 = new rojerusan.RSButtonHover();
        btn_5 = new rojerusan.RSButtonHover();
        txt_Date_año_mes = new rojeru_san.componentes.RSYearDate();
        txt_date_año = new rojeru_san.componentes.RSYearDate();
        lcodigo22 = new javax.swing.JLabel();
        lcodigo14 = new javax.swing.JLabel();
        txt_date_por_fecha = new rojeru_san.componentes.RSDateChooser();
        lcodigo15 = new javax.swing.JLabel();
        txt_fecha_año = new LIB.JTexfieldPH_FielTex();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_borrar = new rojerusan.RSTableMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_ventas = new rojerusan.RSTableMetro();
        btn_modeficar3 = new rojerusan.RSMaterialButtonRound();
        btn_modeficar4 = new rojerusan.RSMaterialButtonRound();
        btn_modeficar5 = new rojerusan.RSMaterialButtonRound();
        lb_ganancias = new javax.swing.JLabel();
        lb_ventas = new javax.swing.JLabel();
        btn_modeficar6 = new rojerusan.RSMaterialButtonRound();
        jPanel5 = new javax.swing.JPanel();
        lcodigo12 = new javax.swing.JLabel();
        lb_ventas1 = new javax.swing.JLabel();
        lb_ganancias1 = new javax.swing.JLabel();
        btn_modeficar7 = new rojerusan.RSMaterialButtonRound();

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

        lcodigo2.setFont(new java.awt.Font("techno overload BRK", 0, 48)); // NOI18N
        lcodigo2.setForeground(new java.awt.Color(227, 94, 106));
        lcodigo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo2.setText(" Reporte del usuario");
        add(lcodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 460, 50));

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 170, 4));

        btn_modeficar1.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar1.setBorder(null);
        btn_modeficar1.setText("ELIMINAR");
        btn_modeficar1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficar1ActionPerformed(evt);
            }
        });
        add(btn_modeficar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 120, 50));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lcodigo11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 490, 40));

        lcodigo24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo24.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo24.setText("REPORTE DE HOY");
        add(lcodigo24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 140, 30));

        lcodigo25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo25.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo25.setText("REPORTE  MENSUAL");
        add(lcodigo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 140, 30));

        lcodigo26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcodigo26.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo26.setText("REPORTE ANUAL");
        add(lcodigo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 150, 30));

        btn_3.setBackground(new java.awt.Color(70, 187, 151));
        btn_3.setText("VER VENTAS");
        btn_3.setColorHover(new java.awt.Color(153, 153, 153));
        btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_3ActionPerformed(evt);
            }
        });
        add(btn_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 140, -1));

        btn_4.setBackground(new java.awt.Color(70, 187, 151));
        btn_4.setText("VER VENTAS");
        btn_4.setColorHover(new java.awt.Color(153, 153, 153));
        btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_4ActionPerformed(evt);
            }
        });
        add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 140, -1));

        btn_5.setBackground(new java.awt.Color(70, 187, 151));
        btn_5.setText("VER VENTAS");
        btn_5.setColorHover(new java.awt.Color(153, 153, 153));
        btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_5ActionPerformed(evt);
            }
        });
        add(btn_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 140, -1));

        txt_Date_año_mes.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_Date_año_mes.setColorButtonHover(new java.awt.Color(70, 187, 151));
        add(txt_Date_año_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 100, -1));

        txt_date_año.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_date_año.setColorButtonHover(new java.awt.Color(70, 187, 151));
        add(txt_date_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 140, -1));

        lcodigo22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo22.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo22.setText("Fecha Año/Mes/Dia");
        add(lcodigo22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        lcodigo14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo14.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo14.setText("Mes  - Año");
        add(lcodigo14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 120, -1));

        txt_date_por_fecha.setColorBackground(new java.awt.Color(153, 153, 153));
        txt_date_por_fecha.setColorButtonHover(new java.awt.Color(70, 187, 151));
        txt_date_por_fecha.setColorForeground(new java.awt.Color(153, 153, 153));
        txt_date_por_fecha.setPlaceholder("seleccionar fecha");
        add(txt_date_por_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, -1));

        lcodigo15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo15.setForeground(new java.awt.Color(70, 187, 151));
        lcodigo15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo15.setText(" Año");
        add(lcodigo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 110, -1));

        txt_fecha_año.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        txt_fecha_año.setForeground(new java.awt.Color(153, 153, 153));
        txt_fecha_año.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha_año.setText("1");
        txt_fecha_año.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_fecha_año.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_fecha_año.setPhColor(new java.awt.Color(255, 255, 255));
        txt_fecha_año.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_fecha_añoKeyReleased(evt);
            }
        });
        add(txt_fecha_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 40, 40));

        tabla_borrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID VENTAS", "NR ADULTOS", "NR NIÑOS", "MONTO", "ID CLIENTE"
            }
        ));
        tabla_borrar.setAltoHead(25);
        tabla_borrar.setColorBackgoundHead(new java.awt.Color(2, 210, 185));
        tabla_borrar.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla_borrar.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla_borrar.setColorFilasBackgound2(new java.awt.Color(2, 220, 185));
        tabla_borrar.setColorFilasForeground1(new java.awt.Color(2, 210, 160));
        tabla_borrar.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tabla_borrar.setColorSelBackgound(new java.awt.Color(50, 105, 125));
        tabla_borrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_borrar.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_borrar.setGridColor(new java.awt.Color(255, 255, 255));
        tabla_borrar.setGrosorBordeFilas(0);
        tabla_borrar.setMultipleSeleccion(false);
        tabla_borrar.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tabla_borrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tabla_borrarMouseMoved(evt);
            }
        });
        tabla_borrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_borrarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_borrarMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_borrar);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 320, 460, 250));

        tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 5 ", "Title 5"
            }
        ));
        tabla_ventas.setAltoHead(25);
        tabla_ventas.setColorBackgoundHead(new java.awt.Color(2, 210, 185));
        tabla_ventas.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla_ventas.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla_ventas.setColorFilasBackgound2(new java.awt.Color(2, 220, 185));
        tabla_ventas.setColorFilasForeground1(new java.awt.Color(2, 210, 160));
        tabla_ventas.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tabla_ventas.setColorSelBackgound(new java.awt.Color(50, 105, 125));
        tabla_ventas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_ventas.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_ventas.setGridColor(new java.awt.Color(255, 255, 255));
        tabla_ventas.setGrosorBordeFilas(0);
        tabla_ventas.setMultipleSeleccion(false);
        tabla_ventas.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tabla_ventas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tabla_ventasMouseMoved(evt);
            }
        });
        tabla_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ventasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_ventasMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_ventas);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 540, 250));

        btn_modeficar3.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar3.setBorder(null);
        btn_modeficar3.setText(">>>");
        btn_modeficar3.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficar3ActionPerformed(evt);
            }
        });
        add(btn_modeficar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 110, 50));

        btn_modeficar4.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar4.setBorder(null);
        btn_modeficar4.setText("pasar todo");
        btn_modeficar4.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficar4ActionPerformed(evt);
            }
        });
        add(btn_modeficar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, 110, 50));

        btn_modeficar5.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar5.setBorder(null);
        btn_modeficar5.setText("LIMPIAR TABLA");
        btn_modeficar5.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficar5ActionPerformed(evt);
            }
        });
        add(btn_modeficar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 150, 50));

        lb_ganancias.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_ganancias.setForeground(new java.awt.Color(70, 187, 151));
        lb_ganancias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_ganancias.setText("0");
        add(lb_ganancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, 210, -1));

        lb_ventas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_ventas.setForeground(new java.awt.Color(70, 187, 151));
        lb_ventas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_ventas.setText("0");
        add(lb_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 120, 230, -1));

        btn_modeficar6.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar6.setBorder(null);
        btn_modeficar6.setText("ACTUALIZAR VENTAS");
        btn_modeficar6.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficar6ActionPerformed(evt);
            }
        });
        add(btn_modeficar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 170, 50));

        jPanel5.setBackground(new java.awt.Color(227, 94, 106));

        lcodigo12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lcodigo12.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo12.setText("VENTAS A BORRAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lcodigo12, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lcodigo12, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 460, -1));

        lb_ventas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_ventas1.setForeground(new java.awt.Color(70, 187, 151));
        lb_ventas1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_ventas1.setText("total de ventas:");
        add(lb_ventas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 210, -1));

        lb_ganancias1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_ganancias1.setForeground(new java.awt.Color(70, 187, 151));
        lb_ganancias1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_ganancias1.setText("ganancias totales: S/");
        add(lb_ganancias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 210, -1));

        btn_modeficar7.setBackground(new java.awt.Color(70, 187, 151));
        btn_modeficar7.setBorder(null);
        btn_modeficar7.setText("<<<");
        btn_modeficar7.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_modeficar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeficar7ActionPerformed(evt);
            }
        });
        add(btn_modeficar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, 110, 50));
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


    private void btn_modeficar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficar1ActionPerformed
        eliminar();
//          

    }//GEN-LAST:event_btn_modeficar1ActionPerformed

    private void btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_3ActionPerformed

        // TODO add your handling code here:
        filtrar_por_año();
    }//GEN-LAST:event_btn_3ActionPerformed

    private void btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_4ActionPerformed

        filtrar_por_fecha();
    }//GEN-LAST:event_btn_4ActionPerformed

    private void btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_5ActionPerformed
        // TODO add your handling code here:
        filtrar_por_mes();
    }//GEN-LAST:event_btn_5ActionPerformed

    private void txt_fecha_añoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecha_añoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fecha_añoKeyReleased

    private void tabla_borrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_borrarMouseMoved
        // TODO add your handling code here:
        //   Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
    }//GEN-LAST:event_tabla_borrarMouseMoved

    private void tabla_borrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_borrarMouseClicked
        //
    }//GEN-LAST:event_tabla_borrarMouseClicked

    private void tabla_borrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_borrarMousePressed

    }//GEN-LAST:event_tabla_borrarMousePressed

    private void tabla_ventasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventasMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ventasMouseMoved

    private void tabla_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ventasMouseClicked

    private void tabla_ventasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ventasMousePressed

    private void btn_modeficar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficar3ActionPerformed
        // TODO add your handling code here:
        copiar_fila();

    }//GEN-LAST:event_btn_modeficar3ActionPerformed

    private void btn_modeficar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficar4ActionPerformed
        // TODO add your handling code here:
        try {
            limpiar_tabla_borrar();
            if (modeloV.getRowCount() >= 0) {
                modeloBorar = modeloV;
                tabla_borrar.setModel(modeloBorar);
                mostrar_totales(modeloBorar);
            } else {
                JOptionPane.showMessageDialog(null, "no hay ventas");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_modeficar4ActionPerformed

    private void btn_modeficar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficar5ActionPerformed
        // TODO add your handling code here:
        limpiar_tabla_borrar();
    }//GEN-LAST:event_btn_modeficar5ActionPerformed

    private void btn_modeficar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficar6ActionPerformed
        // TODO add your handling code here:
        tabla_borrar.setModel(modeloBorar);
        listar_ventas("");
    }//GEN-LAST:event_btn_modeficar6ActionPerformed

    private void btn_modeficar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeficar7ActionPerformed
        try {
            int fila = tabla_borrar.getSelectedRow();
            if (fila >= 0) {
                modeloBorar.removeRow(fila);
                tabla_borrar.setModel(modeloBorar);
                mostrar_totales(modeloBorar);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_modeficar7ActionPerformed
//frm_principal panel= new frm_principal();


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonHover btn_3;
    private rojerusan.RSButtonHover btn_4;
    private rojerusan.RSButtonHover btn_5;
    private rojerusan.RSMaterialButtonRound btn_modeficar1;
    private rojerusan.RSMaterialButtonRound btn_modeficar3;
    private rojerusan.RSMaterialButtonRound btn_modeficar4;
    private rojerusan.RSMaterialButtonRound btn_modeficar5;
    private rojerusan.RSMaterialButtonRound btn_modeficar6;
    private rojerusan.RSMaterialButtonRound btn_modeficar7;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_ganancias;
    private javax.swing.JLabel lb_ganancias1;
    private javax.swing.JLabel lb_ventas;
    private javax.swing.JLabel lb_ventas1;
    private javax.swing.JLabel lcodigo11;
    private javax.swing.JLabel lcodigo12;
    private javax.swing.JLabel lcodigo14;
    private javax.swing.JLabel lcodigo15;
    private javax.swing.JLabel lcodigo2;
    private javax.swing.JLabel lcodigo22;
    private javax.swing.JLabel lcodigo24;
    private javax.swing.JLabel lcodigo25;
    private javax.swing.JLabel lcodigo26;
    private javax.swing.ButtonGroup radioButon;
    private rojerusan.RSTableMetro tabla_borrar;
    private rojerusan.RSTableMetro tabla_ventas;
    private rojeru_san.componentes.RSYearDate txt_Date_año_mes;
    private rojeru_san.componentes.RSYearDate txt_date_año;
    private rojeru_san.componentes.RSDateChooser txt_date_por_fecha;
    private LIB.JTexfieldPH_FielTex txt_fecha_año;
    // End of variables declaration//GEN-END:variables
}
