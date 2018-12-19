package org.ankur.advent2018.test;

import org.ankur.advent2018.D05AlchemicalReduction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D05AlchemicalReductionTest {

    private D05AlchemicalReduction reduction = new D05AlchemicalReduction();

    private final static String TEST_INPUT = "05_polymer_test.txt";
    private final static String INPUT = "05_polymer.txt";

    @Test
    public void polymerUnits() {
        int polymerUnits = reduction.polymerUnits(TEST_INPUT);
        assertEquals("Polymer units are ", 10, polymerUnits);
        polymerUnits = reduction.polymerUnits(INPUT);
        assertEquals("Polymer units are ", 11546, polymerUnits);
    }

    @Test
    public void improvePolymer() {
        int polymerUnits = reduction.improvePolymer(TEST_INPUT);
        assertEquals("Improved Polymer units are ", 4, polymerUnits);
        polymerUnits = reduction.improvePolymer(INPUT);
        assertEquals("Polymer units are ", 5124, polymerUnits);
    }
}