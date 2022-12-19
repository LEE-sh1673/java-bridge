package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    static Player player = new Player();

    @BeforeAll
    static void setUp() {
        player = new Player();
        player.setDestination(List.of("U", "D", "D"));
    }

    @DisplayName("플레이어가 다리의 특정 방향으로 이동할 수 있다. - FAIL")
    @ParameterizedTest
    @MethodSource("provideFailPlayerDirections")
    void movePlayerToDirectionInBridgeFAIL(final List<String> directions) {
        player.reset();
        List<PlayResult> playResults = new ArrayList<>();

        for (String direction : directions) {
            playResults.add(player.move(direction));
        }
        assertThat(playResults.stream()
            .anyMatch(PlayResult::isFail)
        ).isTrue();
    }

    private static Stream<Arguments> provideFailPlayerDirections() {
        return Stream.of(
            Arguments.of(List.of("D")),
            Arguments.of(List.of("U", "U")),
            Arguments.of(List.of("U", "D", "U"))
        );
    }

    @DisplayName("플레이어가 다리의 특정 방향으로 이동할 수 있다. - PASS")
    @ParameterizedTest
    @MethodSource("providePassPlayerDirections")
    void movePlayerToDirectionInBridgePASS(final List<String> directions) {
        player.reset();
        List<PlayResult> playResults = new ArrayList<>();

        for (String direction : directions) {
            playResults.add(player.move(direction));
        }
        assertThat(playResults.stream()
            .allMatch(PlayResult::isPass)
        ).isTrue();
    }

    private static Stream<Arguments> providePassPlayerDirections() {
        return Stream.of(
            Arguments.of(List.of("U")),
            Arguments.of(List.of("U", "D")),
            Arguments.of(List.of("U", "D", "D"))
        );
    }
}
