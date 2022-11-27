package bridge;

import bridge.controller.BridgeGameController;
import bridge.service.BridgeGameServiceImpl;

public class Application {

    public static void main(String[] args) {
        BridgeGameServiceImpl bridgeGameService
            = new BridgeGameServiceImpl(new BridgeRandomNumberGenerator());
        BridgeGameController bridgeGameController
            = new BridgeGameController(bridgeGameService);
        bridgeGameController.start();
    }
}
