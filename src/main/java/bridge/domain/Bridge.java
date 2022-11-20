package bridge.domain;

import static bridge.domain.ErrorMessage.INVALID_BRIDGE_SIZE;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private static final int MAX_BRIDGE_SIZE = 20;

    private static final int MIN_BRIDGE_SIZE = 3;

    private final List<Direction> directions;

    public Bridge(final List<String> directions) {
        validateSize(directions);
        this.directions = directions.stream()
            .map(Direction::of)
            .collect(Collectors.toList());
    }

    private void validateSize(final List<String> directions) {
        if (isOutOfBound(directions)) {
            throw new IllegalArgumentException(
                INVALID_BRIDGE_SIZE.getMessage()
            );
        }
    }

    private boolean isOutOfBound(final List<String> directions) {
        int size = directions.size();
        return size > MAX_BRIDGE_SIZE || size < MIN_BRIDGE_SIZE;
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
}
