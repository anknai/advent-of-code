package org.ankur.advent2019.d11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpacePoliceTest extends SpacePolice {

    private final static String INPUT = "2019_11.txt";


    @Test
    public void count() {
        assertEquals(2212L, super.count(INPUT));
    }

    @Test
    public void registration() {
       super.registration(INPUT);
    }
}