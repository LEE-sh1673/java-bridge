package bridge.domain;

import java.util.Arrays;

public enum Direction {
    UP("U"),
    DOWN("D");

    private final String mark;

    Direction(final String mark) {
        this.mark = mark;
    }

    public static Direction of(final String mark) {
        return Arrays.stream(values())
            .filter(direction -> direction.matchMark(mark))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    private boolean matchMark(final String mark) {
        return this.mark.equals(mark);
    }
}
