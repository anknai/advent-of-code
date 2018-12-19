package org.ankur.advent2017.test;

import org.ankur.advent2017.D02CorruptionChecksum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D02CorruptionChecksumTest {

    private D02CorruptionChecksum corruptionChecksum = new D02CorruptionChecksum();

    private final static String INPUT = "2017_02_spreadsheet.txt";

    @Test
    public void checksum() {
        int checksum = corruptionChecksum.checksum(INPUT);
        assertEquals("The checksum is ", 30994, checksum);
    }

    @Test
    public void divisibleChecksum() {
        int checksum = corruptionChecksum.divisibleChecksum(INPUT);
        assertEquals("The divisible checksum is ", 233, checksum);
    }
}