package sbs;

import jjam.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ProfileScreen extends JFrame{
    private MyPanel panel = new MyPanel();
    private Image card1_back,card2_back,card3_back,card4_back,card5_back;
    private Image card1_front,card2_front,card3_front,card4_front,card5_front;
    public boolean checkFrontBack = true;
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
        JPanel jp = new JPanel(){


            @Override
            public void paint(Graphics g) {
                if (checkFrontBack == true) {
                    g.drawImage(card1_back, 0, 0, 130, 200, this);
                    g.drawImage(card2_back, 150, 0, 130, 200, this);
                    g.drawImage(card3_back, 300, 0, 130, 200, this);
                    g.drawImage(card4_back, 0, 250, 130, 200, this);
                    g.drawImage(card5_back, 150, 250, 130, 200, this);
                }
                else{
                    g.drawImage(card1_front, 0, 0, 130, 200, this);
                    g.drawImage(card2_front, 150, 0, 130, 200, this);
                    g.drawImage(card3_front, 300, 0, 130, 200, this);
                    g.drawImage(card4_front, 0, 250, 130, 200, this);
                    g.drawImage(card5_front, 150, 250, 130, 200, this);
                }
            }
        };
        jp.setBackground(Color.white);
        jp.setBounds(650,150,450,450);
        JButton mainButton = new JButton("<");
        mainButton.setBounds(30,30,70,70);





        String[] title = {"ID","이름","전적","레벨","순위","보유 포인트"};
        JLabel[] gridTitle = new JLabel[6];
        JLabel[] gridElement = new JLabel[6];
        JLabel CardText = new JLabel("보유중인 카드");
        JButton CardFrontBack = new JButton("카드 앞/뒷면 보기");

        CardFrontBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkFrontBack == true)
                    checkFrontBack = false;
                else
                    checkFrontBack = true;
                revalidate();
                repaint();
            }
        });



        ImageIcon card1_backside = new ImageIcon("asset/card1_back_side.png");
        ImageIcon card2_backside = new ImageIcon("asset/card2_back_side.png");
        ImageIcon card3_backside = new ImageIcon("asset/card3_back_side.png");
        ImageIcon card4_backside = new ImageIcon("asset/card4_back_side.png");
        ImageIcon card5_backside = new ImageIcon("asset/card5_back_side.png");

        ImageIcon card1_frontside = new ImageIcon("asset/card1_front_side.jpeg");
        ImageIcon card2_frontside = new ImageIcon("asset/card2_front_side.jpeg");
        ImageIcon card3_frontside = new ImageIcon("asset/card3_front_side.jpeg");
        ImageIcon card4_frontside = new ImageIcon("asset/card4_front_side.jpeg");
        ImageIcon card5_frontside = new ImageIcon("asset/card5_front_side.jpeg");

        card1_back = card1_backside.getImage();
        card2_back = card2_backside.getImage();
        card3_back = card3_backside.getImage();
        card4_back = card4_backside.getImage();
        card5_back = card5_backside.getImage();

        card1_front = card1_frontside.getImage();
        card2_front = card2_frontside.getImage();
        card3_front = card3_frontside.getImage();
        card4_front = card4_frontside.getImage();
        card5_front = card5_frontside.getImage();



        CardText.setFont(new Font("Gothic",Font.BOLD,20));
        CardFrontBack.setFont(new Font("Gothic",Font.BOLD,15));

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
        CardText.setBounds(650, 50,150,100);
        CardFrontBack.setBounds(850, 75,200,50);
        add(jp);

        add(CardText);
        add(CardFrontBack);
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
