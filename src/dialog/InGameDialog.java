package dialog;

import screen.Game_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameDialog extends JDialog {

    public InGameDialog(JFrame frame, String title, int countpoint) {
        super(frame, title, true);
        setLayout(null);

        JLabel header = new JLabel("현재 점수 " + countpoint + "입니다. 카드를 추가하시겠어요?");
        header.setBounds(30, 30, 200, 50);
        add(header);
    }
}
