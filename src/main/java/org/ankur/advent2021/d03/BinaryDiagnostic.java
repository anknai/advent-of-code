package org.ankur.advent2021.d03;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic {

    public int powerConsumption(String input) {
        List<String> strings = FileReader.readFile(input);
        List<Diagnostic> diagnostics = getDiagnostics(strings);

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (Diagnostic diagnostic : diagnostics) {
            if (diagnostic.getOnes() > diagnostic.getZeros()) {
                gammaRate.append("1");
                epsilonRate.append("0");
            } else {
                gammaRate.append("0");
                epsilonRate.append("1");
            }
        }

        int gamma = Integer.parseInt(gammaRate.toString(), 2);
        int epsilon = Integer.parseInt(epsilonRate.toString(), 2);
        return gamma * epsilon;
    }

    private List<Diagnostic> getDiagnostics(List<String> strings) {
        List<Diagnostic> diagnostics = new ArrayList<>();
        for (int i = 0; i < strings.get(0).length(); i++) {
            Diagnostic diagnostic = new Diagnostic(i);
            diagnostics.add(diagnostic);
        }
        for (String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c == '0') {
                    diagnostics.get(i).zeroPlusPlus();
                } else if (c == '1') {
                    diagnostics.get(i).onePlusPlus();
                }
            }
        }
        return diagnostics;
    }

    public int lifeSupportRating(String input) {
        List<String> strings = FileReader.readFile(input);
        int oxygen = Integer.parseInt(getOxygenGeneratorRating(strings, 0), 2);
        int co2 = Integer.parseInt(getCO2ScrubberRating(strings, 0), 2);
        return oxygen * co2;
    }

    private Diagnostic getDiagnostic(List<String> strings, int index) {
        int zeros = 0;
        int ones = 0;
        for (String string : strings) {
            char c = string.charAt(index);
            if (c == '0') {
                zeros++;
            } else {
                ones++;
            }
        }
        return new Diagnostic(index, zeros, ones);
    }

    private String getOxygenGeneratorRating(List<String> strings, int index) {
        Diagnostic diagnostic = getDiagnostic(strings, index);
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            if (diagnostic.getOnes() >= diagnostic.getZeros()) {
                if (string.charAt(index) == '1') {
                    newStrings.add(string);
                }
            } else {
                if (string.charAt(index) == '0') {
                    newStrings.add(string);
                }
            }
        }
        return newStrings.size() == 1 ? newStrings.get(0) : getOxygenGeneratorRating(newStrings, index + 1);
    }

    private String getCO2ScrubberRating(List<String> strings, int index) {
        Diagnostic diagnostic = getDiagnostic(strings, index);
        List<String> newStrings = new ArrayList<>();
        for (String string : strings) {
            if (diagnostic.getZeros() <= diagnostic.getOnes()) {
                if (string.charAt(index) == '0') {
                    newStrings.add(string);
                }
            } else {
                if (string.charAt(index) == '1') {
                    newStrings.add(string);
                }
            }
        }
        return newStrings.size() == 1 ? newStrings.get(0) : getCO2ScrubberRating(newStrings, index + 1);
    }
}
