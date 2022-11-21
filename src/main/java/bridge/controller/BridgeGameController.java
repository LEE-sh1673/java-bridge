package bridge.controller;

import bridge.service.BridgeGame;
import bridge.GameCommend;
import bridge.domain.PlayStatus;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    private final BridgeInputController inputController;

    private final OutputView outputView;

    private BridgeGame bridgeGame;

    public BridgeGameController() {
        inputController = new BridgeInputController(new InputView());
        outputView = new OutputView();
    }

    public void start() {
        // Create Bridge
        bridgeGame = new BridgeGame(inputController.setBridgeSize());

        // Play game
        playGame();

        // Print game results
        outputView.printResult(bridgeGame.isSuccess(), bridgeGame.getAttempts());
    }

    private void playGame() {
        processGame();
        if (!bridgeGame.isSuccess()) {
            askForRestart();
        }
    }

    private void processGame() {
        while (!bridgeGame.isSuccess()) {
            PlayStatus status = bridgeGame.move(inputController.setDirection());
            outputView.printMap(status);

            if (status.isGameOver()) {
                break;
            }
        }
    }

    private void askForRestart() {
        GameCommend gameCommend = inputController.setGameCommend();
        if (gameCommend == GameCommend.RESTART) {
            resetGame();
        }
    }

    private void resetGame() {
        outputView.clearMap();
        bridgeGame.retry();
    }
}
