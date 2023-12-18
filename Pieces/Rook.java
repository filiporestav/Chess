package Pieces;

import java.util.ArrayList;

import GUI.ChessColor;
import Game.Board;
import Game.Pair;

public class Rook extends Piece {

    public Rook(int row, int col, PieceType type, ChessColor color, Board board) {
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
