package org.ankur.advent2019.d09;

import org.ankur.advent.util.FileReader;
import org.ankur.advent.util.IntCodeComputer;

public class SensorBoost {

    public long keycode(String inputFile, long input) {
        String s = FileReader.readFileAsString(inputFile);
        return run(s, input);
    }

    long run(String inputStr, long input) {
        IntCodeComputer computer = new IntCodeComputer(inputStr);
        computer.addInput(input);
        computer.run();
        return computer.output();
    }
}
