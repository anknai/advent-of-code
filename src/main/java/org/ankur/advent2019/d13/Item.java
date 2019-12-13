package org.ankur.advent2019.d13;

public class Item {

    long x;

    long y;

    char type;

    public Item(long x, long y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item other = (Item) obj;
            return other.x == this.x && other.y == this.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int)x* 100 + (int)y;
    }
}
