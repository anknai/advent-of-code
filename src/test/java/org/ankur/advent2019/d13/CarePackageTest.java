package org.ankur.advent2019.d13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarePackageTest extends CarePackage {

    private final static String INPUT = "2019_13.txt";

    @Test
    public void blockTiles() {
        assertEquals(180L, super.blockTiles(INPUT));
    }

    @Test
    public void score() {
        assertEquals(8777L, super.score(INPUT));
    }
}
