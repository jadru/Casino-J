package screen.ui;

import screen.game.Game_1;
import screen.game.Game_3;
import global.GlobalGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static screen.game.Game_3.timer;

public class GameSelectScreen extends GlobalGUI {
    String id;

    GameSelectScreen(String id) {
        super("게임을 선택하세요", "src/asset/game1/game1_bg.png");
        this.id = id;
        createUI();
        repaintGUI();
    }

    private void createUI() {

        ImageIcon blackjack = new ImageIcon("src/asset/btn/BlackJack_btn.png");
        ImageIcon minigame = new ImageIcon("src/asset/btn/MiniGame_btn.png");

        ImageIcon mainbtn = new ImageIcon("src/asset/btn/main_btn.png");
        JButton mainButton = new JButton(mainbtn);
        mainButton.setBounds(30, 20, 100, 100);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);
        add(mainButton);


        JButton btn1 = new JButton(blackjack);
        btn1.setBounds(100, 50, 518, 600);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game_1(id);
                dispose();
            }
        });
        btn1.setBorderPainted(false);
        btn1.setContentAreaFilled(false);
        add(btn1);

        JButton btn3 = new JButton(minigame);
        btn3.setBounds(730, 50, 417, 600);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.schedule(new Game_3.WorkTask(), 0, 100);
                new Game_3(id);
                dispose();
            }
        });
        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        add(btn3);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
    }
}


