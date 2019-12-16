package org.ankur.advent2019.d16;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Test extends Day16 {

    private final static String INPUT = "2019_16.txt";

    @Test
    public void testPart1() {
        assertEquals("01029498", super.init("12345678", 4));
        assertEquals("24176176", super.init("80871224585914546619083218645595", 100));
        assertEquals("73745418", super.init("19617804207202209144916044189917", 100));
        assertEquals("52432133", super.init("69317163492948606335995924319873", 100));
        assertEquals("42205986", super.part1(INPUT, 100));
    }

    @Test
    public void testPart2() {
        assertEquals("84462026", super.init2("03036732577212944063491565474664", 100));
        //assertEquals("78725270", super.init2("02935109699940807407585447034323", 100));
        //assertEquals("53553731", super.init2("03081770884921959731165446850517", 100));
        //assertEquals("120", super.part2(INPUT, 100));
    }
}