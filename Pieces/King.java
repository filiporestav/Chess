package Pieces;

import java.util.ArrayList;

import GUI.ChessColor;
import Board;
import Pair;

public class King extends Piece {

    public King(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public boolean move(int targetRow, int targetCol) {
        Piece targetPiece = board.getPieceAt(targetRow, targetCol);

        if (targetPiece != null && targetPiece.getColor() == this.color) {
            return false; // If same color as our king
        }

        if (this.availableMoves.contains(new Pair<>(targetRow, targetCol))) {
            board.removePiece(this.row, this.col);
            updateCoordinates(targetRow, targetCol);
            return true;
        }
        else return false;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> pairList = new ArrayList<>();

        // Check all 8 possible directions for the King
        int[] directions = {-1, 0, 1};
        for (int rowDirection : directions) {
            for (int colDirection : directions) {
                // Skip the case where both directions are 0 (no movement)
                if (rowDirection == 0 && colDirection == 0) {
                    continue;
                }

                int newRow = this.row + rowDirection;
                int newCol = this.col + colDirection;

                if (board.isValidPosition(newRow, newCol)) {
                    if (board.getPieceAt(newRow, newCol) != null) {
                        if (board.getPieceAt(newRow, newCol).getColor()!=this.color) {
                        pairList.add(new Pair<>(newRow, newCol));
                        }
                    }
                    else {pairList.add(new Pair<>(newRow, newCol));}
                }
            }
        }
        this.availableMoves = pairList;
        return pairList;
    }
    
}
