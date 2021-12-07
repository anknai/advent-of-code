package org.ankur.advent2021.d07;

import org.ankur.advent.util.FileReader;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class TreacheryOfWhale {

    public int shortest(String input) {
        String string = FileReader.readFileAsString(input);
        String[] split = string.split(",");
        return calculate(Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList()), false);
    }

    private int calculate(List<Integer> list, boolean exponential) {
        int shortest = Integer.MAX_VALUE;
        OptionalInt min = list.stream()
                .mapToInt(v -> v)
                .min();
        OptionalInt max = list.stream()
                .mapToInt(v -> v)
                .max();
        if (!min.isPresent()) {
            return -1;
        }
        for (int i = min.getAsInt(); i < max.getAsInt(); i++) {
            int total = 0;
            for (int distance : list) {
                int abs = Math.abs(distance - i);
                if (exponential) {
                    total += abs* (abs+1)/2;
                } else {
                    total += abs;
                }
            }
            if (total < shortest) {
                shortest = total;
            }
        }
        return shortest;
    }

    public int shortestExp(String input) {
        String string = FileReader.readFileAsString(input);
        String[] split = string.split(",");
        return calculate(Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList()), true);
    }
}
