package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Instruction;
import org.ankur.advent2018.domain.Register;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class D19GoWithTheFlow {

    private List<Register> registers;

    private List<Instruction> instructions;

    private Set<Integer> register0;

    private int ip;

    private int bound;

    public int part1(String fileName, boolean isPart2, int max) {
        init(fileName, isPart2);
        int round = 0;
        ip = 0;
        while (ip < instructions.size() && round < max) {
            //System.out.println("Executing ip#" + ip);
            round++;
            Instruction instruction = instructions.get(ip);
            registers.get(bound).setValue(ip);
            //displayPart1(instruction);
            int result = execute(instruction);
            Register c = registers.get(instruction.getC().getNumber());
            c.setValue(result);
            ip = registers.get(bound).getValue();
            //displayRegisters();
            //System.out.println();
            if (isPart2) {
                boolean add = register0.add(registers.get(0).getValue());
                if (add) {
                    System.out.println("Round " + round + " ip " + ip);
                    displayRegisters();
                    System.out.println();
                }
            }
            ip++;
        }
        System.out.println();
        System.out.println("ip is " + ip + " after round " + round);
        return registers.get(0).getValue();
    }

    private void displayPart1(Instruction instruction) {
        //ip=0 [0, 0, 0, 0, 0, 0] seti 5 0 1 [0, 5, 0, 0, 0, 0]
        System.out.print("ip=" + ip + " ");
        displayRegisters();
        System.out.print(" " + instruction + " ");
    }

    private void displayRegisters() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (Register register: registers) {
            builder.append(register.getValue());
            builder.append(", ");
        }
        builder.deleteCharAt(builder.length() - 2);
        builder.deleteCharAt(builder.length() - 1);
        builder.append(']');
        System.out.print(builder.toString());
    }

    private void init(String fileName, boolean isPart2) {
        registers = new ArrayList<>();
        for (int i = 0; i < 6; i ++) {
            Register register = Register.builder()
                    .number(i)
                    .value(0)
                    .build();
            registers.add(register);
        }

        if (isPart2) {
            registers.get(0).setValue(1);
            register0 = new HashSet<>();
        }

        instructions = new ArrayList<>();

        List<String> lines = FileReader.readFile(fileName);
        bound = Character.getNumericValue(lines.get(0).charAt(4));
        System.out.println("Instruction Pointer bound  to " + bound);
        for (int i = 1; i < lines.size(); i++) {
            instructions.add(parse(lines.get(i)));
        }
    }

    private Instruction parse(String line) {
        //seti 5 0 1
        String[] split = line.split(" ");
        String opCode = split[0];
        int code;
        switch (opCode) {
            case "gtri":
                code = 0;
                break;
            case "bani":
                code = 1;
                break;
            case "eqrr":
                code = 2;
                break;
            case "gtir":
                code = 3;
                break;
            case "eqir":
                code = 4;
                break;
            case "bori":
                code = 5;
                break;
            case "seti":
                code = 6;
                break;
            case "setr":
                code = 7;
                break;
            case "addr":
                code = 8;
                break;
            case "borr":
                code = 9;
                break;
            case "muli":
                code = 10;
                break;
            case "banr":
                code = 11;
                break;
            case "addi":
                code = 12;
                break;
            case "eqri":
                code = 13;
                break;
            case "mulr":
                code = 14;
                break;
            case "gtrr":
                code = 15;
                break;
            default:
                code = -1;
                break;
        }

        Instruction instruction = new Instruction();
        instruction.setOpcode(code);
        instruction.setCode(opCode);
        instruction.setA(Integer.parseInt(split[1]));
        instruction.setB(Integer.parseInt(split[2]));
        Register register = Register.builder()
                .number(Integer.parseInt(split[3]))
                .build();
        instruction.setC(register);
        //System.out.println(instruction);
        return instruction;
    }

    private int execute(Instruction instruction) {
        int opCode = instruction.getOpcode();
        Register a = null;
        if (instruction.getA() < registers.size()) {
            a = registers.get(instruction.getA());
        }

        Register b = null;
        if (instruction.getB() < registers.size()) {
            b = registers.get(instruction.getB());
        }
        int aValue = instruction.getA();
        int bValue = instruction.getB();
        int value = -1;
        switch (opCode) {
            case 0:
                //0/ gtri
                if (a.getValue() > bValue) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case 1:
                //1/   bani
                value = a.getValue() & bValue;
                break;
            case 2:
                //2/ eqrr
                if (a.getValue() == b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case 3:
                //3/ gtir
                if (aValue > b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case 4:
                //4/ eqir
                if (aValue == b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case 5:
                //5/ bori
                value = a.getValue() | bValue;
                break;
            case 6:
                //6/ seti
                value = aValue;
                break;
            case 7:
                //7/ setr
                value = a.getValue();
                break;
            case 8:
                //8/ addr
                value = a.getValue() + b.getValue();
                break;
            case 9:
                //9/ borr
                value = a.getValue() | b.getValue();
                break;
            case 10:
                //10/ muli
                value = a.getValue() * bValue;
                break;
            case 11:
                //11/ banr
                value = a.getValue() & b.getValue();
                break;
            case 12:
                //12/ addi
                value = a.getValue() + bValue;
                break;
            case 13:
                //13/ eqri
                if (a.getValue() == bValue) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case 14:
                //14/ mulr
                value = a.getValue() * b.getValue();
                break;
            case 15:
                //15/gtrr
                if (a.getValue() > b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
        }
        return value;
    }


}
