package org.ankur.advent2019.d11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpacePoliceTest extends SpacePolice {

    private final static String INPUT = "archive/2019_11.txt";


    @Test
    void count() {
        assertEquals(2212L, super.count(INPUT));
    }

    @Test
    void registration() {
       super.registration(INPUT);
    }
}