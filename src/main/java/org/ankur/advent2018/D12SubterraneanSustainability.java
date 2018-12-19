package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static org.ankur.advent.util.LogWriter.logSameLine;
import static org.ankur.advent.util.LogWriter.newLine;

public class D12SubterraneanSustainability {

    private static final int MAX_GEN = 1000;
    private static final int MIN_GEN = 150;

    public int potsWithPlants(String fileName, int iterations) {
        List<String> inputList = FileReader.readFile(fileName);

        String input = inputList.get(0).substring(15);

        List<String> patternList = new ArrayList<>();
        for (int i = 2; i < inputList.size(); i++) {
            patternList.add(inputList.get(i));
        }

        char[] potsMap = new char[input.length() + 2 * iterations];
        for (int i = 0; i < potsMap.length; i++) {
            potsMap[i] = '.';
        }
        for (int i = iterations; i < iterations + input.length(); i++) {
            potsMap[i] = input.charAt(i - iterations);
        }

        for (int i = 0; i < iterations; i++) {
            StringBuilder nextIteration = new StringBuilder("..");
            for (int p = 2; p < potsMap.length - 2; p++) {
                String expectedPattern = potsMap[p - 2] + "" + potsMap[p - 1] + "" + potsMap[p] + "" + potsMap[p + 1] + "" + potsMap[p + 2];
                char match = '.';
                for (String pattern: patternList) {
                    if (expectedPattern.equals(pattern.substring(0, 5))) {
                        match = pattern.charAt(9);
                        break;
                    }
                }
                nextIteration.append(match);
            }

            boolean found = false;
            for (int n = 0; n < nextIteration.length(); n++) {
                potsMap[n] = nextIteration.charAt(n);
                if (potsMap[n] == '#' && !found) {
                    found = true;
                }
            }
        }

        int total = 0;
        boolean match = false;
        for (int i = 0; i < potsMap.length; i++) {
            if (potsMap[i] == '#') {
                total += i - iterations;
            }
            if (!match && potsMap[i] == '#') {
                match = true;
                logSameLine("[" + (i - iterations) + "] ");
            }
            if (match) {
                logSameLine(potsMap[i] + "");
            }
        }
        newLine();

        return total;
    }

    public long potsAfterGenerations(String fileName, long generation) {
        if (generation > MAX_GEN) {
            int diff = 0;
            int i = 0;
            int previous = 0;
            int consistentFor = 0;
            boolean consistent = false;
            do {
                int pots = potsWithPlants(fileName, MIN_GEN + i++);
                if (pots - previous == diff) {
                    consistent = true;
                    consistentFor++;
                } else {
                    consistentFor = 0;
                }
                diff = pots - previous;
                previous = pots;
            } while (!consistent || consistentFor <= 3);
            return previous + (diff * (generation - MIN_GEN - i + 1));
        } else {
            return potsWithPlants(fileName, (int)generation);
        }
    }
}
