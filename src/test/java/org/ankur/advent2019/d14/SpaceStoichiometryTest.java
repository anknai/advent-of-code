package org.ankur.advent2019.d14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceStoichiometryTest extends SpaceStoichiometry {

    @Test
    void testOre() {
        assertEquals(31, super.ore("archive/2019_14_01.txt"));
        assertEquals(165, super.ore("archive/2019_14_02.txt"));
        assertEquals(579797, super.ore("archive/2019_14.txt"));
    }

    @Test
    void testOre2() {
        assertEquals(2521844, super.ore2("archive/2019_14.txt", 1_000_000_000_000L, 579797));
    }

    @Test
    void testOre3() {
        assertEquals(2521844, super.ore3("archive/2019_14.txt", 579797));
    }

}