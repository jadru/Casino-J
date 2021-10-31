package sbs;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class SettingScreen extends  JFrame{
    private MyPanel panel = new MyPanel();
	SettingScreen(String id){
        super("설정"); //타이틀


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        JButton mainButton = new JButton("메인 화면으로");
        add(mainButton);

        setSize(1280,720);


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
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("asset/settingImg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}
