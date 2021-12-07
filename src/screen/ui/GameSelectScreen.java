package screen.ui;

import screen.game.Game_1;
import screen.game.Game_2;
import global.GlobalGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        ImageIcon Indian = new ImageIcon("src/asset/btn/IndianPoker_btn.png");
        ImageIcon mainbtn = new ImageIcon("src/asset/btn/main_btn.png");
        JButton mainButton = new JButton(mainbtn);
        mainButton.setBounds(30, 20, 100, 100);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);
        add(mainButton);


        JButton btn1 = new JButton(blackjack);
        btn1.setBounds(260, 200, 302, 350);
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

        JButton btn2 = new JButton(Indian);
        btn2.setBounds(720, 200, 309, 350);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game_2(id);
                dispose();
            }
        });
        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        add(btn2);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(id);

                setVisible(false); // 창 안보이게 하기
            }
        });
    }
}


