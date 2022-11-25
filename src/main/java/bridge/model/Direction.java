package bridge.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum Direction {
    UP("U", 0),
    DOWN("D", 1);

    private final String shape;

    private final int number;

    Direction(final String shape, final int number) {
        this.shape = shape;
        this.number = number;
    }

    public static Direction of(final String shape) {
        return findDirection(direction -> direction.matchShape(shape))
            .orElseThrow(()
                -> new IllegalArgumentException("[ERROR] 이동할 칸은 U 또는 D가 입력되어야 합니다.")
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

    private boolean matchNumber(final int number) {
        return this.number == number;
    }

    private boolean matchShape(final String shape) {
        return this.shape.equals(shape);
    }
}
