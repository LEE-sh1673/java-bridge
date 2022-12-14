package bridge.model;

import static bridge.ErrorMessage.INVALID_BRIDGE_SIZE;

import java.util.ArrayList;
import java.util.List;

public class Bridge {

    private static final int MAX_BRIDGE_SIZE = 20;

    private static final int MIN_BRIDGE_SIZE = 3;

    private final List<Tile> tiles;

    public Bridge(final List<String> direction) {
        validateSize(direction);
        this.tiles = getTiles(direction);
    }

    private void validateSize(final List<String> direction) {
        if (isOutOfBound(direction.size())) {
            throw new IllegalArgumentException(INVALID_BRIDGE_SIZE);
        }
    }

    private boolean isOutOfBound(final int size) {
        return size > MAX_BRIDGE_SIZE || size < MIN_BRIDGE_SIZE;
    }

    private List<Tile> getTiles(final List<String> direction) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < direction.size(); i++) {
            tiles.add(new Tile(direction.get(i), i+1));
        }
        return tiles;
    }

    public CompareResult contains(final Tile tile) {
        if (tiles.contains(tile)) {
            return CompareResult.MATCH;
        }
        return CompareResult.MISS;
    }

    public boolean matchSize(final int size) {
        return tiles.size() == size;
    }
}
