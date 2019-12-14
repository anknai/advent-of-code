package org.ankur.advent2019.d14;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpaceStoichiometryTest extends SpaceStoichiometry {

    @Test
    public void testOre() {
        assertEquals(31, super.ore("2019_14_01.txt"));
        assertEquals(165, super.ore("2019_14_02.txt"));
        assertEquals(579797, super.ore("2019_14.txt"));
    }

    @Test
    public void testOre2() {
        assertEquals(2521844, super.ore2("2019_14.txt", 1_000_000_000_000L, 579797));
    }

    @Test
    public void testOre3() {
        assertEquals(2521844, super.ore3("2019_14.txt", 579797));
    }

}