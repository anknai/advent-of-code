package org.ankur.advent2018.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Space {
    private int w;

    private int x;

    private int y;

    private int z;

    private boolean inSameConstellation(Space other) {
        int distance = Math.abs(w - other.w) + Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
        return distance <= 3;
    }

    public boolean inSameConstellation(Constellation constellation) {
        for (Space other: constellation.getSpaces()) {
            if (inSameConstellation(other)) {
                return true;
            }
        }
        return false;
    }
}
