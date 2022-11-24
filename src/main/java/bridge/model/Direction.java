package bridge.model;

import java.util.Arrays;

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
        return Arrays.stream(values())
            .filter(direction -> direction.matchShape(shape))
            .findAny()
            .orElseThrow(()
                -> new IllegalArgumentException("[ERROR] 이동할 칸은 U 또는 D가 입력되어야 합니다.")
            );
    }

    private boolean matchShape(final String shape) {
        return this.shape.equals(shape);
    }
}
