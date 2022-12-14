package bridge.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BridgeBridgeGameTest {

    static BridgeGame bridgeGame;

    @BeforeAll
    static void setUp() {
        bridgeGame = new BridgeGame();
        bridgeGame.setUp(List.of("U", "D", "D"));
    }

    @DisplayName("게임을 재시도한 횟수를 구할 수 있다.")
    @Test
    void getNumberOfTries() {
        bridgeGame.retry();
        int numberOfTries = bridgeGame.getNumberOfTries();
        assertThat(numberOfTries).isEqualTo(2);
    }

}
