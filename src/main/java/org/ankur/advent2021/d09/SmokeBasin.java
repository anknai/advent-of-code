package org.ankur.advent2021.d09;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class SmokeBasin {
    public int low(String input) {
        List<String> strings = FileReader.readFile(input);
        int width = strings.get(0).length();
        int height = strings.size();
        int[][] data = new int[width][height];
        for (int i = 0; i < height; i++) {
            String string = strings.get(i);
            char[] chars = string.toCharArray();
            for (int j = 0; j < width; j++) {
                char c = chars[j];
                data[j][i] = c - '0';
            }
        }

        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int current = data[j][i];
                int left = j - 1;
                int right = j + 1;
                int up = i - 1;
                int down = i + 1;
                if (left >= 0 && current >= data[left][i]) {
                    continue;
                }
                if (right < width && current >= data[right][i]) {
                    continue;
                }
                if (up >= 0 && current >= data[j][up]) {
                    continue;
                }
                if (down < height && current >= data[j][down]) {
                    continue;
                }
                System.out.println("current is lowest " + current + " at " + j + " " + i);
                integers.add(current);
            }
        }
        Integer total = integers.stream().reduce(0, Integer::sum);
        return total + integers.size();
    }
}
