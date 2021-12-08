package org.ankur.advent2021.d08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SevenSegmentSearchTest extends SevenSegmentSearch {

    private final static String TEST_INPUT = "2021_08_01.txt";
    private final static String MAIN_INPUT = "2021_08_02.txt";

    @Test
    void testCount() {
        assertEquals(26, super.count(TEST_INPUT));
        assertEquals(521, super.count(MAIN_INPUT));
    }

    @Test
    void testNumber() {
        assertEquals(5353, super.number("2021_08_03.txt"));
        assertEquals(61229, super.number(TEST_INPUT));
        assertEquals(1016804, super.number(MAIN_INPUT));
    }
}
