package dialog;

import screen.MainScreen;
import support.SQLiteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameResultDialog extends JDialog {

    public InGameResultDialog(JFrame frame, String title, boolean iswinorlose, String user_id) {
        super(frame, title, true);
        screen.Game_1.setFinish();
        setLayout(new FlowLayout());
        setSize(400, 200);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        SQLiteManager b = new SQLiteManager("","","");
        if(iswinorlose){
            JLabel label1 = new JLabel("WIN! 포인트 100점 적립");
            label1.setFont(new Font("Arial", Font.BOLD, 30));
            add(label1);
            b.givePoint(user_id,100);
        }else{
            JLabel label1 = new JLabel("졌습니다...");
            label1.setFont(new Font("Arial", Font.BOLD, 30));
            add(label1);
        }
        JButton btn = new JButton("메인으로 가기");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                frame.removeAll();
                dispose();
                removeAll();
                repaint();
                revalidate();
                new MainScreen(user_id);
                setVisible(false);
                remove(frame);
            }
        });
        add(btn);
        setVisible(true);
    }
}