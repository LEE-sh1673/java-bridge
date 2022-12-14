package bridge.model;

public enum CompareResult {
    MATCH("O"), MISS("X");

    private final String shape;

    CompareResult(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
