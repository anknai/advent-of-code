package org.ankur.advent2019.d20;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20Test extends Day20 {

    private final static String INPUT = "2019_20.txt";

    @Test
    public void testPart1() {
        assertEquals(80, super.part1(INPUT));
    }

    @Test
    public void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}