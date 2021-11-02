package sbs;

import jjam.SQLiteManager;
import jjam.SignupScreen;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ExchangeScreen extends  JFrame{
    private MyPanel panel = new MyPanel();
	ExchangeScreen(String id){
        super("상점"); //타이틀
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);
        SQLiteManager b = new SQLiteManager("","","");
        String username = b.getNickname(id);
        int userpoint = b.getPoint(id);
        JLabel usertext = new JLabel(username);
        JLabel userPoint = new JLabel("❤"+String.valueOf(userpoint));
        usertext.setFont(new Font("Gothic",Font.BOLD,30));
        userPoint.setFont(new Font("Gothic",Font.BOLD,30));
        usertext.setBounds(190,12,150,100);
        userPoint.setBounds(340,12,350,100);
        add(usertext);
        add(userPoint);





        JButton mainButton = new JButton("메인");
        mainButton.setBounds(30,30,70,70);
        JButton profileButton = new JButton("프로필");
        profileButton.setBounds(100,30,70,70);

        add(profileButton);
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
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("asset/exchangeImg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}
