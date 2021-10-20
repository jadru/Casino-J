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
        JLabel idc = new JLabel("중복확인이 완료되었습니다.");
        JLabel pwc1 = new JLabel("비밀번호가 같습니다.");
        JLabel pwc2 = new JLabel("비밀번호가 다릅니다.");

        JLabel la[] = new JLabel[4];
        JTextField tf[] = new JTextField[2];
        JPasswordField pf [] = new JPasswordField[2];
        JButton idCheck = new JButton("ID중복확인");
        setSize(500,500);
        for(int i = 0; i < 4; i++)
            la[i]=new JLabel(str[i]);
        for(int i = 0; i < 2; i++)
            tf[i] = new JTextField();
        for(int i = 0;i < 2; i++)
            pf[i] = new JPasswordField();

        backLogin.setBounds(100,400,300,30);
        la[0].setBounds(70,70,50,30);
        la[1].setBounds(70,120,50,30);
        la[2].setBounds(70,170,50,30);
        la[3].setBounds(70,220,50,30);

        tf[0].setBounds(120,70,150,30);
        tf[1].setBounds(120,120,150,30);
        pf[0].setBounds(120,170,150,30);
        pf[1].setBounds(120,220,150,30);




        idCheck.setBounds(300,120,100,30);
        pwc1.setBounds(300,220,100,30);
        pwc2.setBounds(300,220,100,30);

        if(pf[0].equals(pf[1])){
            add(pwc1);
        }
        else
            add(pwc2);

        for(int i = 0; i < 4; i++)
            add(la[i]);
        for(int i =0; i< 2; i++){
            add(tf[i]);add(pf[i]);
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
                /* 회원가입 실패
                JOptionPane.showMessageDialog(null, "회원 가입에 실패하였습니다. 입력부분을 다시 확인해주세요",
                        "회원가입 실패", JOptionPane.WARNING_MESSAGE);
                */

                JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다",
                        "회원가입 완료!", JOptionPane.INFORMATION_MESSAGE);
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


