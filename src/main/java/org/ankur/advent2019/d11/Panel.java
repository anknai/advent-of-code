package org.ankur.advent2019.d11;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Panel {

    private int x;

    private int y;

    private boolean painted;

    private int color;

    public Panel(int x, int y, boolean painted, int color) {
        this.x = x;
        this.y = y;
        this.painted = painted;
        this.color = color;
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

    public boolean isPainted() {
        return painted;
    }

    public void setPainted(boolean painted) {
        this.painted = painted;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
