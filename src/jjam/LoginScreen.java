package jjam;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginScreen extends JFrame{
    private  MyPanel panel = new MyPanel();
    public LoginScreen() {
        setTitle("로그인 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);

        JTextField ID = new JTextField();
        JTextField PW = new JTextField();
        JLabel id = new JLabel("ID : ");
        JLabel pw = new JLabel("PW : ");

        String str[] = {"로그인","회원가입"};
        JButton bt[] = new JButton[2];
        for(int i = 0; i < 2; i++) {
            bt[i]=new JButton(str[i]);
        }
        bt[0].setBounds(740,525,100,80);
        bt[1].setBounds(840,525,100,80);
        ID.setBounds(600,530,130,30);
        PW.setBounds(600,570,130,30);
        id.setBounds(570,530,130,30);
        pw.setBounds(570,570,130,30);
        for(int i = 0; i < 2; i++)
            add(bt[i]);
        add(ID);add(PW);add(id);add(pw);
        setSize(1280,720);
		/*

		setVisible(true);*/

        Dimension frameSize = getSize();

        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);




        bt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new sbs.MainScreen();
                setVisible(false); // 창 안보이게 하기
            }
        });
        bt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignupScreen();
                //setVisible(false); // 창 안보이게 하기
            }
        });



    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("asset/background.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
    public static void main(String[] agrs) {
        new LoginScreen();

    }
}






