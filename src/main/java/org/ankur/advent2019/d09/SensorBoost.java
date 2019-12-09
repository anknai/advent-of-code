package org.ankur.advent2019.d09;

import org.ankur.advent.util.FileReader;

import java.util.Arrays;

public class SensorBoost {

    public long alarm(String inputFile, long input) {
        String s = FileReader.readFileAsString(inputFile);
        return alarmString(s, input);
    }

    long alarmString(String inputStr, long input) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 100000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        array = copy;
        input = opCode(array, input);
        return input;
    }

    private long opCode(long[] array, long input) {
        int index = 0;
        int relativeBase = 0;
        while (array[index] != 99) {
            long opcodeStr = array[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                break;
            }
            int modes = (int) opcodeStr / 100;
            int mode1 = modes % 10;
            int mode2 = (modes / 10) % 10;
            long value = 0;

            if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
                long firstValue = parameter(mode1, array, ++index, relativeBase);
                long secondValue = parameter(mode2, array, ++index, relativeBase);

                if (opcode == 1) {
                    value = firstValue + secondValue;
                } else if (opcode == 2) {
                    value = firstValue * secondValue;
                } else if (opcode == 5) {
                    if (firstValue != 0) {
                        index = (int) secondValue;
                        continue;
                    }
                } else if (opcode == 6) {
                    if (firstValue == 0) {
                        index = (int) secondValue;
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
                input = parameter(mode1, array, ++index, relativeBase);
                System.out.println("Input " + input);
            } else if (opcode == 9) {
                relativeBase += parameter(mode1, array, ++index, relativeBase);;
            }

            if (opcode != 4 && opcode != 5 && opcode != 6) {
                int valuePointer = (int) array[++index];
                if (valuePointer >= array.length || valuePointer < 0) {
                    System.out.println(opcode);
                    //System.out.println(Arrays.toString(array));
                    System.out.println(valuePointer);
                    continue;
                }
                array[valuePointer] = value;
            }

            if (opcode < 1 || opcode > 9) {
                System.out.println("Something went wrong, unexpected opcode " + opcode + " at position " + index);
            }

            index++;
        }

        return input;
    }

    private long parameter(int mode, long[] array, int index, int relativeBase) {
        int pointer;
        long value;
        if (mode == 0) {
            pointer = (int) array[index];
            assert pointer < array.length;
            value = array[pointer];
        } else if (mode == 1) {
            value = array[index];
        } else {
            pointer = (int) array[index + relativeBase];
            assert pointer < array.length && pointer >= 0;
            value = array[pointer];
        }
        return value;
    }
}
