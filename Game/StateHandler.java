package NewChess.Game;

/*
 * A simple little class which handles the switching between the
 * selecting state and the deployment state in the chess game.
 */
public class StateHandler {

    private GameState gamestate;

    StateHandler() {
        this.gamestate = GameState.SELECT;
    }

    public GameState getState() {
        return this.gamestate;
    }

    public void changeState() {
        if (this.gamestate.equals(GameState.SELECT)) this.gamestate = GameState.DEPLOY;
        else if (this.gamestate.equals(GameState.DEPLOY)) this.gamestate = GameState.SELECT;
    }

    public void endGame() {
        this.gamestate = GameState.FINISH;
    }

}
