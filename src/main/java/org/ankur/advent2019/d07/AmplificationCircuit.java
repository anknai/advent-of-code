package org.ankur.advent2019.d07;

import org.ankur.advent.util.FileReader;

import java.util.*;

public class AmplificationCircuit {

    private boolean halt = false;

    private int[] ips = new int[5];

    int alarm(String file) {
        return alarmString(FileReader.readFileAsString(file));
    }

    int alarmString(String inputStr) {
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

    long alarmString2(String inputStr) {
        String[] split = inputStr.split(",");
        List<String> combi = initialize(56789, 98765);
        long max = Long.MIN_VALUE;
        for (String integer : combi) {
            integer = "97856";
            System.out.println("combi = " + integer);
            halt = false;
            int[] ints = new int[5];
            ints[0] = integer.charAt(0) - 48;
            ints[1] = integer.charAt(1) - 48;
            ints[2] = integer.charAt(2) - 48;
            ints[3] = integer.charAt(3) - 48;
            ints[4] = integer.charAt(4) - 48;

            long[] amp1 = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
            long[] amp2 = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
            long[] amp3 = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
            long[] amp4 = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
            long[] amp5 = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
            for (int i = 0; i < 5; i++) {
                ips[i] = 0;
            }
            opCode2(amp1, ints[0], 0);
            opCode2(amp2, ints[1], 1);
            opCode2(amp3, ints[2], 2);
            opCode2(amp4, ints[3], 3);
            opCode2(amp5, ints[4], 4);
            long input = 0;
            while (!halt) {
                //System.out.println("Not halted yet " + integer + " " + input);
                input = opCode2(amp1, input, 0);
                input = opCode2(amp2, input, 1);
                input = opCode2(amp3, input, 2);
                input = opCode2(amp4, input, 3);
                input = opCode2(amp5, input, 4);
            }
            if (input > max) {
                max = input;
                System.out.println(input + " " + integer);
            }
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

    private long opCode2(long[] array, long input, int ip) {
        int index = ips[ip];
        while (array[index] != 99) {
            long opcodeStr = array[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                System.out.println("Halting on " + input);
                halt = true;
                break;
            }
            long modes = opcodeStr / 100;
            long mode1 = modes % 10;
            long mode2 = (modes / 10) % 10;

            long value = 0;
            if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
                long firstValue = parameter(mode1, array, ++index);
                long secondValue = parameter(mode2, array, ++index);

                if (opcode == 1) {
                    value = firstValue + secondValue;
                } else if (opcode == 2) {
                    value = firstValue * secondValue;
                } else if (opcode == 5) {
                    if (firstValue != 0) {
                        index = (int)secondValue;
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
                int valuePointer = (int) array[++index];
                assert valuePointer < array.length;
                ips[ip] = ++index;
                System.out.println(ips[ip] + " index " + array[valuePointer]);
                return array[valuePointer];
            }

            if (opcode != 5 && opcode != 6) {
                int valuePointer = (int) array[++index];
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

    private long parameter(long mode, long[] array, int index) {
        int pointer;
        long value;
        if (mode == 0) {
            pointer = (int) array[index];
            assert pointer < array.length;
            value = array[pointer];
        } else {
            value = array[index];
        }
        return value;
    }
}
