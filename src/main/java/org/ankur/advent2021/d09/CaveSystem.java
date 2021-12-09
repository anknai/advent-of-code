package org.ankur.advent2021.d09;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Data
public class CaveSystem {

    private Map<Integer, Cave> caves;

    private int width;

    private int height;

    public CaveSystem(int width, int height) {
        caves = new HashMap<>();
        this.width = width;
        this.height = height;
    }

    public void addCave(Cave cave) {
        caves.put(getIndex(cave), cave);
    }

    public void addCave(int x, int y, int value) {
        addCave(new Cave(x, y, value));
    }

    public Optional<Cave> getCave(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return Optional.ofNullable(caves.get(getIndex(x, y)));
        }
        return Optional.empty();
    }

    private int getIndex(Cave cave) {
        return getIndex(cave.getX(), cave.getY());
    }

    private int getIndex(int x, int y) {
        return width * y + x + 1;
    }

    public List<Integer> findBasins() {
        List<Integer> basinSet = new ArrayList<>();
        Set<Integer> integers = caves.keySet();
        for (Integer index : integers) {
            Cave cave = caves.get(index);
            if (cave.isTerminal() || cave.isTagged()) {
                continue;
            }
            int size = basin(cave.getX(), cave.getY());
            if (size > 0) {
                System.out.println("Basin found for " + cave + " with size " + size);
                basinSet.add(size);
            }
        }

        return basinSet;
    }

    private int basin(int x, int y) {
        Optional<Cave> caveOptional = getCave(x, y);
        if (!caveOptional.isPresent()) {
            return 0;
        }
        Cave cave = caveOptional.get();
        if (cave.isTerminal() || cave.isTagged()) {
            return 0;
        }
        cave.setTagged(true);
        return 1 + basin(x - 1, y) + basin(x + 1, y) + basin(x, y - 1) + basin(x, y + 1);
    }
}
