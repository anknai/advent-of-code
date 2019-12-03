package org.ankur.advent2019.d03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class WirePoint implements Comparable<WirePoint> {
    private int x;

    private int y;

    private int steps;

    /*public WirePoint(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println(this);
    }*/

    public int distance() {
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public int compareTo(WirePoint other) {
        int diff = Integer.compare(this.distance(), other.distance());
        if (diff == 0) {
            return Integer.compare(this.x, other.x);
        }
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof WirePoint) {
            WirePoint other = (WirePoint) o;
            return other.x == this.x && other.y == this.y;
        }

        return false;
    }
}
