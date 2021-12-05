package org.ankur.advent2021.d05;

import org.ankur.advent.util.FileReader;

import java.util.List;

public class HydrothermalVenture {

    public int overlap(String input, int dimension) {
        List<String> strings = FileReader.readFile(input);
        Cloud cloud = new Cloud(dimension);
        strings.forEach(line -> cloud.addLine(line, false));
        return cloud.countMax();
    }

    public int overlapDiagonal(String input, int dimension) {
        List<String> strings = FileReader.readFile(input);
        Cloud cloud = new Cloud(dimension);
        strings.forEach(line -> cloud.addLine(line, true));
        return cloud.countMax();
    }
}
