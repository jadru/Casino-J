package support;

import com.sun.tools.javac.Main;
import panel.GlobalPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.awt.Font.BOLD;

public class GlobalGUI extends JFrame {
    static JPanel panel;
    private final static int WINDOW_HEIGHT = 720;
    private final static int WINDOW_WIDTH = 1280;
    private final static String FONT_PATH = "src/asset/ui/neodgm.ttf";
    private final static String FONT_NAME = "Neo둥근모"; // 변경 금지!!
    protected GlobalGUI(String title, String BG_IMG_URL){
        super();
        setTitle(title);
        GlobalGUI.panel = new GlobalPanel(BG_IMG_URL);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2); //화면 중앙에 띄우기

        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    public static Font casinoFont(int size){
        return new Font(FONT_NAME, Font.BOLD, size);
    }
    public void repaintGUI(){
        this.repaint();
        this.revalidate();
        this.setVisible(true);
    }
}
