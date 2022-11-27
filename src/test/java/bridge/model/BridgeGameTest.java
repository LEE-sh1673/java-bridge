package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.BridgeGame;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class BridgeGameTest {

    static BridgeGame game = new BridgeGame();

    @BeforeEach
    void setUp() {
        game.makeBridge(List.of("U", "D", "D"));
        game.retry();
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
        game.move(direction);
        game.getResult();
        assertThat(game.isOver()).isEqualTo(expected);
    }

    @DisplayName("전체 기능 테스트")
    @ParameterizedTest
    @MethodSource("providePlayerInputWithResult")
    void playGameCorrectly(final List<String> directions, final boolean expected) {
        for (String direction : directions) {
            game.move(direction);
            game.getResult();

            if (game.isOver()) {
                break;
            }
        }
        assertThat(game.isClear()).isEqualTo(expected);
    }

    private static Stream<Arguments> providePlayerInputWithResult() {
        return Stream.of(
            Arguments.of(List.of("U", "D", "D"), true),
            Arguments.of(List.of("D", "U", "D"), false),
            Arguments.of(List.of("D", "U", "D"), false)
        );
    }

    @DisplayName("현재까지 진행한 게임의 결과를 구할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideDirectionWithGameResult")
    void getGameResults(final String movingDirection,
        final Direction direction,
        final String resultMessage) {

        game.move(movingDirection);
        GameResult gameResult = game.getResult();
        assertThat(gameResult.matchDirection(direction)).isTrue();
        assertThat(gameResult.getMessage()).isEqualTo(resultMessage);
    }

    private static Stream<Arguments> provideDirectionWithGameResult() {
        return Stream.of(
            Arguments.of("U", Direction.UP, "O"),
            Arguments.of("D", Direction.DOWN, "X")
        );
    }
}
