package screen.ui;

import dialog.InfoDialog;
import global.GlobalGUI;
import support.SQLiteManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;

public class MainScreen extends GlobalGUI {
    private InfoDialog settingScreen;
    private static String id = "";

    public static void Sound(String file, boolean Loop) {
        Clip clip;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            if (Loop) clip.loop(-1);//Loop 값이true면 사운드재생을무한반복시킵니다.//false면 한번만재생시킵니다.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MainScreen(String id) {
        super("Casino-J", "src/asset/bg/mainImg.jpg");
        MainScreen.id = id;
        makeMainUI();
        repaintGUI();
    }

    private void makeMainUI() {
        SQLiteManager b = new SQLiteManager("", "", "");
        String username = b.getNickname(id);
        int userpoint = b.getPoint(id);
        JLabel usertext = new JLabel(username);
        JLabel userPoint = new JLabel("$" + String.valueOf(userpoint));
        usertext.setFont(casinoFont(30));
        userPoint.setFont(casinoFont(30));

        //Sound("asset/GameBackgroundMusic.mp3", true);

        settingScreen = new InfoDialog(this, "설정");

        String str[] = {"상점", "프로필", "랭킹", "설정", "게임하기"};
        JButton bt[] = new JButton[5];
        JButton logoutBt = new JButton("로그아웃");
        //for(int i = 0; i < 5; i++) {
        //	bt[i]=new JButton(str[i]);
        //}
        bt[1] = new JButton(str[1]);
        usertext.setBounds(260, 12, 150, 100);
        userPoint.setBounds(410, 12, 400, 100);
        add(usertext);
        add(userPoint);

        ImageIcon profile_bt_img = new ImageIcon("src/asset/game1/icons8-test-account-96.png");
        ImageIcon exchange_bt_img = new ImageIcon("src/asset/btn/exchange_button.png");
        ImageIcon ranking_bt_img = new ImageIcon("src/asset/btn/ranking_button.png");
        ImageIcon game_bt_img = new ImageIcon("src/asset/btn/game_button.png");
        ImageIcon setting_bt_img = new ImageIcon("src/asset/btn/produce_Img.png");
        ImageIcon topbar_img = new ImageIcon("src/asset/ui/topbar.png");


        //버튼 및 라벨 이미지 넣기
        JLabel topBar = new JLabel(topbar_img);
        bt[0] = new JButton(exchange_bt_img);
        bt[1] = new JButton(profile_bt_img);
        bt[2] = new JButton(ranking_bt_img);
        bt[3] = new JButton(setting_bt_img);
        bt[4] = new JButton(game_bt_img);

        //버튼 투명하게 만들기

        for (int i = 0; i < 5; i++) {
            bt[i].setBorderPainted(false);
            bt[i].setContentAreaFilled(false);
        }
        /*bt[0].setBorderPainted(false);
        bt[0].setContentAreaFilled(false);
        bt[2].setBorderPainted(false);
        bt[2].setContentAreaFilled(false);
        bt[4].setBorderPainted(false);
        bt[4].setContentAreaFilled(false);
        bt[3].setBorderPainted(false);
        bt[3].setContentAreaFilled(false);*/

        topBar.setBounds(150, 25, 971, 81);


        bt[0].setBounds(310, 400, 324, 83);
        bt[1].setBounds(170, 30, 70, 70);
        bt[2].setBounds(650, 400, 324, 83);
        bt[3].setBounds(503, 512, 280, 152);
        bt[4].setBounds(310, 290, 664, 93);


        for (int i = 0; i < 5; i++) {
            add(bt[i]);
        }

        add(topBar);

        /*
		panel1.add(bt[1]);
        panel1.add(bt[3]);v
        add(panel1);
        panel2.add(bt[4]);
        add(panel2);
        panel3.add(bt[0]);
        panel3.add(bt[2]);
        add(panel3);
        setLayout(new GridLayout(3,0));
		*/

        bt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExchangeScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
        bt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
        bt[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RankScreen(id);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                setVisible(false); // 창 안보이게 하기
            }
        });


        bt[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*new SettingScreen(id);
                setVisible(false); // 창 안보이게 하기 */
                settingScreen.setVisible(true);
            }
        });
        bt[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameSelectScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
        logoutBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                setVisible(false);
            }
        });
    }
}
