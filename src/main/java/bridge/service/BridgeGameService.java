package bridge.service;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.model.GameResult;

public class BridgeGameService {

    private final BridgeMaker bridgeMaker;

    private final BridgeGame bridgeGame;

    private int numberOfTries;

    public BridgeGameService(final BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        this.bridgeGame = new BridgeGame();
        this.numberOfTries = 1;
    }

    public void setUpBridge(final int bridgeSize) {
        bridgeGame.makeBridge(bridgeMaker.makeBridge(bridgeSize));
    }

    public void retryGame() {
        bridgeGame.retry();
        numberOfTries++;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public void movePlayer(final String direction) {
        bridgeGame.move(direction);
    }

    public boolean isOver() {
        return bridgeGame.isOver();
    }

    public boolean isClear() {
        return bridgeGame.isClear();
    }

    public GameResult getResult() {
        return bridgeGame.getResult();
    }
}
