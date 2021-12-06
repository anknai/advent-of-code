package org.ankur.advent2017.test;

import org.ankur.advent2017.D02CorruptionChecksum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D02CorruptionChecksumTest {

    private final D02CorruptionChecksum corruptionChecksum = new D02CorruptionChecksum();

    private final static String INPUT = "2017_02_spreadsheet.txt";

    @Test
    void checksum() {
        int checksum = corruptionChecksum.checksum(INPUT);
        assertEquals(30994, checksum);
    }

    @Test
    void divisibleChecksum() {
        int checksum = corruptionChecksum.divisibleChecksum(INPUT);
        assertEquals(233, checksum);
    }
}