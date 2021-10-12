package sbs;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RankScreen extends  JFrame{
	RankScreen(){
        super("랭킹"); //타이틀
        JPanel jPanel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
        JButton mainButton = new JButton("메인 화면으로");
        add(mainButton);

        setSize(500,500);

        add(jPanel);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen();
                setVisible(false); // 창 안보이게 하기 
            }
        });
    }

}
