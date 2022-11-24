package bridge.model;

import static org.assertj.core.api.Assertions.*;

import bridge.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DirectionTest {

    @DisplayName("이동할 칸에 U 또는 D가 아닌 문자가 입력되는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"A","B","C"})
    void createDirectionOfInvalidArguments(final String shape) {
        assertThatThrownBy(() -> Direction.of(shape))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_DIRECTION_TYPE);
    }


}
