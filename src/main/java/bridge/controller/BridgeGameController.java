package bridge.controller;

import bridge.service.BridgeGame;
import bridge.GameCommend;
import bridge.domain.PlayResult;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    private final BridgeInputController inputController;

    private final OutputView outputView;

    private BridgeGame game;

    public BridgeGameController() {
        inputController = new BridgeInputController(new InputView());
        outputView = new OutputView();
    }

    public void start() {
        // Create Bridge
        game = new BridgeGame(inputController.setBridgeSize());

        // Play game
        playGame();

        // Print game results
        outputView.printResult(game);
    }

    private void playGame() {
        process();

        if (!game.isCleared()) {
            askForRestart();
        }
    }

    private void process() {
        for (int round = 1; round <= game.getRound(); round++) {
            PlayResult playResult = game.move(inputController.setDirection(), round);
            outputView.printMap(playResult);

            if (playResult.isFail() || game.isCleared()) {
                break;
            }
        }
    }

    private void askForRestart() {
        GameCommend gameCommend = inputController.setGameCommend();
        if (gameCommend == GameCommend.RESTART) {
            restartGame();
        }
    }

    private void restartGame() {
        resetGame();
        playGame();
    }

    private void resetGame() {
        outputView.clearMap();
        game.retry();
    }
}
