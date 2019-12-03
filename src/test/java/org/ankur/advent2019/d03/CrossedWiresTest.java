package org.ankur.advent2019.d03;

import org.junit.Test;

import static org.junit.Assert.*;

public class CrossedWiresTest {

    private CrossedWires crossedWires = new CrossedWires();

    private final static String INPUT = "2019_03.txt";

    @Test
    public void readFile() {
        assertEquals(403, crossedWires.readFile(INPUT));
    }

    @Test
    public void readFileShortest() {
        assertEquals(8989, crossedWires.readFileShortest(INPUT));
    }

    @Test
    public void readString() {
        assertEquals(6, crossedWires.readString("R8,U5,L5,D3", "U7,R6,D4,L4"));
        assertEquals(159, crossedWires.readString("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"));
        assertEquals(135, crossedWires.readString("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
    }

    @Test
    public void readStringShortest() {
        assertEquals(30, crossedWires.readStringShortest("R8,U5,L5,D3", "U7,R6,D4,L4"));
        assertEquals(610, crossedWires.readStringShortest("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"));
        assertEquals(410, crossedWires.readStringShortest("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"));
    }
}