package GUI;

import java.awt.*;
import javax.swing.*;
import Game.Board;

public class ChessWindow extends JFrame {
    
    ChessWindow() {
        this.setTitle("Chess");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null); // Center

        Board board = new Board(); // The 'logical' board (the model)
        ChessBoard GUIboard = new ChessBoard(board); // The 'graphical' board (the GUI)
        this.add(GUIboard, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
