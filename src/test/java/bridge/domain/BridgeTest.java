package bridge.domain;

import static bridge.domain.ErrorMessage.INVALID_BRIDGE_SIZE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BridgeTest {

    @DisplayName("다리의 길이가 3~20 범위가 아닌 숫자일 경우 예외르 발생시킨다.")
    @ParameterizedTest
    @MethodSource("provideDirections")
    void createOutOfBoundedBridge(List<String> directions) {
        assertThatThrownBy(() -> new Bridge(directions))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_BRIDGE_SIZE.getMessage());
    }

    private static Stream<Arguments> provideDirections() {
        return Stream.of(
            Arguments.of(List.of("U", "D")),
            Arguments.of(List.of("U")),
            Arguments.of(List.of("")),
            Arguments.of(
                List.of("U", "D", "U", "D", "U",
                    "D", "U", "D", "U", "D",
                    "U", "D", "U", "D", "U",
                    "D", "U", "D", "U", "D", "U")
            )
        );
    }
}
