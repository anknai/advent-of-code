package org.ankur.advent2019.d17;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetAndForget {

    private int index = 0;
    private int relativeBase = 0;
    private boolean halt = false;
    private int j = 0;
    private long output;

    public int part1(String fileName) {
        String s = FileReader.readFileAsString(fileName);
        return init(s);
    }

    public long part2(String fileName) {
        String s = FileReader.readFileAsString(fileName);
        init2(s);
        return output;
    }

    private int init(String inputStr) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 100000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        List<StringBuilder> stringBuilders = new ArrayList<>();
        int line = 0;
        StringBuilder builder = new StringBuilder();
        stringBuilders.add(builder);
        output = 0;
        boolean more = true;
        long previous = 0L;
        while (more) {
            opCode(copy, new int[]{0});
            if (output == 10) {
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
        index = 0;
        relativeBase = 0;
        int[] inputs = new int[100];
        //L10,R8,R6,R10,L12,R8,L12,L10,R8,R6,R10,L12,R8,L12,L10,R8,R6,R10,L8,L8,R12,R8,L12,L10,R8,R6,R10,L10,L8,L8,R10,R8,R6,R10
        String routine = "A,B,A,B,C,C,B,A,C,A\n";
        String a = "L,10,R,8,R,6,R,10\n";
        String b = "L,12,R,8,L,12\n";
        String c = "L,10,R,8,R,8\n";
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 1000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        copy[0] = 2;
        int i = 0;
        for (char c1 : routine.toCharArray()) {
            inputs[i++] = c1;
        }
        for (char c1 : a.toCharArray()) {
            inputs[i++] = c1;
        }
        for (char c1 : b.toCharArray()) {
            inputs[i++] = c1;
        }
        for (char c1 : c.toCharArray()) {
            inputs[i++] = c1;
        }
        inputs[i++] = 'y';
        inputs[i] = '\n';
        halt = false;
        while (!halt) {
            opCode(copy, inputs);
        }
    }

    private void opCode(long[] array, int[] inputs) {
        if (array[index] == 99) {
            halt = true;
            return;
        }
        while (array[index] != 99) {
            long opcodeStr = array[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                System.out.println("Breaking up now");
                halt = true;
                break;
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
                value = inputs[j++];
                mode = mode1;
            } else if (opcode == 4) {
                output = parameter(mode1, array, ++index, relativeBase);
                index++;
                return;
            } else if (opcode == 9) {
                relativeBase += parameter(mode1, array, ++index, relativeBase);
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
