package org.ankur.advent2019.d12;


import lombok.ToString;

@ToString
public class Moon {

    int x;

    int y;

    int z;

    int vx;

    int vy;

    int vz;

    public Moon(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getVz() {
        return vz;
    }

    public void setVz(int vz) {
        this.vz = vz;
    }

    public void updateVx(int i) {
        vx += i;
    }

    public void updateVy(int i) {
        vy += i;
    }

    public void updateVz(int i) {
        vz += i;
    }
}
