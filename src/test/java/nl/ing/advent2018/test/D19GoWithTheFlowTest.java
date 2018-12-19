package nl.ing.advent2018.test;

import nl.ing.advent2018.D19GoWithTheFlow;
import org.junit.Test;

import static org.junit.Assert.*;

public class D19GoWithTheFlowTest {

    private D19GoWithTheFlow flow = new D19GoWithTheFlow();

    private static final String TEST_INPUT = "19_test.txt";
    private static final String INPUT = "19_instructions.txt";

    @Test
    public void part1() {
        int i = flow.part1(TEST_INPUT, false);
        assertEquals("Register 0 has ", 6, i);
        i = flow.part1(INPUT, false);
        assertEquals("Register 0 has ", 2223, i);
    }
}