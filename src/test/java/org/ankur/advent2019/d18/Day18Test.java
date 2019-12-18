package org.ankur.advent2019.d18;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day18Test extends Day18 {

    private final static String INPUT = "2019_18.txt";

    @Test
    public void testPart1() {
        //assertEquals(8, super.part1("2019_18_01.txt"));
        //assertEquals(86, super.part1("2019_18_02.txt"));
        //assertEquals(132, super.part1("2019_18_03.txt"));
        //assertEquals(136, super.part1("2019_18_04.txt"));
        //assertEquals(81, super.part1("2019_18_05.txt"));
        assertEquals(136, super.part1(INPUT));
    }

    @Test
    public void testPart2() {
        assertEquals(80, super.part2(INPUT));
    }
}