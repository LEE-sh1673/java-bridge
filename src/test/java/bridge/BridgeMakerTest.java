package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeMakerTest {

    static BridgeNumberGenerator bridgeNumberGenerator;

    static BridgeMaker bridgeMaker;

    @BeforeAll
    static void setUp() {
        bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
    }

    @DisplayName("일정한 크기의 다리를 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints={3,4,5,6,7,8,9,10})
    void createBridgeOfSpecificSize(int bridgeSize) {
        List<String> bridge = bridgeMaker.makeBridge(bridgeSize);
        assertThat(bridge.size()).isEqualTo(bridgeSize);
        assertThat(bridge).containsAnyOf("U", "D");
    }
}