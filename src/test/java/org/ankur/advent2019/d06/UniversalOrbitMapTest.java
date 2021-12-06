package org.ankur.advent2019.d06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniversalOrbitMapTest extends UniversalOrbitMap {

    private static final String TEST_INPUT = "archive/2019_06_test.txt";
    private static final String INPUT = "archive/2019_06.txt";

    @Test
    void readFile() {
        assertEquals(42, super.readFile1(TEST_INPUT));
        assertEquals(224901, super.readFile1(INPUT));
    }

    @Test
    void readFile2() {
        assertEquals(4, super.readFile2(TEST_INPUT));
        assertEquals(334, super.readFile2(INPUT));
    }
}