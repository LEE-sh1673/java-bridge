package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class Bridge {

    private final List<Tile> tiles;

    public Bridge(final List<String> direction) {
        this.tiles = getTiles(direction);
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
}
