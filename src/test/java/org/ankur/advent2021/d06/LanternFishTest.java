package org.ankur.advent2021.d06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternFishTest extends LanternFish {

    private final static String TEST_INPUT = "2021_06_01.txt";
    private final static String MAIN_INPUT = "2021_06_02.txt";

    @Test
    void testNoOfFish() {
        assertEquals(26, super.solve(TEST_INPUT, 18));
        assertEquals(5934, super.solve(TEST_INPUT, 80));
        assertEquals(351092, super.solve(MAIN_INPUT, 80));
    }

    @Test
    void testNoOfFishInt() {
        assertEquals(26984457539L, super.solve(TEST_INPUT, 256));
        assertEquals(1595330616005L, super.solve(MAIN_INPUT, 256));
    }
}
