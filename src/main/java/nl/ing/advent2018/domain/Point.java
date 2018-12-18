package nl.ing.advent2018.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Getter
@Setter
@ToString(exclude = {"shortestPath", "adjacentNodes"})
public class Point implements Comparable<Point> {

    private int x;

    private int y;

    private PointType type;

    private LinkedList<Point> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Point, Integer> adjacentNodes = new HashMap<>();

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

    public boolean isAdjacent(Point other) {
        if (Math.abs(other.getX() - this.getX()) == 1 && Math.abs(other.getY() - this.getY()) == 0) {
            return true;
        }

        if (Math.abs(other.getX() - this.getX()) == 0 && Math.abs(other.getY() - this.getY()) == 1) {
            return true;
        }

        return false;
    }

    public void addDestination(Point destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public enum PointType {
        WALL('#'),
        OPEN('.'),
        ELF('E'),
        GOBLIN('G');

        private char type;

        private PointType(char type) {
            this.type = type;
        }

        public char getType() {
            return type;
        }
    }
}
