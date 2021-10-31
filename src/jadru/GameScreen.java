package jadru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameScreen extends JFrame {
    private MyPanel panel = new MyPanel();
    public GameScreen(String id){
        super("게임하기"); //타이틀

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        JButton mainButton = new JButton("게임1하기");
        add(mainButton);

        setSize(1080, 720);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game_1();
                setVisible(false); // 창 안보이게 하기
            }
        });
    }
    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("");
        private Image img = icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0,0,getWidth(),getHeight(),this);
        }
    }
}



