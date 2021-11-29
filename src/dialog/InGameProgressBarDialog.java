package dialog;

import screen.Game_1;

import javax.swing.*;
import java.awt.*;

public class InGameProgressBarDialog extends JDialog {
    JProgressBar progress;
    public InGameProgressBarDialog(Game_1 frame, String title) {
        super(frame, title, true);
        setLayout(null);
        setPreferredSize(new Dimension(400, 100));
        progress = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        progress.setSize(400, 100);
        progress.setValue(0);
        add(progress);
    }

    public void addValue(int value){
        progress.setValue(progress.getValue() + value);
        repaint(); revalidate();
    }
}