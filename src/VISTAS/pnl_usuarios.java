/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTAS;

//import COMPONENTES.Mensaje_Dialogo2;
//import CONTROLADOR_3.c_usuario;
//import static VISTA.frm_principal.pnlMenu;
import CONTROLADOR_3.c_usuario;
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
import java.awt.Image;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;

import Componetes.*;
import java.awt.Toolkit;

/**
 *
 * @author raul hacho cutipa
 */
public class pnl_usuarios extends javax.swing.JPanel {

    DefaultTableModel modeloU;
    c_usuario omPersonal = new c_usuario();

    JFileChooser elejirImagen = new JFileChooser();
    File archivo;
    File archivo2;
    byte[] bytesImg;
    ManejoDeImagenes gestion = new ManejoDeImagenes();
    //------------------------------------------------

    public pnl_usuarios() {
        initComponents();

        listar("");

        funciones_fotos.crearCarpetaAlmacenar("FOTOS-USUARIO");

    }

    String imagenCopiar;

    void limpiar() {
        //   txt_id.setText("");
        //  cbo_tipousuario.setSelectedIndex(0);
        txt_correo.setText("");
        txt_clave1.setText("");
        txt_clave.setText("");
        txt_nombre.setText("");
        txt_apellido_paterno.setText("");
        txt_apellido_materno.setText("");
        txt_dni.setText("");
        txt_telefono.setText("");

        txt_buscar.setText("");
        lb_id.setText("");
        lb_nombre.setText("");
        label_nombre_foto.setText("");

        ImageIcon icon = new ImageIcon("wetransfer_monday.gif");
        rSFotoCircle.setImagenDefault(icon);
    }

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
            modeloU = omPersonal.ListarUsuario(dato);
            tabla.setModel(modeloU);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error al listar usuario");
        }
    }

    void insertar() {

        try {

            String nombre_foto = "SIN FOTO";

            if (txt_correo.getText().isEmpty() || !radioButon.getSelection().isSelected() || txt_clave.getText().isEmpty()
                    || txt_nombre.getText().isEmpty() || txt_apellido_paterno.getText().isEmpty() || txt_apellido_materno.getText().isEmpty()
                    || txt_dni.getText().isEmpty() || txt_telefono.getText().isEmpty()
                    || lblpass.getText().equals("* Incorrecto ®")) {

                JOptionPane.showMessageDialog(null, "complete los campos");
            } else {
                if (!label_nombre_foto.getText().equals("")) {
                    //JOptionPane.showMessageDialog(null, "seleccione una foto para el usuario");
                    nombre_foto = funciones_fotos.copiarfoto(imagenCopiar, txt_dni.getText());
                }

                String usuario = "";
                if (rb_administrador.isSelected()) {
                    usuario = "administrador";
                }
                if (rb_usuario.isSelected()) {
                    usuario = "usuario comun";
                }

                omPersonal.insertarUsuario(usuario, txt_correo.getText(), txt_clave.getText().toString(),
                        txt_nombre.getText(), txt_apellido_paterno.getText(), txt_apellido_materno.getText(),
                        txt_dni.getText(), txt_telefono.getText(), nombre_foto);

                //  JOptionPane.showMessageDialog(null, NombreImagen);
                limpiar();

                // copiarfoto(txt_dni.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al insertar usuario");
        }
    }

    void recuperar_usuario() {
        limpiar();
        int select = tabla.getSelectedRow();
        lb_id.setText(modeloU.getValueAt(select, 0).toString());

        if (modeloU.getValueAt(select, 1).toString().equals("administrador")) {
            rb_administrador.setSelected(true);
        }
        if (modeloU.getValueAt(select, 1).toString().equals("usuario comun")) {
            rb_usuario.setSelected(true);
        }

        txt_correo.setText(modeloU.getValueAt(select, 2).toString());
        //   txt_clave1.setText(modeloU.getValueAt(select, 3).toString());

        txt_nombre.setText(modeloU.getValueAt(select, 3).toString());
        txt_apellido_paterno.setText(modeloU.getValueAt(select, 4).toString());
        txt_apellido_materno.setText(modeloU.getValueAt(select, 5).toString());

        lb_nombre.setText(modeloU.getValueAt(select, 3).toString() + " " + modeloU.getValueAt(select, 4).toString() + " " + modeloU.getValueAt(select, 5).toString());
        txt_dni.setText(modeloU.getValueAt(select, 6).toString());
        txt_telefono.setText(modeloU.getValueAt(select, 7).toString());

        //MOSTRAR COMO PREVISUALIZACION
//        Icon imagen = new ImageIcon(getClass().getResource("2020-12-26--77354961.png"));
    }

    void modificar() {
        try {
            if (lb_id.getText().isEmpty() || txt_correo.getText().isEmpty() || !radioButon.getSelection().isSelected() || txt_clave.getText().isEmpty()
                    || txt_nombre.getText().isEmpty() || txt_apellido_paterno.getText().isEmpty() || txt_apellido_materno.getText().isEmpty()
                    || txt_dni.getText().isEmpty() || txt_telefono.getText().isEmpty()
                    || lblpass11.getText().equals("* Incorrecto ®")) {

                JOptionPane.showMessageDialog(null, "complete los campos");
            } else {
                String usuario = "";
                if (rb_administrador.isSelected()) {
                    usuario = "administrador";
                }
                if (rb_usuario.isSelected()) {
                    usuario = "usuario comun";
                }

                String nombref = "";
                if (!label_nombre_foto.getText().equals("")) {
                    nombref = funciones_fotos.copiarfoto(imagenCopiar, txt_dni.getText());
                }
                omPersonal.modificarUsuario(lb_id.getText(), usuario, txt_correo.getText(), txt_clave.getText().toString(),
                        txt_nombre.getText(), txt_apellido_paterno.getText(), txt_apellido_materno.getText(),
                        txt_dni.getText(), txt_telefono.getText(), nombref);

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
                omPersonal.eliminarUsuario(modeloU.getValueAt(sselect, 0).toString());

                //eliminar la imagen
                funciones_fotos.eliminar_foto(modeloU.getValueAt(sselect, 9).toString());
            } else {

                JOptionPane.showMessageDialog(null, "selecione para eliminar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al eliminaar usuario");
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
        txt_nombre = new rojeru_san.RSMTextFull();
        txt_correo = new rojeru_san.RSMTextFull();
        lblpass11 = new javax.swing.JLabel();
        txt_clave = new rojeru_san.RSMPassView();
        btn_usuario2 = new rojerusan.RSMaterialButtonRound();
        txt_apellido_materno = new rojeru_san.RSMTextFull();
        txt_apellido_paterno = new rojeru_san.RSMTextFull();
        txt_dni = new rojeru_san.RSMTextFull();
        txt_telefono = new rojeru_san.RSMTextFull();
        btn_usuarios = new LIB.FSButtonMD();
        rb_usuario = new check.RB();
        rb_administrador = new check.RB();
        rSPanelGradiente2 = new rspanelgradiente.RSPanelGradiente();
        lcodigo = new javax.swing.JLabel();
        txt_clave1 = new rojeru_san.RSMPassView();
        lblcorreo = new javax.swing.JLabel();
        label_nombre_foto = new javax.swing.JLabel();
        lblpass = new javax.swing.JLabel();

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 5 ", "Title 5"
            }
        ));
        tabla.setAltoHead(25);
        tabla.setColorBackgoundHead(new java.awt.Color(70, 187, 151));
        tabla.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tabla.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tabla.setColorFilasBackgound2(new java.awt.Color(172, 224, 208));
        tabla.setColorFilasForeground1(new java.awt.Color(131, 209, 185));
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

        btn_usuarios3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_usuarios3.setText("LIMPIAR");
        btn_usuarios3.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios3.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_usuarios3.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_usuarios3.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios3.setIconTextGap(45);
        btn_usuarios3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios3ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(btn_usuarios3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 150, -1));

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(70, 187, 151));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(70, 187, 151));
        rSPanelGradiente1.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_id.setBackground(new java.awt.Color(255, 99, 71));
        lb_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_id.setForeground(new java.awt.Color(255, 255, 255));
        lb_id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_id.setText("0001");
        rSPanelGradiente1.add(lb_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 30));

        lblImagen.setColorBorde(new java.awt.Color(70, 187, 151));
        lblImagen.setImagen(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_user_48px_1.png"))); // NOI18N
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

        rSPanelGradiente1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 140, 140));

        lb_nombre.setBackground(new java.awt.Color(255, 99, 71));
        lb_nombre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_nombre.setForeground(new java.awt.Color(255, 255, 255));
        lb_nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSPanelGradiente1.add(lb_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 760, 50));

        rSPanelShadow1.add(rSPanelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 220));

        btn_usuarios1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_usuarios1.setText("ELIMINAR");
        btn_usuarios1.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios1.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_usuarios1.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_usuarios1.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios1.setIconTextGap(45);
        btn_usuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios1ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(btn_usuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 150, -1));

        btn_usuarios2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        btn_usuarios2.setText("MODIFICAR");
        btn_usuarios2.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios2.setColorNormal(new java.awt.Color(46, 78, 114));
        btn_usuarios2.setColorPressed(new java.awt.Color(46, 78, 114));
        btn_usuarios2.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios2.setIconTextGap(45);
        btn_usuarios2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuarios2ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(btn_usuarios2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 140, -1));

        txt_buscar.setBackground(new java.awt.Color(46, 78, 114));
        txt_buscar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 4, 0, 0, new java.awt.Color(70, 187, 151)));
        txt_buscar.setForeground(new java.awt.Color(255, 255, 255));
        txt_buscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_buscar.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_buscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_buscar.setPhColor(new java.awt.Color(255, 255, 255));
        txt_buscar.setPlaceholder("Buscar");
        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscarKeyTyped(evt);
            }
        });
        rSPanelShadow1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 220, 40));

        rSPanelGradiente3.setColorPrimario(new java.awt.Color(70, 114, 172));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(70, 99, 151));
        rSPanelGradiente3.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSFotoCircle.setColorBorde(new java.awt.Color(70, 187, 151));
        rSFotoCircle.setColorButtonOptions(new java.awt.Color(46, 78, 114));
        rSFotoCircle.setImagenDefault(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/wetransfer_monday.gif"))); // NOI18N
        rSPanelGradiente3.add(rSFotoCircle, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 5, 102, 100));

        txt_nombre.setBackground(new java.awt.Color(180, 186, 192));
        txt_nombre.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombre.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_nombre.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_nombre.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_nombre.setColorTransparente(true);
        txt_nombre.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_nombre.setPlaceholder("Nombres *");
        txt_nombre.setSoloLetras(true);
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 330, 37));

        txt_correo.setBackground(new java.awt.Color(46, 78, 114));
        txt_correo.setForeground(new java.awt.Color(255, 255, 255));
        txt_correo.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_correo.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_correo.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_correo.setColorTransparente(true);
        txt_correo.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_correo.setPlaceholder("Email *");
        txt_correo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_correoFocusLost(evt);
            }
        });
        txt_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_correoActionPerformed(evt);
            }
        });
        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_correoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_correoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 330, 37));

        lblpass11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblpass11.setForeground(new java.awt.Color(255, 255, 255));
        lblpass11.setText("Seleccione una Foto");
        rSPanelGradiente3.add(lblpass11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 170, 20));

        txt_clave.setBackground(new java.awt.Color(70, 99, 151));
        txt_clave.setForeground(new java.awt.Color(255, 255, 255));
        txt_clave.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_clave.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_clave.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_clave.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_clave.setPlaceholder("Repita su contraseña *");
        txt_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_claveKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_claveKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_claveKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 330, 37));

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

        txt_apellido_materno.setBackground(new java.awt.Color(46, 78, 114));
        txt_apellido_materno.setForeground(new java.awt.Color(255, 255, 255));
        txt_apellido_materno.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_apellido_materno.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_apellido_materno.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_apellido_materno.setColorTransparente(true);
        txt_apellido_materno.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_apellido_materno.setPlaceholder("Apellido Materno *");
        txt_apellido_materno.setSoloLetras(true);
        txt_apellido_materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellido_maternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido_maternoKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_apellido_materno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 160, 37));

        txt_apellido_paterno.setBackground(new java.awt.Color(46, 78, 114));
        txt_apellido_paterno.setForeground(new java.awt.Color(255, 255, 255));
        txt_apellido_paterno.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_apellido_paterno.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_apellido_paterno.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_apellido_paterno.setColorTransparente(true);
        txt_apellido_paterno.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_apellido_paterno.setPlaceholder("Apellido Paterno *");
        txt_apellido_paterno.setSoloLetras(true);
        txt_apellido_paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellido_paternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido_paternoKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_apellido_paterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 150, 37));

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
        rSPanelGradiente3.add(txt_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 37));

        txt_telefono.setBackground(new java.awt.Color(46, 78, 114));
        txt_telefono.setForeground(new java.awt.Color(255, 255, 255));
        txt_telefono.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_telefono.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_telefono.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_telefono.setColorTransparente(true);
        txt_telefono.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_telefono.setPlaceholder("Número de Celular *");
        txt_telefono.setSoloNumeros(true);
        txt_telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_telefonoFocusLost(evt);
            }
        });
        txt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefonoActionPerformed(evt);
            }
        });
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 160, 37));

        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appapagar/image/icons8_unsplash_32px.png"))); // NOI18N
        btn_usuarios.setColorHover(new java.awt.Color(70, 187, 151));
        btn_usuarios.setColorNormal(new java.awt.Color(70, 105, 160));
        btn_usuarios.setColorPressed(new java.awt.Color(70, 105, 160));
        btn_usuarios.setColorTextHover(new java.awt.Color(255, 255, 255));
        btn_usuarios.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_usuarios.setIconTextGap(45);
        btn_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usuariosActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(btn_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 40, 30));

        radioButon.add(rb_usuario);
        rb_usuario.setForeground(new java.awt.Color(255, 255, 255));
        rb_usuario.setText("Usuario");
        rb_usuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rb_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_usuarioActionPerformed(evt);
            }
        });
        rSPanelGradiente3.add(rb_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        radioButon.add(rb_administrador);
        rb_administrador.setForeground(new java.awt.Color(255, 255, 255));
        rb_administrador.setText("Administrador");
        rb_administrador.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rSPanelGradiente3.add(rb_administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        rSPanelGradiente2.setColorPrimario(new java.awt.Color(70, 187, 151));
        rSPanelGradiente2.setColorSecundario(new java.awt.Color(70, 187, 151));
        rSPanelGradiente2.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.ESQUINA_4);
        rSPanelGradiente2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lcodigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lcodigo.setForeground(new java.awt.Color(255, 255, 255));
        lcodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lcodigo.setText("Formulario de Registro");
        rSPanelGradiente2.add(lcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 50));

        rSPanelGradiente3.add(rSPanelGradiente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 30, 270, 50));

        txt_clave1.setBackground(new java.awt.Color(70, 99, 151));
        txt_clave1.setForeground(new java.awt.Color(255, 255, 255));
        txt_clave1.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        txt_clave1.setBotonColor(new java.awt.Color(70, 187, 151));
        txt_clave1.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_clave1.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        txt_clave1.setPlaceholder("Contraseña *");
        txt_clave1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_clave1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_clave1KeyTyped(evt);
            }
        });
        rSPanelGradiente3.add(txt_clave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 330, 37));

        lblcorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblcorreo.setText("Campo requerido  ®");
        rSPanelGradiente3.add(lblcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 170, -1));

        label_nombre_foto.setBackground(new java.awt.Color(255, 255, 255));
        label_nombre_foto.setForeground(new java.awt.Color(255, 255, 255));
        label_nombre_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_nombre_foto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_nombre_foto.setName(""); // NOI18N
        label_nombre_foto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_nombre_fotoMouseClicked(evt);
            }
        });
        rSPanelGradiente3.add(label_nombre_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 350, 20));

        lblpass.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblpass.setForeground(new java.awt.Color(255, 255, 255));
        lblpass.setText("Campo requerido  ®");
        rSPanelGradiente3.add(lblpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 170, 20));

        rSPanelShadow1.add(rSPanelGradiente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 370, 580));

        add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1175, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_apellido_paterno.requestFocus();

        }

    }//GEN-LAST:event_txt_nombreKeyPressed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_nombre.getText(), 20);
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoActionPerformed

    private void txt_correoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_dni.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_apellido_paterno.requestFocus();

        }
    }//GEN-LAST:event_txt_correoKeyPressed

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_correo.getText(), 50);
    }//GEN-LAST:event_txt_correoKeyTyped

    private void txt_claveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_claveKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_clave1.requestFocus();

        }

    }//GEN-LAST:event_txt_claveKeyPressed

    private void txt_claveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_claveKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_clave.getText(), 20);
    }//GEN-LAST:event_txt_claveKeyTyped

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

    private void txt_correoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_correoFocusLost

    }//GEN-LAST:event_txt_correoFocusLost

    private void tablaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseMoved
        // TODO add your handling code here:
        //   Animacion.Animacion.mover_izquierda(0, -180, 2, 10, pnlMenu);
    }//GEN-LAST:event_tablaMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void txt_apellido_maternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_maternoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_correo.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_nombre.requestFocus();

        }

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {//tecla izquierda
            String pw = txt_apellido_materno.getText();
            if (pw.trim().equals("")) {

                txt_apellido_paterno.requestFocus();
            }

        }

    }//GEN-LAST:event_txt_apellido_maternoKeyPressed

    private void txt_apellido_maternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_maternoKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_apellido_materno.getText(), 20);
    }//GEN-LAST:event_txt_apellido_maternoKeyTyped

    private void txt_apellido_paternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_paternoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_correo.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_nombre.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {//arriba
            //pasar al siguiente caja de recto
            txt_apellido_materno.requestFocus();

        }
    }//GEN-LAST:event_txt_apellido_paternoKeyPressed

    private void txt_apellido_paternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido_paternoKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_apellido_paterno.getText(), 20);
    }//GEN-LAST:event_txt_apellido_paternoKeyTyped

    private void txt_dniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_dniFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dniFocusLost

    private void txt_dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dniActionPerformed

    private void txt_dniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_clave1.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_correo.requestFocus();

        }
    }//GEN-LAST:event_txt_dniKeyPressed

    private void txt_dniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_dni.getText(), 8);
    }//GEN-LAST:event_txt_dniKeyTyped

    private void txt_telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_telefonoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoFocusLost

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoActionPerformed

    private void txt_telefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_clave1.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_correo.requestFocus();

        }

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {//tecla izquierda
            String pw = txt_telefono.getText();
            if (pw.trim().equals("")) {

                txt_dni.requestFocus();
            }

        }
    }//GEN-LAST:event_txt_telefonoKeyPressed

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_telefono.getText(), 12);
    }//GEN-LAST:event_txt_telefonoKeyTyped

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

    String nombreimagen = "";
    private void btn_usuarios2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usuarios2ActionPerformed
        // TODO add your handling code here:
        modificar();
        listar("");
    }//GEN-LAST:event_btn_usuarios2ActionPerformed

    private void txt_clave1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clave1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {//tecla enter //tecla abajo
            //pasar al siguiente caja de recto
            txt_clave.requestFocus();

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {//arriba
            //pasar al siguiente caja de recto
            txt_dni.requestFocus();

        }
    }//GEN-LAST:event_txt_clave1KeyPressed

    private void txt_clave1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clave1KeyTyped
        librerias_raul_gabriel.validaciones.solo_limite_texto(evt, txt_clave1.getText(), 20);
    }//GEN-LAST:event_txt_clave1KeyTyped
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
    private void txt_correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyReleased

        lblcorreo.setText(validar(txt_correo.getText()));
    }//GEN-LAST:event_txt_correoKeyReleased

    private void rb_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_usuarioActionPerformed
    public String CompararPass(String pw, String pw2) {
        String text = "";
        pw = pw.trim();
        pw2 = pw2.trim();

        if (pw.equals(pw2) && !pw.equals("")) {
            text = "* Correcto ®";
            lblpass11.setForeground(new java.awt.Color(70, 187, 151));
        } else {
            text = "* Incorrecto ®";
            lblpass11.setForeground(new java.awt.Color(255, 255, 255));
        }

        return text;
    }

    private void txt_claveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_claveKeyReleased

        lblpass.setText(CompararPass(new String(txt_clave1.getPassword()), new String(txt_clave.getPassword())));


    }//GEN-LAST:event_txt_claveKeyReleased

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
        limpiar();

    }//GEN-LAST:event_btn_usuarios3ActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarKeyTyped

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_nombre_foto;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nombre;
    private rojerusan.RSPanelCircleImage lblImagen;
    private javax.swing.JLabel lblcorreo;
    private javax.swing.JLabel lblpass;
    private javax.swing.JLabel lblpass11;
    private javax.swing.JLabel lcodigo;
    private rojerusan.RSFotoCircle rSFotoCircle;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente2;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.ButtonGroup radioButon;
    private check.RB rb_administrador;
    private check.RB rb_usuario;
    private rojerusan.RSTableMetro tabla;
    private rojeru_san.RSMTextFull txt_apellido_materno;
    private rojeru_san.RSMTextFull txt_apellido_paterno;
    private LIB.JTexfieldPH_FielTex txt_buscar;
    private rojeru_san.RSMPassView txt_clave;
    private rojeru_san.RSMPassView txt_clave1;
    private rojeru_san.RSMTextFull txt_correo;
    private rojeru_san.RSMTextFull txt_dni;
    private rojeru_san.RSMTextFull txt_nombre;
    private rojeru_san.RSMTextFull txt_telefono;
    // End of variables declaration//GEN-END:variables
}
