package org.ankur.advent2018.test;

import org.ankur.advent2018.D03FabricSlicer;
import org.ankur.advent2018.domain.FabricSlice;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D03FabricSlicerTest {
    private D03FabricSlicer slicer = new D03FabricSlicer();

    private final static String TEST_INPUT = "03_test.txt";

    private final static String INPUT = "03_fabric.txt";

    @Test
    public void overlappedClaims() {
        int overlappedArea = slicer.overlappedArea(TEST_INPUT);
        assertEquals("Overlapped area is ", 4, overlappedArea);
        overlappedArea = slicer.overlappedArea(INPUT);
        assertEquals("Overlapped area is ", 100595, overlappedArea);
    }

    @Test
    public void noOverlappedClaims() {
        int noOverlappedClaim = slicer.noOverlappedClaim(TEST_INPUT);
        assertEquals("Overlapped area is ", 3, noOverlappedClaim);
        noOverlappedClaim = slicer.noOverlappedClaim(INPUT);
        assertEquals("Overlapped area is ", 415, noOverlappedClaim);
    }

    @Test
    public void getFabricSlicer() {
        FabricSlice fabricSlice = slicer.getFabricSlice("#1 @ 338,764: 20x24");
        assertEquals(1, fabricSlice.getClaimer());
        assertEquals(338, fabricSlice.getLeft());
        assertEquals(764, fabricSlice.getTop());
        assertEquals(20, fabricSlice.getWidth());
        assertEquals(24, fabricSlice.getHeight());

        fabricSlice = slicer.getFabricSlice("#1228 @ 739,174: 3x4");
        assertEquals(1228, fabricSlice.getClaimer());
        assertEquals(739, fabricSlice.getLeft());
        assertEquals(174, fabricSlice.getTop());
        assertEquals(3, fabricSlice.getWidth());
        assertEquals(4, fabricSlice.getHeight());
    }
}