package org.ankur.advent2021.d12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassagePathingTest extends PassagePathing {

    private final static String TEST_INPUT = "2021_12_01.txt";
    private final static String MAIN_INPUT = "2021_12_02.txt";

    @Test
    void testPath() {
        assertEquals(10, super.paths(TEST_INPUT));
        assertEquals(3679, super.paths(MAIN_INPUT));
    }

    @Test
    void testPath2() {
        assertEquals(36, super.paths2(TEST_INPUT));
        assertEquals(107395, super.paths2(MAIN_INPUT));
    }
}