
package check;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/**
 *
 * @author Elber Climaco
 * 
 */
public class CheckaAzul extends JCheckBox{

    public CheckaAzul() {
        this.setIcon(new ImageIcon(getClass().getResource("/img/checkNo.png")));
        this.setSelectedIcon(new ImageIcon(getClass().getResource("/img/checkSi.png")));
        this.setOpaque(false);
    }

}
