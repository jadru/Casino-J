package jadru;

import javax.swing.*;

public class GameScreen {
    private JPanel frame;

    public static void main(String args[]) {
        new SelectDialog();
        JFrame frame = new JFrame("게임하기");
        frame.setContentPane(new GameScreen().frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}