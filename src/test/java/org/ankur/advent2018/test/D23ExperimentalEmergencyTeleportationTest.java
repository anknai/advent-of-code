package org.ankur.advent2018.test;

import org.ankur.advent2018.D23ExperimentalEmergencyTeleportation;
import org.ankur.advent2018.domain.Bot;
import org.junit.Test;

import static org.junit.Assert.*;

public class D23ExperimentalEmergencyTeleportationTest {

    private D23ExperimentalEmergencyTeleportation teleportation = new D23ExperimentalEmergencyTeleportation();

    private static final String TEST_INPUT = "23_test.txt";
    private static final String INPUT = "23_bots.txt";

    @Test
    public void max() throws Exception {
        int max = teleportation.max(TEST_INPUT);
        assertEquals(7, max);

        max = teleportation.max(INPUT);
        assertEquals(430, max);
    }

    @Test
    public void parse() throws Exception {
        Bot bot = teleportation.parse("pos=<1,2,24>, r=15");
        assertEquals(1, bot.getX());
        assertEquals(2, bot.getY());
        assertEquals(24, bot.getZ());
        assertEquals(15, bot.getRadii());

        bot = teleportation.parse("pos=<54322811,13206326,26395311>, r=72206936");
        assertEquals(54322811, bot.getX());
        assertEquals(13206326, bot.getY());
        assertEquals(26395311, bot.getZ());
        assertEquals(72206936, bot.getRadii());
    }

}