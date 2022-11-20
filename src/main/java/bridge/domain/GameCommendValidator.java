package bridge.domain;

import static bridge.ErrorMessage.INVALID_GAME_COMMEND;

public class GameCommendValidator {

    private final String commend;

    public GameCommendValidator(final String commend) {
        validateCommend(commend);
        this.commend = commend;
    }

    private void validateCommend(final String commend) {
        if (!isValidFormat(commend)) {
            throw new IllegalArgumentException(
                INVALID_GAME_COMMEND.getMessage()
            );
        }
    }

    private boolean isValidFormat(final String commend) {
        return commend != null && GameCommend.contains(commend);
    }

    public String getCommend() {
        return commend;
    }
}
