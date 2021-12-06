package org.ankur.advent2018.test;

import org.ankur.advent2018.D04ResponseRecord;
import org.ankur.advent2018.domain.SleepLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D04ResponseRecordTest {

    private final static String INPUT = "04_sleeping_log.txt";
    private final static String TEST_INPUT = "04_test_sleeping_log.txt";

    private D04ResponseRecord responseRecord = new D04ResponseRecord();

    @Test
    void sleepyGuard() {
        int i = responseRecord.sleepyGuard(TEST_INPUT);
        assertEquals(240, i);

        i = responseRecord.sleepyGuard(INPUT);
        assertEquals(19025, i);
    }

    @Test
    void mostFrequentlyAsleep() {
        int i = responseRecord.mostFrequentlyAsleep(TEST_INPUT);
        assertEquals(4455, i);

        i = responseRecord.mostFrequentlyAsleep(INPUT);
        assertEquals(23776, i);
    }

    @Test
    void parseSleepLog() {
        SleepLog sleepLog = responseRecord.parseSleepLog("[1518-09-22 23:50] Guard #2309 begins shift");
        assertEquals(2309, sleepLog.getGuardId());
        assertEquals(2350, sleepLog.getTime());
        assertEquals(15180923, sleepLog.getDate());
        assertEquals(SleepLog.Activity.START, sleepLog.getActivity());

        sleepLog = responseRecord.parseSleepLog("[1518-06-26 00:42] falls asleep");
        assertEquals(2442, sleepLog.getTime());
        assertEquals(SleepLog.Activity.ASLEEP, sleepLog.getActivity());

        sleepLog = responseRecord.parseSleepLog("[1518-10-09 00:43] wakes up");
        assertEquals(2443, sleepLog.getTime());
        assertEquals(SleepLog.Activity.AWAKE, sleepLog.getActivity());
    }
}
