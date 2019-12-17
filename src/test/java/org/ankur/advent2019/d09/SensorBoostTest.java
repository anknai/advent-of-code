package org.ankur.advent2019.d09;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SensorBoostTest extends SensorBoost {

    private final static String INPUT = "2019_09.txt";

    @Test
    public void keycode() {
        assertEquals(2204990589L, super.keycode(INPUT, 1));
    }

    @Test
    public void keycode2() {
        assertEquals(50008, super.keycode(INPUT, 2));
    }

    @Test
    public void run() {
        assertEquals(109, super.run("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99", 1));
        long l = super.run("1102,34915192,34915192,7,4,7,99,0", 1);
        assertEquals(16, String.valueOf(l).length());
        assertEquals(1125899906842624L, super.run("104,1125899906842624,99", 1));
    }
}