package org.ankur.advent2019.d17;

import org.ankur.advent.util.FileReader;
import org.ankur.advent.util.IntCodeComputer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SetAndForget {

    private IntCodeComputer computer;

    public int part1(String fileName) {
        String s = FileReader.readFileAsString(fileName);
        return init(s);
    }

    public long part2(String fileName) {
        String s = FileReader.readFileAsString(fileName);
        init2(s);
        return computer.getOutput();
    }

    private int init(String inputStr) {
        computer = new IntCodeComputer(inputStr);
        List<StringBuilder> stringBuilders = new ArrayList<>();
        int line = 0;
        StringBuilder builder = new StringBuilder();
        stringBuilders.add(builder);
        boolean more = true;
        long previous = 0L;
        while (more) {
            computer.run(new LinkedList<>());
            long output = computer.getOutput();
            if (computer.getOutput() == 10) {
                builder = new StringBuilder();
                stringBuilders.add(builder);
                line++;
                if (previous == 10) {
                    more = false;
                }
            } else {
                char c = (char) output;
                if (c == '#' || c == '.' || c == '^' || c == '>' || c == '<' || c == 'v') {
                    more = true;
                    builder = stringBuilders.get(line);
                    builder.append((char) output);
                } else {
                    break;
                }

            }
            previous = output;
        }
        char[][] space = new char[stringBuilders.get(0).length()][stringBuilders.size()];
        for (int i1 = 0; i1 < stringBuilders.size(); i1++) {
            StringBuilder stringBuilder = stringBuilders.get(i1);
            String s = stringBuilder.toString();
            System.out.println(s);
            char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '#') {
                    space[i][i1] = c;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[0].length; j++) {
                if (space[i][j] == '#') {
                    if (intersection(i, j, space)) {
                        sum += i * j;
                        System.out.println("Intersection at " + i + " " + j + " sum " + sum);
                    }
                }
            }
        }
        return sum;
    }

    private boolean intersection(int x, int y, char[][] space) {
        return exists(x - 1, y, space) && space[x - 1][y] == '#'
                && exists(x + 1, y, space) && space[x + 1][y] == '#'
                && exists(x, y - 1, space) && space[x][y - 1] == '#'
                && exists(x, y + 1, space) && space[x][y + 1] == '#';
    }

    private boolean exists(int x, int y, char[][] space) {
        return x >= 1 && y >= 1 && x < space.length - 1 && y < space[0].length - 1;
    }

    private void init2(String inputStr) {
        Queue<Long> inputs = new LinkedList<>();
        //L10,R8,R6,R10,L12,R8,L12,L10,R8,R6,R10,L12,R8,L12,L10,R8,R6,R10,L8,L8,R12,R8,L12,L10,R8,R6,R10,L10,L8,L8,R10,R8,R6,R10
        String routine = "A,B,A,B,C,C,B,A,C,A\n";
        String a = "L,10,R,8,R,6,R,10\n";
        String b = "L,12,R,8,L,12\n";
        String c = "L,10,R,8,R,8\n";
        computer = new IntCodeComputer(inputStr);
        computer.update(0, 2);
        for (char c1 : routine.toCharArray()) {
            inputs.add((long)c1);
        }
        for (char c1 : a.toCharArray()) {
            inputs.add((long)c1);
        }
        for (char c1 : b.toCharArray()) {
            inputs.add((long)c1);
        }
        for (char c1 : c.toCharArray()) {
            inputs.add((long)c1);
        }
        inputs.add((long)'y');
        inputs.add((long)'\n');
        while (computer.running()) {
            computer.run(inputs);
        }
    }
}
