package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BridgeTest {

    @DisplayName("다리의 칸과 특정 칸을 비교할 수 있다. - MISS")
    @Test
    void compareTileWithBridgeMISS() {
        Bridge bridge = new Bridge(List.of("U", "D", "D"));
        Tile tile = new Tile("U", 2);
        CompareResult compareResult = bridge.contains(tile);
        assertThat(compareResult).isEqualTo(CompareResult.MISS);
    }

}
