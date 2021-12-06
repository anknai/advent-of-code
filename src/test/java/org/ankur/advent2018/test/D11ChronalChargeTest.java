package org.ankur.advent2018.test;

import org.ankur.advent2018.D11ChronalCharge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D11ChronalChargeTest {

    private D11ChronalCharge chronalCharge = new D11ChronalCharge();

    @Test
    void max() {
        String max = chronalCharge.max(18, 3);
        assertEquals( "33,45", max);

        max = chronalCharge.max(7803, 3);
        assertEquals( "20,51", max);

        max = chronalCharge.max(18, 16);
        assertEquals( "90,269", max);

        max = chronalCharge.max(42, 12);
        assertEquals( "232,251", max);
    }

    @Test
    void fuelCharge() {
        int fuel = chronalCharge.fuelCharge(3, 5, 8);
        assertEquals(4, fuel);
        fuel = chronalCharge.fuelCharge(122, 79, 57);
        assertEquals(-5, fuel);
        fuel = chronalCharge.fuelCharge(217, 196, 39);
        assertEquals(0, fuel);
        fuel = chronalCharge.fuelCharge(101, 153, 71);
        assertEquals(4, fuel);
    }

    @Test
    void maxOfAnySize() {
        String max = chronalCharge.maxOfAnySize(18);
        assertEquals( "90,269,16", max);

        max = chronalCharge.maxOfAnySize(7803);
        assertEquals( "230,272,17", max);
    }
}