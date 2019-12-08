package org.ankur.advent2019.d08;

import org.ankur.advent.util.FileReader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SpaceImageFormat {
    public int readFile(String string, int x, int y) {
        String input = FileReader.readFileAsString(string);
        List<String> layers = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            layers.add(input.substring(i, i + x * y));
            i += x * y;

        }
        return count(layers);
    }

    public void readFile2(String string, int x, int y) {
        String input = FileReader.readFileAsString(string);
        List<String> layers = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            layers.add(input.substring(i, i + x * y));
            i += x * y;

        }
        transpose(layers);
    }

    private int count(List<String> layers) {
        int min = Integer.MAX_VALUE;
        String myLayer = "";
        for (String layer : layers) {
            int count = StringUtils.countMatches(layer, '0');
            if (count < min) {
                min = count;
                myLayer = layer;
            }
        }
        int count1 = StringUtils.countMatches(myLayer, '1');
        int count2 = StringUtils.countMatches(myLayer, '2');
        return count1 * count2;
    }

    private void transpose(List<String> layers) {
        StringBuilder mine = new StringBuilder();
        for (int i = 0; i < 150; i++) {
            char now = '2';
            for (String layer : layers) {
                char c = layer.charAt(i);
                if (c == '0') {
                    now = ' ';
                    break;
                }
                if (c == '1') {
                    now = 35;
                    break;
                }
            }
            mine.append(now);
        }

        int i = 0;
        while (i < mine.length()) {
            System.out.println(mine.substring(i, i + 25));
            i += 25;
        }
    }

}
