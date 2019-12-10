package org.ankur.advent2019.d10;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Asteroid implements Comparable<Asteroid> {

    private int x;

    private int y;

    public boolean isVaporized() {
        return vaporized;
    }

    public void setVaporized(boolean vaporized) {
        this.vaporized = vaporized;
    }

    private boolean vaporized;

    private boolean inSight = false;

    private double slope;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Asteroid asteroid) {
        return Double.compare(this.slope, asteroid.slope);
    }
}
