package org.ankur.advent2019.d12;

import java.util.List;

public class MoonWalker {

    public int walk1(List<Moon> moons, int steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println("Step " + (i+1));
            takeStep(moons);
        }
        return energy(moons);
    }

    private void takeStep(List<Moon> moons) {
        for (Moon moon : moons) {
            for (Moon other : moons) {
                if (moon == other) {
                    continue;
                }

                if (moon.getX() < other.getX()) {
                    moon.updateVx(1);
                } else if (moon.getX() > other.getX()) {
                    moon.updateVx(-1);
                }

                if (moon.getY() < other.getY()) {
                    moon.updateVy(1);
                } else if (moon.getY() > other.getY()) {
                    moon.updateVy(-1);
                }

                if (moon.getZ() < other.getZ()) {
                    moon.updateVz(1);
                } else if (moon.getZ() > other.getZ()) {
                    moon.updateVz(-1);
                }
            }
        }
        move(moons);
    }

    private void move(List<Moon> moons) {
        for (Moon moon : moons) {
            moon.setX(moon.getX() + moon.getVx());
            moon.setY(moon.getY() + moon.getVy());
            moon.setZ(moon.getZ() + moon.getVz());
            //System.out.println(moon);
        }
    }

    private int energy(List<Moon> moons) {
        int energy = 0;
        for (Moon moon : moons) {
            int pot = 0;
            pot += Math.abs(moon.getX());
            pot += Math.abs(moon.getY());
            pot += Math.abs(moon.getZ());
            int kin = 0;
            kin += Math.abs(moon.getVx());
            kin += Math.abs(moon.getVy());
            kin += Math.abs(moon.getVz());
            energy += pot * kin;
        }
        return energy;
    }
}
