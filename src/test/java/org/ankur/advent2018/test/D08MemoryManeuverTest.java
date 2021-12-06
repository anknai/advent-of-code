package org.ankur.advent2018.test;

import org.ankur.advent2018.D08MemoryManeuver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D08MemoryManeuverTest {

    private D08MemoryManeuver maneuver = new D08MemoryManeuver();

    private final static String TEST_INPUT = "08_test_metadata.txt";
    private final static String INPUT = "08_metadata.txt";

    @Test
    void getMetadataCount() {
        int metadataCount = maneuver.getMetadataCount(TEST_INPUT);
        assertEquals(138, metadataCount);

        metadataCount = maneuver.getMetadataCount(INPUT);
        assertEquals(47244, metadataCount);
    }

    @Test
    void rootValue() {
        int rootValue = maneuver.rootValue(TEST_INPUT);
        assertEquals(66, rootValue);

        rootValue = maneuver.rootValue(INPUT);
        assertEquals(17267, rootValue);
    }
}