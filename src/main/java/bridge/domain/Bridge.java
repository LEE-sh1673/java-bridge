package bridge.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private final List<Direction> directions;

    public Bridge(final List<String> directions) {
        this.directions = directions.stream()
            .map(Direction::of)
            .collect(Collectors.toList());
    }

    public PlayStatus cross(final Direction direction, final int round) {
        if (matchDirection(direction, round)) {
            return new PlayStatus(direction, PlayResult.MATCH);
        }
        return new PlayStatus(direction, PlayResult.MISS);
    }

    private boolean matchDirection(final Direction direction, final int round) {
        return directions.get(round - 1) == direction;
    }

    public int getSize() {
        return directions.size();
    }
}
