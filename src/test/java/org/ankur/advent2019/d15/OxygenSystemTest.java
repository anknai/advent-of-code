package org.ankur.advent2019.d15;

import org.junit.Test;

import static org.junit.Assert.*;

public class OxygenSystemTest extends OxygenSystem {

    private final static String INPUT = "2019_15.txt";


    @Test
    public void alarm() {
        assertEquals(216, super.alarm(INPUT));
    }

    @Test
    public void alarm2() {
        assertEquals(326, super.alarm2(INPUT));
    }
}