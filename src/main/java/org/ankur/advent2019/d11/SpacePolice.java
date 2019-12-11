package org.ankur.advent2019.d11;

import org.ankur.advent.util.FileReader;

import java.util.*;

public class SpacePolice {

    public long alarm(String inputFile) {
        String s = FileReader.readFileAsString(inputFile);
        return alarmString(s, 0);
    }

    long alarmString(String inputStr, long input) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 1000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        Map<Integer, Panel> indexMap = new HashMap<>();
        int x = 500;
        int y = 500;
        Panel panel = new Panel(x, y, false, 0);
        //panels.add(panel);
        indexMap.put(x * 1000 + y, panel);
        //direction UP, LEFT, RIGHT, DOWN
        char direction = '^';
        while(input != -1) {
            System.out.println("Working on panel " + panel + " direction " + direction);
            input = opCode(copy, panel.getColor());
            panel.setColor((int)input);
            input = opCode(copy, input);
            if (input == -1) {
                System.out.println("Ending");
                return indexMap.size();
            }
            //direction
            if (input == 0) {
                switch (direction) {
                    case '^':
                        direction = '<';
                        x--;
                        break;
                    case '<':
                        direction = 'v';
                        y++;
                        break;
                    case 'v':
                        direction = '>';
                        x++;
                        break;
                    case '>':
                        direction = '^';
                        y--;
                        break;
                }
            } else if (input == 1) {
                switch (direction) {
                    case '^':
                        direction = '>';
                        x++;
                        break;
                    case '<':
                        direction = '^';
                        y--;
                        break;
                    case 'v':
                        direction = '<';
                        x--;
                        break;
                    case '>':
                        direction = 'v';
                        y++;
                        break;
                }
            }
            int index = x * 1000 + y;
            panel = indexMap.get(index);
            if (panel == null) {
                panel = new Panel(x, y, false, 0);
                indexMap.put(x * 1000 + y, panel);
                //panels.add(panel);
            } else {
                panel.setPainted(true);
            }
        }
        return indexMap.size();
    }

    private long opCode(long[] array, long input) {
        int index = 0;
        int relativeBase = 0;
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
                mode = mode1;
                value = input;
            } else if (opcode == 4) {
                input = parameter(mode1, array, ++index, relativeBase);
            } else if (opcode == 9) {
                relativeBase += parameter(mode1, array, ++index, relativeBase);;
            }

            if (opcode != 4 && opcode != 5 && opcode != 6 && opcode != 9) {
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
