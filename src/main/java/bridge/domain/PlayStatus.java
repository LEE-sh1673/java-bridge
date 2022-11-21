package bridge.domain;
public class PlayStatus {

    private final Direction playerDirection;

    private final CompareResult result;

    public PlayStatus(final Direction playerDirection, final CompareResult status) {
        this.playerDirection = playerDirection;
        this.result = status;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public CompareResult getResult() {
        return result;
    }

    public boolean isGameOver() {
        return result == CompareResult.MISS;
    }
}

