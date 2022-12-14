package bridge.controller;

import bridge.GameCommand;
import bridge.model.GameResult;
import bridge.service.BridgeGameService;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {

    private final InputView inputView;

    private final OutputView outputView;

    private final BridgeGameService bridgeGameService;

    public BridgeGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.bridgeGameService = new BridgeGameService();
    }

    public void start() {
        outputView.printGameStartMessage();

        setUpBridge();
        playGame();

        outputView.printResult(bridgeGameService.isClear(),
            bridgeGameService.getNumberOfTries()
        );
    }

    private void setUpBridge() {
        try {
            outputView.printInputBridgeSizeMessage();
            bridgeGameService.setUpBridge(inputView.readBridgeSize());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            setUpBridge();
        }
    }

    private void playGame() {
        play();

        if (!bridgeGameService.isClear()) {
            askRestart();
        }
    }


    private void play() {
        while (!bridgeGameService.isClear()) {
            outputView.updateMap(movePlayer());
            outputView.printMap();

            if (bridgeGameService.isOver()) {
                break;
            }
        }
    }

    private GameResult movePlayer() {
        try {
            outputView.printInputMovingMessage();
            bridgeGameService.movePlayer(inputView.readMoving());
            return bridgeGameService.getResult();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return movePlayer();
        }
    }

    private void askRestart() {
        outputView.printRestartMessage();
        GameCommand gameCommand = getGameCommand();

        if (gameCommand.isRestart()) {
            bridgeGameService.retryGame();
            outputView.clearMap();
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
