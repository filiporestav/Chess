package NewChess.Pieces;

import java.util.ArrayList;

import NewChess.GUI.ChessColor;
import NewChess.Game.Board;
import NewChess.Game.Pair;

public class Knight extends Piece {

    public Knight(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public boolean move(int targetRow, int targetCol) {
        int y_difference = this.row-targetRow; // Different between current row and target row
        int x_difference = this.col-targetCol;
        boolean capture_try = board.getPieceAt(targetRow, targetCol)!=null; // Returns true if it is an attempt to capture
        
        return true;
    
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
    }
    
}
