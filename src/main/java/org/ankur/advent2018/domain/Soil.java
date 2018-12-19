package org.ankur.advent2018.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Soil {

    private int startX;

    private int startY;

    private int endX;

    private int endY;

}
