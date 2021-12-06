package org.ankur.advent2018.test;

import org.ankur.advent2018.D17ReservoirResearch;
import org.ankur.advent2018.domain.Soil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D17ReservoirResearchTest {

    private final D17ReservoirResearch research = new D17ReservoirResearch();

    private static final String TEST_INPUT = "17_soil_test.txt";
    private static final String INPUT = "17_soil.txt";

    @Test
    void capacity() {
        int capacity = research.capacity(TEST_INPUT, false);
        assertEquals(57, capacity);
        capacity = research.capacity(INPUT, false);
        assertEquals(41027, capacity);
        capacity = research.capacity(TEST_INPUT, true);
        assertEquals(29, capacity);
        capacity = research.capacity(INPUT, true);
        assertEquals(34214, capacity);
    }

    @Test
    void parseSoil() {
        Soil soil = research.parseSoil("x=495, y=2..7");
        assertEquals(495, soil.getStartX());
        assertEquals(495, soil.getEndX());
        assertEquals(2, soil.getStartY());
        assertEquals(7, soil.getEndY());
        soil = research.parseSoil("y=7, x=495..501");
        assertEquals(495, soil.getStartX());
        assertEquals(501, soil.getEndX());
        assertEquals(7, soil.getStartY());
        assertEquals(7, soil.getEndY());
    }
}