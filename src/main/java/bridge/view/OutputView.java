package bridge.view;

import static bridge.Constants.BRIDGE_DELIMITER;
import static bridge.Constants.BRIDGE_HEAD;
import static bridge.Constants.BRIDGE_TAIL;
import static bridge.Constants.OUTPUT_FAIL;
import static bridge.Constants.OUTPUT_NONE;
import static bridge.Constants.OUTPUT_PASS;
import static bridge.Constants.ROUND_RESULT;
import static bridge.Constants.ROUND_RESULT_IS_SUCCESS;
import static bridge.Constants.ROUND_RESULT_NUMBER_OF_ATTEMPTS;

import bridge.domain.Direction;
import bridge.domain.PlayResult;
import bridge.service.BridgeGame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private Map<Direction, List<String>> bridges;

    public OutputView() {
        this.bridges = mapBridges();
    }

    private Map<Direction, List<String>> mapBridges() {
        Map<Direction, List<String>> bridges = new HashMap<>();
        for (Direction direction : Direction.values()) {
            bridges.put(direction, new ArrayList<>());
        }
        return bridges;
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final PlayResult playResult) {
        updateBridges(playResult);
        printBridges();
    }

    private void updateBridges(final PlayResult playResult) {
        bridges.keySet()
            .forEach(direction -> updateBridge(direction, playResult));
    }

    private void updateBridge(final Direction direction, final PlayResult playResult) {
        bridges.computeIfAbsent(direction, bridge -> new ArrayList<>())
            .add(getResultMessage(direction, playResult));
    }

    private String getResultMessage(final Direction direction,
        final PlayResult playResult) {

        if (playResult.isPlayerMovedIn(direction)) {
            return convertResultMessage(playResult);
        }
        return OUTPUT_NONE;
    }

    private String convertResultMessage(final PlayResult PlayResult) {
        if (PlayResult.isPass()) {
            return OUTPUT_PASS;
        }
        if (PlayResult.isFail()) {
            return OUTPUT_FAIL;
        }
        return OUTPUT_NONE;
    }

    private void printBridges() {
        Arrays.stream(Direction.values())
            .forEach(this::printBridge);
    }

    private void printBridge(final Direction direction) {
        System.out.print(BRIDGE_HEAD);
        System.out.print(String.join(BRIDGE_DELIMITER, bridges.get(direction)));
        System.out.println(BRIDGE_TAIL);
    }

    public void clearMap() {
        bridges.clear();
        bridges = mapBridges();
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final BridgeGame game) {
        System.out.println(ROUND_RESULT);
        printBridges();
        System.out.println(ROUND_RESULT_IS_SUCCESS + (game.isCleared() ? "성공" : "실패"));
        System.out.println(ROUND_RESULT_NUMBER_OF_ATTEMPTS + game.getAttempts());
    }
}
