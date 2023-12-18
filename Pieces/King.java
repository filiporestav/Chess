package Pieces;

import java.util.ArrayList;

import GUI.ChessColor;
import Game.Board;
import Game.Pair;

public class King extends Piece {

    public King(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
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
