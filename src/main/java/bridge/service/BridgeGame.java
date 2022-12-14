package bridge.service;

import bridge.model.Bridge;
import bridge.model.GameResult;
import bridge.model.Player;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final Player player;

    private int numberOfTries;

    public BridgeGame() {
        this.player = new Player();
    }

    public void setUp(final List<String> directions) {
        player.setDestination(new Bridge(directions));
        numberOfTries = 1;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(final String direction) {
        player.moveTo(direction);
    }

    public GameResult getResult() {
        return player.cross();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        player.retry();
        numberOfTries++;
    }

    public boolean isClear() {
        return player.isArrived();
    }

    public boolean isOver() {
        return player.isFailed();
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }
}
