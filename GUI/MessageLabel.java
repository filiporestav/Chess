package GUI;

import javax.swing.*;
import java.awt.*;

public class MessageLabel extends JLabel {

    MessageLabel() {
        super();
        this.setHorizontalAlignment(JLabel.CENTER); // Center it horizontally
        // Set a larger font size
        Font labelFont = this.getFont();
        this.setFont(new Font(labelFont.getName(), Font.PLAIN, 20)); // You can adjust the size (18 is just an example)
    }

    public void updateText(String text) {
        this.setText(text);
    }
}
