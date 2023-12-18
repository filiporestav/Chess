package NewChess.Pieces;

import java.util.ArrayList;

import NewChess.GUI.ChessColor;
import NewChess.Game.Board;
import NewChess.Game.Pair;

public class Queen extends Piece {

    public Queen(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public boolean move(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getAvailableMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
    }
    
}
