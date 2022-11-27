package bridge;

import bridge.model.GameResult;
import bridge.service.BridgeGameService;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    static BridgeNumberGenerator bridgeNumberGenerator
        = new BridgeRandomNumberGenerator();

    static BridgeGameService bridgeGameService
        = new BridgeGameService(bridgeNumberGenerator);

    static InputView inputView = new InputView();

    static OutputView outputView = new OutputView();

    public static void main(String[] args) {
        outputView.printGameStartMessage();

        setUpBridge();
        playGame();

        outputView.printResult(bridgeGameService.isClear(),
            bridgeGameService.getNumberOfTries()
        );
    }

    private static void setUpBridge() {
        try {
            outputView.printInputBridgeSizeMessage();
            bridgeGameService.setUpBridge(inputView.readBridgeSize());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            setUpBridge();
        }
    }

    private static void playGame() {
        play();

        if (!bridgeGameService.isClear()) {
            askRestart();
        }
    }

    private static void play() {
        while (!bridgeGameService.isClear()) {
            outputView.updateMap(movePlayer());
            outputView.printMap();

            if (bridgeGameService.isOver()) {
                break;
            }
        }
    }

    private static GameResult movePlayer() {
        try {
            outputView.printInputMovingMessage();
            bridgeGameService.movePlayer(inputView.readMoving());
            return bridgeGameService.getResult();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return movePlayer();
        }
    }

    private static void askRestart() {
        outputView.printRestartMessage();
        String gameCommend = inputView.readGameCommand();

        if ("R".equals(gameCommend)) {
            bridgeGameService.retryGame();
            outputView.clearMap();
            playGame();
        }
    }
}
