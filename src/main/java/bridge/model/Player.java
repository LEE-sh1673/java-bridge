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
        return path.get(path.size()-1);
    }
}
