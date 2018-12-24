package org.ankur.advent2018.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bot {

    private int x;

    private int y;

    private int z;

    private int radii;

    private int near;

    public Bot(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Bot(Bot bot) {
        this.x = bot.x;
        this.y = bot.y;
        this.z = bot.z;
        this.radii = bot.radii;
        this.near = bot.near;
    }

    public boolean inRange(Bot other) {
        int distance = Math.abs(other.x - this.x) + Math.abs(other.y - this.y) + Math.abs(other.z - this.z);
        return distance <= radii;
    }

    public void addNeighbour() {
        near++;
    }

    public boolean centerInGrid(Bot min, Bot max) {
        /*int minX = x - radii;
        int maxX = x + radii;
        int minY = y - radii;
        int maxY = y + radii;
        int minZ = z - radii;
        int maxZ = z + radii;*/
        return x >= Math.min(min.getX(), max.getX()) && x <= Math.max(min.getX(), max.getX()) &&
                y >= Math.min(min.getY(), max.getY()) && y <= Math.max(min.getY(), max.getY()) &&
                z >= Math.min(min.getZ(), max.getZ()) && z <= Math.max(min.getZ(), max.getZ());
    }
}
