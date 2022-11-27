package bridge.view;

import bridge.model.Direction;
import bridge.model.GameResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private final Map<Direction, List<String>> bridges;

    public OutputView() {
        this.bridges = new HashMap<>();
        for (Direction direction : Direction.values()) {
            bridges.put(direction, new ArrayList<>());
        }
    }

    public void printGameStartMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    public void printInputBridgeSizeMessage() {
        System.out.println("\n다리의 길이를 입력해주세요.");
    }

    public void printErrorMessage(final Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printInputMovingMessage() {
        System.out.println("\n이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    public void printRestartMessage() {
        System.out.println("\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap() {
        printBridges();
    }

    public void updateMap(final GameResult gameResult) {
        updateBridge(gameResult::matchDirection, gameResult.getMessage());
        updateBridge(direction -> !gameResult.matchDirection(direction), " ");
    }

    private void updateBridge(final Predicate<Direction> condition, final String message) {
        bridges.keySet()
            .stream()
            .filter(condition)
            .forEach(direction -> bridges.get(direction).add(message));
    }

    private void printBridges() {
        Arrays.stream(Direction.values()).forEach(this::printBridge);
    }

    private void printBridge(final Direction direction) {
        System.out.print("[ ");
        System.out.print(String.join(" | ", bridges.get(direction)));
        System.out.println(" ]");
    }

    public void clearMap() {
        bridges.clear();
        for (Direction direction : Direction.values()) {
            bridges.put(direction, new ArrayList<>());
        }
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final boolean isGameCleared, final int numberOfTries) {
        System.out.println("\n최종 게임 결과");
        printMap();
        System.out.println("게임 성공 여부: " + getGameClearStatus(isGameCleared));
        System.out.println("총 시도한 횟수: " + numberOfTries);
    }

    private String getGameClearStatus(final boolean isGameCleared) {
        if (isGameCleared) {
            return "성공";
        }
        return "실패";
    }
}
