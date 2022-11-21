package bridge.controller;

import bridge.domain.BridgeSizeValidator;
import bridge.domain.Direction;
import bridge.GameCommend;
import bridge.view.InputView;

public class BridgeInputController {

    private final InputView inputView;

    public BridgeInputController(final InputView inputView) {
        this.inputView = inputView;
    }

    public int setBridgeSize() {
        try {
            BridgeSizeValidator validator
                = new BridgeSizeValidator(inputView.readBridgeSize());
            return validator.getBridgeSize();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setBridgeSize();
        }
    }

    public Direction setDirection() {
        try {
            return Direction.of(inputView.readMoving());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setDirection();
        }
    }

    public GameCommend setGameCommend() {
        try {
            return GameCommend.of(inputView.readGameCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setGameCommend();
        }
    }
}
