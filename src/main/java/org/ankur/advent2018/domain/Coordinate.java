package org.ankur.advent2018.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Coordinate {

    private int coordinateId;

    private int x;

    private int y;

    private int area;

    private boolean infinite;

    public void addArea() {
        area++;
    }
}
