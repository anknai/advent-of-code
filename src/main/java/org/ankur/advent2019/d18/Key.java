package org.ankur.advent2019.d18;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.SortedSet;

@AllArgsConstructor
@Getter
public class Key {

    private int x;

    private int y;

    private SortedSet<Character> sorted;
}
