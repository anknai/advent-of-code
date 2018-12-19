package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class D18SettlersOfTheNorthPole {

    private char[][] yard;

    private char[][] copy;

    private char[][] original;

    private int trees;

    private int lumberyards;

    private int dimension;

    public int area(String fileName, int minutes) {
        init(fileName);

        for (int i = 0; i < minutes; i++) {
            letTheTimePass();
        }

        return resourceValue();
    }

    private int resourceValue() {
        trees = 0;
        lumberyards = 0;
        copy = copy(yard);
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                increment(x, y);
            }
        }

        return trees * lumberyards;
    }

    private void letTheTimePass() {
        copy = copy(yard);
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                checkAdjacent(x, y);
                if (yard[x][y] == '.') {
                    if (trees >= 3) {
                        yard[x][y] = '|';
                    }
                } else if (yard[x][y] == '|') {
                    if (lumberyards >= 3) {
                        yard[x][y] = '#';
                    }
                } else {
                    //An acre containing a lumberyard will remain a lumberyard if it was adjacent to at least
                    // one other lumberyard and at least one acre containing trees. Otherwise, it becomes open.
                    if (trees < 1 || lumberyards < 1) {
                        yard[x][y] = '.';
                    }
                }
            }
        }
    }

    public int areaAfterTooLong(String fileName, int minutes) {
        init(fileName);
        int current = 0;
        List<Integer> list = new ArrayList<>();
        boolean found = false;
        int value;
        int previous = 0;
        while (!found) {
            current++;
            letTheTimePass();
            value = resourceValue();
            if (list.contains(value)) {
                for (previous = 0; previous < list.size(); previous++) {
                    if (list.get(previous) == value) {
                        System.out.println("Resource value " + value + " found at " + previous + " also at " + current);
                        found = true;
                        break;
                    }
                }
            } else {
                list.add(value);
            }
        }
        int i = minutes - previous;
        int j = i % (current - previous);

        return area(fileName, previous + j);
    }

    private void init(String fileName) {
        List<String> lines = FileReader.readFile(fileName);
        dimension = lines.size();
        yard = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            String line = lines.get(i);
            yard[i] =line.toCharArray();
        }

        display();
        original = copy(yard);
    }

    private void display() {

        /*for (char[] aYard : yard) {
            System.out.println(aYard);
        }
        System.out.println();*/
    }

    private void checkAdjacent(int x, int y) {
        trees = 0;
        lumberyards = 0;
        increment(x - 1, y - 1);
        increment(x - 1, y);
        increment(x - 1, y + 1);
        increment(x, y - 1);
        increment(x, y + 1);
        increment(x + 1, y - 1);
        increment(x + 1, y);
        increment(x + 1, y + 1);
    }

    private void increment(int x, int y) {
        if (x >= 0 && x < dimension && y >= 0 && y < dimension) {
            if (copy[x][y] == '#') {
                lumberyards++;
            }
            if (copy[x][y] == '|') {
                trees++;
            }
        }
    }

    private char[][] copy(char[][] old) {
        char[][] current = new char[dimension][dimension];
        for (int i = 0; i < old.length; i++) {
            System.arraycopy(old[i], 0, current[i], 0, old[i].length);
        }
        return current;
    }
}
