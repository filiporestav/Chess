package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerLabel extends JLabel implements ActionListener {

    private Timer timer;
    private long startTime;
    
    TimerLabel() {
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setHorizontalAlignment(CENTER);
        // Set a larger font size
        Font labelFont = this.getFont();
        this.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        startTime = System.currentTimeMillis(); // Set the time right now, as starting point to compare with
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimerLabel();
    }

    public void updateTimerLabel() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        setText("Playing time: " + dateFormat.format(new Date(elapsedTime)));
    }
}
