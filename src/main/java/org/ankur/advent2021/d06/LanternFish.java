package org.ankur.advent2021.d06;

import org.ankur.advent.util.FileReader;

public class LanternFish {

    public long solve(String input, int round) {
        long[] countOnDay = new long[9];

        long original;
        String[] thisLine = FileReader.readFileAsString(input).split(",");
        for (String s : thisLine) {
            countOnDay[Integer.parseInt(s)]++;
        }

        for(int i = 0; i < round; i++){
            original = countOnDay[0];
            countOnDay[0] = countOnDay[1];
            countOnDay[1] = countOnDay[2];
            countOnDay[2] = countOnDay[3];
            countOnDay[3] = countOnDay[4];
            countOnDay[4] = countOnDay[5];
            countOnDay[5] = countOnDay[6];
            countOnDay[6] = original + countOnDay[7];
            countOnDay[7] = countOnDay[8];
            countOnDay[8] = original;
        }
        long total = 0;
        for (long l : countOnDay) {
            total += l;
        }
        return total;
    }
}
