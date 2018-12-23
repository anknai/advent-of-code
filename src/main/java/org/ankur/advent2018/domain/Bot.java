package org.ankur.advent2018.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString(exclude = {"near"})
public class Bot {

    private int x;

    private int y;

    private int z;

    private int radii;

    private Set<Bot> near;

    public boolean inRange(Bot other) {
        int distance = Math.abs(other.x - this.x) + Math.abs(other.y - this.y) + Math.abs(other.z - this.z);
        //int distance = Math.abs(other.x - this.x + other.y - this.y + other.z - this.z);
        return distance <= radii;
    }

    public void addNeighbour(Bot neighbour) {
        if (null == near) {
            near = new HashSet<>();
        }
        near.add(neighbour);
    }
}
