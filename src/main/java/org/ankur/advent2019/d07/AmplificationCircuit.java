package org.ankur.advent2019.d07;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AmplificationCircuit {

    private boolean halt = false;

    private int[] ips = new int[5];

    int highest(String file) {
        return run(FileReader.readFileAsString(file));
    }

    int feedbackHighest(String file) {
        return feedbackHighestString(FileReader.readFileAsString(file));
    }

    int run(String inputStr) {
        String[] split = inputStr.split(",");
        List<String> combi = initialize(1234, 43210);
        int max = Integer.MIN_VALUE;
        for (String integer : combi) {
            int[] ints = new int[5];
            ints[0] = integer.charAt(0) - 48;
            ints[1] = integer.charAt(1) - 48;
            ints[2] = integer.charAt(2) - 48;
            ints[3] = integer.charAt(3) - 48;
            ints[4] = integer.charAt(4) - 48;
            int input = 0;
            for (int phase : ints) {
                int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                input = opCode(array, phase, input);

            }
            if (input > max) {
                max = input;
                //System.out.println(input + " " + integer);
            }
        }
        return max;
    }

    int feedbackHighestString(String inputStr) {
        String[] split = inputStr.split(",");
        List<String> combi = initialize(56789, 98765);
        int max = Integer.MIN_VALUE;
        for (String integer : combi) {
            System.out.println("combi = " + integer);
            halt = false;
            int[] ints = new int[5];
            ints[0] = integer.charAt(0) - 48;
            ints[1] = integer.charAt(1) - 48;
            ints[2] = integer.charAt(2) - 48;
            ints[3] = integer.charAt(3) - 48;
            ints[4] = integer.charAt(4) - 48;

            int[] ampA = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            int[] ampB = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            int[] ampC = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            int[] ampD = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            int[] ampE = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < 5; i++) {
                ips[i] = 0;
            }
            int input = 0;
            input = opCode2(ampA, ints[0], 0, input);
            input = opCode2(ampB, ints[1], 1, input);
            input = opCode2(ampC, ints[2], 2, input);
            input = opCode2(ampD, ints[3], 3, input);
            input = opCode2(ampE, ints[4], 4, input);
            System.out.println(Arrays.toString(ampA));
            System.out.println(Arrays.toString(ampB));
            System.out.println(Arrays.toString(ampC));
            System.out.println(Arrays.toString(ampD));
            System.out.println(Arrays.toString(ampE));
            while (!halt) {
                input = opCode2(ampA, input, 0, 0);
                input = opCode2(ampB, input, 1, 0);
                input = opCode2(ampC, input, 2, 0);
                input = opCode2(ampD, input, 3, 0);
                input = opCode2(ampE, input, 4, 0);
            }
            max = Math.max(input, max);
            System.out.println(max);
        }
        return max;
    }

    private List<String> initialize(int min, int max) {
        List<String> combi = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            Set<Integer> list = new HashSet<>();
            list.add(5);
            list.add(6);
            list.add(7);
            list.add(8);
            list.add(9);
            //42301
            int _1 = i % 10;
            int _2 = (i / 10) % 10;
            int _3 = (i / 100) % 10;
            int _4 = (i / 1000) % 10;
            int _5 = (i / 10000) % 10;
            if (list.remove(_1) && list.remove(_2) && list.remove(_3) && list.remove(_4) && list.remove(_5)) {
                String s = String.valueOf(i);
                if (s.length() == 4) {
                    s = "0" + s;
                }
                combi.add(s);
                //System.out.println(s);
            }
        }
        return combi;
    }

    private int opCode2(int[] array, int input, int ip, int input2) {
        int index = ips[ip];
        int occur = 0;
        while (true) {
            int opcodeStr = array[index];
            int opcode = opcodeStr % 100;
            if (opcode == 99) {
                halt = true;
                break;
            }
            int modes = opcodeStr / 100;
            int mode1 = modes % 10;
            int mode2 = (modes / 10) % 10;

            int value = 0;
            if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
                int firstValue = parameter(mode1, array, ++index);
                int secondValue = parameter(mode2, array, ++index);

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
                if (occur == 0) {
                    value = input;
                    occur++;
                } else {
                    //System.out.println("Using second value " + input2);
                    value = input2;
                }
            } else if (opcode == 4) {
                int valuePointer = array[++index];
                assert valuePointer < array.length;
                ips[ip] = ++index;
                //System.out.println(ips[ip] + " index " + array[valuePointer]);
                return array[valuePointer];
            }

            if (opcode != 5 && opcode != 6) {
                int valuePointer = array[++index];
                if (valuePointer >= array.length) {
                    System.out.println("BREAK " + opcode + " " + Arrays.toString(array) + " " + valuePointer);
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

    private int opCode(int[] array, int input1, int input2) {
        int index = 0;
        int occur = 0;
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
                if (occur == 0) {
                    value = input1;
                    occur++;
                } else {
                    value = input2;
                }
            } else if (opcode == 4) {
                int valuePointer = array[++index];
                assert valuePointer < array.length;
                input1 = array[valuePointer];
            }

            if (opcode != 4 && opcode != 5 && opcode != 6) {
                int valuePointer = array[++index];
                if (valuePointer >= array.length) {
                    System.out.println("BREAK " + opcode + " " + Arrays.toString(array) + " " + valuePointer);
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

        return input1;
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
