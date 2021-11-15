package screen;

import support.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ExchangeScreen extends  JFrame{
    private Image card1_back,card2_back,card3_back,card4_back,card5_back;
    private Image card1_front,card2_front,card3_front,card4_front,card5_front;
    public boolean checkFrontBack = true;
    private MyPanel panel = new MyPanel();
	ExchangeScreen(String id){
        super("상점"); //타이틀
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);
        SQLiteManager b = new SQLiteManager("","","");
        String username = b.getNickname(id);
        int userpoint = b.getPoint(id);
        JLabel usertext = new JLabel(username);
        JLabel userPoint = new JLabel("❤"+String.valueOf(userpoint));
        usertext.setFont(new Font("Gothic",Font.BOLD,30));
        userPoint.setFont(new Font("Gothic",Font.BOLD,30));
        usertext.setBounds(260,12,150,100);
        userPoint.setBounds(410,12,400,100);
        add(usertext);
        add(userPoint);
        ImageIcon topbar_img = new ImageIcon("asset/topbar.png");
        ImageIcon frontBt = new ImageIcon("asset/frontBt_Img.png");
        ImageIcon backBt = new ImageIcon("asset/backBt_Img.png");
        ImageIcon buyBt = new ImageIcon("asset/buyBt_Img.png");
        ImageIcon possessionBt = new ImageIcon("asset/possessionBt_Img.png");


        JPanel jp = new JPanel(){
            @Override
            public void paint(Graphics g) {
                if (checkFrontBack == true) {
                    g.drawImage(card1_back, 43, 0, 200, 270, this);
                    g.drawImage(card2_back, 323, 0, 200, 270, this);
                    g.drawImage(card3_back, 605, 0, 200, 270, this);
                    g.drawImage(card4_back, 885, 0, 200, 270, this);

                }
                else{
                    g.drawImage(card1_front, 43, 0, 200, 270, this);
                    g.drawImage(card2_front, 323, 0, 200, 270, this);
                    g.drawImage(card3_front, 605, 0, 200, 270, this);
                    g.drawImage(card4_front, 885, 0, 200, 270, this);

                }
            }
        };
        ImageIcon card1_backside = new ImageIcon("asset/card_back_2.png");
        ImageIcon card2_backside = new ImageIcon("asset/card_back_3.png");
        ImageIcon card3_backside = new ImageIcon("asset/card_back_4.png");
        ImageIcon card4_backside = new ImageIcon("asset/card_back_0.png");


        ImageIcon card1_frontside = new ImageIcon("asset/card2_front_side.jpeg");
        ImageIcon card2_frontside = new ImageIcon("asset/card3_front_side.jpeg");
        ImageIcon card3_frontside = new ImageIcon("asset/card4_front_side.jpeg");
        ImageIcon card4_frontside = new ImageIcon("asset/card5_front_side.jpeg");


        card1_back = card1_backside.getImage();
        card2_back = card2_backside.getImage();
        card3_back = card3_backside.getImage();
        card4_back = card4_backside.getImage();


        card1_front = card1_frontside.getImage();
        card2_front = card2_frontside.getImage();
        card3_front = card3_frontside.getImage();
        card4_front = card4_frontside.getImage();


        jp.setBounds(80,270,1200,300);
        add(jp);
        JButton bt[] = new JButton[6];

        bt[0] = new JButton(backBt);
        bt[1] = new JButton(frontBt);
        for(int i = 2; i < 6; i++)
            bt[i] = new JButton(buyBt);

        bt[0].setBounds(100,200,285,65);
        bt[1].setBounds(400,200,285,65);

        for(int i = 2; i < 6; i++)
            bt[i].setBounds(100+((i-2) * 280),555,250,65);

        for(int i = 0; i < 6; i++){
            bt[i].setBorderPainted(false);
            bt[i].setContentAreaFilled(false);
        }

        JLabel topBar = new JLabel(topbar_img);
        topBar.setBounds(150,25,971,81);
        JButton mainButton = new JButton("메인");
        mainButton.setBounds(170,30,70,70);

        for(int i = 0; i < 6; i++)
            add(bt[i]);
        add(mainButton);
        add(topBar);
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
        bt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFrontBack = true;
                revalidate();
                repaint();
            }
        });
        bt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFrontBack = false;
                revalidate();
                repaint();
            }
        });

    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("asset/exchangeImg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}
