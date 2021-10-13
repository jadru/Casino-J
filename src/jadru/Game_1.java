package jadru;

import javax.swing.*;
import java.awt.*;

public class Game_1 {

    private JPanel frame;

    Game_1() {
        JFrame frame = new JFrame("게임하기");
        frame.setContentPane(new Game_1().frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280,1024);
        Dimension frameSize = frame.getSize();

        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        frame.setVisible(true);
    }

}
