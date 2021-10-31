package jadru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static jadru.ShuffleCard.makeCard;
import static jadru.ShuffleCard.pickCards;

public class Game_1 extends JFrame {
    private GamePanel main = new GamePanel("");
    private static final int PADDING = 20;
    Game_1() {
        setTitle("게임1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(main);
        setLayout(null);
        setSize(1280, 740);
        setLocationRelativeTo(null);
        setResizable(false);

        String[][] cards = makeCard();
        int[] user_card = pickCards(7);
        int[] com_card = pickCards(7);

        JPanel com_profile = new JPanel(new CardLayout());
        JPanel user_profile = new JPanel(new CardLayout());
        JPanel com_cards = new JPanel(null);
        JPanel user_cards = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));

        com_profile.setSize(160, 160);
        user_profile.setSize(160, 160);
        com_cards.setSize(1280, 200);
        user_cards.setSize(1280, 200);

        JLabel com_profile_img = new JLabel("문자열", new ImageIcon("asset/icons8-bot-96.png"), SwingConstants.CENTER);
        com_profile.add(com_profile_img);
        JLabel user_profile_img = new JLabel("문자열", new ImageIcon("asset/icons8-text-account-96.png"), SwingConstants.CENTER);
        user_profile.add(user_profile_img);

        com_profile.setLocation(0, 0);
        user_profile.setLocation(1120, 560);
        com_cards.setLocation(0, 160);
        user_cards.setLocation(0, 360);

        com_cards.setBackground(Color.black);
        user_cards.setBackground(Color.darkGray);

        add(com_profile);
        add(user_profile);
        add(com_cards);
        add(user_cards);

        for (int i = 0; i < 7; i++) {
            JButton btn = new JButton();
            btn.setText(cards[user_card[i] / 13][user_card[i] % 13]);
            btn.addActionListener(new cardActionListener());
            btn.setBackground(Color.WHITE);
            btn.setPreferredSize(new Dimension(120, 160));
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setFont(new Font(null, Font.BOLD, 30));
            user_cards.add(btn);
        }

        setVisible(true);
        com_profile.setVisible(true);
        user_profile.setVisible(true);
        com_cards.setVisible(true);
        user_cards.setVisible(true);
    }

    class cardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

        }
    }

    class GamePanel extends JPanel {
        String url = null;
        GamePanel(String url) {
            this.url = url;
        }
        private ImageIcon icon = new ImageIcon(url);
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
