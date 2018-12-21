package org.ankur.advent2018.test;

import org.ankur.advent2018.D21ChronalConversion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D21ChronalConversionTest {

    private D21ChronalConversion conversion = new D21ChronalConversion();

    private static final String INPUT = "21_input.txt";

    @Test
    public void better() {
        /*List<String> lines = FileReader.readFile(INPUT);
        String line9 = lines.get(8);
        int magicNumber = Integer.parseInt(line9.split(" ")[1]);*/
        int i = conversion.findHalt(INPUT, true);
        assertEquals("Register 0 has ", 6_619_857, i);
        i = conversion.findHalt(INPUT, false);
        assertEquals("Register 0 has ", 9_547_924, i);
    }
}
