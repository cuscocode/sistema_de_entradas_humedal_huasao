/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package check;

import javax.swing.ImageIcon;
//import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 *
 * @author RojeruSan
 */
public class RB extends JRadioButton{

    public RB() {
        this.setIcon(new ImageIcon(getClass().getResource("/img/radio_buttonNo.png")));
        this.setSelectedIcon(new ImageIcon(getClass().getResource("/img/radio_buttonSi.png")));
        this.setOpaque(false);
    }

}
