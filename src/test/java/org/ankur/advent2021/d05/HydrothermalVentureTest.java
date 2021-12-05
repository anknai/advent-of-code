package org.ankur.advent2021.d05;

import org.junit.Test;

import static org.junit.Assert.*;

public class HydrothermalVentureTest extends HydrothermalVenture {

    private final static String TEST_INPUT = "2021_05_01.txt";
    private final static String MAIN_INPUT = "2021_05_02.txt";

    @Test
    public void testOverlap() {
        assertEquals(5, super.overlap(TEST_INPUT, 10));
        assertEquals(4745, super.overlap(MAIN_INPUT, 1000));
    }

    @Test
    public void testDiagonalOverlap() {
        assertEquals(12, super.overlapDiagonal(TEST_INPUT, 10));
        assertEquals(18442, super.overlapDiagonal(MAIN_INPUT, 1000));
    }
}