package org.ankur.advent2018.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bot implements Comparable<Bot> {

    private int x;

    private int y;

    private int z;

    private int radii;

    private int near;

    @Override
    public int hashCode() {
        return x*7 + y * 2 + z * 3 + 5;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bot)) {
            return false;
        }

        Bot other = (Bot) o;

        return other.x == this.x && other.y == this.y && other.z == this.z && other.radii == this.radii;
    }

    public boolean inRange(Bot other) {
        int distance = Math.abs(other.x - this.x) + Math.abs(other.y - this.y) + Math.abs(other.z - this.z);
        return distance <= radii;
    }

    public boolean intersects(Bot other) {
        if (distance(other) <= other.radii + this.radii) {
            return true;
        }

        Bot b1 = Bot.builder()
                .x(other.x)
                .y(other.y)
                .z(other.z - radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x)
                .y(other.y - radii)
                .z(other.z)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x - radii)
                .y(other.y)
                .z(other.z)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x)
                .y(other.y)
                .z(other.z + radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x)
                .y(other.y + radii)
                .z(other.z)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x + radii)
                .y(other.y)
                .z(other.z)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x + radii)
                .y(other.y + radii)
                .z(other.z - radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x + radii)
                .y(other.y - radii)
                .z(other.z + radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x - radii)
                .y(other.y + radii)
                .z(other.z + radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x - radii)
                .y(other.y - radii)
                .z(other.z + radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x - radii)
                .y(other.y + radii)
                .z(other.z - radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        b1 = Bot.builder()
                .x(other.x + radii)
                .y(other.y - radii)
                .z(other.z - radii)
                .radii(radii)
                .build();
        if (distance(b1) <= other.radii + this.radii) {
            return true;
        }

        return false;
    }

    private int distance(Bot other) {
        return Math.abs(other.x - this.x) + Math.abs(other.y - this.y) + Math.abs(other.z - this.z);
    }

    public void addNeighbour() {
        near++;
    }

    @Override
    public int compareTo(Bot other) {
        int result = Integer.compare(other.near, this.near);
        if (result != 0) {
            return result;
        }
        int otherX = Math.abs(other.x) + Math.abs(other.y) + Math.abs(other.z);
        int thisX = Math.abs(x) + Math.abs(y) + Math.abs(z);
        return Integer.compare(thisX, otherX);
    }
}
