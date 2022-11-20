package bridge;

import bridge.domain.Direction;
import bridge.domain.PlayResult;
import bridge.domain.PlayStatus;
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

    Map<Direction, List<PlayResult>> bridgesByDirection;

    public OutputView() {
        this.bridgesByDirection = new HashMap<>();
        for (Direction direction : Direction.values()) {
            bridgesByDirection.put(direction, new ArrayList<>());
        }
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(PlayStatus playStatus) {
        updateMap(playStatus);
        renderMap();
    }

    private void updateMap(final PlayStatus playStatus) {
        Direction playerDirection = playStatus.getPlayerDirection();
        PlayResult result = playStatus.getResult();

        bridgesByDirection.keySet()
            .stream()
            .filter(direction -> direction != playerDirection)
            .forEach(direction -> bridgesByDirection.get(direction).add(PlayResult.NONE));

        bridgesByDirection.get(playerDirection).add(result);
    }

    private void renderMap() {
        Arrays.stream(Direction.values())
            .forEach(this::renderMapByDirection);
    }

    private void renderMapByDirection(final Direction direction) {
        List<String> playResults = bridgesByDirection.get(direction)
            .stream()
            .map(PlayResult::getMark)
            .collect(Collectors.toList());
        System.out.print(" [ ");
        System.out.print(String.join(" | ", playResults));
        System.out.println(" ]");
    }

    public void clearMap() {
        for (Direction direction : Direction.values()) {
            bridgesByDirection.put(direction, new ArrayList<>());
        }
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
        System.out.println("최종 게임 결과");
        renderMap();
        System.out.println("게임 성공 여부: ");
        System.out.println("총 시도한 횟수: ");
    }
}
