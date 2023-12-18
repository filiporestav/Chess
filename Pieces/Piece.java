package NewChess.Pieces;

import java.awt.*;
import java.util.*;
import NewChess.GUI.*;
import NewChess.Game.*;

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
     * Moves the piece to the given coordinates
     */
    public abstract boolean move(int targetRow, int targetCol);

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
}
