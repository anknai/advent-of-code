package nl.ing.advent2018.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Cart implements Comparable<Cart> {

    private int x;

    private int y;

    private Direction direction;

    private Turn next;

    private boolean crashed;

    @Override
    public int compareTo(Cart other) {
        if (this.y > other.y) {
            return 1;
        }
        if (this.y < other.y) {
            return -1;
        }
        return Integer.compare(this.x, other.x);
    }

    @Override
    public int hashCode() {
        return x + 32*y + 5;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) o;
        return other.x == this.x && other.y == this.y;
    }

    public enum Direction {
        UP('^'),
        DOWN('v'),
        LEFT('<'),
        RIGHT('>');

        private char side;

        Direction(char i) {
            this.side = i;
        }

        @Override
        public String toString() {
            return String.valueOf(side);
        }

    }

    public enum Turn {
        LEFT,
        RIGHT,
        STRAIGHT;
    }
}
