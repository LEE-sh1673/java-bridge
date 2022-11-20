package bridge.domain;

import static bridge.domain.ErrorMessage.INVALID_BRIDGE_SIZE;

import java.util.List;

public class Bridge {

    private static final int MAX_BRIDGE_SIZE = 20;

    private static final int MIN_BRIDGE_SIZE = 3;

    public Bridge(final List<String> directions) {
        validateSize(directions);
    }

    private void validateSize(final List<String> directions) {
        if (isOutOfBound(directions)) {
            throw new IllegalArgumentException(
                INVALID_BRIDGE_SIZE.getMessage()
            );
        }
    }

    private boolean isOutOfBound(final List<String> directions) {
        int size = directions.size();
        return size > MAX_BRIDGE_SIZE || size < MIN_BRIDGE_SIZE;
    }
}
