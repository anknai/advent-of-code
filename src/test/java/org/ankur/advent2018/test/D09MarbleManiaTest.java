package org.ankur.advent2018.test;

import org.ankur.advent2018.D09MarbleMania;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D09MarbleManiaTest {

    private D09MarbleMania marbleMania = new D09MarbleMania();

    @Test
    public void highScore() {
        long i = marbleMania.highScore(9, 25);
        assertEquals("High score is ", 32, i);

        i = marbleMania.highScore(10, 1618);
        assertEquals("High score is ", 8317, i);

        i = marbleMania.highScore(13, 7999);
        assertEquals("High score is ", 146373, i);

        i = marbleMania.highScore(17, 1104);
        assertEquals("High score is ", 2764, i);

        i = marbleMania.highScore(21, 6111);
        assertEquals("High score is ", 54718, i);

        i = marbleMania.highScore(30, 5807);
        assertEquals("High score is ", 37305, i);

        i = marbleMania.highScore(432, 71019);
        assertEquals("High score is ", 400493, i);

        i = marbleMania.highScore(432, 7101900);
        assertEquals("High score is ", 3_338_341_690L, i);
    }
}