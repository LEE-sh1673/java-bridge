package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.domain.GameCommend;
import bridge.domain.PlayStatus;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    BridgeInputController inputController;

    OutputView outputView;

    BridgeMaker bridgeMaker;

    static int numberOfAttempts = 1;

    static Bridge bridge;

    static GameCommend gameCommend = GameCommend.NONE;

    public BridgeGameController() {
        inputController = new BridgeInputController(new InputView());
        outputView = new OutputView();
        bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    public void start() {
        bridge = createBridge(inputController.setBridgeSize());
        outputView.printResult(playGame(), numberOfAttempts);
    }

    private boolean playGame() {
        boolean isGameDone;
        do {
            isGameDone = processGame();
        } while (!isGameDone && gameCommend != GameCommend.EXIT);
        return isGameDone;
    }

    private boolean processGame() {
        for (int round = 1; round <= bridge.getSize(); round++) {
            PlayStatus status = move(round);
            outputView.printMap(status);

            if (status.isGameOver()) {
                askForRestart();
                return false;
            }
        }
        return true;
    }

    private PlayStatus move(final int round) {
        return bridge.cross(inputController.setDirection(), round);
    }

    private void askForRestart() {
        gameCommend = inputController.setGameCommend();
        if (gameCommend == GameCommend.RESTART) {
            resetGame();
        }
    }

    private void resetGame() {
        outputView.clearMap();
        numberOfAttempts++;
    }

    private Bridge createBridge(final int bridgeSize) {
        return new Bridge(bridgeMaker.makeBridge(bridgeSize));
    }
}
