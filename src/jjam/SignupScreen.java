package jjam;

import sbs.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupScreen extends JFrame {
    private MyPanel panel = new MyPanel();
    SignupScreen(){
        super("회원가입"); //타이틀

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        JButton backLogin = new JButton("회원가입 완료");

        setLayout(null);

        String str[] = {"닉네임","ID","PW","PW확인"};
        JLabel la[] = new JLabel[4];
        JTextField tf[] = new JTextField[4];
        JButton idCheck = new JButton("ID중복확인");
        setSize(500,500);
        for(int i = 0; i < 4; i++)
            la[i]=new JLabel(str[i]);
        for(int i = 0; i < 4; i++)
            tf[i] = new JTextField();

        backLogin.setBounds(100,400,300,30);
        la[0].setBounds(50,70,50,30);
        la[1].setBounds(50,120,50,30);
        la[2].setBounds(50,170,50,30);
        la[3].setBounds(50,220,50,30);

        tf[0].setBounds(100,70,150,30);
        tf[1].setBounds(100,120,150,30);
        tf[2].setBounds(100,170,150,30);
        tf[3].setBounds(100,220,150,30);

        idCheck.setBounds(280,120,100,30);



        for(int i = 0; i < 4; i++) {
            add(la[i]);
            add(tf[i]);
        }
        add(backLogin);
        add(idCheck);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        backLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new LoginScreen();
                setVisible(false); // 창 안보이게 하기
            }
        });
    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("C:\\Users\\SeongByeongseok\\Desktop\\img.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}


