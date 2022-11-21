package bridge.domain;
public class PlayResult {

    private final Direction direction;

    private final CompareResult result;

    public PlayResult(final Direction playerDirection, final CompareResult status) {
        this.direction = playerDirection;
        this.result = status;
    }

    public boolean isPlayerMovedIn(final Direction direction) {
        return this.direction == direction;
    }

    public boolean isFail() {
        return result == CompareResult.MISS;
    }

    public boolean isPass() {
        return result == CompareResult.MATCH;
    }
}

