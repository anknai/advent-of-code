package org.ankur.advent2019.d18;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Path {

    private Vault key;

    private int distance;
}
