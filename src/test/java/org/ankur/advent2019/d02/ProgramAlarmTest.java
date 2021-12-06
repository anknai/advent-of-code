package org.ankur.advent2019.d02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramAlarmTest {

    private ProgramAlarm programAlarm = new ProgramAlarm();

    private final static String INPUT = "archive/2019_02.txt";

    @Test
    void alarm() {
        int /*value = programAlarm.alarmString("1,9,10,3,2,3,11,0,99,30,40,50");
        assertEquals(3500, value);
        value = programAlarm.alarmString("1,0,0,0,99");
        assertEquals(2, value);
        programAlarm.alarmString("2,3,0,3,99");
        assertEquals(6, value);
        programAlarm.alarmString("2,4,4,5,99,0");
        assertEquals(9801, value);
        programAlarm.alarmString("1,1,1,4,99,5,6,0,99");
        assertEquals(30, value);*/
        value = programAlarm.alarm(INPUT);
        assertEquals(4714701, value);
    }

    @Test
    void alarm2() {
        int value = programAlarm.alarmString("1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,5,19,23,2,10,23,27,1,27,5,31,2,9,31,35,1,35,5,39,2,6,39,43,1,43,5,47,2,47,10,51,2,51,6,55,1,5,55,59,2,10,59,63,1,63,6,67,2,67,6,71,1,71,5,75,1,13,75,79,1,6,79,83,2,83,13,87,1,87,6,91,1,10,91,95,1,95,9,99,2,99,13,103,1,103,6,107,2,107,6,111,1,111,2,115,1,115,13,0,99,2,0,14,0", 19690720);
        assertEquals(5121, value);
    }
}