package bridge.model;

import bridge.dto.PlayResultDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayResults {

    private final Map<Direction, List<String>> results;

    public PlayResults() {
        results = new HashMap<>();
        initResults();
    }

    private void initResults() {
        for (Direction direction : Direction.values()) {
            results.put(direction, new ArrayList<>());
        }
    }

    public void report(final String userMoved, final PlayResult playResult) {
        Direction direction = Direction.of(userMoved);
        results.get(direction).add(playResult.getShape());
        results.get(Direction.reverse(direction)).add(PlayResult.NONE.getShape());
    }

    public void clear() {
        results.clear();
        initResults();
    }

    public PlayResultDto toDto() {
        return PlayResultDto.of(results);
    }
}
