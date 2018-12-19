package org.ankur.advent2018.test;

import org.ankur.advent2018.D07SumOfParts;
import org.ankur.advent2018.domain.Step;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D07SumOfPartsTest {

    private D07SumOfParts sumOfParts = new D07SumOfParts();

    private final static String TEST_INPUT = "07_step_test.txt";

    private final static String INPUT = "07_steps.txt";

    @Test
    public void sequence() {
        String sequence = sumOfParts.sequence(TEST_INPUT);
        assertEquals("Sequence is ", "CABDFE", sequence);
        sequence = sumOfParts.sequence(INPUT);
        assertEquals("Sequence is ", "GKCNPTVHIRYDUJMSXFBQLOAEWZ", sequence);
    }

    @Test
    public void timeTaken() {
        int timeTaken = sumOfParts.timeTaken(TEST_INPUT, 2, 0);
        assertEquals("Time taken is ", 15, timeTaken);

        timeTaken = sumOfParts.timeTaken(INPUT, 5, 60);
        assertEquals("Time taken is ", 1265, timeTaken);
    }

    @Test
    public void parse() {
        Step step = sumOfParts.parseInstruction("Step C must be finished before step A can begin.");
        assertEquals("Step is ", "A\tC ", step.toString());
    }
}