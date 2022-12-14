package bridge.service;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.model.GameResult;

public class BridgeGameService {

    private final BridgeMaker bridgeMaker;

    private final BridgeGame bridgeGame;

    private int numberOfTries;

    public BridgeGameService() {
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
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
