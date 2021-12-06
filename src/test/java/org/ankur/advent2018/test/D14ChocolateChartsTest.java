package org.ankur.advent2018.test;

import org.ankur.advent2018.D14ChocolateCharts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D14ChocolateChartsTest {

    private D14ChocolateCharts chocolateCharts = new D14ChocolateCharts();

    @Test
    void nextTen() {
        String next10 = chocolateCharts.nextTen(9);
        assertEquals("5158916779", next10);

        next10 = chocolateCharts.nextTen(5);
        assertEquals("0124515891", next10);

        next10 = chocolateCharts.nextTen(18);
        assertEquals("9251071085", next10);

        next10 = chocolateCharts.nextTen(2018);
        assertEquals("5941429882", next10);

        next10 = chocolateCharts.nextTen(505961);
        assertEquals("9315164154", next10);
    }

    @Test
    void firstOccurrence() {
        int first = chocolateCharts.firstOccurrence("101");
        assertEquals(2, first);

        first = chocolateCharts.firstOccurrence("51589");
        assertEquals(9, first);

        first = chocolateCharts.firstOccurrence("01245");
        assertEquals(5, first);

        first = chocolateCharts.firstOccurrence("92510");
        assertEquals(18, first);

        first = chocolateCharts.firstOccurrence("59414");
        assertEquals(2018, first);

        first = chocolateCharts.firstOccurrence("505961");
        assertEquals(20231866, first);
    }
}
