package org.ankur.advent2019.d02;

import org.ankur.advent.util.FileReader;

import java.util.Arrays;

public class ProgramAlarm {

    public int alarm(String input) {
        String s = FileReader.readFileAsString(input);
        System.out.println(s);
        return alarmString(s);
    }

    int alarmString(String input) {
        String[] split = input.split(",");
        int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        array[1] = 12;
        array[2] = 2;
        return opCode(array);
    }

    int alarmString(String input, int match) {
        String[] split = input.split(",");
        for (int noun = 0; noun < 99; noun++) {
            for (int verb = 0; verb < 99; verb++) {
                int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                array[1] = noun;
                array[2] = verb;
                int opcode = opCode(array);
                if (opcode == match) {
                    return noun * 100 + verb;
                }
            }
        }
        return -1;
    }

    private int opCode(int[] array) {
        int index = 0;
        while (array[index] != 99) {
            int opcode = array[index];
            if (opcode == 99) {
                break;
            }
            int firstPointer = array[++ index];
            int secondPointer = array[++ index];
            int valuePointer = array[++ index];
            if (firstPointer >= array.length || secondPointer >= array.length || valuePointer >= array.length) {
                break;
            }
            int firstValue = array[firstPointer];
            int secondValue = array[secondPointer];
            int value;
            if (opcode == 1) {
                value = firstValue + secondValue;
            } else if (opcode == 2) {
                value = firstValue * secondValue;
            } else {
                System.out.println("Something went wrong, unexpected opcode " + opcode + " at position " + index);
                continue;
            }

            array[valuePointer] = value;
            index++;
        }

        return array[0];
    }
}
