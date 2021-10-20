package jadru;

import javax.swing.*;
import java.awt.*;

public class Game_1 extends JFrame {
    private GamePanel game = new GamePanel();
    Game_1(){
        setTitle("게임1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(game);
        setSize(1080,720);
        setVisible(true);
    }

    class GamePanel extends JPanel {
        private ImageIcon icon = new ImageIcon("C:\\Users\\SeongByeongseok\\Desktop\\img.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }

    public static void main (String [] args){
        new Game_1();
    }
}
