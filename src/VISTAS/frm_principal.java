package VISTAS;

//import CONTROLADOR_3.c_terreno;
import com.sun.net.httpserver.Authenticator;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;

import java.awt.event.ActionListener;
import java.net.URI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import rojerusan.RSAnimation;
import Componetes.*;
import javax.swing.ImageIcon;


/**
 *
 * @author raul hacho cutipa
 */
public class frm_principal extends javax.swing.JFrame {

    int x, y;
    private ActionListener la;
    private ImageIcon imageicono;
  
    public frm_principal() {
        initComponents();
        this.setLocationRelativeTo(null);

        mostrar_usuario();
        imnabilitarcampos();
        imageicono = new ImageIcon(this.getClass().getResource("/appapagar/image/logo1.png"));
        this.setIconImage(imageicono.getImage());
    }

    public void mostrar_usuario() {
        try {
            lb_tipo_usuario.setText(datos_usuario.getTipo_usuario());
            lb_nombre_usuario.setText(datos_usuario.getNombre());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al mostrar usuario");
        }
    }

    public void imnabilitarcampos() {
        try {
            if (lb_tipo_usuario.getText().equals("usuario comun")) {
                btn_usuarios.setEnabled(false);
                btn_empresa.setEnabled(false);
                btn_precios.setEnabled(false);
                btn_configuracion.setEnabled(false);
                btn_reporte1.setEnabled(false);
//                btn_Reporte_G_2.setEnabled(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al inabilitar campos");
        }
    }

//       public login log2 = new login();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_titulo = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btn_usuarios = new LIB.FSButtonMD();
        btn_venta = new LIB.FSButtonMD();
        btn_clientes = new LIB.FSButtonMD();
        btn_empresa = new LIB.FSButtonMD();
        btn_precios = new LIB.FSButtonMD();
        btn_configuracion = new LIB.FSButtonMD();
        jcMousePanel11 = new jcMousePanel.jcMousePanel();
        btn_registro = new LIB.FSButtonMD();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lcliente6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lb_nombre_usuario = new javax.swing.JLabel();
        jcMousePanel5 = new jcMousePanel.jcMousePanel();
        fSLabel1 = new LIB.FSLabel();
        lbl_des = new rojerusan.RSLabelVerticalI();
        lb_tipo_usuario = new javax.swing.JLabel();
        btnsoporte = new javax.swing.JButton();
        btn_reporte1 = new LIB.FSButtonMD();
        panelEncabezado = new rspanelgradiente.RSPanelGradiente();
        btnmin = new rojerusan.RSPanelImage();
        btnclose = new rojerusan.RSPanelImage();
        btnmax = new rojerusan.RSPanelImage();
        rSButtonHover1 = new rojerusan.RSButtonHover();
        contenedor = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jpanel_n = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lcodigo6 = new javax.swing.JLabel();
        lcodigo7 = new javax.swing.JLabel();
        lcodigo4 = new javax.swing.JLabel();
        lcodigo5 = new javax.swing.JLabel();
        lcodigo9 = new javax.swing.JLabel();
        lcodigo10 = new javax.swing.JLabel();
        btn_precio = new LIB.FSButtonMD();
        jpanel_n2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lcodigo19 = new javax.swing.JLabel();
        lcodigo20 = new javax.swing.JLabel();
        lcodigo21 = new javax.swing.JLabel();
        lcodigo22 = new javax.swing.JLabel();
        lcodigo23 = new javax.swing.JLabel();
        lcodigo24 = new javax.swing.JLabel();
        btn_usuarios4 = new LIB.FSButtonMD();
        jpanel_n3 = new javax.swing.JPanel();
        lcodigo27 = new javax.swing.JLabel();
        lcodigo28 = new javax.swing.JLabel();
        lcodigo29 = new javax.swing.JLabel();
        lcodigo30 = new javax.swing.JLabel();
        btn_cliente = new LIB.FSButtonMD();
        jPanel11 = new javax.swing.JPanel();
        lcodigo40 = new javax.swing.JLabel();
        lcodigo41 = new javax.swing.JLabel();
        jpanel_n4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lcodigo31 = new javax.swing.JLabel();
        lcodigo32 = new javax.swing.JLabel();
        lcodigo33 = new javax.swing.JLabel();
        lcodigo34 = new javax.swing.JLabel();
        lcodigo35 = new javax.swing.JLabel();
        lcodigo36 = new javax.swing.JLabel();
        btn_entidades = new LIB.FSButtonMD();
        jpanel_n5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lcodigo37 = new javax.swing.JLabel();
        lcodigo38 = new javax.swing.JLabel();
        lcodigo39 = new javax.swing.JLabel();
        lcodigo42 = new javax.swing.JLabel();
        btn_registro_p = new LIB.FSButtonMD();
        jpanel_n6 = new javax.swing.JPanel();
        btn_usuarios8 = new LIB.FSButtonMD();
        lcodigo43 = new javax.swing.JLabel();
        lcodigo44 = new javax.swing.JLabel();
        lcodigo45 = new javax.swing.JLabel();
        label_titulo1 = new javax.swing.JLabel();
        label_titulo2 = new javax.swing.JLabel();
        label_titulo3 = new javax.swing.JLabel();
        label_titulo4 = new javax.swing.JLabel();
        label_titulo5 = new javax.swing.JLabel();
        label_titulo6 = new javax.swing.JLabel();
        label_titulo7 = new javax.swing.JLabel();
        label_titulo8 = new javax.swing.JLabel();
        label_titulo9 = new javax.swing.JLabel();
        rSButtonTriangle1 = new rojerusan.RSButtonTriangle();
        label_titulo10 = new javax.swing.JLabel();
        label_titulo11 = new javax.swing.JLabel();
        jcMousePanel12 = new jcMousePanel.jcMousePanel();
        lcodigo46 = new javax.swing.JLabel();
        label_titulo13 = new javax.swing.JLabel();
        jpanel_n7 = new javax.swing.JPanel();
        btn_Reporte_G_2 = new LIB.FSButtonMD();
        lcodigo47 = new javax.swing.JLabel();
        lcodigo48 = new javax.swing.JLabel();

        label_titulo.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_titulo.setForeground(new java.awt.Color(58, 175, 159));
        label_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo.setText("MENÚ PRINCIPAL");
        label_titulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_tituloMouseDragged(evt);
            }
        });
        label_titulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_tituloMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_tituloMousePressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenu.setBackground(new java.awt.Color(46, 78, 114));
        pnlMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMenuMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnlMenuMouseMoved(evt);
            }
        });
        pnlMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlMenuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMenuMousePressed(evt);
            }
        });
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_usuarios.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_user_24px_2.png"))); // NOI18N
        btn_usuarios.setText("USUARIOS");
        btn_usuarios.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_usuarios.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_usuarios.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_usuarios.setIconTextGap(45);
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 217, -1));

        btn_venta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_two_tickets_24px.png"))); // NOI18N
        btn_venta.setText("VENTAS    ");
        btn_venta.setColorHover(new java.awt.Color(70, 187, 151));
        btn_venta.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_venta.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_venta.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_venta.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_venta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_venta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_venta.setIconTextGap(45);
        btn_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ventaActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 217, -1));

        btn_clientes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_management_30px.png"))); // NOI18N
        btn_clientes.setText("CLIENTES");
        btn_clientes.setColorHover(new java.awt.Color(70, 187, 151));
        btn_clientes.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_clientes.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_clientes.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_clientes.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_clientes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_clientes.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_clientes.setIconTextGap(45);
        btn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clientesActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 217, -1));

        btn_empresa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_company_30px.png"))); // NOI18N
        btn_empresa.setText("EMPRESA");
        btn_empresa.setColorHover(new java.awt.Color(70, 187, 151));
        btn_empresa.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_empresa.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_empresa.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_empresa.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_empresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_empresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_empresa.setIconTextGap(45);
        btn_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_empresaActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 217, -1));

        btn_precios.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_precios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_expensive_price_24px.png"))); // NOI18N
        btn_precios.setText("PRECIOS   ");
        btn_precios.setColorHover(new java.awt.Color(70, 187, 151));
        btn_precios.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_precios.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_precios.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_precios.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_precios.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_precios.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_precios.setIconTextGap(45);
        btn_precios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_preciosActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_precios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 217, -1));

        btn_configuracion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_services_26px.png"))); // NOI18N
        btn_configuracion.setText("DETALLE.");
        btn_configuracion.setColorHover(new java.awt.Color(70, 187, 151));
        btn_configuracion.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_configuracion.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_configuracion.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_configuracion.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_configuracion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_configuracion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_configuracion.setIconTextGap(45);
        btn_configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configuracionActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_configuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 217, -1));

        jcMousePanel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_new_ticket_48px.png"))); // NOI18N

        javax.swing.GroupLayout jcMousePanel11Layout = new javax.swing.GroupLayout(jcMousePanel11);
        jcMousePanel11.setLayout(jcMousePanel11Layout);
        jcMousePanel11Layout.setHorizontalGroup(
            jcMousePanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jcMousePanel11Layout.setVerticalGroup(
            jcMousePanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pnlMenu.add(jcMousePanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 25, 20));

        btn_registro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_registro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_document_24px.png"))); // NOI18N
        btn_registro.setText("REPORTE P.");
        btn_registro.setColorHover(new java.awt.Color(70, 187, 151));
        btn_registro.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_registro.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_registro.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_registro.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_registro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_registro.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_registro.setIconTextGap(45);
        btn_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registroActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 217, -1));

        jPanel2.setBackground(new java.awt.Color(70, 187, 151));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("≡");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 40, 30));

        lcliente6.setBackground(new java.awt.Color(255, 255, 255));
        lcliente6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lcliente6.setForeground(new java.awt.Color(255, 255, 255));
        lcliente6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcliente6.setText("ENTRADAS");
        lcliente6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lcliente6MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lcliente6MouseExited(evt);
            }
        });
        jPanel2.add(lcliente6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        pnlMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, -1));

        jPanel3.setBackground(new java.awt.Color(70, 99, 151));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_nombre_usuario.setBackground(new java.awt.Color(255, 255, 255));
        lb_nombre_usuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_nombre_usuario.setForeground(new java.awt.Color(255, 255, 255));
        lb_nombre_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nombre_usuario.setText("Gabriel");
        lb_nombre_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_nombre_usuarioMouseExited(evt);
            }
        });
        jPanel3.add(lb_nombre_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 20));

        jcMousePanel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_checked_user_male_64px.png"))); // NOI18N

        javax.swing.GroupLayout jcMousePanel5Layout = new javax.swing.GroupLayout(jcMousePanel5);
        jcMousePanel5.setLayout(jcMousePanel5Layout);
        jcMousePanel5Layout.setHorizontalGroup(
            jcMousePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        jcMousePanel5Layout.setVerticalGroup(
            jcMousePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jcMousePanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 42, 40));

        fSLabel1.setBackground(new java.awt.Color(255, 102, 102));
        fSLabel1.setText("");
        fSLabel1.setLineColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(fSLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 60, 60));

        lbl_des.setForeground(new java.awt.Color(255, 255, 255));
        lbl_des.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_des.setText("Principal");
        jPanel3.add(lbl_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 30, 140));

        lb_tipo_usuario.setBackground(new java.awt.Color(255, 255, 255));
        lb_tipo_usuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_tipo_usuario.setForeground(new java.awt.Color(255, 255, 255));
        lb_tipo_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tipo_usuario.setText("Administrador");
        lb_tipo_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_tipo_usuarioMouseExited(evt);
            }
        });
        jPanel3.add(lb_tipo_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 100, 20));

        pnlMenu.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 220, 140));

        btnsoporte.setBackground(new java.awt.Color(70, 187, 151));
        btnsoporte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsoporte.setForeground(new java.awt.Color(255, 255, 255));
        btnsoporte.setText("AYUDA");
        btnsoporte.setContentAreaFilled(false);
        btnsoporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsoporte.setOpaque(true);
        btnsoporte.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnsoporteMouseMoved(evt);
            }
        });
        btnsoporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsoporteMouseExited(evt);
            }
        });
        btnsoporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsoporteActionPerformed(evt);
            }
        });
        pnlMenu.add(btnsoporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 140, 30));

        btn_reporte1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_reporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_pdf_24px_1.png"))); // NOI18N
        btn_reporte1.setText("REPORTES ");
        btn_reporte1.setColorHover(new java.awt.Color(70, 187, 151));
        btn_reporte1.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_reporte1.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_reporte1.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_reporte1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_reporte1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_reporte1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_reporte1.setIconTextGap(45);
        btn_reporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reporte1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btn_reporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 217, -1));

        getContentPane().add(pnlMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-180, 0, 220, 650));

        panelEncabezado.setBackground(new java.awt.Color(244, 248, 247));
        panelEncabezado.setColorPrimario(new java.awt.Color(239, 243, 255));
        panelEncabezado.setColorSecundario(new java.awt.Color(239, 243, 255));
        panelEncabezado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelEncabezadoMouseDragged(evt);
            }
        });
        panelEncabezado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelEncabezadoMousePressed(evt);
            }
        });
        panelEncabezado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panelEncabezadoKeyPressed(evt);
            }
        });
        panelEncabezado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnmin.setToolTipText("Minimizar");
        btnmin.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/minR.png"))); // NOI18N
        btnmin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnminMouseMoved(evt);
            }
        });
        btnmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnminMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnminMouseExited(evt);
            }
        });
        panelEncabezado.add(btnmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 5, 26, 26));

        btnclose.setToolTipText("Cerrar");
        btnclose.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/close.png"))); // NOI18N
        btnclose.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btncloseMouseMoved(evt);
            }
        });
        btnclose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncloseMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncloseMouseExited(evt);
            }
        });
        panelEncabezado.add(btnclose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 5, 26, 26));

        btnmax.setToolTipText("Maximizar Deshabilitado");
        btnmax.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/maxR.png"))); // NOI18N
        btnmax.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnmaxMouseMoved(evt);
            }
        });
        btnmax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnmaxMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmaxMouseExited(evt);
            }
        });
        panelEncabezado.add(btnmax, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 5, 26, 26));

        rSButtonHover1.setBackground(new java.awt.Color(239, 243, 255));
        rSButtonHover1.setForeground(new java.awt.Color(89, 130, 254));
        rSButtonHover1.setText("cerrar sesión");
        rSButtonHover1.setColorHover(new java.awt.Color(239, 243, 255));
        rSButtonHover1.setColorText(new java.awt.Color(89, 130, 254));
        rSButtonHover1.setColorTextHover(new java.awt.Color(0, 153, 255));
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        panelEncabezado.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 220, -1));

        getContentPane().add(panelEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 1180, 40));

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                contenedorMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                contenedorMouseMoved(evt);
            }
        });
        contenedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contenedorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                contenedorMousePressed(evt);
            }
        });
        contenedor.setLayout(new java.awt.BorderLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/fondo4.jpg"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel_n.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        lcodigo6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo6.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo6.setText("TOTAL ");

        lcodigo7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo7.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo7.setText(" S/ 00.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lcodigo6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lcodigo7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lcodigo4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo4.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo4.setText("Precio Adulto");

        lcodigo5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo5.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo5.setText("Precio Infantil");

        lcodigo9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo9.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo9.setText(" S/ 00.00");

        lcodigo10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo10.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo10.setText(" S/ 00.00");

        btn_precio.setBackground(new java.awt.Color(227, 94, 106));
        btn_precio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(255, 204, 0)));
        btn_precio.setText("Precios ");
        btn_precio.setColorHover(new java.awt.Color(70, 187, 151));
        btn_precio.setColorNormal(new java.awt.Color(227, 94, 106));
        btn_precio.setColorPressed(new java.awt.Color(227, 94, 106));
        btn_precio.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_precio.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_precio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_precio.setIconTextGap(45);
        btn_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_precioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_nLayout = new javax.swing.GroupLayout(jpanel_n);
        jpanel_n.setLayout(jpanel_nLayout);
        jpanel_nLayout.setHorizontalGroup(
            jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpanel_nLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(lcodigo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lcodigo10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addComponent(btn_precio, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jpanel_nLayout.setVerticalGroup(
            jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_nLayout.createSequentialGroup()
                .addComponent(btn_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jpanel_nLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelImage1.add(jpanel_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 230, 170));

        jpanel_n2.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        lcodigo19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo19.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo19.setText("TOTAL ");

        lcodigo20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo20.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo20.setText("2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lcodigo19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(lcodigo20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lcodigo21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo21.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo21.setText("Administrador");

        lcodigo22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo22.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo22.setText("Usuario");

        lcodigo23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo23.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo23.setText("01");

        lcodigo24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo24.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo24.setText("01");

        btn_usuarios4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(255, 204, 0)));
        btn_usuarios4.setText("Usuarios");
        btn_usuarios4.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios4.setColorNormal(new java.awt.Color(0, 204, 204));
        btn_usuarios4.setColorPressed(new java.awt.Color(70, 114, 172));
        btn_usuarios4.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios4.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_usuarios4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios4.setIconTextGap(45);
        btn_usuarios4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_n2Layout = new javax.swing.GroupLayout(jpanel_n2);
        jpanel_n2.setLayout(jpanel_n2Layout);
        jpanel_n2Layout.setHorizontalGroup(
            jpanel_n2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_usuarios4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
            .addGroup(jpanel_n2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_n2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lcodigo22, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo21, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpanel_n2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo24, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lcodigo23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jpanel_n2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpanel_n2Layout.setVerticalGroup(
            jpanel_n2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_n2Layout.createSequentialGroup()
                .addComponent(btn_usuarios4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanel_n2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jpanel_n2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelImage1.add(jpanel_n2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 150, 210, -1));

        jpanel_n3.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lcodigo27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo27.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo27.setText("Clientes Internos");

        lcodigo28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo28.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo28.setText("Clientes Externos");

        lcodigo29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo29.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo29.setText("30");

        lcodigo30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo30.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo30.setText("10");

        btn_cliente.setBackground(new java.awt.Color(89, 130, 254));
        btn_cliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_cliente.setText("Clientes");
        btn_cliente.setColorHover(new java.awt.Color(70, 187, 151));
        btn_cliente.setColorNormal(new java.awt.Color(89, 130, 254));
        btn_cliente.setColorPressed(new java.awt.Color(89, 130, 254));
        btn_cliente.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_cliente.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_cliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cliente.setIconTextGap(45);
        btn_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clienteActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        lcodigo40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo40.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo40.setText("TOTAL ");

        lcodigo41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo41.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo41.setText("2");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(lcodigo40, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(lcodigo41, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo40, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo41, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jpanel_n3Layout = new javax.swing.GroupLayout(jpanel_n3);
        jpanel_n3.setLayout(jpanel_n3Layout);
        jpanel_n3Layout.setHorizontalGroup(
            jpanel_n3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
            .addGroup(jpanel_n3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_n3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lcodigo28, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jpanel_n3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo30, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(lcodigo29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jpanel_n3Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpanel_n3Layout.setVerticalGroup(
            jpanel_n3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_n3Layout.createSequentialGroup()
                .addComponent(btn_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanel_n3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jpanel_n3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelImage1.add(jpanel_n3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 390, 210, -1));

        jpanel_n4.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        lcodigo31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo31.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo31.setText("TOTAL ");

        lcodigo32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo32.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo32.setText("04");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lcodigo31, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lcodigo32, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lcodigo33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo33.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo33.setText("Entidades");

        lcodigo34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo34.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo34.setText("Areas");

        lcodigo35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo35.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo35.setText("03");

        lcodigo36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo36.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo36.setText("01");

        btn_entidades.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(0, 204, 204)));
        btn_entidades.setText("Entidades");
        btn_entidades.setColorHover(new java.awt.Color(70, 187, 151));
        btn_entidades.setColorNormal(new java.awt.Color(0, 153, 153));
        btn_entidades.setColorPressed(new java.awt.Color(70, 187, 151));
        btn_entidades.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_entidades.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_entidades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_entidades.setIconTextGap(45);
        btn_entidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entidadesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_n4Layout = new javax.swing.GroupLayout(jpanel_n4);
        jpanel_n4.setLayout(jpanel_n4Layout);
        jpanel_n4Layout.setHorizontalGroup(
            jpanel_n4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpanel_n4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_n4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lcodigo34, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpanel_n4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lcodigo35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(btn_entidades, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
        jpanel_n4Layout.setVerticalGroup(
            jpanel_n4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_n4Layout.createSequentialGroup()
                .addComponent(btn_entidades, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanel_n4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo33, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(lcodigo36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jpanel_n4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lcodigo35, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(lcodigo34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelImage1.add(jpanel_n4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 210, -1));

        jpanel_n5.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        lcodigo37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo37.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo37.setText("TOTAL");

        lcodigo38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo38.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo38.setText("2");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(lcodigo37, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(lcodigo38, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lcodigo39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo39.setForeground(new java.awt.Color(204, 204, 204));
        lcodigo39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lcodigo39.setText("Mensual");

        lcodigo42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo42.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo42.setText("21212");

        btn_registro_p.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_registro_p.setText("Registros");
        btn_registro_p.setColorHover(new java.awt.Color(70, 187, 151));
        btn_registro_p.setColorNormal(new java.awt.Color(255, 51, 153));
        btn_registro_p.setColorPressed(new java.awt.Color(2, 210, 185));
        btn_registro_p.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_registro_p.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_registro_p.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_registro_p.setIconTextGap(45);
        btn_registro_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registro_pActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_n5Layout = new javax.swing.GroupLayout(jpanel_n5);
        jpanel_n5.setLayout(jpanel_n5Layout);
        jpanel_n5Layout.setHorizontalGroup(
            jpanel_n5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_registro_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpanel_n5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lcodigo39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(lcodigo42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanel_n5Layout.setVerticalGroup(
            jpanel_n5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_n5Layout.createSequentialGroup()
                .addComponent(btn_registro_p, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jpanel_n5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo39, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo42, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelImage1.add(jpanel_n5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 390, 130));

        jpanel_n6.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btn_usuarios8.setText("Ventas");
        btn_usuarios8.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios8.setColorNormal(new java.awt.Color(255, 0, 51));
        btn_usuarios8.setColorPressed(new java.awt.Color(255, 204, 0));
        btn_usuarios8.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios8.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_usuarios8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios8.setIconTextGap(45);
        btn_usuarios8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios8ActionPerformed(evt);
            }
        });

        lcodigo43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo43.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo43.setText("TOTAL");
        lcodigo43.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));

        lcodigo44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo44.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo44.setText("3456787654");

        javax.swing.GroupLayout jpanel_n6Layout = new javax.swing.GroupLayout(jpanel_n6);
        jpanel_n6.setLayout(jpanel_n6Layout);
        jpanel_n6Layout.setHorizontalGroup(
            jpanel_n6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_n6Layout.createSequentialGroup()
                .addComponent(lcodigo43, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lcodigo44, javax.swing.GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btn_usuarios8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jpanel_n6Layout.setVerticalGroup(
            jpanel_n6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_n6Layout.createSequentialGroup()
                .addComponent(btn_usuarios8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanel_n6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodigo43, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcodigo44, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelImage1.add(jpanel_n6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 160, 90));

        lcodigo45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo45.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo45.setText("cuscocode");
        panelImage1.add(lcodigo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 280, 40));

        label_titulo1.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_titulo1.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_titulo1.setText("PUNTO DE VENTA");
        label_titulo1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo1MouseDragged(evt);
            }
        });
        label_titulo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo1MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 250, -1));

        label_titulo2.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo2.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_titulo2.setText("CUSCO");
        label_titulo2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo2MouseDragged(evt);
            }
        });
        label_titulo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo2MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 260, -1));

        label_titulo3.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo3.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo3MouseDragged(evt);
            }
        });
        label_titulo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo3MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 60, 60));

        label_titulo4.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo4.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_titulo4.setText("SOFTWARE");
        label_titulo4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo4MouseDragged(evt);
            }
        });
        label_titulo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo4MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, -1));

        label_titulo5.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo5.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo5MouseDragged(evt);
            }
        });
        label_titulo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo5MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo5MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 60, 60));

        label_titulo6.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo6.setForeground(new java.awt.Color(239, 243, 255));
        label_titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo6.setText("f");
        label_titulo6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 243, 255)));
        label_titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo6MouseDragged(evt);
            }
        });
        label_titulo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo6MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo6MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 60, 60));

        label_titulo7.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo7.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo7MouseDragged(evt);
            }
        });
        label_titulo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo7MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo7MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 70, 60));

        label_titulo8.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo8.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo8MouseDragged(evt);
            }
        });
        label_titulo8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo8MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo8MousePressed(evt);
            }
        });
        label_titulo8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                label_titulo8KeyPressed(evt);
            }
        });
        panelImage1.add(label_titulo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 70, 80));

        label_titulo9.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo9.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo9MouseDragged(evt);
            }
        });
        label_titulo9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo9MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo9MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 60, 60));

        rSButtonTriangle1.setBackground(new java.awt.Color(89, 130, 254));
        rSButtonTriangle1.setText("rSButtonTriangle1");
        rSButtonTriangle1.setColorHover(new java.awt.Color(0, 153, 255));
        rSButtonTriangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonTriangle1ActionPerformed(evt);
            }
        });
        panelImage1.add(rSButtonTriangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 580, 30, 30));

        label_titulo10.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo10.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo10.setForeground(new java.awt.Color(255, 255, 255));
        label_titulo10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo10.setText("f");
        label_titulo10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo10MouseDragged(evt);
            }
        });
        label_titulo10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo10MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo10MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 70, 70));

        label_titulo11.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo11.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        label_titulo11.setForeground(new java.awt.Color(255, 255, 255));
        label_titulo11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo11.setText("f");
        label_titulo11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo11MouseDragged(evt);
            }
        });
        label_titulo11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo11MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo11MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 70, 70));

        jcMousePanel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoWhite_1.png"))); // NOI18N

        javax.swing.GroupLayout jcMousePanel12Layout = new javax.swing.GroupLayout(jcMousePanel12);
        jcMousePanel12.setLayout(jcMousePanel12Layout);
        jcMousePanel12Layout.setHorizontalGroup(
            jcMousePanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jcMousePanel12Layout.setVerticalGroup(
            jcMousePanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        panelImage1.add(jcMousePanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 70, 70));

        lcodigo46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo46.setForeground(new java.awt.Color(153, 153, 153));
        lcodigo46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo46.setText("CUSCOCODE");
        panelImage1.add(lcodigo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 130, -1));

        label_titulo13.setBackground(new java.awt.Color(58, 175, 159));
        label_titulo13.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        label_titulo13.setForeground(new java.awt.Color(89, 130, 254));
        label_titulo13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_titulo13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_titulo13.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                label_titulo13MouseDragged(evt);
            }
        });
        label_titulo13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_titulo13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_titulo13MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label_titulo13MousePressed(evt);
            }
        });
        panelImage1.add(label_titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, 60, 70));

        jpanel_n7.setBackground(new java.awt.Color(255, 255, 255));
        jpanel_n7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btn_Reporte_G_2.setText("Rerportes Genaral");
        btn_Reporte_G_2.setColorHover(new java.awt.Color(70, 187, 151));
        btn_Reporte_G_2.setColorNormal(new java.awt.Color(255, 204, 0));
        btn_Reporte_G_2.setColorPressed(new java.awt.Color(255, 204, 0));
        btn_Reporte_G_2.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_Reporte_G_2.setFont(new java.awt.Font("techno overload BRK", 0, 24)); // NOI18N
        btn_Reporte_G_2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Reporte_G_2.setIconTextGap(45);
        btn_Reporte_G_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Reporte_G_2ActionPerformed(evt);
            }
        });

        lcodigo47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo47.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo47.setText("TOTAL");
        lcodigo47.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));

        lcodigo48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lcodigo48.setForeground(new java.awt.Color(51, 51, 51));
        lcodigo48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lcodigo48.setText("2");

        javax.swing.GroupLayout jpanel_n7Layout = new javax.swing.GroupLayout(jpanel_n7);
        jpanel_n7.setLayout(jpanel_n7Layout);
        jpanel_n7Layout.setHorizontalGroup(
            jpanel_n7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_n7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btn_Reporte_G_2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lcodigo47, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lcodigo48, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpanel_n7Layout.setVerticalGroup(
            jpanel_n7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_n7Layout.createSequentialGroup()
                .addGroup(jpanel_n7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel_n7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lcodigo48, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lcodigo47, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Reporte_G_2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelImage1.add(jpanel_n7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, 390, 40));

        contenedor.add(panelImage1, java.awt.BorderLayout.CENTER);

        getContentPane().add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1180, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void label_tituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_tituloMouseDragged

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_label_tituloMouseDragged

    private void label_tituloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_tituloMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_label_tituloMouseEntered

    private void label_tituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_tituloMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_label_tituloMousePressed

    public void desplegable() {
        int posicion = pnlMenu.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
        } else {
            Animacion.Animacion.mover_derecha(-180, 0, 2, 10, pnlMenu);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//       desplegable();
        int posicion = pnlMenu.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
        } else {
            Animacion.Animacion.mover_derecha(-180, 0, 2, 10, pnlMenu);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuariosActionPerformed

        pnl_usuarios panel_user = new pnl_usuarios();
        label_titulo.setText("REGISTRO DE USUARIO");
        lbl_des.setText("USER");
        contenedor.removeAll();
        contenedor.add(panel_user);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_usuariosActionPerformed

    private void btn_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ventaActionPerformed
        pnl_ventas2 pnl_Ventas = new pnl_ventas2();
        label_titulo.setText("FORMULARIO DE VENTAS");
        lbl_des.setText("VENTAS");
        contenedor.removeAll();
        contenedor.add(pnl_Ventas);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_ventaActionPerformed

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
        // TODO add your handling code here:
        pnl_clientes panel_Clientes = new pnl_clientes();
        label_titulo.setText("REGISTRO DE CLIENTES");
        lbl_des.setText("CLIENTES");
        contenedor.removeAll();
        contenedor.add(panel_Clientes);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void btn_preciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_preciosActionPerformed
        pnl_precio panel_Precio = new pnl_precio();
        label_titulo.setText("REGISTRO DE PRECIOS");
        lbl_des.setText("PRECIO");
        contenedor.removeAll();
        contenedor.add(panel_Precio);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_preciosActionPerformed

    private void btn_configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_configuracionActionPerformed

        //  fig.setVisible(true);
        pnl_Otros panel_eliminar = new pnl_Otros();
        //  label_titulo.setText("REGISTRO DE PRECIOS");
        //  lbl_des.setText("PRECIO");
        contenedor.removeAll();
        contenedor.add(panel_eliminar);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_configuracionActionPerformed

    private void pnlMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMousePressed

    }//GEN-LAST:event_pnlMenuMousePressed

    private void pnlMenuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseDragged
        // TODO add your handling code here:
        Point p = MouseInfo.getPointerInfo().getLocation();
//        this.setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_pnlMenuMouseDragged

    private void lcliente6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lcliente6MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_lcliente6MouseExited

    private void pnlMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlMenuMouseExited

    private void pnlMenuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlMenuMouseMoved

    private void btnminMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminMouseMoved
        //        holaaaaaaaaaaaaaaaaa
        btnmin.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/minA.png")));
    }//GEN-LAST:event_btnminMouseMoved

    private void btnminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminMouseClicked
        // TODO add your handling code here:
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnminMouseClicked

    private void btnminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnminMouseExited
        // TODO add your handling code here:
        btnmin.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/minR.png")));
    }//GEN-LAST:event_btnminMouseExited

    private void btncloseMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncloseMouseMoved
        // TODO add your handling code here:
        btnclose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnclose.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/closeA.png")));
    }//GEN-LAST:event_btncloseMouseMoved

    private void btncloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncloseMouseClicked

        try {

            RSAnimation.setMoverDerecha(this.getX(), 1500, 2, 8, this);
            //Ahora le daremos un tiempo para que la ventana se cierre
            //utilizamos un Thread, le damos 1 segundo
            Thread.sleep(1000);
            //y posteriormente cerramos la ventana
            //            this.dispose();
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Authenticator.Success.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btncloseMouseClicked

    private void btncloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncloseMouseExited
        // TODO add your handling code here:
        btnclose.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/close.png")));
    }//GEN-LAST:event_btncloseMouseExited

    private void btnmaxMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmaxMouseMoved
        btnmax.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/maxA.png")));
    }//GEN-LAST:event_btnmaxMouseMoved
    String max = "oscuro";
    private void btnmaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmaxMouseClicked

        if (max.equals("claro")) {
            max = "oscuro";
            //            this.setExtendedState(NORMAL);

        } else if (max.equals("oscuro")) {
            max = "claro";
            //            this.setExtendedState(MAXIMIZED_BOTH);

        }
    }//GEN-LAST:event_btnmaxMouseClicked

    private void btnmaxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmaxMouseExited
        btnmax.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/maxR.png")));
    }//GEN-LAST:event_btnmaxMouseExited

    private void panelEncabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEncabezadoMouseDragged

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelEncabezadoMouseDragged

    private void panelEncabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEncabezadoMousePressed

        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelEncabezadoMousePressed

    private void panelEncabezadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelEncabezadoKeyPressed

    }//GEN-LAST:event_panelEncabezadoKeyPressed

    private void btn_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_empresaActionPerformed

        pnl_empresa panel_Empresa = new pnl_empresa();
        label_titulo.setText("REGISTRO DE ENTIDAD");
        lbl_des.setText(" ENTIDAD");
        contenedor.removeAll();
        contenedor.add(panel_Empresa);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_empresaActionPerformed

    private void btn_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registroActionPerformed
        // TODO add your handling code here:
        pnl_reportes_p panel_personal = new pnl_reportes_p();
        label_titulo.setText("");
        lbl_des.setText(" ENTIDAD");
        contenedor.removeAll();
        contenedor.add(panel_personal);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_registroActionPerformed

    private void lb_nombre_usuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nombre_usuarioMouseExited
        // TODO add your handling code here:

//                this.setVisible(false);       
    }//GEN-LAST:event_lb_nombre_usuarioMouseExited

    private void btnsoporteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsoporteMouseMoved


    }//GEN-LAST:event_btnsoporteMouseMoved

    private void btnsoporteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsoporteMouseExited

    }//GEN-LAST:event_btnsoporteMouseExited
    frm_AcercaD i = new frm_AcercaD();
    private void btnsoporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsoporteActionPerformed

        //        RSAnimation.setSubir(60, -480, 2, 2, this);
//                RSAnimation.setBajar(60, 650, 2, 2, this);
        i.setVisible(true);
        //                this.setVisible(false);
        ////
    }//GEN-LAST:event_btnsoporteActionPerformed

    private void contenedorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMouseDragged

    }//GEN-LAST:event_contenedorMouseDragged

    private void contenedorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMouseMoved

    }//GEN-LAST:event_contenedorMouseMoved

    private void contenedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMouseClicked

    }//GEN-LAST:event_contenedorMouseClicked

    private void contenedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contenedorMousePressed

    }//GEN-LAST:event_contenedorMousePressed

    private void label_titulo11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo11MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo11MousePressed

    private void label_titulo11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo11MouseEntered

    private void label_titulo11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo11MouseClicked

    private void label_titulo11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo11MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo11MouseDragged

    private void label_titulo10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo10MousePressed

    private void label_titulo10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo10MouseEntered

    private void label_titulo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo10MouseClicked
        // TODO add your handling code here:
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/pages/category/Graphic-Designer/CuscoCode-101821521380078/"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_label_titulo10MouseClicked

    private void label_titulo10MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo10MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo10MouseDragged

    private void label_titulo9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo9MousePressed

    private void label_titulo9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo9MouseEntered

    private void label_titulo9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo9MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo9MouseDragged

    private void label_titulo8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_label_titulo8KeyPressed
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/pages/category/Graphic-Designer/CuscoCode-101821521380078/"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }//GEN-LAST:event_label_titulo8KeyPressed

    private void label_titulo8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo8MousePressed

    private void label_titulo8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo8MouseEntered

    private void label_titulo8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo8MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo8MouseDragged

    private void label_titulo7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo7MousePressed

    private void label_titulo7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo7MouseEntered

    private void label_titulo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo7MouseClicked

        try {
            Desktop.getDesktop().browse(URI.create("https://twitter.com/cuscocode"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_label_titulo7MouseClicked

    private void label_titulo7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo7MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo7MouseDragged

    private void label_titulo6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo6MousePressed

    private void label_titulo6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo6MouseEntered

    private void label_titulo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo6MouseClicked
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/pages/category/Graphic-Designer/CuscoCode-101821521380078/"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_label_titulo6MouseClicked

    private void label_titulo6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo6MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo6MouseDragged

    private void label_titulo5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo5MousePressed

    private void label_titulo5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo5MouseEntered

    private void label_titulo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo5MouseClicked
        try {
            Desktop.getDesktop().browse(URI.create("https://www.pinterest.com/cuscocode/"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }//GEN-LAST:event_label_titulo5MouseClicked

    private void label_titulo5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo5MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo5MouseDragged

    private void label_titulo4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo4MousePressed

    private void label_titulo4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo4MouseEntered

    private void label_titulo4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo4MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo4MouseDragged

    private void label_titulo3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo3MousePressed

    private void label_titulo3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo3MouseEntered

    private void label_titulo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo3MouseClicked

        try {
            Desktop.getDesktop().browse(URI.create("https://www.youtube.com/watch?v=Oml1sJac3Nk&ab_channel=CuscoCode"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_label_titulo3MouseClicked

    private void label_titulo3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo3MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo3MouseDragged

    private void label_titulo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo2MousePressed

    private void label_titulo2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo2MouseEntered

    private void label_titulo2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo2MouseDragged

    private void label_titulo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo1MousePressed

    private void label_titulo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo1MouseEntered

    private void label_titulo1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo1MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo1MouseDragged

    private void btn_usuarios8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios8ActionPerformed
        btn_venta.doClick();
    }//GEN-LAST:event_btn_usuarios8ActionPerformed

    private void btn_registro_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registro_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_registro_pActionPerformed

    private void btn_entidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entidadesActionPerformed
        btn_empresa.doClick();
    }//GEN-LAST:event_btn_entidadesActionPerformed

    private void btn_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clienteActionPerformed
        btn_clientes.doClick();
    }//GEN-LAST:event_btn_clienteActionPerformed

    private void btn_usuarios4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios4ActionPerformed
        btn_usuarios.doClick();
    }//GEN-LAST:event_btn_usuarios4ActionPerformed

    private void btn_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_precioActionPerformed
        btn_precios.doClick();
    }//GEN-LAST:event_btn_precioActionPerformed

    private void label_titulo13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo13MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo13MousePressed

    private void label_titulo13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo13MouseEntered

    private void label_titulo13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo13MouseClicked
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/pages/category/Graphic-Designer/CuscoCode-101821521380078/"));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_label_titulo13MouseClicked

    private void label_titulo13MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_titulo13MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_label_titulo13MouseDragged

    private void btn_reporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reporte1ActionPerformed
        // TODO add your handling code here:
        pnl_reporte pnl_reportess = new pnl_reporte();
        label_titulo.setText("REPORTES DE LAS VENTAS");
        lbl_des.setText("REPORTE");
        contenedor.removeAll();
        contenedor.add(pnl_reportess);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btn_reporte1ActionPerformed
    public void cerrar() {
        frm_principal.this.dispose();
    }
    private void rSButtonTriangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonTriangle1ActionPerformed
    
    }//GEN-LAST:event_rSButtonTriangle1ActionPerformed

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        this.setVisible(false);

        login log = new login();
        log.setVisible(true);
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void lcliente6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lcliente6MouseClicked
        desplegable();
    }//GEN-LAST:event_lcliente6MouseClicked

    private void lb_tipo_usuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_tipo_usuarioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_tipo_usuarioMouseExited

    private void btn_Reporte_G_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Reporte_G_2ActionPerformed

        btn_reporte1.doClick();
    }//GEN-LAST:event_btn_Reporte_G_2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_principal().setVisible(true);

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(frm_principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(frm_principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(frm_principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FSButtonMD btn_Reporte_G_2;
    private LIB.FSButtonMD btn_cliente;
    private LIB.FSButtonMD btn_clientes;
    private LIB.FSButtonMD btn_configuracion;
    private LIB.FSButtonMD btn_empresa;
    private LIB.FSButtonMD btn_entidades;
    private LIB.FSButtonMD btn_precio;
    private LIB.FSButtonMD btn_precios;
    private LIB.FSButtonMD btn_registro;
    private LIB.FSButtonMD btn_registro_p;
    private LIB.FSButtonMD btn_reporte1;
    private LIB.FSButtonMD btn_usuarios;
    private LIB.FSButtonMD btn_usuarios4;
    private LIB.FSButtonMD btn_usuarios8;
    private LIB.FSButtonMD btn_venta;
    private rojerusan.RSPanelImage btnclose;
    private rojerusan.RSPanelImage btnmax;
    private rojerusan.RSPanelImage btnmin;
    private javax.swing.JButton btnsoporte;
    private javax.swing.JPanel contenedor;
    private LIB.FSLabel fSLabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private jcMousePanel.jcMousePanel jcMousePanel11;
    private jcMousePanel.jcMousePanel jcMousePanel12;
    private jcMousePanel.jcMousePanel jcMousePanel5;
    private javax.swing.JPanel jpanel_n;
    private javax.swing.JPanel jpanel_n2;
    private javax.swing.JPanel jpanel_n3;
    private javax.swing.JPanel jpanel_n4;
    private javax.swing.JPanel jpanel_n5;
    private javax.swing.JPanel jpanel_n6;
    private javax.swing.JPanel jpanel_n7;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JLabel label_titulo1;
    private javax.swing.JLabel label_titulo10;
    private javax.swing.JLabel label_titulo11;
    private javax.swing.JLabel label_titulo13;
    private javax.swing.JLabel label_titulo2;
    private javax.swing.JLabel label_titulo3;
    private javax.swing.JLabel label_titulo4;
    private javax.swing.JLabel label_titulo5;
    private javax.swing.JLabel label_titulo6;
    private javax.swing.JLabel label_titulo7;
    private javax.swing.JLabel label_titulo8;
    private javax.swing.JLabel label_titulo9;
    private javax.swing.JLabel lb_nombre_usuario;
    private javax.swing.JLabel lb_tipo_usuario;
    private rojerusan.RSLabelVerticalI lbl_des;
    private javax.swing.JLabel lcliente6;
    private javax.swing.JLabel lcodigo10;
    private javax.swing.JLabel lcodigo19;
    private javax.swing.JLabel lcodigo20;
    private javax.swing.JLabel lcodigo21;
    private javax.swing.JLabel lcodigo22;
    private javax.swing.JLabel lcodigo23;
    private javax.swing.JLabel lcodigo24;
    private javax.swing.JLabel lcodigo27;
    private javax.swing.JLabel lcodigo28;
    private javax.swing.JLabel lcodigo29;
    private javax.swing.JLabel lcodigo30;
    private javax.swing.JLabel lcodigo31;
    private javax.swing.JLabel lcodigo32;
    private javax.swing.JLabel lcodigo33;
    private javax.swing.JLabel lcodigo34;
    private javax.swing.JLabel lcodigo35;
    private javax.swing.JLabel lcodigo36;
    private javax.swing.JLabel lcodigo37;
    private javax.swing.JLabel lcodigo38;
    private javax.swing.JLabel lcodigo39;
    private javax.swing.JLabel lcodigo4;
    private javax.swing.JLabel lcodigo40;
    private javax.swing.JLabel lcodigo41;
    private javax.swing.JLabel lcodigo42;
    private javax.swing.JLabel lcodigo43;
    private javax.swing.JLabel lcodigo44;
    private javax.swing.JLabel lcodigo45;
    private javax.swing.JLabel lcodigo46;
    private javax.swing.JLabel lcodigo47;
    private javax.swing.JLabel lcodigo48;
    private javax.swing.JLabel lcodigo5;
    private javax.swing.JLabel lcodigo6;
    private javax.swing.JLabel lcodigo7;
    private javax.swing.JLabel lcodigo9;
    private rspanelgradiente.RSPanelGradiente panelEncabezado;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    public static javax.swing.JPanel pnlMenu;
    private rojerusan.RSButtonHover rSButtonHover1;
    private rojerusan.RSButtonTriangle rSButtonTriangle1;
    // End of variables declaration//GEN-END:variables

}
