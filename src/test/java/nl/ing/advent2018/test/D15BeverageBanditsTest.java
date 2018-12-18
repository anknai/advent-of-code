package nl.ing.advent2018.test;

import nl.ing.advent2018.D15BeverageBandits;
import org.junit.Test;

import static org.junit.Assert.*;

public class D15BeverageBanditsTest {

    private D15BeverageBandits bandits = new D15BeverageBandits();
    private static final String TEST_INPUT_2 = "15_test_2.txt";
    private static final String TEST_INPUT = "15_test.txt";
    private static final String INPUT = "15_battle.txt";

    @Test
    public void score() {
        int score = bandits.score(TEST_INPUT);
        assertEquals("Score is ", 27730, score);
        score = bandits.score(TEST_INPUT_2);
        assertEquals("Score is ", 27828, score);
        score = bandits.score("15_test_3.txt");
        assertEquals("Score is ", 36334, score);
        score = bandits.score("15_test_4.txt");
        assertEquals("Score is ", 39514, score);
        score = bandits.score("15_test_5.txt");
        assertEquals("Score is ", 27755, score);
        score = bandits.score("15_test_6.txt");
        assertEquals("Score is ", 28944, score);
        score = bandits.score("15_test_7.txt");
        assertEquals("Score is ", 18740, score);
        score = bandits.score(INPUT);
        assertEquals("Score is ", 218272, score);
    }

}