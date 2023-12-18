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
        
        // If white pawn, it can only move in the negative y-direction.
        if (color.equals(ChessColor.WHITE)) {
            if (this.row==6) {
                pairList.add(new Pair<Integer,Integer>(this.row-1, this.col));
                pairList.add(new Pair<Integer,Integer>(this.row-2, this.col));
            }
            if (board.getPieceAt(this.row-1, this.col-1)!=null) {
                pairList.add(new Pair<Integer,Integer>(this.row-1, this.col-1));
            }
            if (board.getPieceAt(this.row-1, this.col+1)!=null) {
                pairList.add(new Pair<Integer,Integer>(this.row-1, this.col+1));
            }
            if (board.getPieceAt(this.row-1, this.col) == null) {
                pairList.add(new Pair<Integer,Integer>(this.row-1, this.col));
            }
        }

        // If black pawn, it can only move in the positive y-direction.
        if (color.equals(ChessColor.BLACK)) {
            if (this.row==1) {
                pairList.add(new Pair<Integer,Integer>(this.row+1, this.col));
                pairList.add(new Pair<Integer,Integer>(this.row+2, this.col));
            }
            if (board.getPieceAt(this.row+1, this.col+1)!=null) {
                pairList.add(new Pair<Integer,Integer>(this.row+1, this.col+1));
            }
            if (board.getPieceAt(this.row+1, this.col-1)!=null) {
                pairList.add(new Pair<Integer,Integer>(this.row+1, this.col-1));
            }
            if (board.getPieceAt(this.row+1, this.col) == null) {
                pairList.add(new Pair<Integer,Integer>(this.row+1, this.col));
            }
        }
        this.availableMoves = pairList;
        return pairList;
    }
}