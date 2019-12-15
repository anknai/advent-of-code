package org.ankur.advent2019.d15;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OxygenSystemTest extends OxygenSystem {

    private final static String INPUT = "2019_15.txt";


    @Test
    public void distance() {
        assertEquals(216, super.distance(INPUT));
    }

    @Test
    public void timeTaken() {
        assertEquals(326, super.timeTaken(INPUT));
    }
}