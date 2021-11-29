package dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameResultDialog extends JDialog {

    public InGameResultDialog(JFrame frame, String title, boolean iswinorlose) {
        super(frame, title, true);
        setLayout(null);
        setSize(400, 200);
    }
}