package org.ankur.advent2019.d05;

import org.ankur.advent.util.FileReader;

import java.util.Arrays;

public class AsteroidChance {

    public int alarm(String inputFile, int input) {
        String s = FileReader.readFileAsString(inputFile);
        return alarmString(s, input);
    }

    int alarmString(String inputStr, int input) {
        String[] split = inputStr.split(",");
        int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        return opCode(array, input);
    }

    private int opCode(int[] array, int input) {
        int index = 0;
        while (array[index] != 99) {
            int opcodeStr = array[index];
            int opcode = opcodeStr % 100;
            if (opcode == 99) {
                break;
            }
            int modes = opcodeStr / 100;
            int mode1 = modes % 10;
            int mode2 = (modes / 10) % 10;
            /*System.out.println("opcode = " + opcode);
            System.out.println("mode1 = " + mode1);
            System.out.println("mode2 = " + mode2);*/
            int value = 0;
            if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
                int firstValue = parameter(mode1, array, ++index);
                int secondValue = parameter(mode2, array, ++index);

                /*System.out.println("firstValue = " + firstValue);
                System.out.println("secondValue = " + secondValue);*/
                if (opcode == 1) {
                    value = firstValue + secondValue;
                } else if (opcode == 2) {
                    value = firstValue * secondValue;
                } else if (opcode == 5) {
                    if (firstValue != 0) {
                        index = secondValue;
                        continue;
                    }
                } else if (opcode == 6) {
                    if (firstValue == 0) {
                        index = secondValue;
                        continue;
                    }
                } else if (opcode == 7) {
                    if (firstValue < secondValue) {
                        value = 1;
                    }
                } else {
                    if (firstValue == secondValue) {
                        value = 1;
                    }
                }
            }

            if (opcode == 3) {
                value = input;
            } else if (opcode == 4) {
                int valuePointer = array[++index];
                assert valuePointer < array.length;
                input = array[valuePointer];
            }

            if (opcode != 4 && opcode != 5 && opcode != 6) {
                int valuePointer = array[++index];
                if (valuePointer >= array.length) {
                    System.out.println(opcode);
                    System.out.println(Arrays.toString(array));
                    System.out.println(valuePointer);
                    break;
                }
                array[valuePointer] = value;
            }

            if (opcode < 1 || opcode > 8) {
                System.out.println("Something went wrong, unexpected opcode " + opcode + " at position " + index);
                continue;
            }

            index++;
        }

        return input;
    }

    private int parameter(int mode, int[] array, int index) {
        int pointer;
        int value;
        if (mode == 0) {
            pointer = array[index];
            assert pointer < array.length;
            value = array[pointer];
        } else {
            value = array[index];
        }
        return value;
    }
}
