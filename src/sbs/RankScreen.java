package sbs;

import jjam.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class RankScreen extends  JFrame{
    private MyPanel panel = new MyPanel();
    ArrayList<ResultSet> users;

    RankScreen(String id){
        super("랭킹"); //타이틀


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(null);
        JButton mainButton = new JButton("메인");
        mainButton.setBounds(170,30,70,70);
        add(mainButton);
        String Title[] = {"1등", "2등", "3등", "4등", "5등"};
        setSize(1280,720);
        SQLiteManager b = new SQLiteManager("","","");
        users = b.getOrderByDescPoint();
        System.out.println(users.get(0).toString());
        String  username[] = new String[5];
        JList<String> userRankingTable = new JList<>();
        int j = 0;
        for (ResultSet rs : users ){
            System.out.println(rs.toString());
        }
        JLabel title[] = new JLabel[5];
        for(int i = 1 ; i < 5; i++){
            title[i] = new JLabel(Title[i]);
            title[i].setFont(new Font("Gothic",Font.BOLD,40));
            title[i].setForeground(Color.WHITE);

        }
        Color a = new Color(70,255,154);
        title[0] = new JLabel(Title[0]);
        title[0].setFont(new Font("Gothic",Font.BOLD,50));
        title[0].setForeground(a);


        title[0].setBounds(480,198,100,100);
        title[1].setBounds(350,308,100,100);
        title[2].setBounds(750,308,100,100);
        title[3].setBounds(350,426,100,100);
        title[4].setBounds(750,426,100,100);


    for(int i = 0; i < 5; i++)
        add(title[i]);




//       userRankingTable.add();


        //for(int i = 0; i < 5; i++)
        //    add(title[i]);

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
        private ImageIcon icon = new ImageIcon("asset/rankImg.png");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }

}
