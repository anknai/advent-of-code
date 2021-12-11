package org.ankur.advent2021.d11;

import org.ankur.advent.util.FileReader;

import java.util.List;

public class DumboOctopus {

    public int flashes(String input, int charges) {
        Cavern cavern = init(input);
        int total = 0;
        for (int i = 0; i < charges; i++) {
            total += cavern.charge();
        }
        cavern.print();
        return total;
    }

    public int flashAll(String input) {
        Cavern cavern = init(input);
        int total = 0;
        boolean flashed = false;
        while (!flashed) {
            total++;
            flashed = cavern.flashAll();
        }
        cavern.print();
        return total;
    }

    private Cavern init(String input) {
        List<String> strings = FileReader.readFile(input);
        int height = strings.size();
        int width = strings.get(0).length();
        Cavern cavern = new Cavern(width, height);
        for (int y = 0; y < height; y++) {
            String string = strings.get(y);
            char[] charArray = string.toCharArray();
            for (int x = 0; x < width; x++) {
                char c = charArray[x];
                cavern.addOctopus(x, y, c - '0');
            }
        }
        return cavern;
    }
}
