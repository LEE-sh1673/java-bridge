package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Tile> path;

    private Bridge destination;

    private int position;

    public Player() {
        this.path = new ArrayList<>();
        this.position = 0;
    }

    public PlayResult moveTo(final String direction) {
        Tile playerMoved = new Tile(direction, position + 1);
        position++;
        path.add(playerMoved);
        return cross(playerMoved);
    }

    private PlayResult cross(final Tile playerMoved) {
        return new PlayResult(playerMoved.getDirection(), destination.contains(playerMoved));
    }

    public void reset() {
        path.clear();
        position = 0;
    }

    public boolean isArrived() {
        return destination.matchSize(path.size());
    }

    public void setDestination(final Bridge bridge) {
        destination = bridge;
    }
}