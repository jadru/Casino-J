package sbs;

import jjam.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ProfileScreen extends JFrame{
    private MyPanel panel = new MyPanel();
	ProfileScreen(String id){
        super("프로필"); //타이틀
        SQLiteManager b = new SQLiteManager("","","");
        String username = b.getNickname(id);
        int userwinLose;
        int userLevel = b.getLevel(id);
        int userRank;
        int userPoint = b.getPoint(id);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);
        JPanel jp = new JPanel();
        jp.setBackground(Color.white);
        jp.setBounds(750,150,400,400);
        JButton mainButton = new JButton("<");
        mainButton.setBounds(30,30,70,70);


        String[] title = {"ID","이름","전적","레벨","순위","보유 포인트"};
        JLabel[] gridTitle = new JLabel[6];
        JLabel[] gridElement = new JLabel[6];
        JLabel CardText = new JLabel("보유중인 카드");
        CardText.setFont(new Font("Gothic",Font.BOLD,20));

        for(int i = 0; i < 6; i++) {
            gridTitle[i] = new JLabel(title[i]);
            gridTitle[i].setBounds(300,100+i*70, 150,100);
            gridTitle[i].setFont(new Font("Gothic",Font.BOLD,20));
            add(gridTitle[i]);
        }

        gridElement[0] = new JLabel(id);
        gridElement[1] = new JLabel(username);
        gridElement[2] = new JLabel("-");//전적들어가야함
        gridElement[3] = new JLabel(String.valueOf(userLevel));
        gridElement[4] = new JLabel("-");//랭킹 들어가야함
        gridElement[5] = new JLabel(String.valueOf(userPoint));

        for(int i = 0; i < 6; i++) {
            gridElement[i].setBounds(450,100+i*70, 100,100);
            gridElement[i].setFont(new Font("Gothic",Font.BOLD,20));
            add(gridElement[i]);
        }
        CardText.setBounds(750, 50,150,100);

        add(jp);
        add(CardText);
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
    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("asset/profileImg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}

