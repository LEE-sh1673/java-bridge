package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.GameCommand;
import bridge.model.GameResult;
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
        outputView.printGameStartMessage();
        setUpBridge();
        playGame();
        askRetry();
        outputView.printResult(game.isClear(), game.getNumberOfTries());
    }

    private void setUpBridge() {
        try {
            outputView.printInputBridgeSizeMessage();
            game.setUp(bridgeMaker.makeBridge(inputView.readBridgeSize()));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            setUpBridge();
        }
    }

    private void playGame() {
        while (!game.isClear()) {
            outputView.updateMap(movePlayer());
            outputView.printMap();

            if (game.isOver()) {
                break;
            }
        }
    }

    private GameResult movePlayer() {
        try {
            outputView.printInputMovingMessage();
            game.move(inputView.readMoving());
            return game.getResult();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return movePlayer();
        }
    }

    private void askRetry() {
        if (!game.isClear()) {
            outputView.printRestartMessage();
            processRestart();
        }
    }

    private void processRestart() {
        if (getGameCommand().isRestart()) {
            outputView.clearMap();
            game.retry();
            playGame();
        }
    }

    private GameCommand getGameCommand() {
        try {
            return GameCommand.of(inputView.readGameCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return getGameCommand();
        }
    }
}
