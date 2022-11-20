package bridge.domain;

import static bridge.domain.ErrorMessage.INVALID_GAME_COMMEND;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GameCommendValidatorTest {

    @DisplayName("게임 재시작/종료에서 R 또는 Q가 아닌 문자가 입력되는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"E", "S_35*abE", "3", "ASFSA", "FSd"})
    void validateIsCorrectGameCommend(String commend) {
        assertThatThrownBy(() -> new GameCommendValidator(commend))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(INVALID_GAME_COMMEND.getMessage());
    }

}
