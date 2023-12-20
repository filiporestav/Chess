package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class MessageLabel extends JLabel {

    MessageLabel() {
        this.setHorizontalAlignment(JLabel.CENTER); // Center it horizontally
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Set a larger font size
        Font labelFont = this.getFont();
        this.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
    }

    public void updateText(String text) {
        this.setText(text);
    }
}
