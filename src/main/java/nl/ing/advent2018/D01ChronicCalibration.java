package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.ing.advent.util.LogWriter.log;

public class D01ChronicCalibration {

    public int calculateFrequency(String input) {
        List<String> frequencyList = FileReader.readFile(input);
        return countFrequency(frequencyList);
    }

    public int firstDuplicate(String input) {
        List<String> frequencyList = FileReader.readFile(input);
        Set<Integer> frequencyOutput = new HashSet<>();
        int frequencyTotal = 0;
        while (true) {
            for (String val : frequencyList) {
                frequencyTotal += Integer.parseInt(val);
                boolean doesExist = frequencyOutput.add(frequencyTotal);
                if (!doesExist) {
                    log("Found duplicate " + frequencyTotal);
                    return frequencyTotal;
                }
            }
        }
    }

    private int countFrequency(List<String> frequencyList) {
        return frequencyList.stream().mapToInt(Integer::parseInt).sum();
    }
}
