package jjam;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginScreen extends JFrame{

    public LoginScreen() {
        setTitle("로그인 화면");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        String str[] = {"로그인","회원가입"};
        JButton bt[] = new JButton[2];
        for(int i = 0; i < 2; i++) {
            bt[i]=new JButton(str[i]);
        }



        for(int i = 0; i < 2; i++)
            c.add(bt[i]);
        setSize(500,500);
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
                setVisible(false); // 창 안보이게 하기
            }
        });

        /*
        bt[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingScreen();
                setVisible(false); // 창 안보이게 하기
            }
        });/*
        bt[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileScreen();
                setVisible(false); // 창 안보이게 하기
            }
        });*/



    }
    public static void main(String[] agrs) {
        new LoginScreen();
    }
}






