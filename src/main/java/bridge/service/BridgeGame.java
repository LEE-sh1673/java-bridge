package bridge.service;

import bridge.dto.GameResultDto;
import bridge.dto.PlayResultDto;
import bridge.model.Bridge;
import bridge.model.PlayResults;
import bridge.model.Player;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final Player player;

    private final PlayResults playResults;

    private int numberOfTries;

    public BridgeGame() {
        this.player = new Player();
        this.playResults = new PlayResults();
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
    public PlayResultDto move(final String direction) {
        playResults.report(player.moveTo(direction));
        return playResults.toDto();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        player.reset();
        playResults.clear();
        numberOfTries++;
    }

    public boolean isClear() {
        return player.isArrived() && playResults.isAllPass();
    }

    public boolean isOver() {
        return playResults.hasNonPass();
    }

    public GameResultDto getTotalResult() {
        return new GameResultDto(playResults.toDto(), isClear(), numberOfTries);
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }
}
