package screen;

import support.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ProfileScreen extends JFrame{
    private MyPanel panel = new MyPanel();
    private Image card0_back,card1_back,card2_back,card3_back,card4_back;

    public boolean checkFrontBack = true;
	ProfileScreen(String id){
        super("프로필"); //타이틀
        SQLiteManager b = new SQLiteManager("","","");
        String username = b.getNickname(id);
        int userWin=b.getWin(id);
        int userLose=b.getLose(id);
        int userLevel = b.getLevel(id);
        int userRank;
        int userPoint = b.getPoint(id);
        double all=userWin+userLose;
        double pow = (userWin/all)*100;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);

        JButton mainButton = new JButton("메인");
        mainButton.setBounds(30,30,70,70);

        String[] title = {"ID","이름","전적","레벨","순위"," $ "};
        JLabel[] gridTitle = new JLabel[6];
        JLabel[] gridElement = new JLabel[6];
        JLabel CardText = new JLabel("보유중인 카드");
        JButton CardBt[] = new JButton[5];



        ImageIcon card0_backside = new ImageIcon("asset/card_back_0.png");
        ImageIcon card1_backside = new ImageIcon("asset/card_back_1.png");
        ImageIcon card2_backside = new ImageIcon("asset/card_back_2.png");
        ImageIcon card3_backside = new ImageIcon("asset/card_back_3.png");
        ImageIcon card4_backside = new ImageIcon("asset/card_back_4.png");

        card0_back = card0_backside.getImage();
        card1_back = card1_backside.getImage();
        card2_back = card2_backside.getImage();
        card3_back = card3_backside.getImage();
        card4_back = card4_backside.getImage();

        CardBt[0] = new JButton(card0_backside);
        CardBt[1] = new JButton(card1_backside);
        CardBt[2] = new JButton(card2_backside);
        CardBt[3] = new JButton(card3_backside);
        CardBt[4] = new JButton(card4_backside);



        CardBt[0].setBounds(690,150,130,160);
        CardBt[1].setBounds(840,150,130,160);
        CardBt[2].setBounds(990,150,130,160);
        CardBt[3].setBounds(690,350,130,160);
        CardBt[4].setBounds(840,350,130,160);


        CardText.setFont(new Font("Gothic",Font.BOLD,25));

        for(int i = 0; i < 6; i++) {
            gridTitle[i] = new JLabel(title[i]);
            gridTitle[i].setBounds(200,100+i*70, 300,100);
            gridTitle[i].setFont(new Font("Gothic",Font.BOLD,35));
            gridTitle[i].setForeground(Color.WHITE);
            add(gridTitle[i]);
        }

        gridElement[0] = new JLabel(id);
        gridElement[1] = new JLabel(username);
        gridElement[2] = new JLabel(userWin+"승 "+userLose+"패 승률:"+pow+"%");
        gridElement[3] = new JLabel(String.valueOf(userLevel));
        gridElement[4] = new JLabel("-");//랭킹 들어가야함
        gridElement[5] = new JLabel(String.valueOf(userPoint));

        for(int i = 0; i < 6; i++) {
            gridElement[i].setBounds(300,100+i*70, 400,100);
            gridElement[i].setFont(new Font("Gothic",Font.BOLD,35));
            gridElement[i].setForeground(Color.WHITE);
            add(gridElement[i]);
        }
        CardText.setBounds(690, 50,150,100);



        add(CardText);
        add(mainButton);
        for(int i =0; i< 5; i++)
            add(CardBt[i]);
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
