package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    static Player player = new Player();

    static Bridge bridge = new Bridge(List.of("U", "D", "D"));

    @DisplayName("플레이어를 특정 방향으로 이동시킬 수 있다.")
    @ParameterizedTest
    @MethodSource("provideDirectionWithOutput")
    void movePlayerToSpecificDirection(final String direction, final Tile expected) {
        player.moveTo(direction);
        assertThat(player.getPosition()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDirectionWithOutput() {
        return Stream.of(
            Arguments.of("U", new Tile("U", 1)),
            Arguments.of("D", new Tile("D", 2)),
            Arguments.of("D", new Tile("D", 3))
        );
    }

    @DisplayName("플레이어가 다리의 특정 방향으로 이동할 수 있다. - FAIL")
    @ParameterizedTest
    @MethodSource("providePlayerDirections")
    void movePlayerToDirectionInBridgeFAIL(final List<String> directions) {
        for (String direction : directions) {
            player.moveTo(direction);
        }
        GameResult gameResult = player.cross(bridge);
        assertThat(gameResult.isFail()).isTrue();
    }

    private static Stream<Arguments> providePlayerDirections() {
        return Stream.of(
            Arguments.of(List.of("D")),
            Arguments.of(List.of("U", "U")),
            Arguments.of(List.of("U", "D", "U"))
        );
    }
}
