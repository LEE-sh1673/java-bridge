package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.BridgeGame;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BridgeGameTest {

    static BridgeGame game;

    @BeforeAll
    static void setUp() {
        game = new BridgeGame();
        game.makeBridge(List.of("U", "D", "D"));
    }

    @DisplayName("플레이어가 다리의 특정 방향으로 이동할 수 있다.")
    @ParameterizedTest
    @MethodSource("providePlayerDirections")
    void movePlayerToDirectionInBridge(final List<String> directions) {
        game.retry();
        game.makeBridge(directions);

        for (String direction : directions) {
            game.move(direction);
            assertThat(game.getResult().isPass()).isTrue();
        }
    }

    private static Stream<Arguments> providePlayerDirections() {
        return Stream.of(
            // 'UP-DOWN-DOWN'
            Arguments.of(List.of("U", "D", "D")),
            // 'DOWN-DOWN-UP'
            Arguments.of(List.of("D", "D", "U")),
            // 'UP-UP-UP'
            Arguments.of(List.of("U", "U", "U"))
        );
    }
}
