package screen.ui;

import global.GlobalGUI;
import support.SQLiteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends GlobalGUI {
    public LoginScreen() {
        super("Casino-J에 로그인", "src/asset/bg/LoginImg.png");

        JTextField ID = new JTextField("ID");
        JPasswordField PW = new JPasswordField("PASS");
        ID.setBackground(Color.BLACK);
        PW.setBackground(Color.BLACK);
        ID.setForeground(Color.WHITE);
        PW.setForeground(Color.WHITE);
        ID.requestFocus();
        ID.grabFocus();
        ID.setBounds(460, 400, 250, 50);
        PW.setBounds(770, 400, 250, 50);

        JLabel gameList[] = new JLabel[3];

        String str[] = {"로그인", "회원가입"};
        JButton bt[] = new JButton[2];

        ImageIcon bt0 = new ImageIcon("src/asset/btn/LogInButton.png");
        ImageIcon bt1 = new ImageIcon("src/asset/btn/SignUpButton.png");
        ImageIcon list1 = new ImageIcon("src/asset/ui/login_gamelist_1.png");
        ImageIcon list3 = new ImageIcon("src/asset/ui/login_gamelist_2.png");
        ImageIcon Doge = new ImageIcon("src/asset/DOGE.png");
        JLabel Coin = new JLabel(Doge);

        bt[0] = new JButton(bt0);
        bt[1] = new JButton(bt1);
        for (int i = 0; i < 2; i++) {
            bt[i].setFocusable(false);
        }
        bt[0].setBounds(450, 450, 305, 100);
        bt[1].setBounds(760, 450, 305, 100);

        bt[0].setBorderPainted(false);
        bt[0].setContentAreaFilled(false);

        bt[1].setBorderPainted(false);
        bt[1].setContentAreaFilled(false);

        Coin.setBounds(800, 160, 250, 250);
        gameList[0] = new JLabel(list1);
        gameList[2] = new JLabel(list3);

        gameList[0].setBounds(200, 180, 400, 80);
        gameList[2].setBounds(200, 270, 400, 80);
        for (int i = 0; i < 2; i++)
            add(bt[i]);
        for (int i = 0; i < 3; i++)
            if (i != 1) add(gameList[i]);
        add(ID);
        add(PW);
        add(Coin);

        ID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ID.getText().equals("ID"))
                    ID.setText("");
            }
        });

        PW.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (PW.getText().equals("PASS"))
                    PW.setText("");

            }
        });

        PW.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
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
        repaintGUI();
    }

    public static void main(String[] agrs) {
        new LoginScreen();
    }

    private void loginPerformed(String id, String pw) {
        SQLiteManager b = new SQLiteManager("", "", "");
        if (b.login(id, pw)) {
            JOptionPane.showMessageDialog(null, "로그인시 10point가 지급됩니다.",
                    "포인트 지급", JOptionPane.WARNING_MESSAGE);
            b.givePoint(id, 10);
            new MainScreen(id);
            setVisible(false); // 창 안보이게 하기
        } else {
            JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.\n아이디와 패스워드를 다시 확인해주세요",
                    "로그인 실패", JOptionPane.WARNING_MESSAGE);
        }
    }
}






