package NewChess.Pieces;

import NewChess.GUI.ChessColor;
import NewChess.Game.*;
import java.util.*;

public class Pawn extends Piece {

    public Pawn(int row, int col, PieceType type, ChessColor color, Board board) {
        super(row, col, type, color, board);
    }

    @Override
    public boolean move(int targetRow, int targetCol) {

        int y_difference = this.row-targetRow; // Different between current row and target row
        int x_difference = this.col-targetCol;
        boolean capture_try = board.getPieceAt(targetRow, targetCol)!=null; // Returns true if it is an attempt to capture

        // If white pawn, it can only move in the positive y-direction.
        if (this.color.equals(ChessColor.WHITE)) {
            if (this.row==6) {
                if ((y_difference==2 || y_difference==1) && x_difference==0 && !capture_try) {
                    board.removePiece(this.row, this.col);
                    updateCoordinates(targetRow, targetCol); 
                    return true; }
                else if (y_difference==1 && Math.abs(x_difference)==1 && capture_try) { board.removePiece(this.row, this.col); updateCoordinates(targetRow, targetCol); return true; }
            }
            else if (y_difference==1) {
                if (board.getPieceAt(targetRow, targetCol)!=null && board.getPieceAt(targetRow, targetCol).getColor().equals(ChessColor.BLACK) && Math.abs(x_difference)==1) {// Capture case 
                    board.removePiece(this.row, this.col);
                    updateCoordinates(targetRow, targetCol); return true; }

                else if (board.getPieceAt(targetRow, targetCol)==null && x_difference==0) { 
                    board.removePiece(this.row, this.col);
                    updateCoordinates(targetRow, targetCol); 
                    return true; } // No capture case
                }
            }
        // If black pawn, it can only move in the negative y-direction.
        else if (this.color.equals(ChessColor.BLACK)) {
            if (this.row==1) {
                if ((y_difference==-2 || y_difference==-1) && x_difference==0 && board.getPieceAt(targetRow, targetCol)==null) {
                    board.removePiece(this.row, this.col);
                    updateCoordinates(targetRow, targetCol); 
                return true; }
                else if (y_difference==-1 && Math.abs(x_difference)==1 && capture_try) { 
                    board.removePiece(this.row, this.col);
                    updateCoordinates(targetRow, targetCol); return true; }
            }
            else if (y_difference==-1) {
                if (board.getPieceAt(targetRow, targetCol)!=null && board.getPieceAt(targetRow, targetCol).getColor().equals(ChessColor.WHITE) && Math.abs(x_difference)==1) {// Capture case 
                    board.removePiece(this.row, this.col);
                    updateCoordinates(targetRow, targetCol); return true; }

                else if (board.getPieceAt(targetRow, targetCol)==null && x_difference==0) { board.removePiece(this.row, this.col); updateCoordinates(targetRow, targetCol); return true; } // No capture case
                }
            }
        return false;
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
        return pairList;
    }
}