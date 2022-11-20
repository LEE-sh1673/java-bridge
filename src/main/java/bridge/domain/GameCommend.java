package bridge.domain;

import java.util.Arrays;

public enum GameCommend {
    RESTART("R"),
    EXIT("Q");

    private final String commend;

    GameCommend(final String commend) {
        this.commend = commend;
    }

    public static boolean contains(final String commend) {
        return Arrays.stream(values())
            .anyMatch(gameCommend -> gameCommend.matchCommend(commend));
    }

    private boolean matchCommend(final String commend) {
        return this.commend.equals(commend);
    }
}
