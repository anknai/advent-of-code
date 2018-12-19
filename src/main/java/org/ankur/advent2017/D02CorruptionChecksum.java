package org.ankur.advent2017;

import org.ankur.advent.util.FileReader;

import java.util.List;
import java.util.TreeSet;

public class D02CorruptionChecksum {

    public int checksum(String input) {
        List<TreeSet<Integer>> list = FileReader.readFileAsListOfIntegerSets(input);
        int checksum = 0;

        for (TreeSet<Integer> set : list) {
            checksum += set.last() - set.first();
        }
        return checksum;
    }

    public int divisibleChecksum(String input) {
        List<TreeSet<Integer>> list = FileReader.readFileAsListOfIntegerSets(input);
        int checksum = 0;

        for (TreeSet<Integer> set : list) {
            outer: for (int next : set) {
                for (int i : set) {
                    if (next == i) {
                        continue;
                    }
                    if (next % i == 0) {
                        checksum += next / i;
                        break outer;
                    }
                }
            }
        }
        return checksum;
    }
}
