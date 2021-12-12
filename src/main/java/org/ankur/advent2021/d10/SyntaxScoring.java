package org.ankur.advent2021.d10;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Complexity: Simple
 * Stack
 */
public class SyntaxScoring {

    public int score(String input) {
        List<String> strings = FileReader.readFile(input);
        int total = 0;
        for (String string : strings) {
            System.out.println("on line " + string);
            Syntax syntax = new Syntax();
            for (char c : string.toCharArray()) {
                int add = syntax.getCorruptedScore(c);
                if (add > 0) {
                    total += add;
                    break;
                }
            }
        }
        return total;
    }

    public long fix(String input) {
        List<String> strings = FileReader.readFile(input);
        List<Long> scores = new ArrayList<>();
        for (String string : strings) {
            Syntax syntax = new Syntax();
            boolean isCorrupt = false;
            for (char c : string.toCharArray()) {
                isCorrupt = syntax.add(c);
                if (isCorrupt) {
                    break;
                }
            }
            if (!isCorrupt) {
                System.out.println("Fixed line " + string);
                scores.add(syntax.fix());
            } else {
                System.out.println("Skipping " + string);
            }
        }
        scores.sort(Long::compareTo);
        int length = scores.size();
        return scores.get((length - 1)/2);
    }
}
