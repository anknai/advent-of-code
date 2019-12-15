package org.ankur.advent2019.d13;

import org.ankur.advent.util.FileReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CarePackage {

    private int index = 0;

    private int relativeBase = 0;
    private boolean halt;

    public long blockTiles(String inputFile) {
        index = 0;
        relativeBase = 0;
        String s = FileReader.readFileAsString(inputFile);
        return run(s, 0);
    }

    public long score(String inputFile) {
        index = 0;
        relativeBase = 0;
        String s = FileReader.readFileAsString(inputFile);
        return run2(s, 2);
    }

    long run2(String inputStr, long input) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 1000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        long output = input;
        Set<Item> items = new HashSet<>();
        Item ball = null;
        Item paddle = null;
        long score = 0;
        copy[0] = 2;
        while (!halt) {
            output = opCode(copy, output);
            long x = output;
            output = opCode(copy, output);
            long y = output;
            output = opCode(copy, output);
            long z = output;
            //System.out.println(x + " " + y + " z" + z);
            if (z == 4) {
                //System.out.println("BBBBBBBBB Ball at " + x + " " + y);
                ball = new Item(x, y, 'b');
                if (items.contains(ball)) {
                    System.out.println("Removing block from " + x + " " + y);
                    items.remove(ball);
                }
            } else if (z == 3) {
                //System.out.println("PPPPPPPPP Paddel at " + x + " " + y);
                paddle = new Item(x, y, 'p');
            } else if (z == 2) {
                //System.out.println("Block at " + x + " " + y);
                Item block = new Item(x, y, '#');
                items.add(block);
            }
            if (null != paddle && null != ball) {
                if (ball.getX() < paddle.getX()) {
                    output = -1;
                } else if (ball.getX() == paddle.getX()) {
                    output = 0;
                } else {
                    output = 1;
                }
            }
            if (x == -1 && y == 0) {
                System.out.println("New Score " + score);
                score = z;
            }
        }
        return score;
    }

    long run(String inputStr, long input) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 1000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        long output = input;
        int count = 0;
        while (!halt) {
            output = opCode(copy, output);
            long x = output;
            output = opCode(copy, output);
            long y = output;
            output = opCode(copy, output);
            long z = output;
            System.out.println(x + " " + y + " " + z);
            if (z == 2) {
                count++;
            }
        }
        return count;
    }

    private long opCode(long[] array, long input) {
        if (array[index] == 99) {
            System.out.println("Halting");
            halt = true;
            return -1L;
        }
        while (array[index] != 99) {
            long opcodeStr = array[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                halt = true;
                System.out.println("Breaking up now");
                return -1;
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
                mode = mode1;
                value = input;
            } else if (opcode == 4) {
                input = parameter(mode1, array, ++index, relativeBase);
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
