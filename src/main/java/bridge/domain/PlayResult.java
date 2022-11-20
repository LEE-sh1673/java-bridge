package bridge.domain;

public enum PlayResult {
    MATCH("O"),
    MISS("X"),
    NONE(" ");

    private final String sign;

    PlayResult(final String sign) {
        this.sign = sign;
    }
}
