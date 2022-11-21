package bridge.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SquareTest {

    @DisplayName("이동할 칸에 U나 D가 아닌 문자가 입력될 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "D64#C", "0", "&", "*%^#$"})
    void createSquareWithInvalidDirection(final String input) {
        assertThatThrownBy(
            () -> Direction.of(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("서로 칸을 비교할 수 있다.")
    @Test
    void compareSquares() {
        Square square = new Square(Direction.UP, 1);
        assertThat(square.equals(new Square(Direction.DOWN, 1))).isFalse();
        assertThat(square.equals(new Square(Direction.UP, 1))).isTrue();
        assertThat(square.equals(new Square(Direction.UP, 2))).isFalse();
    }
}
