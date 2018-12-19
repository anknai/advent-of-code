package org.ankur.advent2018.test;

import org.ankur.advent2018.D10StarsAlignment;
import org.ankur.advent2018.domain.Star;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D10StarsAlignmentTest {

    private D10StarsAlignment alignment = new D10StarsAlignment();

    private final static String TEST_INPUT = "10_aligned_test.txt";
    private final static String INPUT = "10_aligned.txt";

    @Test
    public void message() {
        int seconds = alignment.message(TEST_INPUT, 25);
        assertEquals("It took", 3, seconds);
        seconds = alignment.message(INPUT, 100);
        assertEquals("It took", 10639, seconds);
    }

    @Test
    public void star() {
        Star star = alignment.parseStar("position=< 3, -2> velocity=<-1,  1>");
        assertEquals("Star x is ", 3, star.getX());
        assertEquals("Star y is ", -2, star.getY());
        assertEquals("Star x-velocity is ", -1, star.getXVelocity());
        assertEquals("Star y-velocity is ", 1, star.getYVelocity());
        //position=<-52997, -31743> velocity=< 5,  3>
        star = alignment.parseStar("position=<-52997, -31743> velocity=< 5,  3>");
        assertEquals("Star x is ", -52997, star.getX());
        assertEquals("Star y is ", -31743, star.getY());
        assertEquals("Star x-velocity is ", 5, star.getXVelocity());
        assertEquals("Star y-velocity is ", 3, star.getYVelocity());
    }
}
