package org.ankur.advent2019.d10;

import lombok.*;

@Getter
@Setter
@ToString
public class Asteroid {

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

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInSight() {
        return inSight;
    }

    public void setInSight(boolean inSight) {
        this.inSight = inSight;
    }
}
