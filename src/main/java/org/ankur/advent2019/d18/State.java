package org.ankur.advent2019.d18;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.SortedSet;

@Getter
@Setter
@AllArgsConstructor
public class State {

    private int x;

    private int y;

    private SortedSet<Character> keys;

    private int distance;
}
