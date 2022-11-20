package bridge.domain;

import static bridge.ErrorMessage.INVALID_GAME_COMMEND;

import java.util.Arrays;

public enum GameCommend {
    RESTART("R"),
    EXIT("Q");

    private final String commend;

    GameCommend(final String commend) {
        this.commend = commend;
    }

    public static GameCommend of(final String commend) {
        return Arrays.stream(values())
            .filter(gameCommend -> gameCommend.matchCommend(commend))
            .findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException(INVALID_GAME_COMMEND.getMessage())
            );
    }

    private boolean matchCommend(final String commend) {
        return this.commend.equals(commend);
    }
}
