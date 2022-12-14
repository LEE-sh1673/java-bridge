package bridge.model;

import bridge.dto.PlayResultDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayResults {

    private final List<PlayResult> results;

    public PlayResults() {
        results = new ArrayList<>();
    }

    public void report(final PlayResult playResult) {
        results.add(playResult);
    }

    public boolean isAllPass() {
        return results.stream().allMatch(PlayResult::isPass);
    }

    public boolean hasNonPass() {
        return results.stream().anyMatch(PlayResult::isFail);
    }

    public void clear() {
        results.clear();
    }

    public PlayResultDto toDto() {
        return PlayResultDto.of(getGameResult());
    }

    private Map<Direction, List<String>> getGameResult() {
        Map<Direction, List<String>> gameResult = new HashMap<>();

        for (Direction direction : Direction.values()) {
            gameResult.put(direction, new ArrayList<>());
        }
        mapGameResult(gameResult);
        return gameResult;
    }

    private void mapGameResult(final Map<Direction, List<String>> gameResult) {
        for (PlayResult result : results) {
            Direction direction = result.getDirection();
            gameResult.get(direction).add(result.getMessage());
            gameResult.get(Direction.reverse(direction)).add(" ");
        }
    }
}
