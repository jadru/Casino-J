package screen;

import panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(1280, 740);
        setLocationRelativeTo(null);
        setResizable(false);

        JButton btn1 = new JButton("블랙잭");
        btn1.setSize(620, 350);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game_1(id);
                dispose();
            }
        });
        add(btn1);

        JButton btn3 = new JButton("카드맞추기");
        btn3.setSize(620, 350);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game_3(id);
                dispose();
            }
        });
        add(btn3);
    }
}


