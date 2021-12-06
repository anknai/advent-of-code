package org.ankur.advent2019.d10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonitoringStationTest extends MonitoringStation {

    private final static String INPUT = "archive/2019_10.txt";

    @Test
    void test1() {
        assertEquals(8, super.readFile1("archive/2019_10_01.txt"));
        assertEquals(33, super.readFile1("archive/2019_10_02.txt"));
        assertEquals(35, super.readFile1("archive/2019_10_03.txt"));
        assertEquals(41, super.readFile1("archive/2019_10_04.txt"));
        assertEquals(210, super.readFile1("archive/2019_10_05.txt"));
    }

    @Test
    void test2() {
        assertEquals(288, super.readFile1(INPUT));
    }

    @Test
    void test3() {
        super.readFile2(INPUT, 17, 22);
    }

}