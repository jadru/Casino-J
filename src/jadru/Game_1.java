package jadru;

import javax.swing.*;

public class Game_1 {

    Game_1() {
        JFrame frame = new JFrame("게임하기");
        frame.setContentPane(new GameScreen().frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
