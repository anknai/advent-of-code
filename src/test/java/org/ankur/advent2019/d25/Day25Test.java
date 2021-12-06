package org.ankur.advent2019.d25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test extends Day25 {

    private final static String INPUT = "archive/2019_25.txt";

    @Test
    void testPart1() {
        assertEquals(80, super.part1(INPUT));
    }

    @Test
    void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}