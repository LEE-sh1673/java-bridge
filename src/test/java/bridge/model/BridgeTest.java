package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BridgeTest {

    static Bridge bridge = new Bridge(List.of("U", "D", "D"));

    @DisplayName("다리의 칸과 특정 칸을 비교할 수 있다. - MISS")
    @Test
    void compareTileWithBridgeMISS() {
        Tile tile = new Tile("U", 2);
        assertThat(bridge.contains(tile)).isEqualTo(CompareResult.MISS);
    }

    @DisplayName("다리의 칸과 특정 칸을 비교할 수 있다. - MATCH")
    @Test
    void compareTileWithBridgeMATCH() {
        Tile tile = new Tile("U", 1);
        assertThat(bridge.contains(tile)).isEqualTo(CompareResult.MATCH);
    }

}
