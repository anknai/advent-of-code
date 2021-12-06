package org.ankur.advent2019.d23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test extends Day23 {

    private final static String INPUT = "archive/2019_23.txt";

    @Test
    void testPart1() {
        assertEquals(80, super.part1(INPUT));
    }

    @Test
    void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}