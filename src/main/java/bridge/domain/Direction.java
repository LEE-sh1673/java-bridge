package bridge.domain;

import static bridge.ErrorMessage.INVALID_DIRECTION;

import java.util.Arrays;

public enum Direction {
    UP("U", 1),
    DOWN("D", 0);

    private final String mark;

    private final int number;

    Direction(final String mark, final int number) {
        this.mark = mark;
        this.number = number;
    }

    public static Direction of(final String mark) {
        return Arrays.stream(values())
            .filter(direction -> direction.matchMark(mark))
            .findAny()
            .orElseThrow(() ->
                new IllegalArgumentException(INVALID_DIRECTION.getMessage())
            );
    }

    public static Direction of(final int number) {
        return Arrays.stream(values())
            .filter(direction -> direction.matchNumber(number))
            .findAny()
            .orElseThrow(() ->
                new IllegalArgumentException(INVALID_DIRECTION.getMessage())
            );
    }

    private boolean matchNumber(final int number) {
        return this.number == number;
    }

    private boolean matchMark(final String mark) {
        return this.mark.equals(mark);
    }

    public String getMark() {
        return mark;
    }
}
