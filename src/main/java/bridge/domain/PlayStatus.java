package bridge.domain;
public class PlayStatus {

    private final Direction playerDirection;

    private final PlayResult result;

    public PlayStatus(final Direction playerDirection, final PlayResult status) {
        this.playerDirection = playerDirection;
        this.result = status;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public PlayResult getResult() {
        return result;
    }

    public boolean isGameOver() {
        return result == PlayResult.MISS;
    }
}

