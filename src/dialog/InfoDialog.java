package dialog;

import screen.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDialog extends JDialog {
    private ImageIcon settingbgImg = new ImageIcon("src/asset/bg/settingScreen.png");
    ImageIcon soundOn = new ImageIcon("src/asset/ui/soundOn.png");
    ImageIcon soundOff = new ImageIcon("src/asset/ui/soundOff.png");
    JButton okButton = new JButton("확인");
    JButton bgSound = new JButton(soundOn);
    JButton efSound = new JButton(soundOn);
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
        bgSound.setBounds(240, 100, 100, 100);
        efSound.setBounds(240, 200, 100, 100);
        bgSound.setBorderPainted(false);
        bgSound.setContentAreaFilled(false);
        efSound.setBorderPainted(false);
        efSound.setContentAreaFilled(false);

        add(okButton);
        add(bgSound);
        add(efSound);


        bg.setBounds(0, 0, 844, 400);
        add(bg);


        setSize(844, 428);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        bgSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bgCheck) {
                    bgCheck = false;
                    bgSound.setIcon(soundOn);
                    MainScreen.Sound("src/asset/GameBackgroundMusic.mp3", true);
                } else {
                    bgCheck = true;
                    bgSound.setIcon(soundOff);
                }
                repaint();
                revalidate();
            }
        });
        efSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (efCheck) {
                    efCheck = false;
                    efSound.setIcon(soundOn);
                } else {
                    efCheck = true;
                    efSound.setIcon(soundOff);
                }
                repaint();
                revalidate();
            }
        });


    }

}
