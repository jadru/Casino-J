package panel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final String BG_IMG_URL = "src/asset/game1/game1_bg.png";
    private final Image img = new ImageIcon(BG_IMG_URL).getImage();
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0,0,getWidth(),getHeight(),this);
    }
}
