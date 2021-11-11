package sbs;

import jjam.SQLiteManager;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.ResultSet;
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
        mainButton.setBounds(30,30,70,70);
        add(mainButton);
        String Title[] = {"순위", "닉네임", "레벨", "전적", "포인트"};
        setSize(1280,720);
        SQLiteManager b = new SQLiteManager("","","");
        users = b.getOrderByDescPoint();
        System.out.println(users.get(0).toString());

        JLabel title[] = new JLabel[5];
        for(int i = 0; i < 5; i++){
            title[i] = new JLabel(Title[i]);
        }

        title[0].setBounds(300,100,100,100);
        title[1].setBounds(400,100,100,100);
        title[2].setBounds(500,100,100,100);
        title[3].setBounds(600,100,100,100);
        title[4].setBounds(700,100,100,100);

        JList<String> userRankingTable = new JList<>();

        for (ResultSet rs : users ){
            System.out.println(rs.toString());
        }
//        userRankingTable.add()


        for(int i = 0; i < 5; i++)
            add(title[i]);

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
