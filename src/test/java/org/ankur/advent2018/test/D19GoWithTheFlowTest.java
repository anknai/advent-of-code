package org.ankur.advent2018.test;

import org.ankur.advent2018.D19GoWithTheFlow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D19GoWithTheFlowTest {

    private D19GoWithTheFlow flow = new D19GoWithTheFlow();

    private static final String TEST_INPUT = "19_test.txt";
    private static final String INPUT = "19_instructions.txt";

    @Test
    public void part1() {
        int i = flow.part1(TEST_INPUT, false, 100_000);
        assertEquals("Register 0 has ", 6, i);
        i = flow.part1(INPUT, false,10_000_000);
        assertEquals("Register 0 has ", 2223, i);
    }

    @Test
    public void part2() {
        flow.part1(INPUT, true,1_000_000_000);
        //Look for the pattern when register 0's value is changing.
        //10551282 = 1 * 2 * 3 * 7 * 251221
        //[1, 0, 0, 16, 0, 0]
        //[0, 0, 10551282, 34, 10550400, 0]
        //[3, 2, 10551282, 7, 1, 5275641] 1 + 2
        //[6, 3, 10551282, 7, 1, 3517094] 3 + 3
        //[12, 6, 10551282, 7, 1, 1758547] 6 + 2*3
        //[19, 7, 10551282, 7, 1, 1507326] 12 + 7
        //[33, 14, 10551282, 7, 1, 753663] 19 + 7*2
        //[54, 21, 10551282, 7, 1, 502442] 33 + 7*3
        //[96, 42, 10551282, 7, 1, 251221] 54 + 7*3*2
        //[251317, 251221, 10551282, 7, 1, 1] 96 + 251221
        //251317 + 251221*2 = 753759
        //753759 + 251221*3 = 1507422
        //1507422 + 251221*6 = 3014748
        //3014748 + 251221*7 = 4773295
        //4773295 + 251221*14 = 8290389
        //8290389 + 251221*21 = 13566030
        //13566030 + 251221*42 = 24117312

    }
}