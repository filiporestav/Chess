package Pieces;

import java.util.*;
import GUI.*;
import Game.*;

public abstract class Piece {

    int row;
    int col;
    String name;
    ChessColor color;
    PieceType type;
    Board board;
    ArrayList<Pair<Integer, Integer>> availableMoves;

    Piece(int row, int col, PieceType type, ChessColor color, Board board) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.color = color;
        this.board = board; // The board object
    }

    /*
     * Moves the piece to if the coordinates are in the available moves list.
     */
    public boolean move(int targetRow, int targetCol) {
        if (this.availableMoves.contains(new Pair<>(targetRow, targetCol))) {
            board.removePiece(this.row, this.col);
            updateCoordinates(targetRow, targetCol);
            return true;
        }
        else return false;
    }

    /*
     * Returns the row index of the piece.
     */
    public int getRow() {
        return this.row;
    }

    /*
     * Returns the column index of the piece.
     */
    public int getCol() {
        return this.col;
    }

    /*
     * Returns the type of the piece
     */
    public PieceType getType() {
        return this.type;
    }

    /*
     * Returns the color of this piece
     */
    public ChessColor getColor() {
        return this.color;
    }

    /*
     * Updates the coordinates (row and column) for this piece.
     */
    public void updateCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /*
     * Returns a list with the available moves for this piece.
     */
    public abstract ArrayList<Pair<Integer, Integer>> getAvailableMoves();

    /*
     * Method which checks if the king is in check due to this piece.
     */
    public boolean check() {
        for (Pair<Integer, Integer> coordinates : getAvailableMoves()) {
            Piece piece = board.getPieceAt(coordinates.getRow(), coordinates.getCol());
            if (piece!= null) {
                if (piece.getType()==PieceType.KING && piece.getColor()!=this.color) {
                    this.board.setMessage("Check! Please move the king.");
                    return true;
                }
            }
        }
        return false;
    }
}
