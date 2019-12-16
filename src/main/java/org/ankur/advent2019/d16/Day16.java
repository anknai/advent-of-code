package org.ankur.advent2019.d16;

import org.ankur.advent.util.FileReader;

public class Day16 {

    public String part1(String fileName, int phases) {
        String strings = FileReader.readFileAsString(fileName);
        return init(strings, phases);
    }

    public String part2(String fileName, int phases) {
        String strings = FileReader.readFileAsString(fileName);
        return init2(strings, phases);
    }

    public String init2(String strings, int phases) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            stringBuilder.append(strings);
        }
        strings = stringBuilder.toString();
        char[] charArray = strings.toCharArray();
        String offset = strings.substring(0, 7);
        int length = strings.length();
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = charArray[i] - 48;
        }
        for (int phase = 0; phase < phases; phase++) {
            for (int repeat = 0; repeat < length; repeat++) {
                int[] pattern = pattern(repeat, length);
                int number = 0;
                for (int i = repeat; i < length; i++) {
                    int in = ints[i];
                    number += (in * pattern[i + 1]);
                }
                ints[repeat] = Math.abs(number % 10);
            }
        }
        StringBuilder str = new StringBuilder();
        int off = Integer.parseInt(offset);
        for (int i = off; i < off + 8; i++) {
            System.out.print(ints[i]);
            str.append(ints[i]);
        }
        System.out.println();
        return str.toString();
    }

    public String init(String strings, int phases) {
        char[] charArray = strings.toCharArray();
        int length = strings.length();
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = charArray[i] - 48;
        }
        for (int phase = 0; phase < phases; phase++) {
            for (int repeat = 0; repeat < length; repeat++) {
                int[] pattern = pattern(repeat, length);
                int number = 0;
                for (int i = repeat; i < length; i++) {
                    int in = ints[i];
                    number += (in * pattern[i + 1]);
                }
                ints[repeat] = Math.abs(number % 10);
                //System.out.print(ints[repeat] + ", ");
            }
            //System.out.println();
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            System.out.print(ints[i]);
            str.append(ints[i]);
        }
        System.out.println();
        return str.toString();
    }

    private int[] pattern(int index, int length) {
        int[] pattern = new int[length + 1];
        for (int i1 = 0; i1 < pattern.length; i1++) {
            int n = i1 / (index + 1);
            if (n % 4 == 0 || n%4 == 2) {
                pattern[i1] = 0;
            } else if (n % 4 == 1) {
                pattern[i1] = 1;
            } else {
                pattern[i1] = -1;
            }
            //System.out.print(pattern[i1] + ", ");
        }
        //System.out.println();
        return pattern;
    }
}
