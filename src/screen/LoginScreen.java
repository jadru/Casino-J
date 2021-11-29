package screen;

import dialog.MyModalDialog;
import support.SQLiteManager;

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
        JPasswordField PW = new JPasswordField();
        JLabel id_label = new JLabel("ID : ");
        JLabel pw_label = new JLabel("PW : ");

        String str[] = {"로그인","회원가입"};
        JButton bt[] = new JButton[2];
        for(int i = 0; i < 2; i++) {
            bt[i]=new JButton(str[i]);
            bt[i].setFocusable(false);
        }
        bt[0].setBounds(740,525,100,80);
        bt[1].setBounds(840,525,100,80);
        ID.setBounds(600,530,130,30);
        PW.setBounds(600,570,130,30);
        id_label.setBounds(570,530,130,30);
        pw_label.setBounds(570,570,130,30);
        for(int i = 0; i < 2; i++)
            add(bt[i]);
        add(ID);add(PW);add(id_label);add(pw_label);
        setSize(1280,720);
		/*

		setVisible(true);*/

        Dimension frameSize = getSize();

        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        PW.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    loginPerformed(ID.getText(), PW.getText());
            }
        });


        bt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPerformed(ID.getText(), PW.getText());
                repaint();
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
    private void loginPerformed(String id, String pw){
        SQLiteManager b = new SQLiteManager("","","");
        if(b.login(id,pw)){
            JOptionPane.showMessageDialog(null, "로그인시 10point가 지급됩니다.",
                    "포인트 지급", JOptionPane.WARNING_MESSAGE);
            b.givePoint(id,10);
            new MainScreen(id);
            setVisible(false); // 창 안보이게 하기
        }
        else{
            JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.\n아이디와 패스워드를 다시 확인해주세요",
                    "로그인 실패", JOptionPane.WARNING_MESSAGE);
        }
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






