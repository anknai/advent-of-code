package org.ankur.advent2021.d13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransparentOrigamiTest extends TransparentOrigami {

    private final static String TEST_INPUT = "2021_13_01.txt";
    private final static String MAIN_INPUT = "2021_13_02.txt";

    @Test
    void testDots() {
        assertEquals(17, super.dots(TEST_INPUT));
        assertEquals(682, super.dots(MAIN_INPUT));
    }

    @Test
    void testDotsContinue() {
        super.dotsContinue(TEST_INPUT);
        super.dotsContinue(MAIN_INPUT);
    }
}