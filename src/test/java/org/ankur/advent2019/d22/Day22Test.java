package org.ankur.advent2019.d22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test extends Day22 {

    private final static String INPUT = "archive/2019_22.txt";

    @Test
    void testPart1() {
        assertEquals(80, super.part1(INPUT));
    }

    @Test
    void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}