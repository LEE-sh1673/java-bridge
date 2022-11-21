package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class Bridge {

    private final List<Square> squares;

    public Bridge(final List<String> directions) {
        this.squares = new ArrayList<>();
        for (int i = 0; i < directions.size(); i++) {
            squares.add(new Square(Direction.of(directions.get(i)), i+1));
        }
    }

    public CompareResult compare(final Square square) {
        if (squares.contains(square)) {
            return CompareResult.MATCH;
        }
        return CompareResult.MISS;
    }

    public int getSize() {
        return squares.size();
    }
}
