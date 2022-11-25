package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Tile> path;

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

    public GameResult cross(final Bridge bridge) {
        Tile tile = getPosition();
        return new GameResult(tile.getDirection(), bridge.contains(tile));
    }

    public void retry() {
        path.clear();
        position = 0;
    }

    public boolean isLastRound() {
        return position == path.size();
    }
}