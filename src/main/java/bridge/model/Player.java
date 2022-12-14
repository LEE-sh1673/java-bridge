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

    public void moveTo(final String direction) {
        path.add(new Tile(direction, position+1));
        position++;
    }

    public Tile getPosition() {
        return path.get(position - 1);
    }

    public GameResult cross() {
        Tile tile = getPosition();
        return new GameResult(tile.getDirection(), destination.contains(tile));
    }

    public void retry() {
        path.clear();
        position = 0;
    }

    public boolean isArrived() {
        return isAllMatch() && destination.matchSize(path.size());
    }

    private boolean isAllMatch() {
        return path.stream().allMatch(destination::matchPosition);
    }

    public boolean isFailed() {
        return !destination.matchPosition(getPosition());
    }

    public void setDestination(final Bridge bridge) {
        destination = bridge;
    }
}