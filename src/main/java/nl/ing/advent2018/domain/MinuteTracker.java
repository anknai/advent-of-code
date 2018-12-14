package nl.ing.advent2018.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class MinuteTracker {

    private int minute;

    private int guardId;

    public MinuteTracker(int minute, int guardId) {
        this.minute = minute;
        this.guardId = guardId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MinuteTracker)) {
            return false;
        }

        MinuteTracker other = (MinuteTracker) o;
        return other.guardId == this.guardId && other.minute == this.minute;
    }

    @Override
    public int hashCode() {
        return this.getGuardId() * (this.getMinute() + 32);
    }
}
