package bridge.dto;

import bridge.model.Direction;
import java.util.List;
import java.util.Map;

public class PlayResultDto {

    private final List<String> upward;

    private final List<String> downward;

    public PlayResultDto(List<String> upward, List<String> downward) {
        this.upward = upward;
        this.downward = downward;
    }

    public static PlayResultDto of(final Map<Direction, List<String>> result) {
        return new PlayResultDto(
            result.get(Direction.UP),
            result.get(Direction.DOWN)
        );
    }

    public List<String> getUpward() {
        return upward;
    }

    public List<String> getDownward() {
        return downward;
    }
}
