package bridge.domain;

public enum PlayResult {
    MATCH("O"),
    MISS("X"),
    NONE(" ");

    private final String mark;

    PlayResult(final String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
