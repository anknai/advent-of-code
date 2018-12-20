package org.ankur.advent2018.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@ToString(exclude = {"shortestPath", "adjacentNodes", "distance"})
@NoArgsConstructor
public class Point implements Comparable<Point> {

    private int x;

    private int y;

    private UnitType unitType;

    private AreaType areaType;

    private LinkedList<Point> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Point, Integer> adjacentNodes = new TreeMap<>();

    public Point(int x, int y, AreaType areaType) {
        this.x = x;
        this.y = y;
        this.areaType = areaType;
    }

    @Override
    public int compareTo(Point other) {
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
        return x* 5 + y*13 + 29;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point other = (Point) o;
        return (other.x == this.x && other.y == this.y);
    }

    public boolean isAdjacent(Point other) {
        if (Math.abs(other.getX() - this.getX()) == 1 && Math.abs(other.getY() - this.getY()) == 0) {
            return true;
        }

        return Math.abs(other.getX() - this.getX()) == 0 && Math.abs(other.getY() - this.getY()) == 1;

    }

    public void addDestination(Point destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public enum UnitType {
        OPEN('.'),
        ELF('E'),
        GOBLIN('G');

        private char type;

        UnitType(char type) {
            this.type = type;
        }

        public char getType() {
            return type;
        }
    }

    public enum AreaType {
        WALL('#'),
        ME('X'),
        ROOM('.'),
        HOR_DOOR('-'),
        VER_DOOR('|');

        private char type;

        AreaType(char type) {
            this.type = type;
        }

        public char getType() {
            return type;
        }
    }
}
