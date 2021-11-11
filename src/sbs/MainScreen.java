package sbs;
import  jjam.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainScreen extends JFrame{
    private MyPanel panel = new MyPanel();
	public MainScreen(String id) {
		setTitle("Main화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        SQLiteManager b = new SQLiteManager("","","");
        String username = b.getNickname(id);
        int userpoint = b.getPoint(id);
        JLabel usertext = new JLabel(username);
        JLabel userPoint = new JLabel("❤"+String.valueOf(userpoint));
        usertext.setFont(new Font("Gothic",Font.BOLD,30));
        userPoint.setFont(new Font("Gothic",Font.BOLD,30));
        setLayout(null);
		String str[] = {"상점","프로필","랭킹","설정", "게임하기"};
		JButton bt[] = new JButton[5];

		//for(int i = 0; i < 5; i++) {
		//	bt[i]=new JButton(str[i]);
        //}
        bt[1] = new JButton(str[1]);
        usertext.setBounds(260,12,150,100);
        userPoint.setBounds(410,12,400,100);
        add(usertext);
        add(userPoint);


        ImageIcon exchange_bt_img = new ImageIcon("asset/exchange_button.png");
        ImageIcon ranking_bt_img = new ImageIcon("asset/ranking_button.png");
        ImageIcon game_bt_img = new ImageIcon("asset/game_button.png");
        ImageIcon setting_bt_img = new ImageIcon("asset/setting_button.png");
        ImageIcon topbar_img = new ImageIcon("asset/topbar.png");

        //버튼 및 라벨 이미지 넣기
       JLabel topBar = new JLabel(topbar_img);
        bt[0] = new JButton(exchange_bt_img);
        bt[2] = new JButton(ranking_bt_img);
        bt[3] = new JButton(setting_bt_img);
        bt[4] = new JButton(game_bt_img);

        //버튼 투명하게 만들기
        bt[0].setBorderPainted(false);
        bt[0].setContentAreaFilled(false);
        bt[2].setBorderPainted(false);
        bt[2].setContentAreaFilled(false);
        bt[4].setBorderPainted(false);
        bt[4].setContentAreaFilled(false);

        topBar.setBounds(150,25,971,81);


        bt[0].setBounds(310,400,324,83);
        bt[1].setBounds(170,30,70,70);
        bt[2].setBounds(650,400,324,83);
        bt[3].setBounds(1035,270,108,184);
        bt[4].setBounds(310,290,664,93);


        for(int i = 0 ; i < 5; i++){
            add(bt[i]);
        }
        add(topBar);
        /*
		panel1.add(bt[1]);
        panel1.add(bt[3]);v
        add(panel1);
        panel2.add(bt[4]);
        add(panel2);
        panel3.add(bt[0]);
        panel3.add(bt[2]);
        add(panel3);
        setLayout(new GridLayout(3,0));
		*/

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
                new ExchangeScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });
        bt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });
        bt[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });



        bt[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });
        bt[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new jadru.GameScreen(id);
                setVisible(false); // 창 안보이게 하기
            }
        });
		
		
		
	}
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("asset/mainImg.jpg");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
	public static void main(String[] agrs) {
	}
}
