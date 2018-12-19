package org.ankur.advent2018.test;

import org.ankur.advent2018.D18SettlersOfTheNorthPole;
import org.junit.Test;

import static org.junit.Assert.*;

public class D18SettlersOfTheNorthPoleTest {

    private D18SettlersOfTheNorthPole northPole = new D18SettlersOfTheNorthPole();

    private static final String TEST_INPUT = "18_test.txt";
    private static final String INPUT = "18_lumberyard.txt";

    @Test
    public void area() {
        int area = northPole.area(TEST_INPUT, 10);
        assertEquals("Area is ", 1147, area);
        area = northPole.area(INPUT, 10);
        assertEquals("Area is ", 675100, area);
        area = northPole.area(INPUT, 1000);
        assertEquals("Area is ", 191820, area);
        area = northPole.area(INPUT, 468);
        assertEquals("Area is ", 191820, area);
    }

    @Test
    public void areaAfterTooLong() {
        int area = northPole.areaAfterTooLong(INPUT, 10_000);
        assertEquals("Area is ", 189240, area);
        area = northPole.areaAfterTooLong(INPUT, 100_000);
        assertEquals("Area is ", 179944, area);
    }
}
