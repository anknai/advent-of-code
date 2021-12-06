package org.ankur.advent2019.d24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test extends Day24 {

    private final static String INPUT = "archive/2019_24.txt";

    @Test
    void testPart1() {
        assertEquals(80, super.part1(INPUT));
    }

    @Test
    void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}