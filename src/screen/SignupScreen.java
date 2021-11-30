package screen;

import support.SQLiteManager;

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
        JLabel idc = new JLabel();
        JLabel pwc = new JLabel();

        JLabel la[] = new JLabel[4];
        JTextField tf[] = new JTextField[2];
        JPasswordField pf1 = new JPasswordField();
        JPasswordField pf2 = new JPasswordField();
        JButton idCheck = new JButton("ID중복확인");
        JButton pwCheck = new JButton("PW중복확인");
        setSize(500,500);
        for(int i = 0; i < 4; i++)
            la[i]=new JLabel(str[i]);
        for(int i = 0; i < 2; i++)
            tf[i] = new JTextField();

        backLogin.setBounds(100,400,300,30);
        la[0].setBounds(70,70,50,30);
        la[1].setBounds(70,120,50,30);
        la[2].setBounds(70,170,50,30);
        la[3].setBounds(70,220,50,30);

        tf[0].setBounds(120,70,150,30);
        tf[1].setBounds(120,120,150,30);
        pf1.setBounds(120,170,150,30);
        pf2.setBounds(120,220,150,30);

        idc.setBounds(280,120,30,30);
        pwc.setBounds(280,220,30,30);
        idCheck.setBounds(300,120,110,30);
        pwCheck.setBounds(300,220,110,30);


        for(int i = 0; i < 4; i++)
            add(la[i]);
        for(int i =0; i< 2; i++){
            add(tf[i]);
        }
        add(pf1);
        add(pf2);
        add(backLogin);
        add(idCheck);
        add(pwCheck);
        add(idc);
        add(pwc);
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        idCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = tf[1].getText();
                SQLiteManager b = new SQLiteManager("","","");
                if(b.idCheck(a)==true){
                    idc.setText("X");
                }
                else{
                    idc.setText("√");
                }

            }
        });
        pwCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = new String(pf1.getPassword());
                String b = new String(pf2.getPassword());
                if(a.equals("")||b.equals("")){
                    pwc.setText("X");
                }
                else if(a.equals(b)){
                    pwc.setText("√");
                }
                else{
                    pwc.setText("X");
                }
            }
        });

        backLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pwc.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요",
                            "비밀번호 오류", JOptionPane.WARNING_MESSAGE);
                    return ;
                }
                String pwc1 = pwc.getText();
                Character pwc2 = pwc1.charAt(0);
                String idc1 = idc.getText();
                Character idc2 = idc1.charAt(0);
                if(pwc2.equals('√')&&idc2.equals('√')) {
                    JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다",
                            "회원가입 완료!", JOptionPane.INFORMATION_MESSAGE);
                    SQLiteManager text = new SQLiteManager(tf[1].getText(), tf[0].getText(), new String(pf2.getPassword()));
                    text.insert();
                    setVisible(false); // 창 안보이게 하기
                }
                else{
                    JOptionPane.showMessageDialog(null, "회원 가입에 실패하였습니다.\n입력부분을 다시 확인해주세요",
                            "회원가입 실패", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("src/asset/mainimg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}


