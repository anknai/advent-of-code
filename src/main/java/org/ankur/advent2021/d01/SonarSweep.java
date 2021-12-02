package org.ankur.advent2021.d01;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SonarSweep {

    public int sweepIncrease(String input) {
        List<String> sonarList = FileReader.readFile(input);
        return count(sonarList);
    }

    private int count(List<String> list) {
        int count = 0;
        int before = parseInt(list.get(0));
        for (String string : list) {
            int current = parseInt(string);
            if (current > before) {
                count++;
            }
            before = current;
        }
        return count;
    }

    public int detailedSweep(String input) {
        List<String> list = FileReader.readFile(input);
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size() - 2; i++) {
            int newTotal = parseInt(list.get(i)) + parseInt(list.get(i + 1)) + parseInt(list.get(i + 2));
            newList.add(String.valueOf(newTotal));
        }
        return count(newList);
    }
}
