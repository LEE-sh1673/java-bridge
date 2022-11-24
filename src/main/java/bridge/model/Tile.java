package bridge.model;

public class Tile {

    private final Direction direction;

    private final int position;

    public Tile(final String direction, final int position) {
        this.direction = Direction.of(direction);
        this.position = position;
    }

    public CompareResult compareTo(final Tile other) {
        return CompareResult.MISS;
    }
}
