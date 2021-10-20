package jadru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static jadru.ShuffleCard.makeCard;
import static jadru.ShuffleCard.pickCards;

public class Game_1 extends JFrame {
    private GamePanel game = new GamePanel();
    Game_1(){
        setTitle("게임1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(game);
        setSize(1080,720);
        new GameCore();
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

    class GameCore {
        class cardListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource();

            }
        };
        GameCore(){
            String[][] cards = makeCard();
            int[] user_card = pickCards(7);
            int[] com_card = pickCards(7);
            JButton start = new JButton("시작하기");
            add(start);
            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0; i < 7; i++){
                        JButton btn = new JButton();
                        btn.setText(cards[user_card[i]/13][user_card[i]%13]);
                        btn.addActionListener(new cardListener());
                        add(btn);
                    }
                }
            });

        }

    }

    public static void main (String [] args){
        new Game_1();
    }
}
