package nl.ing.advent2018.test;

import nl.ing.advent2018.D08MemoryManeuver;
import org.junit.Test;

import static org.junit.Assert.*;

public class D08MemoryManeuverTest {

    private D08MemoryManeuver maneuver = new D08MemoryManeuver();

    private final static String TEST_INPUT = "08_test_metadata.txt";
    private final static String INPUT = "08_metadata.txt";

    @Test
    public void getMetadataCount() {
        int metadataCount = maneuver.getMetadataCount(TEST_INPUT);
        assertEquals("Count is ", 138, metadataCount);

        metadataCount = maneuver.getMetadataCount(INPUT);
        assertEquals("Count is ", 47244, metadataCount);
    }

    @Test
    public void rootValue() {
        int rootValue = maneuver.rootValue(TEST_INPUT);
        assertEquals("Value is ", 66, rootValue);

        rootValue = maneuver.rootValue(INPUT);
        assertEquals("Value is ", 17267, rootValue);
    }
}