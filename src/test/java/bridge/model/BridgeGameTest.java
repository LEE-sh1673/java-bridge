package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.BridgeGame;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class BridgeGameTest {

    static BridgeGame game;

    @BeforeAll
    static void setUp() {
        game = new BridgeGame();
        game.makeBridge(List.of("U", "D", "D"));
    }

    @DisplayName("플레이어가 다리를 완전히 건넜는지에 대한 여부를 구할 수 있다. - True")
    @ParameterizedTest
    @MethodSource("providePlayerDirections")
    void returnTrueIfPlayerCompleteCrossing(final List<String> directions,
        final List<String> bridgeDirections,
        final boolean expected) {

        movePlayerToDirectionInBridge(directions, bridgeDirections, expected);
        assertThat(game.isClear()).isEqualTo(expected);
    }

    @DisplayName("플레이어가 다리의 특정 방향으로 이동할 수 있다.")
    @ParameterizedTest
    @MethodSource("providePlayerDirections")
    void movePlayerToDirectionInBridge(final List<String> directions,
        final List<String> bridgeDirections,
        final boolean expected) {

        game.retry();
        game.makeBridge(bridgeDirections);

        for (String direction : directions) {
            game.move(direction);
            assertThat(game.getResult().isPass()).isEqualTo(expected);
        }
    }

    private static Stream<Arguments> providePlayerDirections() {
        return Stream.of(
            // 'UP-DOWN-DOWN'
            Arguments.of(List.of("U", "D", "D"),List.of("U", "D", "D"), true),
            // 'DOWN-DOWN-UP'
            Arguments.of(List.of("D", "D", "U"), List.of("D", "D", "U"), true),
            // 'UP-UP-UP'
            Arguments.of(List.of("U", "U", "U"), List.of("U", "U", "U"), true)
        );
    }

    @DisplayName("플레이어가 다리를 완전히 건넜는지에 대한 여부를 구할 수 있다. - False")
    @ParameterizedTest
    @MethodSource("provideFailPlayerDirections")
    void returnFalseIfPlayerCompleteCrossing(final List<String> directions,
        final List<String> bridgeDirections,
        final boolean expected) {

        movePlayerToDirectionInBridge(directions, bridgeDirections, expected);
        assertThat(game.isClear()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFailPlayerDirections() {
        return Stream.of(
            // 'DOWN-UP-DOWN', but 'UP-DOWN-UP'.
            Arguments.of(List.of("D", "U", "D"), List.of("U", "D", "U"), false),
            // 'DOWN-DOWN-DOWN', but 'UP-UP-UP'.
            Arguments.of(List.of("D", "D", "D"), List.of("U", "U", "U"), false),
            // 'UP-DOWN-DOWN', but 'DOWN-UP-UP'.
            Arguments.of(List.of("U", "D", "D"), List.of("D", "U", "U"), false)
        );
    }

    @DisplayName("플레이어가 다리를 건너는데 실패했는지에 대한 여부를 구할 수 있다.")
    @ParameterizedTest
    @CsvSource({"U,false", "D,true"})
    void returnTrueIfPlayerFailToCrossBridge(final String direction, final boolean expected) {
        game.retry();
        game.move(direction);
        game.getResult();
        assertThat(game.isOver()).isEqualTo(expected);
    }
}
