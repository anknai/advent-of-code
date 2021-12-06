package org.ankur.advent2018.test;

import org.ankur.advent2018.D12SubterraneanSustainability;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D12SubterraneanSustainabilityTest {

    private D12SubterraneanSustainability sustainability = new D12SubterraneanSustainability();

    private final static String TEST_INPUT = "12_plants_test.txt";
    private final static String INPUT = "12_plants.txt";

    @Test
    void potsWithPlants() {
        int potsWithPlants = sustainability.potsWithPlants(TEST_INPUT, 20);
        assertEquals(325, potsWithPlants);

        potsWithPlants = sustainability.potsWithPlants(INPUT, 20);
        assertEquals(3793, potsWithPlants);
    }

    @Test
    void potsAfterGenerations() {
        long generations = 50_000_000_000L;
        long hugeNumber = sustainability.potsAfterGenerations(INPUT, generations);
        assertEquals(4_300_000_002_414L, hugeNumber);

        hugeNumber = sustainability.potsAfterGenerations(TEST_INPUT, generations);
        assertEquals(999_999_999_374L, hugeNumber);
    }
}
