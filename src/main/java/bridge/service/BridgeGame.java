package bridge.service;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.domain.Direction;
import bridge.domain.PlayResult;
import bridge.domain.Square;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final Bridge bridge;

    private int playerPasses;

    private int attempts;

    public BridgeGame(final int bridgeSize) {
        this.bridge = createBridge(bridgeSize);
        this.playerPasses = 0;
        this.attempts = 1;
    }

    private Bridge createBridge(final int bridgeSize) {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return new Bridge(bridgeMaker.makeBridge(bridgeSize));
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public PlayResult move(final Direction direction, final int round) {
        Square square = new Square(direction, round);
        PlayResult playResult = new PlayResult(direction, bridge.compare(square));

        if (!playResult.isFail()) {
            playerPasses++;
        }
        return playResult;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        attempts += 1;
        playerPasses = 0;
    }

    public boolean isCleared() {
        return playerPasses == bridge.getSize();
    }

    public int getAttempts() {
        return attempts;
    }

    public int getRound() {
        return bridge.getSize();
    }
}
