package org.ankur.advent2019.d11;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpacePoliceTest extends SpacePolice {

    private final static String INPUT = "2019_11.txt";


    @Test
    public void alarm() {
        assertEquals(2204990589L, super.alarm(INPUT));
    }
}