package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.GameCommand;
import bridge.dto.PlayResultDto;
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
        outputView.printResult(game.getTotalResult());
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
            outputView.printMap(movePlayer());

            if (game.isOver()) {
                break;
            }
        }
    }

    private PlayResultDto movePlayer() {
        try {
            outputView.printInputMovingMessage();
            return game.move(inputView.readMoving());
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
