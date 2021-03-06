package dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDialog extends JDialog {
    JButton okButton = new JButton("확인");
    private ImageIcon settingbgImg = new ImageIcon("src/asset/bg/InfoDialog_bg.png");
    private JLabel bg = new JLabel(settingbgImg);

    public InfoDialog(JFrame frame, String title) {
        super(frame, title, true);
        setLayout(null);

        setSize(844, 428);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기

        okButton.setBounds(700, 320, 100, 50);

        add(okButton);

        bg.setBounds(0, 0, 844, 400);
        add(bg);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


    }

}
