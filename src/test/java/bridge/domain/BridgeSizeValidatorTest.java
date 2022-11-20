package bridge.domain;

import static bridge.ErrorMessage.INVALID_BRIDGE_SIZE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BridgeSizeValidatorTest {

    @DisplayName("다리의 길이가 3~20 범위가 아닌 숫자일 경우 예외르 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 21, 22, 23})
    void validateBridgeSize(int bridgeSize) {
        assertThatThrownBy(() -> new BridgeSizeValidator(bridgeSize))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_BRIDGE_SIZE.getMessage());
    }
}
