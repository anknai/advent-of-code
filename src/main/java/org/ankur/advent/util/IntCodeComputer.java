package org.ankur.advent.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IntCodeComputer {

    private int index = 0;

    private int relativeBase = 0;

    private boolean halt = false;

    private Queue<Long> inputs;

    private Queue<Long> outputs;

    private long[] copy;

    private long[] instructions;

    private boolean needsInput;

    public IntCodeComputer(String input) {
        String[] split = input.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        copy = new long[array.length + 100000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        instructions = new long[copy.length];
        System.arraycopy(copy, 0, instructions, 0, copy.length);
        outputs = new LinkedList<>();
        inputs = new LinkedList<>();
    }

    public IntCodeComputer(String input, boolean basic) {
        String[] split = input.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        copy = new long[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        instructions = new long[copy.length];
        System.arraycopy(copy, 0, instructions, 0, copy.length);
        outputs = new LinkedList<>();
        inputs = new LinkedList<>();
    }

    public void addInput(long input) {
        inputs.add(input);
    }

    public void addInput(int input) {
        inputs.add((long)input);
    }

    public void update(int index, long value) {
        copy[index] = value;
        instructions[index] = value;
    }

    public void reset() {
        index = 0;
        relativeBase = 0;
        halt = false;
        instructions = new long[copy.length];
        needsInput = false;
        System.arraycopy(copy, 0, instructions, 0, copy.length);
        outputs = new LinkedList<>();
        inputs = new LinkedList<>();
    }

    public boolean needsInput() {
        return needsInput;
    }

    public boolean running() {
        return !halt;
    }

    public long output() {
        Long output = outputs.poll();
        if (null == output) {
            System.err.println("No output to poll");
            return -1;
        }
        return output;
    }

    public boolean hasOutput() {
        return outputs.size() > 0;
    }

    public void run() {
        if (instructions[index] == 99) {
            System.err.println("halting");
            halt = true;
            return;
        }
        while (instructions[index] != 99) {
            long opcodeStr = instructions[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                System.err.println("halting");
                halt = true;
                return;
            }
            int modes = (int) opcodeStr / 100;
            int mode1 = modes % 10;
            int mode2 = (modes / 10) % 10;
            int mode = (modes / 100 );
            long value = 0;

            if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
                long firstValue = parameter(mode1, instructions, ++index, relativeBase);
                long secondValue = parameter(mode2, instructions, ++index, relativeBase);

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
                Long poll = inputs.poll();
                if (poll == null) {
                    System.err.println("Needs input");
                    needsInput = true;
                    return;
                }
                value = poll;
                mode = mode1;
            } else if (opcode == 4) {
                outputs.add(parameter(mode1, instructions, ++index, relativeBase));
            } else if (opcode == 9) {
                relativeBase += parameter(mode1, instructions, ++index, relativeBase);
            }

            if (opcode != 4 && opcode != 5 && opcode != 6 && opcode != 9) {
                int valuePointer = (int) instructions[++index];
                if (mode == 2) {
                    valuePointer += relativeBase;
                }
                assert valuePointer < instructions.length;
                assert valuePointer >= 0;

                instructions[valuePointer] = value;
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
