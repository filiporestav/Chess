package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Game.*;
import java.util.*;
import javax.swing.*;
import Pieces.*;

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
        availableMoves = pieceBoard.getPieceAt(row, col).getAvailableMoves();
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


        if (pieceBoard.movePieceFrom(row, col)) {
            if (this.selectedSquare != null) {
                selectedSquare.setColor(); 
                unhighlightChoices();
            }
            this.selectedSquare = btn;
            btn.setBackground(Color.LIGHT_GRAY);
            highlightChoices();

            // Check if automatic move is possible
            if (pieceBoard.getSelectedPiece().getAvailableMoves().size() == 1) {
                // If only one move is available, confirm automatically
                pieceBoard.automaticMove(row, col);
                updateBoardGraphics();
            }
        }

        else if (pieceBoard.movePieceTo(row, col)) {
            unhighlightChoices();
            this.selectedSquare.removePiece();
            btn.setPiece(pieceBoard.getSelectedPiece());
            this.selectedSquare.setColor(); // Resets the color
            this.selectedSquare = null;
        }
    }

    /*
     * Performs the automatic move if only one move is available for
     * the chosen piece.
     */
    public void performAutomaticMove() {
        // Store the original position
        int originalRow = pieceBoard.getSelectedPiece().getRow();
        int originalCol = pieceBoard.getSelectedPiece().getCol();

        // Execute automatic move
        pieceBoard.automaticMove(originalRow, originalCol);
        // Update the graphical board
        updateBoardGraphics();

        boolean confirmed = confirmAutomaticMove(this);
        if (!confirmed) { // If not confirmed, move back to previous position
            pieceBoard.getSelectedPiece().updateCoordinates(originalRow, originalCol);
            updateBoardGraphics();
        }
        else {
            pieceBoard.removePiece(originalRow, originalCol);
        }
    }

    /*
     * Method which updates the board graphics.
     */
    private void updateBoardGraphics() {
        // Update the graphical representation of the chess board
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                GUIboard[row][col].removePiece();
                Piece piece = pieceBoard.getPieceAt(row, col);
                if (piece != null) GUIboard[row][col].setPiece(piece);
            }
        }
    }

    /*
     * Implements the UI logic for confirming automatic move.
     * Show confirmation doalig or button for confirmation.
     * Return true if confirmed, else false.
     */
    private boolean confirmAutomaticMove(Component parentComponent) {
        return false;
    }

}
