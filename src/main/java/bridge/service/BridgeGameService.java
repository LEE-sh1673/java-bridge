package bridge.service;

import bridge.model.GameResult;

public interface BridgeGameService {

    void setUpBridge(final int bridgeSize);

    void retryGame();

    int getNumberOfTries();

    void movePlayer(final String direction);

    boolean isOver();

    boolean isClear();

    GameResult getResult();
}
