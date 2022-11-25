package bridge.service;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;

public class BridgeGameService {

    private final BridgeGame bridgeGame;

    private int numberOfTries;

    public BridgeGameService() {
        this.bridgeGame = new BridgeGame();
        this.numberOfTries = 1;
    }

    public void setUpBridge(final int bridgeSize) {
        //TODO: Refactoring later
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
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
}
