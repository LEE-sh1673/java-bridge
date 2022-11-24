package bridge.model;

import java.util.Objects;

public class Tile {

    private final Direction direction;

    private final int position;

    public Tile(final String direction, final int position) {
        this.direction = Direction.of(direction);
        this.position = position;
    }

    public CompareResult compareTo(final Tile other) {
        if (this.equals(other)) {
            return CompareResult.MATCH;
        }
        return CompareResult.MISS;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Tile other = (Tile) obj;
        return matchDirection(other)
            && matchPosition(other);
    }

    private boolean matchDirection(final Tile other) {
        return this.direction == other.direction;
    }

    private boolean matchPosition(final Tile other) {
        return this.position == other.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, position);
    }
}
