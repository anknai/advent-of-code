package org.ankur.advent2021.d08;

import org.ankur.advent.util.FileReader;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SevenSegmentSearch {
    public int count(String input) {
        List<String> strings = FileReader.readFile(input);
        int total = 0;
        for (String string : strings) {
            String output = StringUtils.substringAfter(string, "| ");
            String[] outputs = output.split(" ");
            for (String o : outputs) {
                int length = o.length();
                if (length == 2 || length == 4 || length == 3 || length == 7) {
                    total++;
                }
            }
        }
        return total;
    }

    public int number(String input) {
        List<String> strings = FileReader.readFile(input);
        int total = 0;
        for (String string : strings) {
            String str = StringUtils.substringBefore(string, " |");
            System.out.println(str);
            SevenSegmentDisplay display = new SevenSegmentDisplay(str.split(" "));
            String output = StringUtils.substringAfter(string, "| ");
            StringBuilder number = new StringBuilder();
            for (String o : output.split(" ")) {
                number.append(display.getDisplay(o));
            }
            System.out.println(number);
            total += Integer.parseInt(number.toString());
        }
        return total;
    }
}
