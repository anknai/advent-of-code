package org.ankur.advent2019.d06;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniversalOrbitMapTest extends UniversalOrbitMap {

    private static final String TEST_INPUT = "2019_06_test.txt";
    private static final String INPUT = "2019_06.txt";

    @Test
    public void readFile() {
        assertEquals(42, super.readFile1(TEST_INPUT));
        assertEquals(224901, super.readFile1(INPUT));
    }

    @Test
    public void readFile2() {
        assertEquals(4, super.readFile2(TEST_INPUT));
        assertEquals(334, super.readFile2(INPUT));
    }
}