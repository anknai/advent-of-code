package nl.ing.advent2018.test;

import nl.ing.advent2018.D04ResponseRecord;
import nl.ing.advent2018.domain.SleepLog;
import org.junit.Test;

import static org.junit.Assert.*;

public class D04ResponseRecordTest {

    private final static String INPUT = "04_sleeping_log.txt";
    private final static String TEST_INPUT = "04_test_sleeping_log.txt";

    private D04ResponseRecord responseRecord = new D04ResponseRecord();

    @Test
    public void sleepyGuard() {
        int i = responseRecord.sleepyGuard(TEST_INPUT);
        assertEquals("ID is ", 240, i);

        i = responseRecord.sleepyGuard(INPUT);
        assertEquals("ID is ", 19025, i);
    }

    @Test
    public void mostFrequentlyAsleep() {
        int i = responseRecord.mostFrequentlyAsleep(TEST_INPUT);
        assertEquals("ID is ", 4455, i);

        i = responseRecord.mostFrequentlyAsleep(INPUT);
        assertEquals("ID is ", 23776, i);
    }

    @Test
    public void parseSleepLog() {
        SleepLog sleepLog = responseRecord.parseSleepLog("[1518-09-22 23:50] Guard #2309 begins shift");
        assertEquals("Guard id", 2309, sleepLog.getGuardId());
        assertEquals("time", 2350, sleepLog.getTime());
        assertEquals("date", 15180923, sleepLog.getDate());
        assertEquals("Activity", SleepLog.Activity.START, sleepLog.getActivity());

        sleepLog = responseRecord.parseSleepLog("[1518-06-26 00:42] falls asleep");
        assertEquals("time", 2442, sleepLog.getTime());
        assertEquals("Activity", SleepLog.Activity.ASLEEP, sleepLog.getActivity());

        sleepLog = responseRecord.parseSleepLog("[1518-10-09 00:43] wakes up");
        assertEquals("time", 2443, sleepLog.getTime());
        assertEquals("Activity", SleepLog.Activity.AWAKE, sleepLog.getActivity());
    }
}
