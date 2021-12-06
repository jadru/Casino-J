package dialog;

import screen.ui.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDialog extends JDialog {
    private ImageIcon settingbgImg = new ImageIcon("src/asset/bg/InfoDialog_bg.png");
    JButton okButton = new JButton("확인");
    static boolean bgCheck = false;
    static boolean efCheck = false;

    private JLabel bg = new JLabel(settingbgImg);

    public InfoDialog(JFrame frame, String title) {
        super(frame, title, true);
        setLayout(null);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기

        okButton.setBounds(700, 320, 100, 50);

        add(okButton);



        bg.setBounds(0, 0, 844, 400);
        add(bg);


        setSize(844, 428);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        


    }

}
