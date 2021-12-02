package org.ankur.advent2021.d01;

import org.junit.Assert;
import org.junit.Test;

public class SonarSweepTest {

    private final static String TEST_INPUT = "2021_01_01.txt";
    private final static String MAIN_INPUT = "2021_01_02.txt";

    private final SonarSweep sweep = new SonarSweep();

    @Test
    public void testSweep() {
        int i = sweep.sweepIncrease(TEST_INPUT);
        Assert.assertEquals(7, i);
    }

    @Test
    public void testSweepMain() {
        int i = sweep.sweepIncrease(MAIN_INPUT);
        Assert.assertEquals(1121, i);
    }

    @Test
    public void testDetailedSweep() {
        int i = sweep.detailedSweep(TEST_INPUT);
        Assert.assertEquals(5, i);
    }

    @Test
    public void testDetailedSweepMain() {
        int i = sweep.detailedSweep(MAIN_INPUT);
        Assert.assertEquals(1065, i);
    }
}