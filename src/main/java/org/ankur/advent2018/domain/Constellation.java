package org.ankur.advent2018.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Constellation {

    private List<Space> spaces = new ArrayList<>();

    public void addSpace(Space space) {
        spaces.add(space);
    }
}
