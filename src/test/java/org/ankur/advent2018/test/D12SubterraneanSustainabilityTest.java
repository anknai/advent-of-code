package org.ankur.advent2018.test;

import org.ankur.advent2018.D12SubterraneanSustainability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D12SubterraneanSustainabilityTest {

    private D12SubterraneanSustainability sustainability = new D12SubterraneanSustainability();

    private final static String TEST_INPUT = "12_plants_test.txt";
    private final static String INPUT = "12_plants.txt";

    @Test
    public void potsWithPlants() {
        int potsWithPlants = sustainability.potsWithPlants(TEST_INPUT, 20);
        assertEquals("Pots with plants ", 325, potsWithPlants);

        potsWithPlants = sustainability.potsWithPlants(INPUT, 20);
        assertEquals("Pots with plants ", 3793, potsWithPlants);
    }

    @Test
    public void potsAfterGenerations() {
        long generations = 50_000_000_000L;
        long hugeNumber = sustainability.potsAfterGenerations(INPUT, generations);
        assertEquals("Pots with plants ", 4_300_000_002_414L, hugeNumber);

        hugeNumber = sustainability.potsAfterGenerations(TEST_INPUT, generations);
        assertEquals("Pots with plants ", 999_999_999_374L, hugeNumber);
    }
}
