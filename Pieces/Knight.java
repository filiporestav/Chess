package Pieces;

import java.util.ArrayList;

import GUI.ChessColor;
import Game.Board;
import Game.Pair;

public class Knight extends Piece {

    public Knight(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> pairList = new ArrayList<>();
        
        int[][] knightMoves = {
            {-2, -1,}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newY = this.row + move[0]; // Representing the y coordinate
            int newX = this.col + move[1]; // Representing the x coordinare

            if (board.isValidPosition(newY, newX)) {
                if (board.getPieceAt(newY, newX) == null) { // If empty square
                    pairList.add(new Pair<>(newY, newX));
                }
                else if (board.getPieceAt(newY, newX).getColor() != this.color) { // Capture case
                    pairList.add(new Pair<>(newY, newX));
                }
            }
        }
        this.availableMoves = pairList;
        return pairList;
    }
    
}
