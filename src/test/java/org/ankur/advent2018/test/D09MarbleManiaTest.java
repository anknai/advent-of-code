package org.ankur.advent2018.test;

import org.ankur.advent2018.D09MarbleMania;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D09MarbleManiaTest {

    private D09MarbleMania marbleMania = new D09MarbleMania();

    @Test
    void highScore() {
        long i = marbleMania.highScore(9, 25);
        assertEquals(32, i);

        i = marbleMania.highScore(10, 1618);
        assertEquals(8317, i);

        i = marbleMania.highScore(13, 7999);
        assertEquals(146373, i);

        i = marbleMania.highScore(17, 1104);
        assertEquals(2764, i);

        i = marbleMania.highScore(21, 6111);
        assertEquals(54718, i);

        i = marbleMania.highScore(30, 5807);
        assertEquals(37305, i);

        i = marbleMania.highScore(432, 71019);
        assertEquals(400493, i);

        i = marbleMania.highScore(432, 7101900);
        assertEquals(3_338_341_690L, i);
    }
}