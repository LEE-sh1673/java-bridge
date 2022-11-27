package bridge.service;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.BridgeRandomNumberGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BridgeBridgeGameServiceTest {

    static BridgeGameService bridgeGameService;

    @BeforeAll
    static void setUp() {
        bridgeGameService = new BridgeGameServiceImpl(new BridgeRandomNumberGenerator());
        bridgeGameService.setUpBridge(3);
    }

    @DisplayName("게임을 재시도한 횟수를 구할 수 있다.")
    @Test
    void getNumberOfTries() {
        bridgeGameService.retryGame();
        int numberOfTries = bridgeGameService.getNumberOfTries();
        assertThat(numberOfTries).isEqualTo(2);
    }

}
