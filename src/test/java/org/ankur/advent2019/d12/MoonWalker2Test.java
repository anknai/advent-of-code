package org.ankur.advent2019.d12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoonWalker2Test extends MoonWalker2 {

    @Test
    public void walk1() {
        List<Moon> moons = new ArrayList<>();
        moons.add(new Moon(-1, 0, 2));
        moons.add(new Moon(2, -10, -7));
        moons.add(new Moon(4, -8, 8));
        moons.add(new Moon(3, 5, -1));
        super.walk1(moons, 1000);
        assertEquals(2772, super.lcm(44, 28, 18));
    }

    @Test
    public void walk2() {
        List<Moon> moons = new ArrayList<>();
        moons.add(new Moon(-7, 17, -11));
        moons.add(new Moon(9, 12, 5));
        moons.add(new Moon(-9, 0, -4));
        moons.add(new Moon(4, 6, 0));
        super.walk1(moons, 1000000);
        assertEquals(324_618_307_124_784L, super.lcm(186028, 193052, 144624));
    }
}