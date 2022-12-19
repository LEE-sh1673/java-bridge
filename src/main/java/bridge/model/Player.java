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

    public void setDestination(final List<String> bridge) {
        destination = new Bridge(bridge);
    }

    public PlayResult move(final String direction) {
        Tile playerMoved = new Tile(direction, position + 1);
        updatePath(playerMoved);
        return cross(playerMoved);
    }

    private void updatePath(final Tile playerMoved) {
        path.add(playerMoved);
        position++;
    }

    private PlayResult cross(final Tile playerMoved) {
        return destination.contains(playerMoved);
    }

    public void reset() {
        position = 0;
        path.clear();
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
        return position != 0 && cross(path.get(position - 1)).isFail();
    }
}