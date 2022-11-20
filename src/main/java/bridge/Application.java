package bridge;

import bridge.controller.BridgeInputController;
import bridge.domain.Direction;
import bridge.domain.GameCommend;
import bridge.view.InputView;

public class Application {

    static BridgeInputController inputController
        = new BridgeInputController(new InputView());

    public static void main(String[] args) {
        int bridgeSize = inputController.setBridgeSize();
        System.out.println("bridgeSize = " + bridgeSize);

        Direction direction = inputController.setDirection();
        System.out.println("direction = " + direction.name());

        GameCommend gameCommend = inputController.setGameCommend();
        System.out.println("gameCommend = " + gameCommend.name());
    }
}
