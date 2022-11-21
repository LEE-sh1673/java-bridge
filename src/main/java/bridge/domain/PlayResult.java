package bridge.domain;
public class PlayResult {

    private final Direction playerDirection;

    private final CompareResult result;

    public PlayResult(final Direction playerDirection, final CompareResult status) {
        this.playerDirection = playerDirection;
        this.result = status;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public boolean isFail() {
        return result == CompareResult.MISS;
    }

    public boolean isPass() {
        return result == CompareResult.MATCH;
    }
}

