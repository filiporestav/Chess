package Game;

import java.util.Objects;

public class Pair<R, C> {
    private R row;
    private C col;

    public Pair(R row, C col) {
        this.row = row;
        this.col = col;
    }

    public R getRow() {
        return this.row;
    }

    public C getCol() {
        return this.col;
    }

    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If the same instance (point to the same object in memory)
        if ((obj == null) || getClass() != obj.getClass()) return false; // If null or not the same class as current object
        Pair<?, ?> pair = (Pair<?, ?>) obj; // Cast the object to a Pair instance (flexibility to also handle cases if row and column are unknown <?>)
        return Objects.equals(row, pair.row) && Objects.equals(col, pair.col);
    }
}
