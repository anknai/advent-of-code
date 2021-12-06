package org.ankur.advent2018.test;

import org.ankur.advent2018.D18SettlersOfTheNorthPole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D18SettlersOfTheNorthPoleTest {

    private D18SettlersOfTheNorthPole northPole = new D18SettlersOfTheNorthPole();

    private static final String TEST_INPUT = "18_test.txt";
    private static final String INPUT = "18_lumberyard.txt";

    @Test
    void area() {
        int area = northPole.area(TEST_INPUT, 10);
        assertEquals(1147, area);
        area = northPole.area(INPUT, 10);
        assertEquals(675100, area);

    }

    @Test
    void areaAfterTooLong() {
        int area = northPole.area(INPUT, 1000);
        assertEquals(191820, area);
        area = northPole.area(INPUT, 468);
        assertEquals(191820, area);
    }
}
