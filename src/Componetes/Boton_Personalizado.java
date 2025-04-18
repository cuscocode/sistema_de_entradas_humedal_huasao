package Componetes;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;


/**
 *
 * @author Oscar
 */
public class Boton_Personalizado extends JButton implements FocusListener, MouseListener{
    
    public Boton_Personalizado(){
        super();
        Boton_Personalizado.this.setSize(new Dimension(100,42));
        Boton_Personalizado.this.setForeground(new Color(88, 88, 88));
        Boton_Personalizado.this.setBorderPainted(true);
        Boton_Personalizado.this.setContentAreaFilled(false);
        Boton_Personalizado.this.setOpaque(true);
        Boton_Personalizado.this.setBackground(new Color(88, 88, 88));
        Boton_Personalizado.this.setBorder(BorderFactory.createLineBorder(new Color(88, 88, 88),2));
        Boton_Personalizado.this.setFocusPainted(false);
        Boton_Personalizado.this.addFocusListener(Boton_Personalizado.this);
        Boton_Personalizado.this.addMouseListener(Boton_Personalizado.this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        //borde del boton aceptar
        setBorder(BorderFactory.createLineBorder(new Color(88, 88, 88),2));
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBorder(BorderFactory.createLineBorder(new Color(162,183,188),2));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
        Boton_Personalizado.this.setBackground( new Color(88, 88, 88));
        Boton_Personalizado.this.setForeground(new Color(255,255,255));
        Boton_Personalizado.this.setBorder(BorderFactory.createLineBorder(new Color(162,183,188),2));

    }
    
    @Override
    public void mousePressed(MouseEvent e) {
       Boton_Personalizado.this.setBackground( new Color(88, 88, 88));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Boton_Personalizado.this.setBackground( new Color(88, 88, 88));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       Boton_Personalizado.this.setBackground( new Color(88, 88, 88));
       Boton_Personalizado.this.setForeground(new Color(88, 88, 88));      
       Boton_Personalizado.this.setBorder(BorderFactory.createLineBorder(new Color(88, 88, 88),2));

    }

        
    
}
