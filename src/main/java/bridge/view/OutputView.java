package bridge.view;

import static bridge.view.Constants.BRIDGE_FROM;
import static bridge.view.Constants.BRIDGE_MIDDLE;
import static bridge.view.Constants.GAME_RESULT_FAIL_MESSAGE;
import static bridge.view.Constants.GAME_RESULT_MESSAGE;
import static bridge.view.Constants.GAME_RESULT_NUMBER_OF_TRIES_MESSAGE;
import static bridge.view.Constants.GAME_RESULT_STATUS_MESSAGE;
import static bridge.view.Constants.GAME_RESULT_SUCCESS_MESSAGE;
import static bridge.view.Constants.GAME_START_MESSAGE;
import static bridge.view.Constants.READ_BRIDGE_SIZE_MESSAGE;
import static bridge.view.Constants.READ_MOVING_MESSAGE;
import static bridge.view.Constants.READ_OPTION_MESSAGE;

import bridge.dto.GameResultDto;
import bridge.dto.PlayResultDto;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printInputBridgeSizeMessage() {
        System.out.println(READ_BRIDGE_SIZE_MESSAGE);
    }

    public void printErrorMessage(final Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printInputMovingMessage() {
        System.out.println(READ_MOVING_MESSAGE);
    }

    public void printRestartMessage() {
        System.out.println(READ_OPTION_MESSAGE);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final PlayResultDto resultDto) {
        printBridgeFormat(resultDto.getUpward());
        printBridgeFormat(resultDto.getDownward());
    }
    
    private void printBridgeFormat(List<String> bridge) {
        String formatBridge = String.join(BRIDGE_MIDDLE, bridge);
        String outputBridge = String.format(BRIDGE_FROM, formatBridge);
        System.out.println(outputBridge);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final GameResultDto gameResultDto) {
        System.out.println(GAME_RESULT_MESSAGE);
        printMap(gameResultDto.getPlayResultDto());
        System.out.println(GAME_RESULT_STATUS_MESSAGE + getGameClearStatus(gameResultDto.isClear()));
        System.out.println(GAME_RESULT_NUMBER_OF_TRIES_MESSAGE + gameResultDto.getNumberOfTries());
    }

    private String getGameClearStatus(final boolean isGameCleared) {
        if (isGameCleared) {
            return GAME_RESULT_SUCCESS_MESSAGE;
        }
        return GAME_RESULT_FAIL_MESSAGE;
    }
}
