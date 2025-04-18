/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

//import COMPONENTES.Mensaje_Dialogo2;
//import CONTROLADOR_3.c_usuario;
//import static VISTA.frm_principal.pnlMenu;
import CONTROLADOR_3.c_informacion;
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
import Componetes.funciones_fotos;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raul hacho cutipa
 */
public class pnl_empresa extends javax.swing.JPanel {

    private ImageIcon imageicono;
    DefaultTableModel modeloI;
    c_informacion omInfoGeneral = new c_informacion();

    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    File archivo2;
    byte[] bytesImg;
    ManejoDeImagenes gestion = new ManejoDeImagenes();
    //------------------------------------------------

    //  frm_principal frmPrincipal = new frm_principal();
//    c_usuario omPersonal = new c_usuario();
    public pnl_empresa() {
        initComponents();
        listar("");
        funciones_fotos.crearCarpetaAlmacenar("FOTOS-INFO");
        imageicono = new ImageIcon(this.getClass().getResource("/appapagar/image/logo1.png"));

    }

    String imagenCopiar;

    void limpiar() {

        txt_razon_social.setText("");
        txt_ruc.setText("");
        txt_area.setText("");
        txt_negocio.setText("");
        txt_direccion.setText("");
        txt_caja.setText("");

        txt_buscar.setText("");
        lb_id.setText("");
        lb_nombre.setText("");
        label_nombre_foto.setText("");

        ImageIcon icon = new ImageIcon("wetransfer_monday.gif");
        rSFotoCircle.setImagenDefault(icon);
    }

    //------------llamar imagen ---------------
    //------------llamar imagen ---------------
    void llamarIMG() {
        final JFileChooser elejirImagen = new JFileChooser();
        elejirImagen.setCurrentDirectory(new File("Imagenes/"));
//  tratamiento de imagnes con java metodo 2 -----------------

        if (elejirImagen.showDialog(null, "ABRIR ARCHIVO") == JFileChooser.APPROVE_OPTION) {
            archivo = elejirImagen.getSelectedFile();

            //  JOptionPane.showMessageDialog(null, archivo);
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    //   String contenido = gestion.AbrirATexto(archivo);
//                    txtAreaTexto.setText(contenido);
                } else {
                    if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png")
                            || archivo.getName().endsWith("JPG") || archivo.getName().endsWith("jpeg")) {

                        bytesImg = gestion.AbrirAImagen(archivo);
                        rSFotoCircle.setImagenDefault((new ImageIcon(bytesImg))); //mostrar imagen

                        imagenCopiar = elejirImagen.getSelectedFile().getAbsolutePath();
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de imagen.");
                    }
                }
                label_nombre_foto.setText(archivo.getName() + "");
            }
        }
    }

    void listar(String dato) {
        try {
            modeloI = omInfoGeneral.ListarInformacion(dato);
            tabla.setModel(modeloI);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al listar imformacion general");
        }
    }

    void insertar() {
        try {
            if (txt_razon_social.getText().isEmpty() || txt_ruc.getText().isEmpty()
                    || txt_area.getText().isEmpty() || txt_negocio.getText().isEmpty()
                    || txt_direccion.getText().isEmpty() || txt_caja.getText().isEmpty()) {

                JOptionPane.showConfirmDialog(null, "complete los campos");
            } else {
                String ruta_foto = "SIN FOTO";
                if (!label_nombre_foto.getText().equals("")) {
                    ruta_foto = funciones_fotos.copiarfoto(imagenCopiar, txt_ruc.getText());
                }

                omInfoGeneral.insertarInformacion(txt_razon_social.getText(), txt_ruc.getText().toString(),
                        txt_area.getText(), txt_negocio.getText(), txt_direccion.getText(),
                        txt_caja.getText(), ruta_foto);

                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al insertar informacion general");
        }
    }

    void recuperar_usuario() {
        int select = tabla.getSelectedRow();
        lb_id.setText(modeloI.getValueAt(select, 0).toString());
        txt_razon_social.setText(modeloI.getValueAt(select, 1).toString());
        txt_ruc.setText(modeloI.getValueAt(select, 2).toString());
        txt_area.setText(modeloI.getValueAt(select, 3).toString());
        txt_negocio.setText(modeloI.getValueAt(select, 4).toString());
        txt_direccion.setText(modeloI.getValueAt(select, 5).toString());
        txt_caja.setText(modeloI.getValueAt(select, 6).toString());

        lb_nombre.setText(modeloI.getValueAt(select, 1).toString() + " " + modeloI.getValueAt(select, 2).toString());
    }

    void modificar() {
        try {
            if (txt_razon_social.getText().isEmpty() || txt_ruc.getText().isEmpty()
                    || txt_area.getText().isEmpty() || txt_negocio.getText().isEmpty()
                    || txt_direccion.getText().isEmpty() || txt_caja.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "complete los campos");
            } else {

                String nombref = "";
                if (!label_nombre_foto.getText().equals("")) {
                    nombref = funciones_fotos.copiarfoto(imagenCopiar, txt_ruc.getText());
                }

                omInfoGeneral.modificarInformacion(lb_id.getText(), txt_razon_social.getText(), txt_ruc.getText().toString(),
                        txt_area.getText(), txt_negocio.getText(), txt_direccion.getText(),
                        txt_caja.getText(), nombref);
                limpiar();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ocurrio un error al modificar");
        }
    }

    void eliminar() {
        try {

            int sselect = tabla.getSelectedRow();
            if (sselect >= 0) {

                omInfoGeneral.eliminarInformacion(modeloI.getValueAt(sselect, 0).toString());
                funciones_fotos.eliminar_foto(modeloI.getValueAt(sselect, 7).toString());

            } else {

                JOptionPane.showMessageDialog(null, "selecione para eliminar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al eliminaar informacion general");
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
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new rojerusan.RSTableMetro();
        btn_usuarios3 = new LIB.FSButtonMD();
        rSPanelGradiente1 = new rspanelgradiente.RSPanelGradiente();
        lb_id = new javax.swing.JLabel();
        lblImagen = new rojerusan.RSPanelCircleImage();
        lb_nombre = new javax.swing.JLabel();
        btn_usuarios1 = new LIB.FSButtonMD();
        btn_usuarios2 = new LIB.FSButtonMD();
        txt_buscar = new LIB.JTexfieldPH_FielTex();
        rSPanelGradiente3 = new rspanelgradiente.RSPanelGradiente();
        rSFotoCircle = new rojerusan.RSFotoCircle();
        txt_razon_social = new rojeru_san.RSMTextFull();
        txt_negocio = new rojeru_san.RSMTextFull();
        btn_usuario2 = new rojerusan.RSMaterialButtonRound();
        txt_area = new rojeru_san.RSMTextFull();
        txt_ruc = new rojeru_san.RSMTextFull();
        txt_direccion = new rojeru_san.RSMTextFull();
        txt_caja = new rojeru_san.RSMTextFull();
        btn_usuarios = new LIB.FSButtonMD();
        lblcorreo = new javax.swing.JLabel();
        label_nombre_foto = new javax.swing.JLabel();
        rSPanelGradiente4 = new rspanelgradiente.RSPanelGradiente();
        lcodigo2 = new javax.swing.JLabel();
        lblcorreo2 = new javax.swing.JLabel();
        lblcorreo3 = new javax.swing.JLabel();
        lblcorreo4 = new javax.swing.JLabel();
        rSPanelGradiente5 = new rspanelgradiente.RSPanelGradiente();
        jLabel1 = new javax.swing.JLabel();

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

        rSPanelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelShadow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 5 ", "Title 5"
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

        rSPanelShadow1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 770, 250));

        btn_usuarios3.setBackground(new java.awt.Color(255, 197, 0));
        btn_usuarios3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        btn_usuarios3.setText("CERRAR SECIÓN");
        btn_usuarios3.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios3.setColorNormal(new java.awt.Color(255, 197, 0));
        btn_usuarios3.setColorPressed(new java.awt.Color(255, 197, 0));
        btn_usuarios3.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios3.setIconTextGap(45);
        btn_usuarios3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios3ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(btn_usuarios3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 150, -1));

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(0, 204, 102));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(0, 204, 255));
        rSPanelGradiente1.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_id.setBackground(new java.awt.Color(255, 99, 71));
        lb_id.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_id.setForeground(new java.awt.Color(255, 255, 255));
        lb_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_id.setText("E00001");
        rSPanelGradiente1.add(lb_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 180, 50));

        lblImagen.setColorBorde(new java.awt.Color(2, 210, 185));
        lblImagen.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/logo1.png"))); // NOI18N
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout lblImagenLayout = new javax.swing.GroupLayout(lblImagen);
        lblImagen.setLayout(lblImagenLayout);
        lblImagenLayout.setHorizontalGroup(
            lblImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        lblImagenLayout.setVerticalGroup(
            lblImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        rSPanelGradiente1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 140, 140));

        lb_nombre.setBackground(new java.awt.Color(255, 99, 71));
        lb_nombre.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_nombre.setForeground(new java.awt.Color(255, 255, 255));
        lb_nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nombre.setText("ENTIDAD");
        rSPanelGradiente1.add(lb_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 770, 50));

        rSPanelShadow1.add(rSPanelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 220));

        btn_usuarios1.setBackground(new java.awt.Color(255, 197, 0));
        btn_usuarios1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        btn_usuarios1.setText("ELIMINAR");
        btn_usuarios1.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios1.setColorNormal(new java.awt.Color(255, 197, 0));
        btn_usuarios1.setColorPressed(new java.awt.Color(255, 197, 0));
        btn_usuarios1.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios1.setIconTextGap(45);
        btn_usuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios1ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(btn_usuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 150, -1));

        btn_usuarios2.setBackground(new java.awt.Color(255, 197, 0));
        btn_usuarios2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        btn_usuarios2.setText("MODIFICAR");
        btn_usuarios2.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios2.setColorNormal(new java.awt.Color(255, 197, 0));
        btn_usuarios2.setColorPressed(new java.awt.Color(255, 197, 0));
        btn_usuarios2.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios2.setIconTextGap(45);
        btn_usuarios2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios2ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(btn_usuarios2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 140, -1));

        txt_buscar.setBackground(new java.awt.Color(255, 197, 0));
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
        rSPanelShadow1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 220, 40));

        rSPanelGradiente3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        rSPanelGradiente3.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(255, 255, 255));
        rSPanelGradiente3.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.HORIZONTAL);
        rSPanelGradiente3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSFotoCircle.setColorBorde(new java.awt.Color(203, 238, 231));
        rSFotoCircle.setColorFondo(new java.awt.Color(203, 238, 231));
        rSFotoCircle.setGrosorBordePopu(1);
        rSFotoCircle.setImagenDefault(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_company_100px.png"))); // NOI18N
        rSPanelGradiente3.add(rSFotoCircle, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 90, 90));

        txt_razon_social.setBackground(new java.awt.Color(180, 186, 192));
        txt_razon_social.setForeground(new java.awt.Color(153, 153, 153));
        txt_razon_social.setBordeColorFocus(new java.awt.Color(204, 204, 204));
        txt_razon_social.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_razon_social.setColorTransparente(true);
        txt_razon_social.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_razon_social.setPlaceholder("Rason social *");
        txt_razon_social.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_razon_socialKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_razon_socialKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_razon_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 330, 40));

        txt_negocio.setBackground(new java.awt.Color(46, 78, 114));
        txt_negocio.setForeground(new java.awt.Color(153, 153, 153));
        txt_negocio.setBordeColorFocus(new java.awt.Color(204, 204, 204));
        txt_negocio.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_negocio.setColorTransparente(true);
        txt_negocio.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_negocio.setPlaceholder("Nombre del Negocio *");
        txt_negocio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_negocioFocusLost(evt);
            }
        });
        txt_negocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_negocioActionPerformed(evt);
            }
        });
        txt_negocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_negocioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_negocioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_negocioKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_negocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 330, 37));

        btn_usuario2.setBackground(new java.awt.Color(2, 210, 185));
        btn_usuario2.setBorder(null);
        btn_usuario2.setText("Registrar");
        btn_usuario2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btn_usuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuario2ActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(btn_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 230, 50));

        txt_area.setBackground(new java.awt.Color(46, 78, 114));
        txt_area.setForeground(new java.awt.Color(153, 153, 153));
        txt_area.setBordeColorFocus(new java.awt.Color(204, 204, 204));
        txt_area.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_area.setColorTransparente(true);
        txt_area.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_area.setPlaceholder("Aréa  *");
        txt_area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_areaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_areaKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_area, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 330, 37));

        txt_ruc.setBackground(new java.awt.Color(46, 78, 114));
        txt_ruc.setForeground(new java.awt.Color(153, 153, 153));
        txt_ruc.setBordeColorFocus(new java.awt.Color(204, 204, 204));
        txt_ruc.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_ruc.setColorTransparente(true);
        txt_ruc.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_ruc.setPlaceholder("Ruc *");
        txt_ruc.setSoloNumeros(true);
        txt_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rucKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_ruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 330, 37));

        txt_direccion.setBackground(new java.awt.Color(46, 78, 114));
        txt_direccion.setForeground(new java.awt.Color(153, 153, 153));
        txt_direccion.setBordeColorFocus(new java.awt.Color(204, 204, 204));
        txt_direccion.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_direccion.setColorTransparente(true);
        txt_direccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_direccion.setPlaceholder("Dirección *");
        txt_direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_direccionFocusLost(evt);
            }
        });
        txt_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_direccionActionPerformed(evt);
            }
        });
        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_direccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 330, 37));

        txt_caja.setBackground(new java.awt.Color(46, 78, 114));
        txt_caja.setForeground(new java.awt.Color(153, 153, 153));
        txt_caja.setBordeColorFocus(new java.awt.Color(204, 204, 204));
        txt_caja.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_caja.setColorTransparente(true);
        txt_caja.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_caja.setPlaceholder("Caja *");
        txt_caja.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cajaFocusLost(evt);
            }
        });
        txt_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cajaActionPerformed(evt);
            }
        });
        txt_caja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cajaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cajaKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_caja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 330, 37));

        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_unsplash_32px.png"))); // NOI18N
        btn_usuarios.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios.setColorNormal(new java.awt.Color(255, 197, 0));
        btn_usuarios.setColorPressed(new java.awt.Color(255, 197, 0));
        btn_usuarios.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios.setIconTextGap(45);
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(btn_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 40, 30));

        lblcorreo.setForeground(new java.awt.Color(2, 210, 185));
        lblcorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcorreo.setText("Fotografia");
        rSPanelGradiente3.add(lblcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 55, 110, -1));

        label_nombre_foto.setBackground(new java.awt.Color(2, 210, 185));
        label_nombre_foto.setForeground(new java.awt.Color(2, 210, 185));
        label_nombre_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_nombre_foto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_nombre_foto.setName(""); // NOI18N
        label_nombre_foto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_nombre_fotoMouseClicked(evt);
            }
        });
        rSPanelGradiente3.add(label_nombre_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 350, 20));

        rSPanelGradiente4.setColorPrimario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente4.setColorSecundario(new java.awt.Color(2, 210, 185));
        rSPanelGradiente4.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lcodigo2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lcodigo2.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo2.setText("Formulario de Registro");
        rSPanelGradiente4.add(lcodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 50));

        rSPanelGradiente3.add(rSPanelGradiente4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 50));

        lblcorreo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcorreo2.setForeground(new java.awt.Color(255, 172, 0));
        lblcorreo2.setText("Campo requerido  ®");
        rSPanelGradiente3.add(lblcorreo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, -1));

        lblcorreo3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcorreo3.setForeground(new java.awt.Color(255, 172, 0));
        lblcorreo3.setText("Foto Empresa");
        rSPanelGradiente3.add(lblcorreo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 120, -1));

        lblcorreo4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcorreo4.setForeground(new java.awt.Color(255, 172, 0));
        lblcorreo4.setText("Campo requerido  ®");
        rSPanelGradiente3.add(lblcorreo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 170, -1));

        rSPanelShadow1.add(rSPanelGradiente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 370, 580));

        rSPanelGradiente5.setBackground(new java.awt.Color(255, 197, 0));
        rSPanelGradiente5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(2, 212, 165)));
        rSPanelGradiente5.setColorPrimario(new java.awt.Color(255, 197, 0));
        rSPanelGradiente5.setColorSecundario(new java.awt.Color(255, 197, 0));
        rSPanelGradiente5.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_search_30px.png"))); // NOI18N
        jLabel1.setToolTipText("");
        rSPanelGradiente5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        rSPanelShadow1.add(rSPanelGradiente5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 40, 40));

        add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1175, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_razon_socialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_razon_socialKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_ruc.requestFocus();

        }

    }//GEN-LAST:event_txt_razon_socialKeyPressed

    private void txt_razon_socialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_razon_socialKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_razon_social.getText(), 50);
    }//GEN-LAST:event_txt_razon_socialKeyTyped

    private void txt_negocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_negocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_negocioActionPerformed

    private void txt_negocioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_negocioKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_direccion.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_area.requestFocus();

        }
    }//GEN-LAST:event_txt_negocioKeyPressed

    private void txt_negocioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_negocioKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_negocio.getText(), 50);
    }//GEN-LAST:event_txt_negocioKeyTyped

    private void btn_usuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuario2ActionPerformed

        insertar();
        listar("");
    }//GEN-LAST:event_btn_usuario2ActionPerformed

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

    private void txt_negocioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_negocioFocusLost

    }//GEN-LAST:event_txt_negocioFocusLost

    private void tablaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseMoved
        // TODO add your handling code here:
        //   Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
    }//GEN-LAST:event_tablaMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void txt_areaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_areaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_negocio.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_ruc.requestFocus();

        }


    }//GEN-LAST:event_txt_areaKeyPressed

    private void txt_areaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_areaKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_area.getText(), 50);
    }//GEN-LAST:event_txt_areaKeyTyped

    private void txt_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_area.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_razon_social.requestFocus();

        }
    }//GEN-LAST:event_txt_rucKeyPressed

    private void txt_rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_ruc.getText(), 11);
    }//GEN-LAST:event_txt_rucKeyTyped

    private void txt_direccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_direccionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_direccionFocusLost

    private void txt_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_direccionActionPerformed

    private void txt_direccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_caja.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_negocio.requestFocus();

        }
    }//GEN-LAST:event_txt_direccionKeyPressed

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_direccion.getText(), 50);
    }//GEN-LAST:event_txt_direccionKeyTyped

    private void txt_cajaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cajaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cajaFocusLost

    private void txt_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cajaActionPerformed

    private void txt_cajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cajaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_direccion.requestFocus();

        }


    }//GEN-LAST:event_txt_cajaKeyPressed

    private void txt_cajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cajaKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_caja.getText(), 10);
    }//GEN-LAST:event_txt_cajaKeyTyped

    private void btn_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuariosActionPerformed
        //------------llamar imagen ---------------
        llamarIMG();

//     rSFotoCircle.setImagenDefault(icon);
//     rSFotoCircle.setImagenDefault(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_administrator_male_80px_1.png")));

    }//GEN-LAST:event_btn_usuariosActionPerformed

    private void btn_usuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios1ActionPerformed
        eliminar();
        listar("");
    }//GEN-LAST:event_btn_usuarios1ActionPerformed

    private void btn_usuarios2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios2ActionPerformed
        // TODO add your handling code here:
        modificar();
        listar("");
    }//GEN-LAST:event_btn_usuarios2ActionPerformed
    public String validar(String correo) {
        String lbl = "";
        if (correo.trim().isEmpty()) {
            lbl = ("Campo requerido");
        }

        if (!correo.contains("@") && !correo.contains(".")) {
            lbl = ("*Incorrecto");

        } else {
            if (!correo.contains("@")) {
                lbl = ("*Falta:  @");
            }

            if (!correo.contains(".")) {
                lbl = ("*Falta:  .");
            }

            if (correo.contains("@") && correo.contains(".") && !correo.contains("[a-z]")) {
                lbl = ("*Invalido");
            }

            if (correo.contains(".") && correo.contains("@") && correo.length() > 6) {
                for (int x = 0; x < correo.length(); x++) {
                    char c = correo.charAt(x);
                    // Si no está entre a y z, ni entre A y Z, ni es un espacio
                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                        //            return false;
                        lblcorreo.setForeground(new java.awt.Color(255, 255, 255));
                    } else {
                        lbl = ("Completado ®");
                        lblcorreo.setForeground(new java.awt.Color(70, 187, 151));
                    }
                }

            } //    return true;
            else {
                lblcorreo.setForeground(new java.awt.Color(255, 255, 255));
            }
        }
        return lbl;
    }
    private void txt_negocioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_negocioKeyReleased


    }//GEN-LAST:event_txt_negocioKeyReleased


    private void label_nombre_fotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_nombre_fotoMouseClicked

//        try {
//            File carpeta = new File(rutaaa);
//            Desktop.getDesktop().open(carpeta);
//
//        } catch (IOException ex) {
//            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_label_nombre_fotoMouseClicked

    private void lblImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenMouseClicked
        if (evt.getClickCount() == 2) {
            System.out.println("double clicked");
            try {
                String url = "" + archivo;
                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe", "/c", url);
                p.start();
            } catch (IOException ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_lblImagenMouseClicked
//frm_principal panel= new frm_principal();

    private void btn_usuarios3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios3ActionPerformed
        login LOGIN = new login();

        LOGIN.setVisible(true);
//        panel .setVisible(false);        

    }//GEN-LAST:event_btn_usuarios3ActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
        listar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRound btn_usuario2;
    private LIB.FSButtonMD btn_usuarios;
    private LIB.FSButtonMD btn_usuarios1;
    private LIB.FSButtonMD btn_usuarios2;
    private LIB.FSButtonMD btn_usuarios3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_nombre_foto;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nombre;
    private rojerusan.RSPanelCircleImage lblImagen;
    private javax.swing.JLabel lblcorreo;
    private javax.swing.JLabel lblcorreo2;
    private javax.swing.JLabel lblcorreo3;
    private javax.swing.JLabel lblcorreo4;
    private javax.swing.JLabel lcodigo2;
    private rojerusan.RSFotoCircle rSFotoCircle;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente4;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.ButtonGroup radioButon;
    private rojerusan.RSTableMetro tabla;
    private rojeru_san.RSMTextFull txt_area;
    private LIB.JTexfieldPH_FielTex txt_buscar;
    private rojeru_san.RSMTextFull txt_caja;
    private rojeru_san.RSMTextFull txt_direccion;
    private rojeru_san.RSMTextFull txt_negocio;
    private rojeru_san.RSMTextFull txt_razon_social;
    private rojeru_san.RSMTextFull txt_ruc;
    // End of variables declaration//GEN-END:variables
}
