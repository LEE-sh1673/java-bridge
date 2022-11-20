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
    void crossBridgePerRound(int round, Direction direction, PlayStatus expected) {
        PlayStatus status = bridge.cross(direction, round);
        assertThat(status.getPlayerDirection())
            .isEqualTo(expected.getPlayerDirection());
        assertThat(status.getResult())
            .isEqualTo(expected.getResult());
    }

    private static Stream<Arguments> providePlayerInputWithExpectedOutputInRound() {
        return Stream.of(
            Arguments.of(1, Direction.UP, new PlayStatus(Direction.UP, PlayResult.MATCH)),
            Arguments.of(1, Direction.DOWN, new PlayStatus(Direction.DOWN, PlayResult.MISS)),
            Arguments.of(2, Direction.DOWN, new PlayStatus(Direction.DOWN, PlayResult.MATCH)),
            Arguments.of(3, Direction.DOWN, new PlayStatus(Direction.DOWN, PlayResult.MATCH)),
            Arguments.of(2, Direction.UP, new PlayStatus(Direction.UP, PlayResult.MISS))
        );
    }
}
