package org.ankur.advent2019.d17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetAndForgetTest extends SetAndForget {

    private final static String INPUT = "archive/2019_17.txt";

    @Test
    void testPart1() {
        assertEquals(7328, super.part1(INPUT));
    }

    @Test
    void testPart2() {
        assertEquals(1289413, super.part2(INPUT));
    }
}