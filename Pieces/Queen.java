package Pieces;

import java.util.ArrayList;

import GUI.ChessColor;
import Game.Board;
import Game.Pair;

public class Queen extends Piece {

    public Queen(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        ArrayList<Pair<Integer, Integer>> pairList = new ArrayList<>(); // Create a new pairList each time, as position changes.
        
        int[][] queenDirections = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1}, {0, 1},
        {1, -1}, {1, 0}, {1, 1}
        };

        for (int direction[] : queenDirections) {
            addMovesInDirection(pairList, direction[0], direction[1]);
        }
        this.availableMoves = pairList;
        return pairList;
    }

    /*
     * Helper method to add available moves for the bishop
     */
    private void addMovesInDirection(ArrayList<Pair<Integer, Integer>> pairList, int rowDirection, int colDirection) {
        int currRow = this.row + rowDirection;
        int currCol = this.col + colDirection;

        while (board.isValidPosition(currRow, currCol)) {
            Piece pieceAtCurrentPosition = board.getPieceAt(currRow, currCol);

            if (pieceAtCurrentPosition == null) {
                // If empty square, add it to available moves
                pairList.add(new Pair<>(currRow, currCol));
            }
            else if (pieceAtCurrentPosition.getColor() != this.color) {
                // Capture opponents piece
                pairList.add(new Pair<>(currRow, currCol));
                break; // Do not continue to add in this direction
            }
            else { // Own piece blocking, do not add in this direction
                break; 
            }
            
            currRow += rowDirection;
            currCol += colDirection;
        }
    }
    
}
