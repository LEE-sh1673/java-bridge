package bridge.model;

public class GameResult {

    private final Direction direction;

    private final CompareResult compareResult;

    public GameResult(final Direction direction, final CompareResult compareResult) {
        this.direction = direction;
        this.compareResult = compareResult;
    }

    public boolean isFail() {
        return compareResult == CompareResult.MISS;
    }

    public boolean isPass() {
        return compareResult == CompareResult.MATCH;
    }

    public String getResult() {
        return compareResult.getShape();
    }

    public Direction getDirection() {
        return direction;
    }
}
