package org.ankur.advent2019.d15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OxygenSystemTest extends OxygenSystem {

    private final static String INPUT = "archive/2019_15.txt";
    private final static String INPUT_2 = "archive/2019_15_02.txt";


    @Test
    void distance() {
        assertEquals(216, super.distance(INPUT));
        assertEquals(240, super.distance(INPUT_2));
    }

    @Test
    void timeTaken() {
        assertEquals(326, super.timeTaken(INPUT));
        assertEquals(322, super.timeTaken(INPUT_2));
    }
}