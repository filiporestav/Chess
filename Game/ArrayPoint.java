package NewChess.Game;

/*
 * Class which holds a point, represented by a row and column index.
 */
public class ArrayPoint<R,C> {
    
    private final R row;
    private final C col;

    public ArrayPoint(R row, C col) {
        assert row != null;
        assert col != null;

        this.row = row;
        this.col = col;
    }

    public R getRow() {
        return this.row;
    }

    public C getCol() {
        return this.col;
    }
}
