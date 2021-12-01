package screen;

import dialog.InfoDialog;
import support.SQLiteManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.*;
import java.awt.font.ImageGraphicAttribute;

public class LoginScreen extends JFrame{
    private  MyPanel panel = new MyPanel();
    private  Image bt0_img, bt1_img;
    public LoginScreen() {
        setTitle("로그인 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);

        JTextField ID = new JTextField();
        JPasswordField PW = new JPasswordField();
        ID.setBackground(Color.BLACK);
        PW.setBackground(Color.BLACK);
        ID.setFont(new Font("Gothic",Font.BOLD,35));
        PW.setFont(new Font("Gothic",Font.BOLD,35));
        ID.setForeground(Color.WHITE);
        PW.setForeground(Color.WHITE);

        JLabel id_label = new JLabel("ID : ");
        JLabel pw_label = new JLabel("PW : ");
        JLabel gameList[] = new JLabel[3];

        String str[] = {"로그인","회원가입"};
        JButton bt[] = new JButton[2];

        ImageIcon bt0 = new ImageIcon("src/asset/btn/LogInButton.png");
        ImageIcon bt1 = new ImageIcon("src/asset/btn/SignInButton.png");
        ImageIcon list1 = new ImageIcon("src/asset/ui/login_gamelist_1.png");
        ImageIcon list2 = new ImageIcon("src/asset/ui/login_gamelist_2.png");
        ImageIcon list3 = new ImageIcon("src/asset/ui/login_gamelist_3.png");
        ImageIcon Doge = new ImageIcon("src/asset/DOGE.png");
        JLabel Coin = new JLabel(Doge);

        bt[0] = new JButton(bt0);
        bt[1] = new JButton(bt1);
        for(int i = 0; i < 2; i++) {
            bt[i].setFocusable(false);
        }
        bt[0].setBounds(450,450,305,100);
        bt[1].setBounds(760,450,305,100);
        bt[0].setBorderPainted(false);
        bt[0].setContentAreaFilled(false);
        bt[1].setBorderPainted(false);
        bt[1].setContentAreaFilled(false);
        ID.setBounds(460,400,250,50);
        PW.setBounds(770,400,250,50);
        id_label.setBounds(570,530,130,30);
        pw_label.setBounds(570,570,130,30);
        Coin.setBounds(800,160,250,250);
        gameList[0] = new JLabel(list1);
        gameList[1] = new JLabel(list2);
        gameList[2] = new JLabel(list3);

        gameList[0].setBounds(200,160,400,80);
        gameList[1].setBounds(200,230,400,80);
        gameList[2].setBounds(200,310,400,80);
        for(int i = 0; i < 2; i++)
            add(bt[i]);
        for(int i = 0; i < 3; i++)
            add(gameList[i]);
        add(ID);add(PW);add(id_label);add(pw_label);
        add(Coin);
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
        private ImageIcon icon = new ImageIcon("src/asset/bg/LoginImg.png");
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






