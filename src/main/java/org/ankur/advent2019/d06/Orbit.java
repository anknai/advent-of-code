package org.ankur.advent2019.d06;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Orbit {
    //AAA)BBB, which means "BBB is in orbit around AAA".

    //orbitee AAA
    private String orbitee;

    //orbiter BBB
    private String orbitter;

    private int nrOfOrbits;

    public Orbit(String orbitter, String orbitee) {
        this.orbitter = orbitter;
        this.orbitee = orbitee;
        nrOfOrbits = 1;
    }

    public void incOrbits() {
        nrOfOrbits++;
    }
}
