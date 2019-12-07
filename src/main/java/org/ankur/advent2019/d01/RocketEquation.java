package org.ankur.advent2019.d01;

import org.ankur.advent.util.FileReader;

import java.util.List;

public class RocketEquation {

    long calculateFrequency(String input) {
        List<String> massList = FileReader.readFile(input);
        long total = 0L;
        for (String mass : massList) {
            total += calculateFuel(Integer.parseInt(mass));
        }
        return total;
    }

    long recursiveFuelCalculation(String input) {
        List<String> massList = FileReader.readFile(input);
        long total = 0L;
        for (String mass : massList) {
            total += recursiveCalculation(Integer.parseInt(mass));
        }
        return total;
    }

    int calculateFuel(int mass) {
        return mass / 3 - 2;
    }

    long recursiveCalculation(int mass) {
        long total = 0L;
        while (true) {
            int fuel = calculateFuel(mass);
            if (fuel <= 0) {
                break;
            }
            total += fuel;
            mass = fuel;
        }
        return total;
    }
}
