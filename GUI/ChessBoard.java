package NewChess.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import NewChess.Game.*;
import java.util.*;
import javax.swing.*;
import NewChess.Pieces.*;

public class ChessBoard extends JPanel implements ActionListener {

    private int size = 8;
    private ChessSquare[][] GUIboard = new ChessSquare[size][size];
    private Board pieceBoard;
    private JPanel chessBoardPanel;
    private ChessSquare selectedSquare;
    private ArrayList<Pair<Integer, Integer>> availableMoves; // Stores available moves for a selected piece
    
    ChessBoard(Board pieceBoard) {
        super(new BorderLayout());
        chessBoardPanel = new JPanel(new GridLayout(size, size));
        this.pieceBoard = pieceBoard; // The "model" piece board
        this.selectedSquare = null;
        setupSquares();
        setupPieces();
        this.add(chessBoardPanel, BorderLayout.CENTER);
    }

    /*
     * Method which sets up the squares with different colors on the chess board.
     * Takes the chess board as a JPanel as input.
     */
    private void setupSquares() {
        for (int row=0; row<size; row++) {
            for (int col=0; col<size; col++) {
                ChessSquare square = new ChessSquare(row, col);
                square.addActionListener(this);
                GUIboard[row][col] = square;
                chessBoardPanel.add(square);
            }
        }
    }

    /*
     * Sets up the graphics on the pieces for each ChessSquare.
     * The position of each piece is gathered from the pieceBoard
     * from the Board class.
     */
    private void setupPieces() {
        // Setup the graphical board
        for (int row=0; row<size; row++) {
            for (int col=0; col<size; col++) {
                Piece piece = pieceBoard.getPieceAt(row, col);
                if (piece != null) GUIboard[row][col].setPiece(piece);
            }
        }
    }

    /*
     * Method which highlights the available squares a
     * chosen piece can move to.
     */
    private void highlightChoices() {
        int row = this.selectedSquare.getRow();
        int col = this.selectedSquare.getCol();
        availableMoves = pieceBoard.getAvailableMoves(row, col);
        for (Pair<Integer, Integer> indices : availableMoves) {
            GUIboard[indices.getRow()][indices.getCol()].setSelectColor();
        }
    }

    /*
     * Method which removes the highlight of available
     * squares a chosen piece can move to.
     */
    private void unhighlightChoices() {
        for (Pair<Integer, Integer> indices : availableMoves) {
            GUIboard[indices.getRow()][indices.getCol()].setColor();
        }
    }

    /*
     * Method which is executed when pressing on the JPanel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ChessSquare btn = (ChessSquare) e.getSource();
        int row = btn.getRow();
        int col = btn.getCol();

        if (pieceBoard.movePieceFrom(row, col) && btn.getPiece() != null) {
            if (this.selectedSquare != null) {
                selectedSquare.setColor(); 
                unhighlightChoices();
            }
            this.selectedSquare = btn;
            btn.setBackground(Color.LIGHT_GRAY);
            highlightChoices();
        }

        else if (pieceBoard.movePieceTo(row, col)) {
            unhighlightChoices();
            this.selectedSquare.removePiece();
            btn.setPiece(pieceBoard.getSelectedPiece());
            this.selectedSquare.setColor(); // Resets the color
            this.selectedSquare = null;
        }
    }

}
