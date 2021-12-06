package org.ankur.advent2021.d01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonarSweepTest {

    private final static String TEST_INPUT = "2021_01_01.txt";
    private final static String MAIN_INPUT = "2021_01_02.txt";

    private final SonarSweep sweep = new SonarSweep();

    @Test
    void testSweep() {
        int i = sweep.sweepIncrease(TEST_INPUT);
        assertEquals(7, i);
    }

    @Test
    void testSweepMain() {
        int i = sweep.sweepIncrease(MAIN_INPUT);
        assertEquals(1121, i);
    }

    @Test
    void testDetailedSweep() {
        int i = sweep.detailedSweep(TEST_INPUT);
        assertEquals(5, i);
    }

    @Test
    void testDetailedSweepMain() {
        int i = sweep.detailedSweep(MAIN_INPUT);
        assertEquals(1065, i);
    }
}