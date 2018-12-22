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
public class Region implements Comparable<Region> {

    private int x;

    private int y;

    private RegionType regionType;

    private LinkedList<Region> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Region, Integer> adjacentNodes = new TreeMap<>();

    public Region(int x, int y, RegionType regionType) {
        this.x = x;
        this.y = y;
        this.regionType = regionType;
    }

    @Override
    public int compareTo(Region other) {
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
        if (!(o instanceof Region)) {
            return false;
        }
        Region other = (Region) o;
        return (other.x == this.x && other.y == this.y);
    }

    public boolean isAdjacent(Region other) {
        return Math.abs(other.getX() - this.getX()) == 1 && Math.abs(other.getY() - this.getY()) == 0
                || Math.abs(other.getX() - this.getX()) == 0 && Math.abs(other.getY() - this.getY()) == 1;

    }

    public void addDestination(Region destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public enum RegionType {
        ROCKY(0),
        WET(1),
        NARROW(2);

        private int type;

        RegionType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
