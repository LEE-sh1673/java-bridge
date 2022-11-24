package bridge.model;

import static bridge.ErrorMessage.INVALID_BRIDGE_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BridgeTest {

    static Bridge bridge = new Bridge(List.of("U", "D", "D"));

    @DisplayName("다리의 칸과 특정 칸을 비교할 수 있다. - MISS")
    @Test
    void compareTileWithBridgeMISS() {
        Tile tile = new Tile("U", 2);
        assertThat(bridge.contains(tile)).isEqualTo(CompareResult.MISS);
    }

    @DisplayName("다리의 칸과 특정 칸을 비교할 수 있다. - MATCH")
    @Test
    void compareTileWithBridgeMATCH() {
        Tile tile = new Tile("U", 1);
        assertThat(bridge.contains(tile)).isEqualTo(CompareResult.MATCH);
    }

    @DisplayName("다리 길이에 3 ~ 20의 범위가 아닌 숫자가 입력되는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("provideListOfDirection")
    void createInvalidSizeBridge(final List<String> directions) {
        assertThatThrownBy(() -> new Bridge(directions))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_BRIDGE_SIZE);
    }

    private static Stream<Arguments> provideListOfDirection() {
        return Stream.of(
            Arguments.of(List.of()),
            Arguments.of(List.of("U")),
            Arguments.of(List.of("U", "D")),
            Arguments.of(List.of("U", "D", "D", "D", "D",
                "U", "D", "D", "D", "D",
                "U", "D", "D", "D", "D",
                "U", "D", "D", "D", "D",
                "D"))
        );
    }

}
