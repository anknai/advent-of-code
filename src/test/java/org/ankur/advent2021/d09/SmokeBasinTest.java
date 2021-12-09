package org.ankur.advent2021.d09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmokeBasinTest extends SmokeBasin {

    private final static String TEST_INPUT = "2021_09_01.txt";
    private final static String MAIN_INPUT = "2021_09_02.txt";

    @Test
    void testLow() {
        assertEquals(15, super.low(TEST_INPUT));
        assertEquals(539, super.low(MAIN_INPUT));
    }
}