package org.ankur.advent2018.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@ToString(exclude = {"shortestPath", "adjacentNodes", "distance"})
public class Node implements Comparable<Node> {

    private int x;

    private int y;

    private NodeType type;

    private LinkedList<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Node, Integer> adjacentNodes = new TreeMap<>();

    public Node(int x, int y, NodeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    @Override
    public int compareTo(Node other) {
        if (this.y > other.y) {
            return 1;
        }
        if (this.y < other.y) {
            return -1;
        }
        return Integer.compare(this.x, other.x);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) {
            return false;
        }
        Node other = (Node) o;
        return (other.x == this.x && other.y == this.y);
    }

    @Override
    public int hashCode() {
        return x* 5 + y*13 + 29;
    }

    public enum NodeType {
        WALL('#'),
        ME('X'),
        ROOM('.'),
        HOR_DOOR('-'),
        VER_DOOR('|');

        private char type;

        NodeType(char type) {
            this.type = type;
        }

        public char getType() {
            return type;
        }
    }
}
