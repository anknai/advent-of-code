package nl.ing.advent2018.test;

import nl.ing.advent2018.D17ReservoirResearch;
import nl.ing.advent2018.domain.Soil;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import static org.junit.Assert.*;

public class D17ReservoirResearchTest {

    private D17ReservoirResearch research = new D17ReservoirResearch();

    private static final String TEST_INPUT = "17_soil_test.txt";
    private static final String INPUT = "17_soil.txt";

    @Test
    public void capacity() {
        int capacity = research.capacity(TEST_INPUT);
        //capacity = research.capacity(INPUT);
        assertEquals("Reservoir capacity is ", 67, capacity);
    }

    @Test
    public void parseSoil() {
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