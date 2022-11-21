package bridge.domain;

import java.util.Objects;

public class Square {

    private final Direction direction;

    private final int position;

    public Square(final Direction direction, final int position) {
        this.direction = direction;
        this.position = position;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Square other = (Square) obj;
        return matchDirection(other) && matchPosition(other);
    }

    private boolean matchDirection(final Square other) {
        return this.direction == other.direction;
    }

    private boolean matchPosition(final Square other) {
        return this.position == other.position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(direction.getMark() + position);
    }
}
