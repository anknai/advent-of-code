package org.ankur.advent2019.d17;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetAndForgetTest extends SetAndForget {

    private final static String INPUT = "2019_17.txt";

    @Test
    public void testPart1() {
        assertEquals(7328, super.part1(INPUT));
    }

    @Test
    public void testPart2() {
        assertEquals(1289413, super.part2(INPUT));
    }
}