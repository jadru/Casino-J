package panel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final String BG_IMG_URL = null;
    private ImageIcon icon = new ImageIcon(BG_IMG_URL);
    private final Image img = icon.getImage();
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0,0,getWidth(),getHeight(),this);
    }
}
