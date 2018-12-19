package org.ankur.advent2018.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FabricSlice {

    private int claimer;

    private int left;

    private int top;

    private int width;

    private int height;

    public int getArea() {
        return width * height;
    }
}
