package org.ankur.advent2019.d12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoonWalker2 {

    List<Moon> originals = new ArrayList<>();

    public int walk1(List<Moon> moons, int steps) {
        for (Moon moon : moons) {
            Moon other = new Moon(moon.getX(), moon.getY(), moon.getZ());
            originals.add(other);
        }
        for (int i = 0; i < steps; i++) {
            //System.out.println("Step " + (i+1));
            takeStep(moons);
            repeat(moons, i + 1);

        }
        return energy(moons);
    }

    private void repeat(List<Moon> moons, int step) {
        for (int i = 0; i < moons.size(); i++) {
            Moon moon = moons.get(i);
            int x = moon.getX();
            int vx = moon.getVx();
            Moon orig = originals.get(i);
            /*if (orig.getX() == x && vx == 0) {
                System.out.println("x is repeated in step " + step + " for moon " + i);
            }
            if (orig.getY() == moon.getY() && moon.getVy() == 0) {
                System.out.println("y is repeated in step " + step + " for moon " + i);
            }*/
            if (orig.getZ() == moon.getZ() && moon.getVz() == 0) {
                System.out.println("z is repeated in step " + step + " for moon " + i);
            }
        }
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

    public long lcm(long a, long b, long c) {
        return LCM(LCM(a, b), c);
    }

    /**
     * Calculate Lowest Common Multiplier
     */
    public static long LCM(long a, long b) {
        return (a * b) / GCF(a, b);
    }

    /**
     * Calculate Greatest Common Factor
     */
    public static long GCF(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return (GCF(b, a % b));
        }
    }
}
