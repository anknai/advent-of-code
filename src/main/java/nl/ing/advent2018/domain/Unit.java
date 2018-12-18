package nl.ing.advent2018.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Getter
@Setter
@ToString
public class Unit implements Comparable<Unit> {
    private Point point;
    private int hitPoint = 200;
    private int attackPower = 3;

    @Override
    public int compareTo(Unit other) {
        return this.point.compareTo(other.point);
    }

    public Point.PointType getType() {
        return this.point.getType();
    }

    public List<Point> getAdjacent(List<Point> points) {
        List<Point> adjacents = new ArrayList<>();
        for (Point other : points) {
            if (this.point.isAdjacent(other)) {
                adjacents.add(other);
            }
        }
        return adjacents;
    }

    public Optional<Unit> getAdjacentEnemy(List<Unit> units) {
        Point current = this.getPoint();
        int hitpoint = Integer.MAX_VALUE;
        Unit enemy= null;
        for (Unit unit: units) {
            if (current.isAdjacent(unit.getPoint())) {
                if (current.getType() != unit.getType()) {
                    if (unit.getHitPoint() < hitpoint) {
                        hitpoint = unit.getHitPoint();
                        enemy = unit;
                    }
                }
            }
        }

        if (null == enemy) {
            return Optional.empty();
        } else {
            return Optional.of(enemy);
        }
    }

    public void getHit(int attackPower) {
        this.hitPoint -= attackPower;
    }
}
