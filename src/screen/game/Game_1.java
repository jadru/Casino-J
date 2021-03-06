package screen.game;

import global.GlobalGUI;
import screen.ui.MainScreen;
import support.SQLiteManager;
import support.ShuffleCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.awt.Font.BOLD;
import static support.ShuffleCard.getCardFromDeck;
import static support.ShuffleCard.makeCardDeck;

public class Game_1 extends GlobalGUI {

    private static final String COMPUTER_NAME = "블랙잭 AI";
    private static final String COMPUTER_IMG_URL = "src/asset/game1/icons8-bot-96.png";
    private static final String USER_IMG_URL = "src/asset/game1/icons8-test-account-96.png";
    private static final int PADDING = 20;
    static SQLiteManager sql_manager;
    static JPanel com_card_panel;
    static JPanel user_card_panel;
    static JPanel button_panel;
    private static String[][] every_cards;
    private static boolean[] using_cards;
    private static ArrayList<Integer> user_cards_deck;
    private static ArrayList<Integer> com_cards_deck;
    private static int usercard_cnt;
    private static int comcard_cnt;
    private static JFrame game;
    private static Random rd = new Random();
    private static String user_id = "";
    JPanel com_profile_panel;
    JPanel user_profile_panel;

    public Game_1(String user_id) {
        super(COMPUTER_NAME, "src/asset/game1/game1_bg.png");
        Game_1.user_id = user_id;
        game = this;
        setScreenGUI();
        setGameGUI();
        startGame();
        repaintGUI();
    }

    public static void addCardGUI(int pick, JPanel cardPanel) {
        JButton btn = new JButton();
        btn.setText(every_cards[pick / 13][pick % 13]);
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 160));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setFont(new Font(Font.SERIF, BOLD, 30));
        btn.setFocusable(false);
        switch ((pick / 13)) {
            case 1:
                btn.setForeground(Color.RED);
            case 2:
                btn.setForeground(Color.RED);
                break;
            default:
                btn.setForeground(Color.BLACK);
        }
        cardPanel.add(btn);
    }

    private static void addCardBack(JPanel cardPanel) {
        JButton btn = new JButton();
        btn.setFocusable(false);
        btn.setIcon(new ImageIcon(support.ThemeManager.getCardBackImgURL(user_id)));
//        btn.addActionListener(new cardActionListener());
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 160));
        btn.setMargin(new Insets(0, 0, 0, 0));
        cardPanel.add(btn);
    }

    public static void resultOut(boolean iswin) {
        com_card_panel.removeAll();
        com_card_panel.setBackground(new Color(0, 0, 0, 0));
        for (int card : com_cards_deck) {
            addCardGUI(card, com_card_panel);
        }
        repaintGUI();
        String winorlose = "";
        if (iswin) {
            winorlose = "WIN! 포인트 100점 적립!";
            sql_manager.giveRecord(user_id, 1, 0, 100);
        } else {
            winorlose = "졌습니다...";
            sql_manager.giveRecord(user_id, 0, 1, 0);
        }
        JLabel label = new JLabel(winorlose);
        JButton mainButton = new JButton(new ImageIcon("src/asset/btn/main_btn.png"));
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.dispose();
                game.removeAll();
                new MainScreen(user_id);
                game.setVisible(false);
            }
        });

        label.setPreferredSize(new Dimension(600, 110));
        mainButton.setPreferredSize(new Dimension(100, 100));
        button_panel.add(label);
        button_panel.add(mainButton);
    }

    public static void addUserCard() {
        user_cards_deck.add(getCardFromDeck());
        usercard_cnt++;
        addCardGUI(user_cards_deck.get(usercard_cnt - 1), user_card_panel);
        game.revalidate();
        game.repaint();
    }

    private static void addComCardRandom() {
        if (calculatePoint(com_cards_deck, comcard_cnt) <= 21 - 10) {
            com_cards_deck.add(getCardFromDeck());
            addCardBack(com_card_panel);
            comcard_cnt++;
        } else if (calculatePoint(com_cards_deck, comcard_cnt) < 21 - 3) {
            if (rd.nextBoolean()) {
                com_cards_deck.add(getCardFromDeck());
                addCardBack(com_card_panel);
                comcard_cnt++;
            }
        }
        if (calculatePoint(com_cards_deck, comcard_cnt) > 21) {
            resultOut(true);
        }
    }

    public static void finishGame() {
        int compoint = calculatePoint(com_cards_deck, comcard_cnt);
        int userpoint = calculatePoint(user_cards_deck, usercard_cnt);
        if (userpoint > 21) {
            resultOut(false);
        } else if (compoint > 21) {
            resultOut(true);
        } else {
            if (compoint > userpoint) {
                resultOut(false);
            } else {
                resultOut(true);
            }
        }
    }

    public static int calculatePoint(ArrayList<Integer> list, int cnt) {
        int getpoint = 0;
        int hasA = 0;

        for (int i = 0; i < cnt; i++) {
            int point = list.get(i) % 13;
            if (point > 9) point = 10;
            else point++;
            if (point == 0) hasA++;
            getpoint += point;
        }

        if (getpoint > 21) {
            while (hasA > 0) {
                getpoint -= 10;
                hasA--;
            }
        }
        return getpoint;
    }

    private static void clearAllintButtonPanel() {
        button_panel.removeAll();
        button_panel.repaint();
        button_panel.revalidate();
    }

    private void setScreenGUI() {

        sql_manager = new SQLiteManager("", "", "");
        ShuffleCard.game = this;

        user_cards_deck = new ArrayList<Integer>();
        com_cards_deck = new ArrayList<Integer>();

        com_profile_panel = new JPanel(new CardLayout());
        user_profile_panel = new JPanel(new CardLayout());
        com_card_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
        user_card_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
        button_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));

        com_profile_panel.setBounds(0, 0, 300, 160);
        user_profile_panel.setBounds(980, 0, 300, 160);
        com_card_panel.setBounds(0, 160, 1280, 200);
        user_card_panel.setBounds(0, 360, 1280, 200);
        button_panel.setBounds(0, 560, 1280, 160);

        com_profile_panel.setBackground(new Color(0, 0, 0, 0));
        user_profile_panel.setBackground(new Color(0, 0, 0, 0));
        com_card_panel.setBackground(new Color(0, 0, 0, 0));
        user_card_panel.setBackground(new Color(0, 0, 0, 0));
        button_panel.setBackground(new Color(0XFFFFFF));

        usercard_cnt = 0;
        comcard_cnt = 0;

        JLabel com_profile_img = new JLabel(COMPUTER_NAME,
                new ImageIcon(COMPUTER_IMG_URL),
                SwingConstants.CENTER);

        JLabel user_profile_img = new JLabel(sql_manager.getNickname(user_id),
                new ImageIcon(USER_IMG_URL),
                SwingConstants.CENTER);

        com_profile_img.setFont(casinoFont(30));
        user_profile_img.setFont(casinoFont(30));

        com_profile_img.setForeground(new Color(255, 255, 255));
        user_profile_img.setForeground(new Color(255, 255, 255));

        user_profile_img.setHorizontalTextPosition(JLabel.LEFT);

//        setBackground(support.ThemeManager.getBackgroundColor(user_id));

        add(com_profile_panel);
        add(user_profile_panel);
        add(com_card_panel);
        add(user_card_panel);
        com_profile_panel.add(com_profile_img);
        user_profile_panel.add(user_profile_img);
        add(button_panel);

        setVisible(true);
        com_profile_panel.setVisible(true);
        user_profile_panel.setVisible(true);
        com_card_panel.setVisible(true);
        user_card_panel.setVisible(true);
        button_panel.setVisible(true);

        repaint();
        revalidate();
    }

    private void startGame() {
        int userpoint = calculatePoint(user_cards_deck, usercard_cnt);
        if (userpoint > 21) {
            Game_1.resultOut(false);
        } else {
            setButtonPanel(userpoint);
        }
    }

    private void setGameGUI() {
        every_cards = makeCardDeck();
        using_cards = new boolean[52];
        Arrays.fill(using_cards, false);
        for (int i = 0; i < 2; i++) {
            user_cards_deck.add(getCardFromDeck());
            comcard_cnt++;
        }
        for (int i = 0; i < 2; i++) {
            com_cards_deck.add(getCardFromDeck());
            usercard_cnt++;
        }

        for (int i = 0; i < 2; i++) {
            addCardBack(com_card_panel);
        }
        for (int card : user_cards_deck) {
            addCardGUI(card, user_card_panel);
        }

        repaint();
        revalidate();
    }

    public void setAtUsedCard(boolean status, int index) {
        using_cards[index] = status;
    }

    public boolean getAtUsedCard(int index) {
        return using_cards[index];
    }

    private void setButtonPanel(int point) {

        button_panel.removeAll();
        JLabel header = new JLabel("현재 점수 " + point + "점입니다. 카드를 추가하시겠어요?");

        JButton addcard_btn = new JButton(new ImageIcon("src/asset/game1/icon_card.png"));
        JButton lose_btn = new JButton(new ImageIcon("src/asset/game1/icon_lose.png"));
        JButton finish_btn = new JButton(new ImageIcon("src/asset/game1/icon_play.png"));

        addcard_btn.setPreferredSize(new Dimension(110, 110));
        lose_btn.setPreferredSize(new Dimension(110, 110));
        finish_btn.setPreferredSize(new Dimension(110, 110));
        header.setPreferredSize(new Dimension(600, 110));

        addcard_btn.setBorderPainted(false);
        addcard_btn.setContentAreaFilled(false);

        finish_btn.setBorderPainted(false);
        finish_btn.setContentAreaFilled(false);

        lose_btn.setBorderPainted(false);
        lose_btn.setContentAreaFilled(false);

        addcard_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserCard();
                clearAllintButtonPanel();
                addComCardRandom();
                startGame();
            }
        });
        lose_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllintButtonPanel();
                resultOut(false);
            }
        });
        finish_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllintButtonPanel();
                addComCardRandom();
                finishGame();
            }
        });

        button_panel.add(header);
        button_panel.add(lose_btn);
        button_panel.add(finish_btn);
        button_panel.add(addcard_btn);

    }
}
