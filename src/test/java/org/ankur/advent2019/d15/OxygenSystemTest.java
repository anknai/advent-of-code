package org.ankur.advent2019.d15;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OxygenSystemTest extends OxygenSystem {

    private final static String INPUT = "2019_15.txt";
    private final static String INPUT_2 = "2019_15_02.txt";


    @Test
    public void distance() {
        assertEquals(216, super.distance(INPUT));
        assertEquals(240, super.distance(INPUT_2));
    }

    @Test
    public void timeTaken() {
        assertEquals(326, super.timeTaken(INPUT));
        assertEquals(322, super.timeTaken(INPUT_2));
    }
}