package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;
import nl.ing.advent2018.domain.MinuteTracker;
import nl.ing.advent2018.domain.SleepLog;
import nl.ing.advent2018.domain.SleepingPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static nl.ing.advent.util.LogWriter.log;

public class D04ResponseRecord {

    public int sleepyGuard(String fileName) {
        List<String> sleepLog = FileReader.readFile(fileName);
        TreeSet<SleepLog> sleepLogSet = new TreeSet<>();
        for (String log: sleepLog) {
            sleepLogSet.add(parseSleepLog(log));
        }

        int currentGuardId = 0;
        Map<Integer, Integer> sleepingMap = new HashMap<>();
        int start = 0;
        int end;
        for (SleepLog logFromSet : sleepLogSet) {
            int guardId = logFromSet.getGuardId();
            if (guardId > 0) {
                currentGuardId = guardId;
            }
            if (guardId == 0) {
                logFromSet.setGuardId(currentGuardId);
            }
            switch (logFromSet.getActivity()) {
                case START:
                    Integer currentValue = sleepingMap.get(currentGuardId);
                    if (currentValue == null) {
                        sleepingMap.put(logFromSet.getGuardId(), 0);
                    } else {
                        sleepingMap.put(logFromSet.getGuardId(), currentValue);
                    }
                    break;
                case ASLEEP:
                    start = logFromSet.getTime();
                    break;
                case AWAKE:
                    end = logFromSet.getTime();
                    currentValue = sleepingMap.get(currentGuardId);
                    sleepingMap.put(currentGuardId, currentValue + (end - start));
                    break;
            }
        }
        int max = 0;
        int maxKey = 0;
        for (Map.Entry<Integer, Integer> set : sleepingMap.entrySet()) {
            if (max < set.getValue()) {
                max = set.getValue();
                maxKey = set.getKey();
            }
        }
        log("Max key: " + maxKey + "\t" + max);

        List<SleepingPattern> sleepingPatterns = new ArrayList<>();

        for (SleepLog logFromSet : sleepLogSet) {
            if (logFromSet.getGuardId() != maxKey) {
                continue;
            }

            switch (logFromSet.getActivity()) {
                case START:
                    continue;
                case ASLEEP:
                    start = logFromSet.getTime();
                    break;
                case AWAKE:
                    end = logFromSet.getTime();
                    SleepingPattern pattern = new SleepingPattern(start, end);
                    sleepingPatterns.add(pattern);
                    break;
            }
        }

        int[] logArray = new int[60];
        for (int i = 0; i < 60; i++) {
            logArray[i] = 0;
        }
        for (SleepingPattern pattern : sleepingPatterns) {
            for (int i = pattern.getStartTime(); i < pattern.getEndTime(); i++) {
                logArray[i - 2400]++;
            }
        }

        max = 0;
        int maxIndex = 0;
        for (int i = 0; i < 60; i++) {
            if (max < logArray[i]) {
                max = logArray[i];
                maxIndex = i;
            }
        }

        log(max + " " + maxIndex);

        return maxKey * maxIndex;
    }

    public int mostFrequentlyAsleep(String fileName) {
        List<String> sleepLog = FileReader.readFile(fileName);
        TreeSet<SleepLog> sleepLogSet = new TreeSet<>();
        for (String log: sleepLog) {
            sleepLogSet.add(parseSleepLog(log));
        }

        int currentGuardId = 0;
        MinuteTracker tracker;
        int start = 0;
        int end;
        Map<MinuteTracker, Integer> minuteTrackers = new HashMap<>();
        for (SleepLog logFromSet : sleepLogSet) {
            int guardId = logFromSet.getGuardId();
            if (guardId > 0) {
                currentGuardId = guardId;
            }
            if (guardId == 0) {
                logFromSet.setGuardId(currentGuardId);
            }
            switch (logFromSet.getActivity()) {
                case START:
                    break;
                case ASLEEP:
                    start = logFromSet.getTime();
                    break;
                case AWAKE:
                    end = logFromSet.getTime();
                    for (int i = start; i < end; i++) {
                        tracker = new MinuteTracker(i - 2400, currentGuardId);
                        Integer count = minuteTrackers.get(tracker);
                        count = (count == null) ? 0 : count;
                        count ++;
                        minuteTrackers.put(tracker, count);
                    }
                    break;
            }
        }

        int max = 0;
        MinuteTracker maxMinuteTracker = new MinuteTracker();
        for (Map.Entry<MinuteTracker, Integer> entrySet : minuteTrackers.entrySet()) {
            if (max < entrySet.getValue()) {
                max = entrySet.getValue();
                maxMinuteTracker = entrySet.getKey();
            }
        }
        log(maxMinuteTracker + "\t" + max);
        return maxMinuteTracker.getGuardId() * maxMinuteTracker.getMinute();
    }

    public SleepLog parseSleepLog(String log) {
        //[1518-09-22 23:50] Guard #2309 begins shift
        //[1518-06-26 00:42] falls asleep
        //[1518-10-09 00:43] wakes up
        SleepLog sleepLog = new SleepLog();
        String dateStr = log.substring(1, 11);
        dateStr = dateStr.replaceAll("-", "");
        sleepLog.setDate(Integer.parseInt(dateStr));

        String timeStr = log.substring(12, 17);
        timeStr = timeStr.replace("00:", "24:");
        timeStr = timeStr.replace(":", "");
        sleepLog.setTime(Integer.parseInt(timeStr));

        if (sleepLog.getTime() < 2400) {
            sleepLog.setDate(sleepLog.getDate() + 1);
        }

        String text = log.substring(19);
        sleepLog.setText(text);

        if (text.contains("Guard")) {
            ////[1518-09-22 23:50] Guard #2309 begins shift
            String guardStr = text.replace("Guard #", "")
                    .replace(" begins shift", "");
            sleepLog.setGuardId(Integer.parseInt(guardStr));
            sleepLog.setActivity(SleepLog.Activity.START);
        } else if (text.contains("falls asleep")) {
            sleepLog.setActivity(SleepLog.Activity.ASLEEP);
        } else {
            sleepLog.setActivity(SleepLog.Activity.AWAKE);
        }

        return sleepLog;
    }
}
