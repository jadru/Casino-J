package screen.game;

import global.GlobalGUI;
import screen.ui.MainScreen;
import support.SQLiteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import static java.awt.Font.BOLD;
import static support.ShuffleCard.getOneCardFromDeck;
import static support.ShuffleCard.makeCardDeck;

public class Game_2 extends GlobalGUI {

    private static final String COMPUTER_NAME = "인디언 AI";
    private static final String COMPUTER_IMG_URL = "src/asset/game1/icons8-bot-96.png";
    private static final String USER_IMG_URL = "src/asset/game1/icons8-test-account-96.png";
    private static final int PADDING = 20;
    static SQLiteManager sql_manager;
    static JPanel com_card_panel;
    static JPanel user_card_panel;
    static JPanel button_panel;
    static int combat;
    static int userbat;
    static int userpoint;
    static int comcard;
    static int usercard;
    private static String[][] every_cards;
    private static boolean[] using_cards;
    private static JFrame game;
    private static Random rd = new Random();
    private static String user_id = "";
    JPanel com_profile_panel;
    JPanel user_profile_panel;
    int userbatcnt = 0;

    public Game_2(String user_id) {
        super(COMPUTER_NAME, "src/asset/game1/game1_bg.png");
        Game_2.user_id = user_id;
        game = this;
        setScreenGUI();
        setGameGUI();
        setButtonPanel();
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
        user_card_panel.removeAll();
        user_card_panel.setBackground(new Color(0, 0, 0, 0));
        addCardGUI(usercard, user_card_panel);
        String winorlose = "";
        if (iswin) {
            winorlose = "WIN! 컴퓨터가 배팅한 $" + combat + "적립!";
            sql_manager.giveRecord(user_id, 1, 0, combat);
        } else {
            winorlose = "LOSE.. " + sql_manager.getNickname(user_id) + "님의 $ -" + userbat + "";
            if (userbat > userpoint)
                userbat = userpoint;
            sql_manager.giveRecord(user_id, 0, 1, -userbat);
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
        button_panel.removeAll();
        button_panel.add(label);
        button_panel.add(mainButton);
        repaintGUI();
    }

    private static void clearAllintButtonPanel() {
        button_panel.removeAll();
        button_panel.repaint();
        button_panel.revalidate();
    }

    private void setScreenGUI() {

        sql_manager = new SQLiteManager("", "", "");

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

        combat = 0;
        userbat = 0;

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
    }

    private void setGameGUI() {
        every_cards = makeCardDeck();
        using_cards = new boolean[52];
        Arrays.fill(using_cards, false);

        userpoint = sql_manager.getPoint(user_id);
        comcard = getOneCardFromDeck();
        usercard = getOneCardFromDeck();

        addCardGUI(comcard, com_card_panel);
        addCardBack(user_card_panel);

        combat += 10;
        userbat += 10;

        repaintGUI();
    }

    private void setButtonPanel() {

        button_panel.removeAll();
        JLabel header = new JLabel("배팅하세요! 컴퓨터 : $" + combat + "배팅, " + sql_manager.getNickname(user_id) + "님 $" + userbat + "배팅");
        JButton addpoint_btn = new JButton(new ImageIcon("src/asset/game1/icon_money.png"));
        JTextField pointfield = new JTextField("10");
        JButton lose = new JButton(new ImageIcon("src/asset/game1/icon_lose.png"));
        JButton finish_btn = new JButton(new ImageIcon("src/asset/game1/icon_play.png"));

        button_panel.setBackground(new Color(0XFFFFFF));

        addpoint_btn.setBorderPainted(false);
        addpoint_btn.setContentAreaFilled(false);

        finish_btn.setBorderPainted(false);
        finish_btn.setContentAreaFilled(false);

        lose.setBorderPainted(false);
        lose.setContentAreaFilled(false);

        addpoint_btn.setPreferredSize(new Dimension(110, 110));
        pointfield.setPreferredSize(new Dimension(210, 110));
        finish_btn.setPreferredSize(new Dimension(110, 110));
        lose.setPreferredSize(new Dimension(110, 110));
        header.setPreferredSize(new Dimension(600, 110));

        button_panel.add(header);
        button_panel.add(pointfield);
        button_panel.add(lose);
        button_panel.add(finish_btn);
        button_panel.add(addpoint_btn);

        addpoint_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(pointfield.getText());
                if (userbat + num > userpoint)
                    userbat = userpoint;
                else {
                    userbat += num;
                    pointfield.setText("0");
                    userbatcnt++;
                    computerBatting();
                    clearAllintButtonPanel();
                    setButtonPanel();
                }
            }
        });
        pointfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                } else {
                    int num = Integer.parseInt(String.valueOf(e.getKeyChar()));
                    if (userbat + num > userpoint)
                        pointfield.setText(String.valueOf(userpoint - userbat));
                }
            }
        });
        finish_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computerBatting();
                finish();
            }
        });
        lose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultOut(false);
            }
        });
    }

    private void finish() {
        if (comcard % 13 > usercard % 13) {
            resultOut(false);
        } else if (comcard % 13 == usercard % 13) {
            resultOut(comcard / 13 >= usercard / 13);
        } else {
            resultOut(true);
        }
    }

    private void computerBatting() {
        int card = usercard % 13;
        if (combat >= 9999)
            return;
        if (card >= 9) {
            combat += Math.random() * 50;
        } else if (card > 5) {
            if (rd.nextBoolean()) {
                combat += Math.random() * 100;
            }
        }
    }
}
