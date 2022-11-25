package bridge.model;

public enum CompareResult {
    MATCH("O"), MISS("X");

    private final String shape;

    CompareResult(final String shape) {
        this.shape = shape;
    }

    public boolean isMatch() {
        return this == MATCH;
    }

    public String getShape() {
        return shape;
    }
}
