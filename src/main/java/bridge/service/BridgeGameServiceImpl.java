package bridge.service;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.model.GameResult;

public class BridgeGameServiceImpl implements BridgeGameService {

    private final BridgeMaker bridgeMaker;

    private final BridgeGame bridgeGame;

    private int numberOfTries;

    public BridgeGameServiceImpl(final BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        this.bridgeGame = new BridgeGame();
        this.numberOfTries = 1;
    }

    @Override
    public void setUpBridge(final int bridgeSize) {
        bridgeGame.makeBridge(bridgeMaker.makeBridge(bridgeSize));
    }

    @Override
    public void retryGame() {
        bridgeGame.retry();
        numberOfTries++;
    }

    @Override
    public int getNumberOfTries() {
        return numberOfTries;
    }

    @Override
    public void movePlayer(final String direction) {
        bridgeGame.move(direction);
    }

    @Override
    public boolean isOver() {
        return bridgeGame.isOver();
    }

    @Override
    public boolean isClear() {
        return bridgeGame.isClear();
    }

    @Override
    public GameResult getResult() {
        return bridgeGame.getResult();
    }
}
