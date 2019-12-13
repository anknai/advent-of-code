package org.ankur.advent2019.d13;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarePackageTest extends CarePackage {

    private final static String INPUT = "2019_13.txt";

    @Test
    public void alarm() {
        assertEquals(180L, super.alarm(INPUT));
    }

    @Test
    public void testAlarm2() {
        assertEquals(8777L, super.alarm2(INPUT));
    }

    @Test
    public void testAlarmString() {
    }
}