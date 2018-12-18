package nl.ing.advent2018.test;

import nl.ing.advent2018.D15BeverageBandits;
import org.junit.Test;

import static org.junit.Assert.*;

public class D15BeverageBanditsTest {

    private D15BeverageBandits bandits = new D15BeverageBandits();
    private static final String TEST_INPUT = "15_test_2.txt";

    @Test
    public void score() throws Exception {
        int score = bandits.score(TEST_INPUT);
        assertEquals("Score is ", 27730, score);
    }

}