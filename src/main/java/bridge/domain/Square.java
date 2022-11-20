package bridge.domain;

public class Square {

    private final Direction direction;

    public Square(final String direction) {
        this.direction = Direction.of(direction);
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
        return this.direction == other.direction;
    }
}
