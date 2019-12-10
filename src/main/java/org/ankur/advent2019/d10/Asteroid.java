package org.ankur.advent2019.d10;

import lombok.*;

@Getter
@Setter
@ToString
public class Asteroid {

    private int x;

    private int y;

    private int total;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isInSight() {
        return inSight;
    }

    public void setInSight(boolean inSight) {
        this.inSight = inSight;
    }
}
