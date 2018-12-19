package org.ankur.advent2018.test;

import org.ankur.advent2018.D01ChronicCalibration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D01ChronicCalibrationTest {

    private final static String TEST_INPUT = "01_test.txt";
    private final static String INPUT = "01_frequency.txt";

    private D01ChronicCalibration calibration = new D01ChronicCalibration();

    @Test
    public void calculateFrequency() {
        int total = calibration.calculateFrequency(TEST_INPUT);
        assertEquals("Final frequency is ", 0, total);
        total = calibration.calculateFrequency(INPUT);
        assertEquals("Final frequency is ", 505, total);
    }

    @Test
    public void firstDuplicate() {
        int duplicate = calibration.firstDuplicate(TEST_INPUT);
        assertEquals("First duplicate is ", 1, duplicate);
        duplicate = calibration.firstDuplicate(INPUT);
        assertEquals("First duplicate is ", 72330, duplicate);
    }
}
