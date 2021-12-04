package screen;

import support.GlobalGUI;
import support.SQLiteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class RankScreen extends GlobalGUI {
    ArrayList<ResultSet> users;

    RankScreen(String id) throws SQLException {
        super("랭킹", "src/asset/bg/rankImg.png");
        JButton mainButton = new JButton(new ImageIcon("src/asset/btn/main_btn.png"));
        mainButton.setBounds(30, 20, 100, 100);
        mainButton.setBorderPainted(false);
        mainButton.setContentAreaFilled(false);
        add(mainButton);
        String Title[] = new String[5];
        SQLiteManager b = new SQLiteManager("", "", "");
        Vector<String> userRankingTable = new Vector<>();
        userRankingTable = b.getrank();
        for (int i = 0; i < 5; i++) {
            System.out.println(userRankingTable.get(i));
            Title[i] = userRankingTable.get(i);
        }
        int j = 0;
        JLabel title[] = new JLabel[5];
        for (int i = 1; i < 5; i++) {
            title[i] = new JLabel(Title[i]);
            title[i].setFont(casinoFont(40));
            title[i].setForeground(Color.WHITE);
        }
        Color a = new Color(70, 255, 154);
        title[0] = new JLabel(Title[0]);
        title[0].setFont(casinoFont(50));
        title[0].setForeground(a);

        title[0].setBounds(480, 198, 1000, 100);
        title[1].setBounds(350, 308, 1000, 100);
        title[2].setBounds(750, 308, 1000, 100);
        title[3].setBounds(350, 426, 1000, 100);
        title[4].setBounds(750, 426, 1000, 100);

        for (int i = 0; i < 5; i++)
            add(title[i]);

//       userRankingTable.add();


        //for(int i = 0; i < 5; i++)
        //    add(title[i]);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen(id);
                setVisible(false); // 창 안보이게 하기 
            }
        });
        repaintGUI();
    }

}
