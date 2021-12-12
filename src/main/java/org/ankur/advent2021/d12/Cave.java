package org.ankur.advent2021.d12;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Cave {

    private String name;

    private boolean isStart;

    private boolean isEnd;

    private boolean isMajor;

    public void setTraversed(boolean traversed) {
        traverseCount++;
        this.traversed = traversed;
    }

    private boolean traversed;

    public void resetTraverse() {
        this.traverseCount = 0;
        traversed = false;
    }

    private int traverseCount = 0;

    private Set<Cave> neighbours = new HashSet<>();

    public Cave(String name) {
        this.name = name;
        char c = name.charAt(0);
        if ("start".equals(name)) {
            isStart = true;
        } else if ("end".equals(name)) {
            isEnd = true;
        } else if (c < 95) {
            isMajor = true;
        }
    }

    public void addNeighbour(Cave neighbour) {
        neighbours.add(neighbour);
    }

    public boolean isTraversed() {
        return !isMajor && traversed;
    }

    public boolean isNotTraversedTwice() {
        return !isMajor && traverseCount < 2;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cave)) {
            return false;
        }
        return name.equals(((Cave) other).name);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(name + "(" + isMajor + ") -> ");
        for (Cave neighbour : neighbours) {
            string.append(" ").append(neighbour.getName());
        }
        return string.toString();
    }
}
