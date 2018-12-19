package org.ankur.advent2018.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SleepLog implements Comparable<SleepLog> {
    private String text;

    private int date;

    private int time;

    private int guardId;

    private Activity activity;

    @Override
    public int hashCode() {
        return this.date + this.time * 32 + 5;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SleepLog)) {
            return false;
        }
        SleepLog other = (SleepLog) o;
        return other.date == this.date && other.time == this.time;
    }

    @Override
    public int compareTo(SleepLog o) {
        if (this.date > o.date) {
            return 1;
        }

        if (this.date < o.date) {
            return -1;
        }

        return Integer.compare(this.time, o.time);
    }

    public enum Activity {
        START,
        ASLEEP,
        AWAKE;
    }
}
