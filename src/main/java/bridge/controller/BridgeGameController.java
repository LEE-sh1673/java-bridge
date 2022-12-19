package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.GameCommand;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    private final InputView inputView;

    private final OutputView outputView;

    private final BridgeMaker bridgeMaker;

    private final BridgeGame game;

    public BridgeGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        this.game = new BridgeGame();
    }

    public void start() {
        setUp();
        playGame();
        askRetry();
        printResult();
    }

    private void setUp() {
        outputView.printGameStartMessage();
        game.setUp(bridgeMaker.makeBridge(readBridgeSize()));
    }

    private int readBridgeSize() {
        try {
            outputView.printInputBridgeSizeMessage();
            return inputView.readBridgeSize();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return inputView.readBridgeSize();
        }
    }

    private void playGame() {
        while (!game.isEnd()) {
            game.move(readDirection());
            outputView.printMap(game.getPlayResult());
        }
    }

    private String readDirection() {
        try {
            outputView.printInputMovingMessage();
            return inputView.readMoving();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return readDirection();
        }
    }

    private void askRetry() {
        if (game.isFail()) {
            outputView.printRestartMessage();
            processRestart();
        }
    }

    private void processRestart() {
        if (readGameCommand().isRestart()) {
            game.retry();
            playGame();
        }
    }

    private GameCommand readGameCommand() {
        try {
            return GameCommand.of(inputView.readGameCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return readGameCommand();
        }
    }

    private void printResult() {
        outputView.printResult(game.getGameResult());
    }
}
