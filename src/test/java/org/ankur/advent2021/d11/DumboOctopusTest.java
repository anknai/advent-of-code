package org.ankur.advent2021.d11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DumboOctopusTest extends DumboOctopus {

    private final static String TEST_INPUT = "2021_11_01.txt";
    private final static String MAIN_INPUT = "2021_11_02.txt";

    @Test
    void testFlash() {
        assertEquals(1656, super.flashes(TEST_INPUT, 100));
        assertEquals(1603, super.flashes(MAIN_INPUT, 100));
    }

    @Test
    void testFlashAll() {
        assertEquals(195, super.flashAll(TEST_INPUT));
        assertEquals(222, super.flashAll(MAIN_INPUT));
    }

}