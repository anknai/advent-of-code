package org.ankur.advent2019.d15;

import org.ankur.advent2018.domain.Point;

public class OxygenPoint extends Point {
    public OxygenPoint() {
    }

    public OxygenPoint(int x, int y, AreaType areaType) {
        super(x, y, areaType);
    }

    @Override
    public boolean isAdjacent(Point other) {
        if (other.getAreaType() == AreaType.WALL) {
            return false;
        }
        if (Math.abs(other.getX() - this.getX()) == 1 && Math.abs(other.getY() - this.getY()) == 0) {
            return true;
        }

        return Math.abs(other.getX() - this.getX()) == 0 && Math.abs(other.getY() - this.getY()) == 1;

    }
}
