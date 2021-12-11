package org.ankur.advent2021.d11;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Octopus {

    private final int x;

    private final int y;

    private int energy;

    @Setter
    private boolean flashed;

    public Octopus(int x, int y, int energy) {
        this.x = x;
        this.y = y;
        this.energy = energy;
    }

    public void charge() {
        energy++;
    }

    public void reset() {
        if (energy > 9) {
            energy = 0;
        }
    }
}
