package org.ankur.advent2019.d19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test extends Day19 {

    private final static String INPUT = "archive/2019_19.txt";

    @Test
    void testPart1() {
        assertEquals(80, super.part1(INPUT));
    }

    @Test
    void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}