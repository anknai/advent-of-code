package org.ankur.advent2018.test;

import org.ankur.advent2018.D15BeverageBandits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D15BeverageBanditsTest {

    private D15BeverageBandits bandits = new D15BeverageBandits();
    private static final String TEST_INPUT_2 = "15_test_2.txt";
    private static final String TEST_INPUT = "15_test.txt";
    private static final String INPUT = "15_battle.txt";

    @Test
    void score() {
        int score = bandits.score(TEST_INPUT, 3, false);
        assertEquals(27730, score);
        score = bandits.score(TEST_INPUT_2, 3, false);
        assertEquals(27828, score);
        score = bandits.score("15_test_3.txt", 3, false);
        assertEquals(36334, score);
        score = bandits.score("15_test_4.txt", 3, false);
        assertEquals(39514, score);
        score = bandits.score("15_test_5.txt", 3, false);
        assertEquals(27755, score);
        score = bandits.score("15_test_6.txt", 3, false);
        assertEquals(28944, score);
        score = bandits.score("15_test_7.txt", 3, false);
        assertEquals(18740, score);
        score = bandits.score(INPUT, 3, false);
        assertEquals(218272, score);
        score = bandits.score("15_battle_2.txt", 3, false);
        assertEquals(198744, score);
    }

    @Test
    void elfWins() {
        int score = bandits.letTheElvesWin(INPUT);
        assertEquals(40861, score);
        score = bandits.letTheElvesWin("15_battle_2.txt");
        assertEquals(66510, score);
    }

}