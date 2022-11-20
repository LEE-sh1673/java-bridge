package bridge.domain;

public enum ErrorMessage {
    INVALID_DIRECTION("이동할 칸은 U 또는 D만 입력 가능합니다."),
    INVALID_BRIDGE_SIZE("다리 길이는 3이상 20이하의 숫자를 입력해야 합니다."),
    INVALID_GAME_COMMEND("다리 길이는 3이상 20이하의 숫자를 입력해야 합니다.");

    private final String message;

    private static final String MESSAGE_HEAD = "[ERROR]";

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.join(" ", MESSAGE_HEAD, message);
    }
}
