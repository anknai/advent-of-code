package org.ankur.advent2019.d12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MoonWalkerTest extends MoonWalker {

    @Test
    public void walk1() {
        List<Moon> moons = new ArrayList<>();
        moons.add(new Moon(-1, 0, 2));
        moons.add(new Moon(2, -10, -7));
        moons.add(new Moon(4, -8, 8));
        moons.add(new Moon(3, 5, -1));
        assertEquals(179, super.walk1(moons, 10));
    }

    @Test
    public void walk2() {
        List<Moon> moons = new ArrayList<>();
        moons.add(new Moon(-7, 17, -11));
        moons.add(new Moon(9, 12, 5));
        moons.add(new Moon(-9, 0, -4));
        moons.add(new Moon(4, 6, 0));
        assertEquals(7013, super.walk1(moons, 1000));
    }
}