package org.ankur.advent2018.test;

import org.ankur.advent2018.D14ChocolateCharts;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class D14ChocolateChartsTest {

    private D14ChocolateCharts chocolateCharts = new D14ChocolateCharts();

    @Test
    public void nextTen() {
        String next10 = chocolateCharts.nextTen(9);
        assertEquals("Next 10 are ", "5158916779", next10);

        next10 = chocolateCharts.nextTen(5);
        assertEquals("Next 10 are ", "0124515891", next10);

        next10 = chocolateCharts.nextTen(18);
        assertEquals("Next 10 are ", "9251071085", next10);

        next10 = chocolateCharts.nextTen(2018);
        assertEquals("Next 10 are ", "5941429882", next10);

        next10 = chocolateCharts.nextTen(505961);
        assertEquals("Next 10 are ", "9315164154", next10);
    }

    @Test
    public void firstOccurrence() {
        int first = chocolateCharts.firstOccurrence("101");
        assertEquals("First occurrence is ", 2, first);

        first = chocolateCharts.firstOccurrence("51589");
        assertEquals("First occurrence is ", 9, first);

        first = chocolateCharts.firstOccurrence("01245");
        assertEquals("First occurrence is ", 5, first);

        first = chocolateCharts.firstOccurrence("92510");
        assertEquals("First occurrence is ", 18, first);

        first = chocolateCharts.firstOccurrence("59414");
        assertEquals("First occurrence is ", 2018, first);

        first = chocolateCharts.firstOccurrence("505961");
        assertEquals("First occurrence is ", 20231866, first);
    }
}
