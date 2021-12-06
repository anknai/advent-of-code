package org.ankur.advent2018.test;

import org.ankur.advent2018.D10StarsAlignment;
import org.ankur.advent2018.domain.Star;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D10StarsAlignmentTest {

    private D10StarsAlignment alignment = new D10StarsAlignment();

    private final static String TEST_INPUT = "10_aligned_test.txt";
    private final static String INPUT = "10_aligned.txt";

    @Test
    void message() {
        int seconds = alignment.message(TEST_INPUT, 25);
        assertEquals(3, seconds);
        seconds = alignment.message(INPUT, 100);
        assertEquals(10639, seconds);
    }

    @Test
    void star() {
        Star star = alignment.parseStar("position=< 3, -2> velocity=<-1,  1>");
        assertEquals(3, star.getX());
        assertEquals(-2, star.getY());
        assertEquals(-1, star.getXVelocity());
        assertEquals(1, star.getYVelocity());
        //position=<-52997, -31743> velocity=< 5,  3>
        star = alignment.parseStar("position=<-52997, -31743> velocity=< 5,  3>");
        assertEquals(-52997, star.getX());
        assertEquals(-31743, star.getY());
        assertEquals(5, star.getXVelocity());
        assertEquals(3, star.getYVelocity());
    }
}
