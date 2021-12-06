package org.ankur.advent2018.test;

import org.ankur.advent2018.D05AlchemicalReduction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D05AlchemicalReductionTest {

    private D05AlchemicalReduction reduction = new D05AlchemicalReduction();

    private final static String TEST_INPUT = "05_polymer_test.txt";
    private final static String INPUT = "05_polymer.txt";

    @Test
    void polymerUnits() {
        int polymerUnits = reduction.polymerUnits(TEST_INPUT);
        assertEquals(10, polymerUnits);
        polymerUnits = reduction.polymerUnits(INPUT);
        assertEquals(11546, polymerUnits);
    }

    @Test
    void improvePolymer() {
        int polymerUnits = reduction.improvePolymer(TEST_INPUT);
        assertEquals(4, polymerUnits);
        polymerUnits = reduction.improvePolymer(INPUT);
        assertEquals(5124, polymerUnits);
    }
}