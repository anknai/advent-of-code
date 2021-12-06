package org.ankur.advent2021.d02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiveTest {

    private final static String TEST_INPUT = "2021_02_01.txt";
    private final static String MAIN_INPUT = "2021_02_02.txt";
    private final Dive dive = new Dive();

    @Test
    void dive() {
        assertEquals(150, dive.dive(TEST_INPUT));
        assertEquals(1728414, dive.dive(MAIN_INPUT));
    }

    @Test
    void diveWithAim() {
        assertEquals(900, dive.diveWithAim(TEST_INPUT));
        assertEquals(1765720035, dive.diveWithAim(MAIN_INPUT));
    }
}