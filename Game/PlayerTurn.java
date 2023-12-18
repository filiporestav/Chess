package Game;

import GUI.ChessColor;

/*
 * A simple class which helps to keep track of the player turn between two players
 * in the chess game.
 */
public class PlayerTurn {

    private ChessColor playerTurn;
    
    PlayerTurn() {
        playerTurn = ChessColor.WHITE;
    }

    public void changePlayerTurn() {
        if (playerTurn == ChessColor.WHITE) playerTurn = ChessColor.BLACK;
        else playerTurn = ChessColor.WHITE;
    }

    public ChessColor getPlayerTurn() {
        return this.playerTurn;
    }
}
