package org.ankur.advent2018.test;

import org.ankur.advent2018.D21ChronalConversion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D21ChronalConversionTest {

    private D21ChronalConversion conversion = new D21ChronalConversion();

    private static final String INPUT = "21_input.txt";

    @Test
    public void part1() {
        int i = conversion.part1(INPUT, 9_547_924,10_000);
        assertEquals("Register 0 has ", 6_619_857, i);
    }

    @Test
    public void part2() {
        int i = conversion.part2(INPUT, 6_619_858,1148, 3000);
        assertEquals("Register 0 has ", 9_547_924, i);
    }
}
