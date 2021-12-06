package org.ankur.advent2019.d13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarePackageTest extends CarePackage {

    private final static String INPUT = "archive/2019_13.txt";

    @Test
    void blockTiles() {
        assertEquals(180L, super.blockTiles(INPUT));
    }

    @Test
    void score() {
        assertEquals(8777L, super.score(INPUT));
    }
}
