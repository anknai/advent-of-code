package org.ankur.advent2021.d07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreacheryOfWhaleTest extends TreacheryOfWhale {

    private final static String TEST_INPUT = "2021_07_01.txt";
    private final static String MAIN_INPUT = "2021_07_02.txt";

    @Test
    void testShortest() {
        assertEquals(37, shortest(TEST_INPUT));
        assertEquals(337833, shortest(MAIN_INPUT));
    }

    @Test
    void testShortestExp() {
        assertEquals(168, shortestExp(TEST_INPUT));
        assertEquals(96678050, shortestExp(MAIN_INPUT));
    }
}
