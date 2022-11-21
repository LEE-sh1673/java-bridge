package bridge.view;

import static bridge.Constants.BRIDGE_DELIMITER;
import static bridge.Constants.BRIDGE_HEAD;
import static bridge.Constants.BRIDGE_TAIL;
import static bridge.Constants.ROUND_RESULT;
import static bridge.Constants.ROUND_RESULT_IS_SUCCESS;
import static bridge.Constants.ROUND_RESULT_NUMBER_OF_ATTEMPTS;

import bridge.domain.Direction;
import bridge.domain.CompareResult;
import bridge.domain.PlayResult;
import bridge.service.BridgeGame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private Map<Direction, List<CompareResult>> bridges;

    public OutputView() {
        this.bridges = mapBridges();
    }

    private Map<Direction, List<CompareResult>> mapBridges() {
        Map<Direction, List<CompareResult>> bridges = new HashMap<>();
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
        updateValidSpaceBridge(playResult);
        updateEmptySpaceBridge(playResult);
    }

    private void updateValidSpaceBridge(final PlayResult playResult) {
        updateBridge(playResult.getPlayerDirection(), playResult.getResult());
    }

    private void updateBridge(final Direction direction, final CompareResult compareResult) {
        bridges.computeIfAbsent(direction, bridge -> new ArrayList<>())
            .add(compareResult);
    }

    private void updateEmptySpaceBridge(final PlayResult playResult) {
        bridges.keySet()
            .stream()
            .filter(direction -> direction != playResult.getPlayerDirection())
            .forEach(direction -> updateBridge(direction, CompareResult.NONE));
    }

    private void printBridges() {
        Arrays.stream(Direction.values())
            .forEach(this::printBridge);
    }

    private void printBridge(final Direction direction) {
        System.out.print(BRIDGE_HEAD);
        printPlayResults(direction);
        System.out.println(BRIDGE_TAIL);
    }

    private void printPlayResults(final Direction direction) {
        String results = bridges.get(direction)
            .stream()
            .map(CompareResult::getMark)
            .collect(Collectors.joining(BRIDGE_DELIMITER));
        System.out.print(results);
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
        System.out.println(ROUND_RESULT_IS_SUCCESS + (game.isCleared() ? "성공": "실패"));
        System.out.println(ROUND_RESULT_NUMBER_OF_ATTEMPTS + game.getAttempts());
    }
}
