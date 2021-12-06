package org.ankur.advent2018.test;

import org.ankur.advent2018.D06ChronalCoordinates;
import org.ankur.advent2018.domain.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D06ChronalCoordinatesTest {

    private D06ChronalCoordinates chronalCoordinates = new D06ChronalCoordinates();

    private final static String TEST_INPUT = "06_chronal_test.txt";

    private final static String INPUT = "06_chronal.txt";

    @Test
    void largestManhattan() {
        int i = chronalCoordinates.largestManhattan(TEST_INPUT);
        assertEquals(17, i);

        i = chronalCoordinates.largestManhattan(INPUT);
        assertEquals(3358, i);
    }

    @Test
    void closestRegion() {
        int i = chronalCoordinates.closestRegion(TEST_INPUT, 32);
        assertEquals(16, i);

        i = chronalCoordinates.closestRegion(INPUT, 10000);
        assertEquals(45909, i);
    }

    @Test
    void parseCoordinate() {
        Coordinate coordinate = chronalCoordinates.parseString("247, 302");
        System.out.println(coordinate);
    }
}