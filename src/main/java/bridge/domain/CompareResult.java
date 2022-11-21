package bridge.domain;

public enum CompareResult {
    MATCH("O"),
    MISS("X"),
    NONE(" ");

    private final String mark;

    CompareResult(final String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
