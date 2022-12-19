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

    public PlayResult move(final String direction) {
        Tile playerMoved = new Tile(direction, position + 1);
        position++;
        path.add(playerMoved);
        return cross(playerMoved);
    }

    private PlayResult cross(final Tile playerMoved) {
        return destination.contains(playerMoved);
    }

    public void reset() {
        path.clear();
        position = 0;
    }

    public boolean isArrived() {
        return matchSize() && isAllPass();
    }

    private boolean matchSize() {
        return destination.matchSize(path.size());
    }

    private boolean isAllPass() {
        return path.stream()
            .map(this::cross)
            .allMatch(PlayResult::isPass);
    }

    public boolean isFail() {
        return path.stream()
            .anyMatch(tile -> cross(tile).isFail());
    }

    public void setDestination(final List<String> bridge) {
        destination = new Bridge(bridge);
    }
}