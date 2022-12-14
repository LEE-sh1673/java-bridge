package bridge.model;

public class PlayResult {

    private final Direction direction;

    private final CompareResult compareResult;

    public PlayResult(final Direction direction, final CompareResult compareResult) {
        this.direction = direction;
        this.compareResult = compareResult;
    }

    public boolean isFail() {
        return compareResult == CompareResult.MISS;
    }

    public boolean isPass() {
        return compareResult == CompareResult.MATCH;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getMessage() {
        return compareResult.getShape();
    }
}
