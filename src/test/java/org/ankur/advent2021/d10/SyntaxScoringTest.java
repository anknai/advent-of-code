package org.ankur.advent2021.d10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SyntaxScoringTest extends SyntaxScoring {

    private final static String TEST_INPUT = "2021_10_01.txt";
    private final static String MAIN_INPUT = "2021_10_02.txt";

    @Test
    void testScore() {
        assertEquals(26397, super.score(TEST_INPUT));
        assertEquals(415953, super.score(MAIN_INPUT));
    }

    @Test
    void testFix() {
        assertEquals(288957L, super.fix(TEST_INPUT));
        assertEquals(2292863731L, super.fix(MAIN_INPUT));
    }
}