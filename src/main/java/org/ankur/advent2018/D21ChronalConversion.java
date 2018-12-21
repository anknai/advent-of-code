package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Instruction;
import org.ankur.advent2018.domain.Register;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class D21ChronalConversion {

    private List<Register> registers;

    private List<Instruction> instructions;

    private int bound;

    public int part1(String fileName, int register0, int max) {
        init(fileName, register0);
        int ip = 0;
        int round = 0;
        while (ip < instructions.size()) {
            ip = 0;
            round = 0;
            while (ip < instructions.size() && round < max) {
                round++;
                Instruction instruction = instructions.get(ip);
                registers.get(bound).setValue(ip);
                int result = D19GoWithTheFlow.execute(instruction, registers);
                Register c = registers.get(instruction.getC().getNumber());
                c.setValue(result);
                ip = registers.get(bound).getValue();
                ip++;
            }
            reset(++register0);
        }
        --register0;
        System.out.println("Using " + register0 + " as register 0 value ");
        System.out.println("ip is " + ip + " after round " + round);
        return register0;
    }

    public int part2(String fileName, int register0, int inst, int max) {
        init(fileName, register0);
        int ip = 0;
        int round = inst;
        int previousMax = inst;
        while (true) {
            ip = 0;
            round = 0;
            while (ip < instructions.size() && round < max) {
                round++;
                Instruction instruction = instructions.get(ip);
                registers.get(bound).setValue(ip);
                int result = D19GoWithTheFlow.execute(instruction, registers);
                Register c = registers.get(instruction.getC().getNumber());
                c.setValue(result);
                ip = registers.get(bound).getValue();
                ip++;
            }
            if (register0 % 1_00_000 == 0) {
                System.out.println("Using " + register0 + " as register 0 value ");
                System.out.println("ip is " + ip + " after round " + round);
            }
            if (round < previousMax) {
                break;
            } else if (round > previousMax && round != max) {
                previousMax = round;
            }
            reset(++register0);
        }
        --register0;
        System.out.println("Using " + register0 + " as register 0 value ");
        System.out.println("ip is " + ip + " after round " + round);
        return register0;
    }

    private void init(String fileName, int zeroValue) {
        registers = new ArrayList<>();
        for (int i = 0; i < 6; i ++) {
            Register register = Register.builder()
                    .number(i)
                    .value(0)
                    .build();
            registers.add(register);
        }

        registers.get(0).setValue(zeroValue);

        instructions = new ArrayList<>();

        List<String> lines = FileReader.readFile(fileName);
        bound = Character.getNumericValue(lines.get(0).charAt(4));
        for (int i = 1; i < lines.size(); i++) {
            instructions.add(D19GoWithTheFlow.parse(lines.get(i)));
        }
    }

    private void reset(int register0) {
        registers.get(0).setValue(register0);
        for (int i = 1; i < 6; i++) {
            registers.get(i).setValue(0);
        }
    }
}
