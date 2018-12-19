package org.ankur.advent2018.test;

import org.ankur.advent2018.D13MineCartMadness;
import org.junit.Test;

import static org.junit.Assert.*;

public class D13MineCartMadnessTest {

    private D13MineCartMadness cartMadness = new D13MineCartMadness();

    private final static String TEST_INPUT = "13_cart_test.txt";
    private final static String TEST_INPUT_LAST = "13_crashed_test.txt";
    private final static String INPUT = "13_cart.txt";

    @Test
    public void crashPoint() {
        String s = cartMadness.crashPoint(TEST_INPUT);
        assertEquals("Crash point is ", "7,3", s);
        s = cartMadness.crashPoint(INPUT);
        assertEquals("Crash point is ", "58,93", s);
    }

    @Test
    public void lastCar() {
        String s = cartMadness.lastCar(TEST_INPUT_LAST);
        assertEquals("Last Car is at ", "6,4", s);
        s = cartMadness.lastCar(INPUT);
        assertEquals("Crash point is ", "91,72", s);
    }
}