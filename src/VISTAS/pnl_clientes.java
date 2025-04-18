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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raul hacho cutipa
 */
public class pnl_clientes extends javax.swing.JPanel {
    
    private ImageIcon imageicono;
    DefaultTableModel modeloC;
    c_cliente omCliente = new c_cliente();
    
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    File archivo2;
    byte[] bytesImg;
    ManejoDeImagenes gestion = new ManejoDeImagenes();
    //------------------------------------------------

    //  frm_principal frmPrincipal = new frm_principal();
//    c_usuario omPersonal = new c_usuario();
    public pnl_clientes() {
        initComponents();
        
         imageicono = new ImageIcon(this.getClass().getResource("/appapagar/image/logo1.png"));
//        
        
        txt_idcliente.setEnabled(false);
        listar("");
        txt_buscar.setEnabled(true);
    }
    
    void limpiar() {
        txt_idcliente.setText("");
        txt_dni.setText("");
        buttonGroup1.clearSelection();
        
    }
    
    void listar(String dato) {
        try {
            modeloC = omCliente.ListarCliente(dato);
            tabla.setModel(modeloC);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al listar visitante");
        }
    }
    
    void insertar() {
        try {
            if (/*txt_dni.getText().isEmpty() ||*/ buttonGroup1.getSelection() == null) {
                
                JOptionPane.showConfirmDialog(null, "complete los campos");
            } else {
                
               String tipoVisitante = "";
                if (rb_local.isSelected()) {
                    tipoVisitante ="Local";
                }
                if (rb_externo.isSelected()) {
                   tipoVisitante ="Externo";
                }
                if (rb_extranjero.isSelected()) {
                     tipoVisitante ="Extranjero";
                }
                
                String dni="SIN DNI";
                if (!txt_dni.getText().isEmpty()) {
                    dni=txt_dni.getText();
                }
                omCliente.insertarCliente(dni, tipoVisitante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al insertar datos cliente");
        }
    }
    
    void recuperar_usuario() {
        int select = tabla.getSelectedRow();
        txt_idcliente.setText(modeloC.getValueAt(select, 0).toString());
        txt_dni.setText(modeloC.getValueAt(select, 1).toString());
        if (modeloC.getValueAt(select, 2).toString().equals("Externo")) {
            rb_externo.setSelected(true);
        }
        if (modeloC.getValueAt(select, 2).toString().equals("Extranjero")) {
            rb_extranjero.setSelected(true);
        }
        
        
        
    }
    
    void modificar() {
        try {
            if (txt_dni.getText().isEmpty() || buttonGroup1.getSelection() == null) {
                
                JOptionPane.showMessageDialog(null, "complete los campos");
            } else {
               String tipoVisitante = "";
                if (rb_local.isSelected()) {
                    tipoVisitante ="Local";
                }
                if (rb_externo.isSelected()) {
                   tipoVisitante ="Externo";
                }
                if (rb_extranjero.isSelected()) {
                     tipoVisitante ="Extranjero";
                }
                omCliente.modificarCliente(txt_idcliente.getText(), txt_dni.getText(), tipoVisitante);
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "ocurrio un error al modificar");
        }
    }
    
    void eliminar() {
        try {
            
            int sselect = tabla.getSelectedRow();
            if (sselect >= 0) {
                omCliente.eliminarCliente(modeloC.getValueAt(sselect, 0).toString());
            } else {
                
                JOptionPane.showMessageDialog(null, "selecione para eliminar");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al eliminar datos cliente");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new rojerusan.RSTableMetro();
        btn_usuarios3 = new LIB.FSButtonMD();
        rSPanelGradiente1 = new rspanelgradiente.RSPanelGradiente();
        jLabel3 = new javax.swing.JLabel();
        btn_usuarios1 = new LIB.FSButtonMD();
        btn_usuarios2 = new LIB.FSButtonMD();
        txt_buscar = new LIB.JTexfieldPH_FielTex();
        rSPanelGradiente5 = new rspanelgradiente.RSPanelGradiente();
        jLabel1 = new javax.swing.JLabel();
        rSPanelGradiente3 = new rspanelgradiente.RSPanelGradiente();
        lblImagen = new rojerusan.RSPanelCircleImage();
        jLabel2 = new javax.swing.JLabel();
        btn_usuario2 = new rojerusan.RSMaterialButtonRound();
        txt_dni = new rojeru_san.RSMTextFull();
        rSPanelGradiente2 = new rspanelgradiente.RSPanelGradiente();
        lcodigo = new javax.swing.JLabel();
        lblcorreo = new javax.swing.JLabel();
        txt_idcliente = new rojeru_san.RSMTextFull();
        lblcorreo1 = new javax.swing.JLabel();
        rb_local = new check.RB();
        rb_externo = new check.RB();
        rb_extranjero = new check.RB();

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

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "DNI", "TIPO"
            }
        ));
        tabla.setAltoHead(25);
        tabla.setColorBackgoundHead(new java.awt.Color(2, 210, 185));
        tabla.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla.setColorFilasBackgound2(new java.awt.Color(2, 220, 185));
        tabla.setColorFilasForeground1(new java.awt.Color(2, 210, 160));
        tabla.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tabla.setColorSelBackgound(new java.awt.Color(50, 105, 125));
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla.setGridColor(new java.awt.Color(255, 255, 255));
        tabla.setGrosorBordeFilas(0);
        tabla.setMultipleSeleccion(false);
        tabla.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tabla.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tablaMouseMoved(evt);
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 770, 250));

        btn_usuarios3.setBackground(new java.awt.Color(2, 210, 185));
        btn_usuarios3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        btn_usuarios3.setText("CERRAR SECIÓN");
        btn_usuarios3.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios3.setColorNormal(new java.awt.Color(2, 210, 185));
        btn_usuarios3.setColorPressed(new java.awt.Color(2, 210, 185));
        btn_usuarios3.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios3.setIconTextGap(45);
        btn_usuarios3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios3ActionPerformed(evt);
            }
        });
        add(btn_usuarios3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 150, -1));

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(51, 153, 255));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente1.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_user_24px_2.png"))); // NOI18N
        rSPanelGradiente1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 130, 60));

        add(rSPanelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 220));

        btn_usuarios1.setBackground(new java.awt.Color(2, 210, 185));
        btn_usuarios1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        btn_usuarios1.setText("ELIMINAR");
        btn_usuarios1.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios1.setColorNormal(new java.awt.Color(2, 210, 185));
        btn_usuarios1.setColorPressed(new java.awt.Color(2, 210, 185));
        btn_usuarios1.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios1.setIconTextGap(45);
        btn_usuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios1ActionPerformed(evt);
            }
        });
        add(btn_usuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 150, -1));

        btn_usuarios2.setBackground(new java.awt.Color(2, 210, 185));
        btn_usuarios2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        btn_usuarios2.setText("MODIFICAR");
        btn_usuarios2.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios2.setColorNormal(new java.awt.Color(2, 210, 185));
        btn_usuarios2.setColorPressed(new java.awt.Color(2, 210, 185));
        btn_usuarios2.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios2.setIconTextGap(45);
        btn_usuarios2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios2ActionPerformed(evt);
            }
        });
        add(btn_usuarios2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 140, -1));

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
        add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 220, 40));

        rSPanelGradiente5.setBackground(new java.awt.Color(255, 197, 0));
        rSPanelGradiente5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        rSPanelGradiente5.setColorPrimario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente5.setColorSecundario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente5.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_search_30px.png"))); // NOI18N
        jLabel1.setToolTipText("");
        rSPanelGradiente5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        add(rSPanelGradiente5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 40, 40));

        rSPanelGradiente3.setColorPrimario(new java.awt.Color(70, 114, 172));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(70, 99, 151));
        rSPanelGradiente3.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagen.setColorBorde(new java.awt.Color(70, 187, 151));
        lblImagen.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_company_30px.png"))); // NOI18N
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_user_24px_4.png"))); // NOI18N

        javax.swing.GroupLayout lblImagenLayout = new javax.swing.GroupLayout(lblImagen);
        lblImagen.setLayout(lblImagenLayout);
        lblImagenLayout.setHorizontalGroup(
            lblImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblImagenLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        lblImagenLayout.setVerticalGroup(
            lblImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblImagenLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        rSPanelGradiente3.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 110));

        btn_usuario2.setBackground(new java.awt.Color(70, 187, 151));
        btn_usuario2.setBorder(null);
        btn_usuario2.setText("Registrar");
        btn_usuario2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_usuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario2ActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(btn_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 230, 50));

        txt_dni.setBackground(new java.awt.Color(46, 78, 114));
        txt_dni.setForeground(new java.awt.Color(255, 255, 255));
        txt_dni.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_dni.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_dni.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_dni.setColorTransparente(true);
        txt_dni.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_dni.setPlaceholder("DNI *");
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
        rSPanelGradiente3.add(txt_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 150, 37));

        rSPanelGradiente2.setColorPrimario(new java.awt.Color(70, 187, 151));
        rSPanelGradiente2.setColorSecundario(new java.awt.Color(70, 187, 151));
        rSPanelGradiente2.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lcodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo.setText("Registro Clientes");
        rSPanelGradiente2.add(lcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 50));

        rSPanelGradiente3.add(rSPanelGradiente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 30, 270, 50));

        lblcorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblcorreo.setText("Campo tipo visitante  ®");
        rSPanelGradiente3.add(lblcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 170, -1));

        txt_idcliente.setBackground(new java.awt.Color(180, 186, 192));
        txt_idcliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_idcliente.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_idcliente.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_idcliente.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_idcliente.setColorTransparente(true);
        txt_idcliente.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_idcliente.setPlaceholder("Numero Identificador *");
        txt_idcliente.setSoloLetras(true);
        txt_idcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idclienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idclienteKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_idcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 180, 37));

        lblcorreo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcorreo1.setForeground(new java.awt.Color(255, 255, 255));
        lblcorreo1.setText("Datos Opcionales  ®");
        rSPanelGradiente3.add(lblcorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 170, -1));

        buttonGroup1.add(rb_local);
        rb_local.setForeground(new java.awt.Color(255, 255, 255));
        rb_local.setText("Local");
        rb_local.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rSPanelGradiente3.add(rb_local, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        buttonGroup1.add(rb_externo);
        rb_externo.setForeground(new java.awt.Color(255, 255, 255));
        rb_externo.setText("Externo");
        rb_externo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_externo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_externoActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(rb_externo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, -1, -1));

        buttonGroup1.add(rb_extranjero);
        rb_extranjero.setForeground(new java.awt.Color(255, 255, 255));
        rb_extranjero.setText("Extranjero");
        rb_extranjero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_extranjero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_extranjeroActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(rb_extranjero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, -1, -1));

        add(rSPanelGradiente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 370, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        //
        recuperar_usuario();
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed
        

    }//GEN-LAST:event_tablaMousePressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseDragged

    private void tablaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseMoved
        // TODO add your handling code here:
        //   Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
    }//GEN-LAST:event_tablaMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void btn_usuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios1ActionPerformed
        
        eliminar();
        listar("");
    }//GEN-LAST:event_btn_usuarios1ActionPerformed

    private void btn_usuarios2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios2ActionPerformed
        // TODO add your handling code here:
        modificar();
        listar("");
    }//GEN-LAST:event_btn_usuarios2ActionPerformed

    private void lblImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenMouseClicked

    }//GEN-LAST:event_lblImagenMouseClicked
//frm_principal panel= new frm_principal();

    private void btn_usuarios3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios3ActionPerformed

    }//GEN-LAST:event_btn_usuarios3ActionPerformed

    private void btn_usuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario2ActionPerformed

        
        insertar();
        listar("");
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

    private void txt_idclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idclienteKeyPressed

    }//GEN-LAST:event_txt_idclienteKeyPressed

    private void txt_idclienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idclienteKeyTyped
        // TODO add your handling code here:
        //        librerias_raul_gabriel.validaciones.solo_texto_con_limite(evt, txt_nombre.getText(), 20);
    }//GEN-LAST:event_txt_idclienteKeyTyped

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
        listar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void rb_externoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_externoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_externoActionPerformed

    private void rb_extranjeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_extranjeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_extranjeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRound btn_usuario2;
    private LIB.FSButtonMD btn_usuarios1;
    private LIB.FSButtonMD btn_usuarios2;
    private LIB.FSButtonMD btn_usuarios3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSPanelCircleImage lblImagen;
    private javax.swing.JLabel lblcorreo;
    private javax.swing.JLabel lblcorreo1;
    private javax.swing.JLabel lcodigo;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente2;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente5;
    private javax.swing.ButtonGroup radioButon;
    private check.RB rb_externo;
    private check.RB rb_extranjero;
    private check.RB rb_local;
    private rojerusan.RSTableMetro tabla;
    private LIB.JTexfieldPH_FielTex txt_buscar;
    private rojeru_san.RSMTextFull txt_dni;
    private rojeru_san.RSMTextFull txt_idcliente;
    // End of variables declaration//GEN-END:variables
}
