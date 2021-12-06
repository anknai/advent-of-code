package org.ankur.advent2019.d01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RocketEquationTest {

    private final static String TEST_INPUT = "archive/19_01_mass.txt";

    private final RocketEquation equation = new RocketEquation();

    @Test
    void calculateFrequency() {
        System.out.println(equation.calculateFrequency(TEST_INPUT));
    }

    @Test
    void calculateFuelPart2() {
        System.out.println(equation.recursiveFuelCalculation(TEST_INPUT));
    }

    @Test
    void calculateFuel() {
        assertEquals(33583, equation.calculateFuel(100756));
        assertEquals(654, equation.calculateFuel(1969));
        assertEquals(2, equation.calculateFuel(14));
    }

    @Test
    void recursiveCalculateFuel() {
        assertEquals(50346, equation.recursiveCalculation(100756));
        assertEquals(966, equation.recursiveCalculation(1969));
        assertEquals(2, equation.recursiveCalculation(14));
    }
}