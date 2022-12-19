package bridge.model;

public enum PlayResult {
    PASS("O"), FAIL("X");

    private final String shape;

    PlayResult(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }

    public boolean isPass() {
        return this == PASS;
    }

    public boolean isFail() {
        return this == FAIL;
    }
}
