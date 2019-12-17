package org.ankur.advent2019.d17;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day17 {

    int index = 0;
    int relativeBase = 0;

    public int part1(String fileName) {
        String s = FileReader.readFileAsString(fileName);
        return init(s);
    }

    public long part2(String fileName) {
        String s = FileReader.readFileAsString(fileName);
        return init2(s);
    }

    private int init(String inputStr) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 100000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        long input = 0;
        List<StringBuilder> output = new ArrayList<>();
        int count = 0;
        int line = 0;
        StringBuilder builder = new StringBuilder();
        output.add(builder);
        while (count < 10000) {
            input = opCode(copy, input);
            if (input == 10) {
                builder = new StringBuilder();
                output.add(builder);
                line++;
            } else {
                char c = (char) input;
                if (c == '#' || c == '.' || c == '^' || c == '>' || c == '<' || c == 'v') {
                    builder = output.get(line);
                    builder.append((char) input);
                } else {
                    System.out.println("Breaking on " + count);
                    break;
                }

            }
            count++;
        }
        for (StringBuilder stringBuilder : output) {
            System.out.println(stringBuilder.toString());
        }
        return 0;
    }

    private long init2(String inputStr) {
        index = 0;
        relativeBase = 0;
        //L10,R8,R6,R10,L12,R8,L12,L10,R8,R6,R10,L12,R8,L12,L10,R8,R6,R10,L8,L8,R12,R8,L12,L10,R8,R6,R10,L10,L8,L8,R10,R8,R6,R10
        String routine = "A,B,A,B,C,C,B,A,C,A\n";
        //char[] as = {76, 44, 58, 44, 82, 44, 56, 44, 82, 44, 54, 44, 82, 44, 58, 44, 10};
        String a = "L,10,R,8,R,6,R,10\n";
        //char[] bs = {76, 44, 60, 44, 82, 44, 56, 44, 76, 44, 60, 10};
        String b = "L,12,R,8,L,12\n";
        //char[] cs = {76, 44, 58, 44, 82, 44, 56, 44, 82, 44, 56, 10};
        String c = "L,10,R,8,R,8\n";
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 100000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        copy[0] = 2;
        long input;
        for (char c1 : routine.toCharArray()) {
            opCode(copy, c1);
        }
        for (char c1 : a.toCharArray()) {
            opCode(copy, c1);
        }
        for (char c1 : b.toCharArray()) {
            opCode(copy, c1);
        }
        for (char c1 : c.toCharArray()) {
            opCode(copy, c1);
        }
        opCode(copy, 'y');
        input = opCode(copy, '\n');
        return input;
    }

    private long opCode(long[] array, long input) {
        System.out.print(input + " ");
        if (array[index] == 99) {
            return -1;
        }
        while (array[index] != 99) {
            long opcodeStr = array[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                System.out.println("Breaking up now");
                return -1L;
            }
            int modes = (int) opcodeStr / 100;
            int mode1 = modes % 10;
            int mode2 = (modes / 10) % 10;
            int mode = (modes / 100 );
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
                System.out.println("input requested...");
                mode = mode1;
                value = input;
            } else if (opcode == 4) {
                input = parameter(mode1, array, ++index, relativeBase);
                System.out.println("------" + input);
                ++index;
                return input;
            } else if (opcode == 9) {
                relativeBase += parameter(mode1, array, ++index, relativeBase);;
            }

            if (opcode != 5 && opcode != 6 && opcode != 9) {
                int valuePointer = (int) array[++index];
                if (mode == 2) {
                    valuePointer += relativeBase;
                }
                assert valuePointer < array.length;
                assert valuePointer >= 0;

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
            pointer = (int) array[index] + relativeBase;
            assert pointer < array.length;
            assert pointer >= 0;
            value = array[pointer];
        }
        return value;
    }

}
