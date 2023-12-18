package NewChess.GUI;

import java.awt.Color;
import NewChess.Pieces.Piece;

import javax.swing.*;

public class ChessSquare extends JButton {

    int row;
    int col;
    Piece piece; // The piece on this square
    Icon image;
    GridColor gridcolor;
    
    ChessSquare(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.piece = null;
        setColor();
    }

    /*
     * Sets a piece and icon on this square
     */
    protected void setPiece(Piece piece) {
        this.piece = piece;
        setIcon();
    }

    /*
     * Sets an image as an icon for this square.
     */
    private void setIcon() {
        if (piece != null) {
            ChessImage img = new ChessImage(piece);
            this.setIcon(img.getImage());
        }
        else this.setIcon(null);
    } 

    /*
     * Sets the color of a square, or resets it if it has changed.
     */
    public void setColor() {
        if ((this.row%2==0 && this.col%2==0) || this.row%2!=0 && this.col%2!=0) gridcolor = GridColor.LIGHT;
        else gridcolor = GridColor.GREEN;
        this.setBackground(gridcolor.getCustomColor());
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    /*
     * Colors the square with a highlighting color.
     */
    protected void setSelectColor() {
        gridcolor = GridColor.ORANGE;
        this.setBackground(gridcolor.getCustomColor());
        this.setOpaque(true);
        this.setBorderPainted(false);
    }

    /*
     * Returns the Piece object of the square.
     * If the square does not have any piece on it,
     * it returns null.
     */
    public Piece getPiece() {
        return this.piece;
    }

    /*
     * Removes the piece from this square by setting the
     * piece to null and the icon to null.
     */
    protected void removePiece() {
        setPiece(null);
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
