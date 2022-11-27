package bridge;

import static bridge.ErrorMessage.INVALID_GAME_COMMAND;

import java.util.Arrays;

public enum GameCommand {

    RESTART("R"),
    EXIT("Q");

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand of(final String command) {
        return Arrays.stream(values())
            .filter(gameCommand -> gameCommand.matchCommand(command))
            .findAny()
            .orElseThrow(() -> {
               throw new IllegalArgumentException(INVALID_GAME_COMMAND);
            });
    }

    private boolean matchCommand(final String command) {
        return this.command.equals(command);
    }

    public boolean isRestart() {
        return this == RESTART;
    }
}
