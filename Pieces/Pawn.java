package Pieces;

import GUI.ChessColor;
import Game.*;
import java.util.*;

public class Pawn extends Piece {

    public Pawn(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> pairList = new ArrayList<>();
        int direction = (color == ChessColor.WHITE) ? -1 : 1;

        // Check one square forward
        int targetRow = this.row + direction;
        if (board.isValidPosition(targetRow, this.col) && board.getPieceAt(targetRow, this.col)==null) {
            pairList.add(new Pair<>(targetRow, this.col));

            // Check two squares forward for the initial move
            targetRow = this.row + 2 * direction;
            if (this.row == (color == ChessColor.WHITE ? 6 : 1) && board.isValidPosition(targetRow, this.col) && board.getPieceAt(targetRow, this.col)==null) {
                pairList.add(new Pair<>(targetRow, this.col));
            }
        }

        // Check diagonal captures
        checkCapture(pairList, this.row + direction, this.col - 1);
        checkCapture(pairList, this.row + direction, this.col + 1);

        this.availableMoves = pairList;
        return pairList;
    }

    private void checkCapture(ArrayList<Pair<Integer, Integer>> pairList, int targetRow, int targetCol) {
        if (board.isValidPosition(targetRow, targetCol) && board.getPieceAt(targetRow, targetCol) != null 
        && board.getPieceAt(targetRow, targetCol).getColor() != this.color) {
            pairList.add(new Pair<>(targetRow, targetCol));
        }
    }

    public boolean promotion() {
        if (this.row == 0 && this.color.equals(ChessColor.WHITE)) {
            return true;
        }
        else if (this.row == 7 && this.color.equals(ChessColor.BLACK)) {
            return true;
        }
        return false;
    }
}