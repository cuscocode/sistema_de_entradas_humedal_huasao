/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package check;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class CheckRed extends JCheckBox{

    public CheckRed() {
        this.setIcon(new ImageIcon(getClass().getResource("/img/uncheck-red1.png")));
        this.setSelectedIcon(new ImageIcon(getClass().getResource("/img/check-red1.png")));
        this.setOpaque(false);
    }

}
