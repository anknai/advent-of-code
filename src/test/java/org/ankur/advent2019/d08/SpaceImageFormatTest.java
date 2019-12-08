package org.ankur.advent2019.d08;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceImageFormatTest extends SpaceImageFormat {

    private final static String TEST_INPUT = "2019_08.txt";


    @Test
    public void readFile() {
        assertEquals(2016, super.readFile(TEST_INPUT, 25, 6));
    }

    @Test
    public void readFile2() {
        super.readFile2(TEST_INPUT, 25, 6);
    }
}