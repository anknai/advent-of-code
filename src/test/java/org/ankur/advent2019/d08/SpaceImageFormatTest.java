package org.ankur.advent2019.d08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceImageFormatTest extends SpaceImageFormat {

    private final static String TEST_INPUT = "archive/2019_08.txt";


    @Test
    void readFile() {
        assertEquals(2016, super.readFile(TEST_INPUT, 25, 6));
    }

    @Test
    void readFile2() {
        super.readFile2(TEST_INPUT, 25, 6);
    }
}