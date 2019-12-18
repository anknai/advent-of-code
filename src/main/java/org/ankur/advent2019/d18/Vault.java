package org.ankur.advent2019.d18;

import lombok.ToString;
import org.ankur.advent2018.domain.Point;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@ToString(callSuper = true)
public class Vault extends Point {

    private char assortment;

    public Vault(int x, int y, AreaType areaType, char assortment) {
        super(x, y, areaType);
        this.assortment = assortment;
    }

    public boolean isAdjacent(Vault other, List<Character> traversed) {
        if (other.getAreaType() == AreaType.DOOR && !traversed.contains(other.getAssortment())) {
            //System.out.println(Arrays.toString(traversed.toArray()));
            return false;
        }
        if (this.getAreaType() == AreaType.KEY && !traversed.contains(this.getAssortment())) {
            return false;
        }
        if (Math.abs(other.getX() - this.getX()) == 1 && Math.abs(other.getY() - this.getY()) == 0) {
            return true;
        }

        return Math.abs(other.getX() - this.getX()) == 0 && Math.abs(other.getY() - this.getY()) == 1;
    }

    public char getAssortment() {
        return assortment;
    }

    public void open() {
        this.setAreaType(AreaType.ME);
        this.assortment = '@';
    }

    public void swap(Vault other) {
        int x = other.getX();
        int y = other.getY();
        other.setX(this.getX());
        other.setY(this.getY());
        this.setX(x);
        this.setY(y);
    }
}
