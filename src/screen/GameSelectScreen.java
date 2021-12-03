package screen;

import panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static screen.Game_3.timer;

public class GameSelectScreen extends JFrame {
    String id;
    private GamePanel game;
    GameSelectScreen(String id){
        this.id = id;
        game = new GamePanel();
        createUI();
    }

    private void createUI(){
        setTitle("게임을 선택하세요");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(game);
        setLayout(null);
        setVisible(true);
        setSize(1280, 740);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon blackjack = new ImageIcon("src/asset/btn/BlackJack_btn.png");
        ImageIcon minigame = new ImageIcon("src/asset/btn/MiniGame_btn.png");


        JButton btn1 = new JButton(blackjack);
        btn1.setBounds(100,50,518, 600);
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
        btn3.setBounds(730,50,417, 600);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.schedule(new Game_3.WorkTask(),0,100);
                new Game_3(id);
                dispose();
            }
        });
        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        add(btn3);
    }
}


