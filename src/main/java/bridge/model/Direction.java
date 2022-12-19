package bridge.model;

import static bridge.ErrorMessage.INVALID_DIRECTION_TYPE;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum Direction {
    UP("U", 1),
    DOWN("D", 0);

    private final String shape;

    private final int number;

    Direction(final String shape, final int number) {
        this.shape = shape;
        this.number = number;
    }

    public static Direction of(final String shape) {
        return findDirection(direction -> direction.matchShape(shape))
            .orElseThrow(()
                -> new IllegalArgumentException(INVALID_DIRECTION_TYPE)
            );
    }

    private static Optional<Direction> findDirection(final Predicate<Direction> condition) {
        return Arrays.stream(values())
            .filter(condition)
            .findAny();
    }

    public static String getShape(final int number) {
        return findDirection(direction -> direction.matchNumber(number))
            .map(direction -> direction.shape)
            .orElse(DOWN.shape);
    }

    public static Direction reverse(final Direction direction) {
        if (direction == Direction.UP) {
            return Direction.DOWN;
        }
        return Direction.UP;
    }

    private boolean matchNumber(final int number) {
        return this.number == number;
    }

    private boolean matchShape(final String shape) {
        return this.shape.equals(shape);
    }
}
