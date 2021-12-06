package org.ankur.advent2018.test;

import org.ankur.advent2018.D13MineCartMadness;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D13MineCartMadnessTest {

    private final D13MineCartMadness cartMadness = new D13MineCartMadness();

    private final static String TEST_INPUT = "13_cart_test.txt";
    private final static String TEST_INPUT_LAST = "13_crashed_test.txt";
    private final static String INPUT = "13_cart.txt";

    @Test
    void crashPoint() {
        String s = cartMadness.crashPoint(TEST_INPUT);
        assertEquals("7,3", s);
        s = cartMadness.crashPoint(INPUT);
        assertEquals("58,93", s);
    }

    @Test
    void lastCar() {
        String s = cartMadness.lastCar(TEST_INPUT_LAST);
        assertEquals("6,4", s);
        s = cartMadness.lastCar(INPUT);
        assertEquals("91,72", s);
    }
}