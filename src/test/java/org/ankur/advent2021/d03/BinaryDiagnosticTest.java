package org.ankur.advent2021.d03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryDiagnosticTest extends BinaryDiagnostic {

    private final static String TEST_INPUT = "2021_03_01.txt";
    private final static String MAIN_INPUT = "2021_03_02.txt";

    @Test
    public void powerConsumption() {
        assertEquals(198, super.powerConsumption(TEST_INPUT));
        assertEquals(4174964, super.powerConsumption(MAIN_INPUT));
    }

    @Test
    public void lifeSupportRating() {
        assertEquals(230, super.lifeSupportRating(TEST_INPUT));
        assertEquals(4474944, super.lifeSupportRating(MAIN_INPUT));
    }
}
