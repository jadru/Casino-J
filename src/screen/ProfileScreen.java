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
        int userskin = b.getSkin(id);

        double all=userWin+userLose;
        double pow = (userWin/all)*100;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);

        ImageIcon mainbtn = new ImageIcon("src/asset/btn/main_btn.png");
        JButton mainButton = new JButton(mainbtn);
        mainButton.setBounds(30,20,100,100);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);

        String[] title = {"ID","이름","전적","레벨","순위"," $ "};
        JLabel[] gridTitle = new JLabel[6];
        JLabel[] gridElement = new JLabel[6];
        JLabel CardText = new JLabel("보유중인 카드");
        JButton CardBt[] = new JButton[5];


        ImageIcon selectCard = new ImageIcon("src/asset/ui/select_Img.png");
        ImageIcon card0_backside = new ImageIcon("src/asset/theme/card_back_0.png");
        ImageIcon card1_backside = new ImageIcon("src/asset/theme/card_back_1.png");
        ImageIcon card2_backside = new ImageIcon("src/asset/theme/card_back_2.png");
        ImageIcon card3_backside = new ImageIcon("src/asset/theme/card_back_3.png");
        ImageIcon card4_backside = new ImageIcon("src/asset/theme/card_back_4.png");

        JLabel select = new JLabel(selectCard);

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

        if(userskin == 0){
            select.setBounds(690,220,130,30);
        }
        else if(userskin == 1){
            select.setBounds(840,220,130,30);
        }
        else if(userskin == 2){
            select.setBounds(990,220,130,30);
        }
        else if(userskin == 3){
            select.setBounds(690,420,130,30);
        }
        else if(userskin == 4){
            select.setBounds(840,420,130,30);
        }



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
        gridElement[2] = new JLabel(userWin+"승 "+userLose+"패 승률:"+Math.round(pow)+"퍼");
        gridElement[3] = new JLabel(String.valueOf(userLevel));
        gridElement[4] = new JLabel("-");//랭킹 들어가야함
        gridElement[5] = new JLabel(String.valueOf(userPoint));

        for(int i = 0; i < 6; i++) {
            gridElement[i].setBounds(300,100+i*70, 500,100);
            gridElement[i].setFont(new Font("Gothic",Font.BOLD,35));
            gridElement[i].setForeground(Color.WHITE);
            add(gridElement[i]);
        }
        CardText.setBounds(690, 50,200,100);

        add(select);

        add(CardText);
        add(mainButton);

        for(int i =0; i< 5; i++){
            if(b.skinCheck(id,i)==true){
                add(CardBt[i]);
            }
        }

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

        CardBt[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.SkinChange(id,0);
                select.setBounds(690,220,130,30);
                repaint();
            }
        });
        CardBt[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.SkinChange(id,1);
                select.setBounds(840,220,130,30);
                repaint();
            }
        });
        CardBt[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.SkinChange(id,2);
                select.setBounds(990,220,130,30);
                repaint();
            }
        });
        CardBt[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.SkinChange(id,3);
                select.setBounds(690,420,130,30);
                repaint();
            }
        });
        CardBt[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.SkinChange(id,4);
                select.setBounds(840,420,130,30);
                repaint();
            }
        });
        repaint(); revalidate();


    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("src/asset/bg/profileImg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);

        }
    }
}
