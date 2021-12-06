package org.ankur.advent2018.test;

import org.ankur.advent2018.D01ChronicCalibration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D01ChronicCalibrationTest {

    private final static String TEST_INPUT = "01_test.txt";
    private final static String INPUT = "01_frequency.txt";

    private final D01ChronicCalibration calibration = new D01ChronicCalibration();

    @Test
    void calculateFrequency() {
        int total = calibration.calculateFrequency(TEST_INPUT);
        assertEquals(0, total);
        total = calibration.calculateFrequency(INPUT);
        assertEquals(505, total);
    }

    @Test
    void firstDuplicate() {
        int duplicate = calibration.firstDuplicate(TEST_INPUT);
        assertEquals(1, duplicate);
        duplicate = calibration.firstDuplicate(INPUT);
        assertEquals(72330, duplicate);
    }
}
