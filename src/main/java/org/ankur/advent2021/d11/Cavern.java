package org.ankur.advent2021.d11;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Cavern {
    private final Map<Integer, Octopus> octopusMap;

    private final int width;

    private final int height;

    public Cavern(int width, int height) {
        octopusMap = new HashMap<>();
        this.width = width;
        this.height = height;
    }

    public void addOctopus(int x, int y, int energy) {
        Octopus octopus = new Octopus(x, y, energy);
        octopusMap.put(getIndex(octopus), octopus);
    }

    public Optional<Octopus> getOctopus(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return Optional.ofNullable(octopusMap.get(getIndex(x, y)));
        }
        return Optional.empty();
    }

    private int getIndex(Octopus octopus) {
        return getIndex(octopus.getX(), octopus.getY());
    }

    private int getIndex(int x, int y) {
        return width * y + x + 1;
    }

    public int charge() {
        Set<Integer> integers = octopusMap.keySet();
        integers.stream()
                .map(octopusMap::get)
                .forEach(octopus -> {
                    octopus.charge();
                    octopus.setFlashed(false);
                });

        int flashes = integers.stream()
                .map(octopusMap::get)
                .filter(octopus -> !octopus.isFlashed() && octopus.getEnergy() > 9)
                .mapToInt(octopus -> charge(octopus.getX(), octopus.getY()))
                .sum();

        integers.stream().map(octopusMap::get).forEach(Octopus::reset);
        return flashes;
    }

    private int charge(int x, int y) {
        Optional<Octopus> optional = getOctopus(x, y);
        if (!optional.isPresent()) {
            return 0;
        }
        Octopus octopus = optional.get();
        octopus.charge();
        if (!octopus.isFlashed() && octopus.getEnergy() > 9) {
            octopus.setFlashed(true);
            return 1 + charge(x - 1, y) + charge(x + 1, y) + charge(x, y - 1) + charge(x, y + 1)
                    + charge(x -1 , y -1) + charge(x + 1 , y + 1) + charge(x -1 , y + 1) + charge(x + 1 , y - 1);
        }
        return 0;
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Octopus octopus = octopusMap.get(getIndex(x, y));
                System.out.print(octopus.getEnergy());
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean flashAll() {
        charge();
        Set<Integer> integers = octopusMap.keySet();
        return integers.stream().map(octopusMap::get).noneMatch(octopus -> octopus.getEnergy() != 0);
    }
}
