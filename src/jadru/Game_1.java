package jadru;

import jjam.SQLiteManager;
import sbs.ExchangeScreen;
import sbs.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static jadru.ShuffleCard.makeCard;
import static jadru.ShuffleCard.pickCards;

public class Game_1 extends JFrame {
    private GamePanel main = new GamePanel("");
    private static final int PADDING = 20;
    String[][] cards;
    boolean [] used_card = new boolean[54];
    int[] user_card;
    int[] com_card;

    Game_1(String id) {
        setTitle("게임1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(main);
        setLayout(null);
        setSize(1280, 740);
        setLocationRelativeTo(null);
        setResizable(false);
        ShuffleCard.game = this;
        cards = makeCard();

        Arrays.fill(used_card, false);
        user_card = pickCards(7);
        com_card = pickCards(7);

        JPanel com_profile = new JPanel(new CardLayout());
        JPanel user_profile = new JPanel(new CardLayout());
        JPanel com_cards = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
        JPanel user_cards = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));

        com_profile.setSize(160, 160);
        user_profile.setSize(160, 160);
        com_cards.setSize(1280, 200);
        user_cards.setSize(1280, 200);

        JLabel com_profile_img = new JLabel("COMPUTER", new ImageIcon("asset/icons8-bot-96.png"), SwingConstants.CENTER);
        com_profile.add(com_profile_img);
        SQLiteManager b = new SQLiteManager("","","");
        JLabel user_profile_img = new JLabel(b.getNickname(id), new ImageIcon("asset/icons8-test-account-96.png"), SwingConstants.CENTER);
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
            MakeCardGUI(user_card, i, user_cards);
        }

        for (int i = 0; i < 7; i++){
            MakeCardGUI(com_card, i, com_cards);
        }

        JButton reset = new JButton("로그인하기");
        reset.setSize(100, 100);
        reset.setLocation(200, 30);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_card = pickCards(7);
                com_card = pickCards(7);
                user_cards.removeAll();
                com_cards.removeAll();
                for (int i = 0; i < 7; i++) {
                    MakeCardGUI(user_card, i, user_cards);
                }

                for (int i = 0; i < 7; i++){
                    MakeCardGUI(com_card, i, com_cards);
                }
                revalidate();
            }
        });
        JButton mainButton = new JButton("메인");
        mainButton.setBounds(300,30,70,70);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
        add(mainButton);

        add(reset);

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

    private void MakeCardGUI(int[] card, int i, JPanel cardPanel){
        JButton btn = new JButton();
        btn.setText(cards[card[i] / 13][card[i] % 13]);
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 160));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setFont(new Font("Arial", Font.BOLD, 30));
        switch((card[i] / 13)){
            case 1: btn.setForeground(Color.RED);
            case 3: btn.setForeground(Color.RED); break;
            default: btn.setForeground(Color.BLACK);
        }
        cardPanel.add(btn);
    }

    private void addCardBack(JPanel cardPanel){
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon("asset/card_back_1.png"));
        btn.addActionListener(new cardActionListener());
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 160));
        btn.setMargin(new Insets(0, 0, 0, 0));
        cardPanel.add(btn);
    }

    public void setAtUsedCard(boolean card, int index){
        used_card[index] = card;
    }
    public boolean getAtUsedCard(int index){
        return used_card[index];
    }
}
