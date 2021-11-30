package dialog;

import screen.Game_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGamePlayDialog extends JDialog {
    int point;
    public InGamePlayDialog(JFrame frame, String title, int point) {
        super(frame, title, true);
        setLayout(null);
        setSize(400, 200);
        this.point = point;
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2 - 400,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("현재 점수 " + point + "점입니다. 카드를 추가하시겠어요?");
        JButton addcard_btn = new JButton("한장 더");
        JButton lose_btn = new JButton("기권 패");
        JButton finish_btn = new JButton("한판 승");

        addcard_btn.setBounds(30, 60, 110, 110);
        lose_btn.setBounds(140, 60, 110, 110);
        finish_btn.setBounds(250, 60, 110, 110);
        header.setBounds(30, 30, 340, 30);

        add(header); add(lose_btn); add(finish_btn); add(addcard_btn);
        addcard_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.Game_1.addUserCardFromDialog();
                setVisible(false);
                dispose();
                removeAll();
                repaint();
                revalidate();
                remove(frame);
            }
        });
        lose_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.Game_1.resultOut(false, point);
                setVisible(false);
                dispose();
                removeAll();
                repaint();
                revalidate();
                screen.Game_1.setFinish();
                remove(frame);
            }
        });
        finish_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.Game_1.finishGame();
                setVisible(false);
                dispose();
                removeAll();
                repaint();
                revalidate();
                screen.Game_1.setFinish();
                remove(frame);
            }
        });
        setVisible(true);
    }
}
