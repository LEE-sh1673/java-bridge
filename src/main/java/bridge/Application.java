package bridge;

import bridge.controller.BridgeInputController;
import bridge.domain.Bridge;
import bridge.domain.GameCommend;
import bridge.domain.PlayStatus;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    static BridgeInputController inputController
        = new BridgeInputController(new InputView());

    static OutputView outputView = new OutputView();

    static BridgeMaker bridgeMaker
        = new BridgeMaker(new BridgeRandomNumberGenerator());

    static int numberOfAttempts = 1;

    static Bridge bridge;

    static GameCommend gameCommend = GameCommend.NONE;

    public static void main(String[] args) {
        // create bridge based on size user input
        int bridgeSize = inputController.setBridgeSize();
        bridge = createBridge(bridgeSize);
        outputView.printResult(playGame(), numberOfAttempts);
    }

    private static boolean playGame() {
        boolean isGameDone;
        do {
            isGameDone = processGame();
        } while (!isGameDone && gameCommend != GameCommend.EXIT);
        return isGameDone;
    }

    private static boolean processGame() {
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

    private static PlayStatus move(final int round) {
        return bridge.cross(inputController.setDirection(), round);
    }

    private static void askForRestart() {
        gameCommend = inputController.setGameCommend();
        if (gameCommend == GameCommend.RESTART) {
            resetGame();
        }
    }

    private static void resetGame() {
        outputView.clearMap();
        numberOfAttempts++;
    }

    private static Bridge createBridge(final int bridgeSize) {
        return new Bridge(bridgeMaker.makeBridge(bridgeSize));
    }
}
