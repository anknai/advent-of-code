package org.ankur.advent2018.test;

import org.ankur.advent2018.D25FourDimensionalAdventure;
import org.junit.Test;

import static org.junit.Assert.*;

public class D25FourDimensionalAdventureTest {

    private D25FourDimensionalAdventure adventure = new D25FourDimensionalAdventure();

    private static final String TEST1 = "25_test_1.txt";
    private static final String INPUT = "25_space.txt";

    @Test
    public void constellations() throws Exception {
        int constellations = adventure.constellations(TEST1);
        assertEquals(2, constellations);
        constellations = adventure.constellations("25_test_2.txt");
        assertEquals(4, constellations);
        constellations = adventure.constellations("25_test_3.txt");
        assertEquals(3, constellations);
        constellations = adventure.constellations("25_test_4.txt");
        assertEquals(8, constellations);
        constellations = adventure.constellations(INPUT);
        assertEquals(318, constellations);
    }

}