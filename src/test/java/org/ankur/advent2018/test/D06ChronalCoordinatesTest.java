package org.ankur.advent2018.test;

import org.ankur.advent2018.D06ChronalCoordinates;
import org.ankur.advent2018.domain.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D06ChronalCoordinatesTest {

    private D06ChronalCoordinates chronalCoordinates = new D06ChronalCoordinates();

    private final static String TEST_INPUT = "06_chronal_test.txt";

    private final static String INPUT = "06_chronal.txt";

    @Test
    public void largestManhattan() {
        int i = chronalCoordinates.largestManhattan(TEST_INPUT);
        assertEquals("Largest area is ", 17, i);

        i = chronalCoordinates.largestManhattan(INPUT);
        assertEquals("Largest area is ", 3358, i);
    }

    @Test
    public void closestRegion() {
        int i = chronalCoordinates.closestRegion(TEST_INPUT, 32);
        assertEquals("Closest area is ", 16, i);

        i = chronalCoordinates.closestRegion(INPUT, 10000);
        assertEquals("Closest area is ", 45909, i);
    }

    @Test
    public void parseCoordinate() {
        Coordinate coordinate = chronalCoordinates.parseString("247, 302");
        System.out.println(coordinate);
    }
}