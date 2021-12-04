package screen;

import panel.GlobalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingScreen extends JFrame {
    private GlobalPanel panel = new GlobalPanel("src/asset/settingImg.png");

    SettingScreen(String id) {
        super("설정"); //타이틀


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);
        JButton mainButton = new JButton("메인");
        mainButton.setBounds(30, 30, 70, 70);
        add(mainButton);

        setSize(700, 500);


        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });

    }
}
