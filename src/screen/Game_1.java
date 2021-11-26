package screen;

import support.SQLiteManager;
import support.ShuffleCard;
import support.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static support.ShuffleCard.makeCardDeck;
import static support.ShuffleCard.getCardFromDeck;

public class Game_1 extends JFrame {

    private static final String COMPUTER_NAME = "블랙잭 AI";
    private static final String COMPUTER_IMG_URL = "asset/icons8-bot-96.png";
    private static final String USER_IMG_URL = "asset/icons8-test-account-96.png";
    private static final String BG_IMG_URL = null;
    private static final int PADDING = 20;

    String [][] every_cards;
    boolean [] using_cards;
    int[] user_card_deck;
    int[] com_cards_deck;

    String user_id;
    private GamePanel main = new GamePanel();
    ThemeManager theme;
    SQLiteManager sql_manager;

    JPanel com_profile_panel;
    JPanel user_profile_panel;
    JPanel com_card_panel;
    JPanel user_card_panel;
    JPanel button_panel;

    JButton backtomain_button;
    JButton addcard_button;
    JButton finish_button;

    Game_1(String id) {
        this.user_id = id;
        generateGUI();
        startGame();
    }

    private void generateGUI(){
        setTitle(COMPUTER_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(main);
        setLayout(null);
        setSize(1280, 740);
        setLocationRelativeTo(null);
        setResizable(false);

        theme = new ThemeManager(user_id);
        sql_manager = new SQLiteManager("","","");

        ShuffleCard.game = this;

        com_profile_panel = new JPanel(new CardLayout());
        user_profile_panel = new JPanel(new CardLayout());
        com_card_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
        user_card_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
        button_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));

        com_profile_panel.setBounds(0, 0, 160, 160);
        user_profile_panel.setBounds(1120, 0, 160, 160);
        com_card_panel.setBounds(0, 160, 1280, 200);
        user_card_panel.setBounds(0, 360, 1280, 200);
        button_panel.setBounds(0, 560, 1280, 160);

        JLabel com_profile_img = new JLabel(COMPUTER_NAME,
                new ImageIcon(COMPUTER_IMG_URL),
                SwingConstants.CENTER);

        JLabel user_profile_img = new JLabel(sql_manager.getNickname(user_id),
                new ImageIcon(USER_IMG_URL),
                SwingConstants.CENTER);

        setBackground(theme.getBackgroundColor());

        setButtonPanel();
        
        add(com_profile_panel); add(user_profile_panel); add(com_card_panel); add(user_card_panel);
        com_profile_panel.add(com_profile_img); user_profile_panel.add(user_profile_img); add(button_panel);

        setVisible(true); com_profile_panel.setVisible(true); user_profile_panel.setVisible(true);
        com_card_panel.setVisible(true); user_card_panel.setVisible(true); button_panel.setVisible(true);
    }

    private void startGame(){
        every_cards = makeCardDeck();
        using_cards = new boolean[52];
        Arrays.fill(using_cards, false);

        user_card_deck = getCardFromDeck(2);
        com_cards_deck = getCardFromDeck(2);

        for (int i = 0; i < 2; i++) {
            addCardBack(com_card_panel);
        }
        for (int card : user_card_deck) {
            MakeCardGUI(user_card_deck, card, user_card_panel);
        }
    }

    class cardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

        }
    }

    class GamePanel extends JPanel {
        private ImageIcon icon = new ImageIcon(BG_IMG_URL);
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }

    }

    private void MakeCardGUI(int[] deck, int pick, JPanel cardPanel){
        JButton btn = new JButton();
        btn.setText(every_cards[pick / 13][pick % 13]);
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 160));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setFont(new Font("Arial", Font.BOLD, 30));
        switch((pick / 13)){
            case 1: btn.setForeground(Color.RED);
            case 3: btn.setForeground(Color.RED); break;
            default: btn.setForeground(Color.BLACK);
        }
        cardPanel.add(btn);
    }

    private void addCardBack(JPanel cardPanel){
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon("asset/card_back_1.png"));
//        btn.addActionListener(new cardActionListener());
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 160));
        btn.setMargin(new Insets(0, 0, 0, 0));
        cardPanel.add(btn);
    }

    public void setAtUsedCard(boolean status, int index){
        using_cards[index] = status;
    }
    public boolean getAtUsedCard(int index){
        return using_cards[index];
    }
    private void setButtonPanel(){
        backtomain_button = new JButton("메인");
        addcard_button = new JButton("카드 추가");
        finish_button = new JButton("게임 종료");

        backtomain_button.setSize(50,50);
        addcard_button.setSize(50,50);
        finish_button.setSize(50,50);

        button_panel.add(backtomain_button);
        button_panel.add(addcard_button);
        button_panel.add(finish_button);

        backtomain_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(user_id);
                setVisible(false); // 창 안보이게 하기
            }
        });

        addcard_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        finish_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    void gameFinished(){
        
    }
}
