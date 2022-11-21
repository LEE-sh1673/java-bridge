package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BridgeTest {

    static Bridge bridge = new Bridge(List.of("U", "D", "D"));

    @DisplayName("다리에서 플레이어가 이동한 칸을 비교한 결과를 구할 수 있다.")
    @ParameterizedTest
    @MethodSource("providePlayerInputWithExpectedOutputInRound")
    void crossBridgePerRound(Square square, CompareResult expected) {
        assertThat(bridge.compare(square)).isEqualTo(expected);
    }

    private static Stream<Arguments> providePlayerInputWithExpectedOutputInRound() {
        return Stream.of(
            Arguments.of(new Square(Direction.UP, 1), CompareResult.MATCH),
            Arguments.of(new Square(Direction.DOWN, 1), CompareResult.MISS),
            Arguments.of(new Square(Direction.DOWN, 2), CompareResult.MATCH),
            Arguments.of(new Square(Direction.DOWN, 3), CompareResult.MATCH),
            Arguments.of(new Square(Direction.UP, 2), CompareResult.MISS)
        );
    }
}
