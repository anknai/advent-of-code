package org.ankur.advent2018.test;

import org.ankur.advent2018.D11ChronalCharge;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D11ChronalChargeTest {

    private D11ChronalCharge chronalCharge = new D11ChronalCharge();

    @Test
    public void max() {
        String max = chronalCharge.max(18, 3);
        assertEquals("Max is ", "33,45", max);

        max = chronalCharge.max(7803, 3);
        assertEquals("Max is ", "20,51", max);

        max = chronalCharge.max(18, 16);
        assertEquals("Max is ", "90,269", max);

        max = chronalCharge.max(42, 12);
        assertEquals("Max is ", "232,251", max);
    }

    @Test
    public void fuelCharge() {
        int fuel = chronalCharge.fuelCharge(3, 5, 8);
        assertEquals("Fuel level is ", 4, fuel);
        fuel = chronalCharge.fuelCharge(122, 79, 57);
        assertEquals("Fuel level is ", -5, fuel);
        fuel = chronalCharge.fuelCharge(217, 196, 39);
        assertEquals("Fuel level is ", 0, fuel);
        fuel = chronalCharge.fuelCharge(101, 153, 71);
        assertEquals("Fuel level is ", 4, fuel);
    }

    @Test
    public void maxOfAnySize() {
        String max = chronalCharge.maxOfAnySize(18);
        assertEquals("Max is ", "90,269,16", max);

        max = chronalCharge.maxOfAnySize(7803);
        assertEquals("Max is ", "230,272,17", max);
    }
}