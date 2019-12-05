package org.ankur.advent2019.d05;

import org.junit.Test;

import static org.junit.Assert.*;

public class AsteroidChanceTest extends AsteroidChance {

    private static final String INPUT = "2019_05.txt";

    @Test
    public void alarmString() {
        assertEquals(5, super.alarmString("3,0,4,0,99", 5));
        assertEquals(5, super.alarmString("1002,4,3,4,33", 5));
    }

    @Test
    public void alarmInput() {
        assertEquals(13978427, super.alarm(INPUT, 1));
        assertEquals(11189491, super.alarm(INPUT, 5));
    }

    @Test
    public void alarmString2() {
        assertEquals(1, super.alarmString("3,9,8,9,10,9,4,9,99,-1,8", 8));
        assertEquals(0, super.alarmString("3,9,8,9,10,9,4,9,99,-1,8", 7));
        assertEquals(1, super.alarmString("3,9,7,9,10,9,4,9,99,-1,8", 6));
        assertEquals(0, super.alarmString("3,9,7,9,10,9,4,9,99,-1,8", 10));
        assertEquals(1, super.alarmString("3,3,1108,-1,8,3,4,3,99", 8));
        assertEquals(0, super.alarmString("3,3,1108,-1,8,3,4,3,99", 6));
        assertEquals(999, super.alarmString("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 6));
    }

}