package GUI;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import Pieces.Piece;

public class ChessImage {

    Icon image;
    
    ChessImage (Piece piece) {
        createImage(piece);
    }

    private void createImage(Piece piece) {
        String piece_color;
        if (piece.getColor().equals(ChessColor.BLACK)) piece_color = "black";
        else piece_color = "white";

        String image_path = String.format("../images/%s_%s.png", piece_color, piece.getType().toString().toLowerCase());
        try {
            this.image = new ImageIcon(piece.getClass().getResource(image_path));
        } catch (Exception e) {
            System.out.println("Could not find image from the file path.");
        }
    

    }

    public Icon getImage() {
        return this.image;
    }
}
