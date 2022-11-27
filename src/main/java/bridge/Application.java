package bridge;

import bridge.controller.BridgeGameController;
import bridge.service.BridgeGameServiceImpl;

public class Application {

    public static void main(String[] args) {
        BridgeGameController bridgeGameController = new BridgeGameController(
            new BridgeGameServiceImpl(new BridgeRandomNumberGenerator())
        );
        bridgeGameController.start();
    }
}
