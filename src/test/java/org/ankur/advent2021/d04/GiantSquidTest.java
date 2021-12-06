package org.ankur.advent2021.d04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GiantSquidTest extends GiantSquid {

    private final static String TEST_INPUT = "2021_04_01.txt";
    private final static String MAIN_INPUT = "2021_04_02.txt";

    @Test
    void bingo() {
        assertEquals(4512, super.bingo(TEST_INPUT));
        assertEquals(50008, super.bingo(MAIN_INPUT));
    }

    @Test
    void bingoLoser() {
        assertEquals(1924, super.bingoLoser(TEST_INPUT));
        assertEquals(17408, super.bingoLoser(MAIN_INPUT));
    }
}