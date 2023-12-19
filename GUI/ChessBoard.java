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
    private MessageLabel messageLabel;

    ChessBoard(Board pieceBoard) {
        super(new BorderLayout());
        chessBoardPanel = new JPanel(new GridLayout(size, size));

        this.pieceBoard = pieceBoard; // The "model" piece board
        this.selectedSquare = null;
        setupSquares();

        // Create and add message label
        messageLabel = new MessageLabel();
        this.add(messageLabel, BorderLayout.PAGE_END);
        // Add the panel to the chess board
        this.add(chessBoardPanel, BorderLayout.CENTER);

        updateBoardGraphics();

    }

    /*
     * Method which sets up the squares with different colors on the chess board.
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
                // If only one move is available, perform the move automatically upon confirmation
                performAutomaticMove();
            }
        }

        else if (pieceBoard.movePieceTo(row, col)) {
            unhighlightChoices();
            updateBoardGraphics();
            this.selectedSquare.setColor(); // Resets the color
            this.selectedSquare = null;
        }

        // Update the message based on the game state
        messageLabel.updateText(pieceBoard.getMessage());
    }

    /*
     * Performs the automatic move if only one move is available for
     * the chosen piece.
     */
    public void performAutomaticMove() {
        // Store the original position
        int originalRow = pieceBoard.getSelectedPiece().getRow();
        int originalCol = pieceBoard.getSelectedPiece().getCol();

        Piece selectedPiece = pieceBoard.getSelectedPiece(); // Create a copy of the selected piece

        Pair<Integer, Integer> coordinates = selectedPiece.getAvailableMoves().get(0);
        int newRow = coordinates.getRow();
        int newCol = coordinates.getCol();

        Piece capturePiece = pieceBoard.getPieceAt(newRow, newCol); // Create a copy of the potential "capture" piece

        highlightChoices();

        // Execute automatic move
        pieceBoard.automaticMove();
        pieceBoard.removePiece(originalRow, originalCol);
        // Update the graphical board
        updateBoardGraphics();

        boolean confirmed = showConfirmationDialog((Component) SwingUtilities.getRoot(this));
        if (!confirmed) { // If not confirmed, move back to previous position
            pieceBoard.removePiece(newRow, newCol);
            pieceBoard.setPiece(originalRow, originalCol, selectedPiece);
            if (capturePiece != null) {
                pieceBoard.setPiece(newRow, newCol, capturePiece);
            }
        }
        else {
            pieceBoard.changeTurnAndState();
        }
        updateBoardGraphics();
        unhighlightChoices();
    }

    /*
     * Updates the graphics on the pieces for each ChessSquare.
     * The position of each piece is gathered from the pieceBoard
     * from the Board class.
     */
    private void updateBoardGraphics() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                GUIboard[row][col].removePiece();
                Piece piece = pieceBoard.getPieceAt(row, col);
                if (piece != null) GUIboard[row][col].setPiece(piece);
            }
        }
        messageLabel.updateText(pieceBoard.getMessage()); // Update text message
    }

    /*
     * Implements the UI logic for confirming automatic move.
     * Show confirmation box for confirmation.
     * Return true if confirmed, else false.
     */
    private boolean showConfirmationDialog(Component parentComponent) {
        int result = JOptionPane.showConfirmDialog(
            parentComponent,
            "Do you want to do the automatic move?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION
        );
        return result == JOptionPane.YES_OPTION;
    }

}
