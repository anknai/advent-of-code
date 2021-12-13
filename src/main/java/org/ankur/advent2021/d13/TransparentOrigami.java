package org.ankur.advent2021.d13;

import org.ankur.advent.util.FileReader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Loops and indexes
 * Manual observation for second part
 * Complexity: Simple to Medium
 */
public class TransparentOrigami {

    private int[][] origami;

    private List<String> folds;

    int width = 0;
    int height = 0;

    public int dots(String input) {
        init(input);
        String s = folds.get(0);
        fold(s);
        return count();
    }

    public void dotsContinue(String input) {
        init(input);
        folds.forEach(this::fold);
        display();
    }

    private void init(String input) {
        List<Dot> dots = new ArrayList<>();
        List<String> strings = FileReader.readFile(input);
        int stringsSize = strings.size();
        int foldStartsHere = 0;
        for (int i = 0; i < stringsSize; i++) {
            String string = strings.get(i);
            if (string.trim().equals("")) {
                foldStartsHere = i;
                break;
            }
            int x = Integer.parseInt(string.split(",")[0]);
            int y = Integer.parseInt(string.split(",")[1]);
            if (x > width) {
                width = x;
            }
            if (y > height) {
                height = y;
            }
            Dot dot = new Dot(x, y);
            dots.add(dot);
        }
        width++;
        height++;
        origami = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                origami[x][y] = 0;
            }
        }
        dots.forEach(dot -> origami[dot.getX()][dot.getY()] = 1);
        folds = new ArrayList<>();
        for (int i = foldStartsHere + 1; i < stringsSize; i++) {
            String s = strings.get(i);
            s = StringUtils.substringAfter(s, "fold along ");
            folds.add(s);
        }
    }

    private void display() {
        System.out.println("Width is " + width + " height is " + height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (origami[x][y] == 1) {
                    System.out.print('█');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int count() {
        int total = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (origami[x][y] == 1) {
                    total++;
                }
            }
        }
        return total;
    }

    private void fold(String s) {
        String[] split = s.split("=");
        if (split[0].startsWith("x")) {
            foldAtX(Integer.parseInt(split[1]));
        } else {
            foldAtY(Integer.parseInt(split[1]));
        }
    }

    private void foldAtY(int y) {
        int times = height - y - 1;
        for (int i = 1; i <= times; i++) {
            for (int x = 0; x < width; x++) {
                origami[x][y - i] = origami[x][y - i] | origami[x][y + i];
            }
        }
        height = y;
    }

    private void foldAtX(int x) {
        int times = width - x - 1;
        for (int y = 0; y < height; y++) {
            for (int i = 1; i <= times; i++) {
                origami[x - i][y] = origami[x - i][y] | origami[x + i][y];
            }
        }
        width = x;
    }
}
