package nl.ing.advent2018.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        for (Unit unit: units) {
            if (current.isAdjacent(unit.getPoint())) {
                if (current.getType() != unit.getType()) {
                    return Optional.of(unit);
                }
            }
        }
        return Optional.empty();
    }
}
