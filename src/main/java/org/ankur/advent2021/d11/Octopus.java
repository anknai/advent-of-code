package org.ankur.advent2021.d11;

import lombok.Data;

@Data
public class Octopus {

    private int x;

    private int y;

    private int energy;

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
