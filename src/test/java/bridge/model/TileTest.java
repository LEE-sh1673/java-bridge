package bridge.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TileTest {

    @DisplayName("다리의 칸을 비교할 수 있다. - MISS")
    @Test
    void compareTilesMISS() {
        Tile tile1 = new Tile("U", 1);
        assertThat(tile1.compareTo(new Tile("D", 2)))
            .isEqualTo(PlayResult.FAIL);
    }

    @DisplayName("다리의 칸을 비교할 수 있다. - PASS")
    @Test
    void compareTilesMATCH() {
        Tile tile1 = new Tile("U", 1);
        assertThat(tile1.compareTo(new Tile("U", 1)))
            .isEqualTo(PlayResult.PASS);
    }
}
