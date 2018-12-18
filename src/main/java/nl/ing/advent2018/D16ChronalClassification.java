package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;
import nl.ing.advent2018.domain.Instruction;
import nl.ing.advent2018.domain.Register;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class D16ChronalClassification {

    private List<Set<String>> scores;

    public int threeOrMore(String fileName) {
        return init(fileName);
    }

    private int init(String fileName) {
        List<String> lines = FileReader.readFile(fileName);
        Iterator<String> iterator = lines.iterator();
        int count = 0;
        scores = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            scores.add(setTheSet());
        }
        while (iterator.hasNext()) {
            List<Register> beforeList = parseRegister(iterator.next());
            Instruction instruction = parse(iterator.next());
            List<Register> afterList = parseRegister(iterator.next());
            instruction.setAfterList(afterList);
            instruction.setBeforeList(beforeList);
            if (execute(instruction) >= 3) {
                count++;
            }
            iterator.next();
        }

        for (int i = 0; i < scores.size(); i++) {
            Set<String> match = scores.get(i);
            Iterator<String> iterator1 = match.iterator();
            System.out.print(i + "/ ");
            while (iterator1.hasNext()) {
                System.out.print(iterator1.next() + " ");
            }
            System.out.println();
        }
        return count;
    }

    private Set<String> setTheSet() {
        Set<String> set = new HashSet<>();
        set.add("addi");
        set.add("addr");
        set.add("muli");
        set.add("mulr");
        set.add("banr");
        set.add("bani");
        set.add("borr");
        set.add("bori");
        set.add("setr");
        set.add("seti");
        set.add("gtir");
        set.add("gtri");
        set.add("gtrr");
        set.add("eqir");
        set.add("eqri");
        set.add("eqrr");
        return set;
    }

    private int execute(Instruction instruction) {
        int a = instruction.getA();
        Register aRegister = instruction.getBeforeList().get(a);
        int b = instruction.getB();
        Register bRegister = instruction.getBeforeList().get(b);
        Register cRegister = instruction.getC();
        int c = cRegister.getNumber();
        int cValue = instruction.getAfterList().get(c).getValue();
        int count = 0;
        int op = instruction.getOpcode();
        Set<String> match = new HashSet<>();
        if (cValue == registerImmediate(aRegister, b, c, "add")) {
            match.add("addi");
            count++;
        }
        if (cValue == registerImmediate(aRegister, b, c, "mul")) {
            match.add("muli");
            count++;
        }
        if (cValue == registerImmediate(aRegister, b, c, "ban")) {
            match.add("bani");
            count++;
        }
        if (cValue == registerImmediate(aRegister, b, c, "bor")) {
            match.add("bori");
            count++;
        }
        if (cValue == registerImmediate(aRegister, a, c, "set")) {
            match.add("seti");
            count++;
        }
        if (cValue == registerImmediate(aRegister, b, c, "gtr")) {
            match.add("gtri");
            count++;
        }
        if (cValue == registerImmediate(aRegister, b, c, "eqr")) {
            match.add("eqri");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "add")) {
            match.add("addr");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "mul")) {
            match.add("mulr");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "ban")) {
            match.add("banr");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "bor")) {
            match.add("borr");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "set")) {
            match.add("setr");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "gtr")) {
            match.add("gtrr");
            count++;
        }
        if (cValue == register(aRegister, bRegister, c, "eqr")) {
            match.add("eqrr");
            count++;
        }
        if (cValue == immediateRegister(a, bRegister, c, "gt")) {
            match.add("gtir");
            count++;
        }
        if (cValue == immediateRegister(a, bRegister, c, "eq")) {
            match.add("eqir");
            count++;
        }
        scores.get(op).retainAll(match);
        return count;
    }

    public Instruction parse(String line) {
        //9 2 1 2
        String[] split = line.split(" ");
        Instruction instruction = new Instruction();
        instruction.setOpcode(Integer.parseInt(split[0]));
        instruction.setA(Integer.parseInt(split[1]));
        instruction.setB(Integer.parseInt(split[2]));
        Register register = Register.builder()
                .number(Integer.parseInt(split[3]))
                .build();
        instruction.setC(register);
        //System.out.println(instruction);
        return instruction;
    }

    public List<Register> parseRegister(String line) {
        //Before: [3, 2, 1, 1]
        line = line.replace("Before: [", "");
        line = line.replace("After:  [", "");
        line = line.replace("]", "");
        String[] splits = line.split(", ");
        List<Register> list = new ArrayList<>();
        for (int i = 0; i < splits.length; i ++) {
            Register register = Register.builder()
                    .number(i)
                    .value(Integer.parseInt(splits[i]))
                    .build();
            list.add(register);
        }
        return list;
    }

    private int registerImmediate(Register a, int b, int c, String type) {
        Register register = Register.builder()
                .number(c)
                .build();
        int value = -1;
        switch (type) {
            case "add":
                value = a.getValue() + b;
                break;
            case "mul":
                value = a.getValue() * b;
                break;
            case "ban":
                value = a.getValue() & b;
                break;
            case "bor":
                value = a.getValue() | b;
                break;
            case "set":
                value = b;
                break;
            case "gtr":
                if (a.getValue() > b) {
                    value = 1;
                } else {
                    value = 0;
                } 
                break;
            case "eqr":
                if (a.getValue() == b) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            default:
                assert false;
        }
        register.setValue(value);
        //System.out.println(type + "i " + register);
        return value;
    }

    private int immediateRegister(int a, Register b, int c, String type) {
        Register register = Register.builder()
                .number(c)
                .build();
        int value = -1;
        switch (type) {
            case "gt":
                if (a > b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case "eq":
                if (a == b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            default:
                assert false;
        }
        register.setValue(value);
        //System.out.println(type + "ir " + register);
        return value;
    }

    private int register(Register a, Register b, int c, String type) {
        Register register = Register.builder()
                .number(c)
                .build();
        int value = -1;
        switch (type) {
            case "add":
                value = a.getValue() + b.getValue();
                break;
            case "mul":
                value = a.getValue() * b.getValue();
                break;
            case "ban":
                value = a.getValue() & b.getValue();
                break;
            case "bor":
                value = a.getValue() | b.getValue();
                break;
            case "set":
                value = a.getValue();
                break;
            case "gtr":
                if (a.getValue() > b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            case "eqr":
                if (a.getValue() == b.getValue()) {
                    value = 1;
                } else {
                    value = 0;
                }
                break;
            default:
                assert false;
        }
        register.setValue(value);
        //System.out.println(type + "r " + register);
        return value;
    }

    public int part2(String fileName) {
        List<String> lines = FileReader.readFile(fileName);
        List<Register> registers = parseRegister("After:  [2, 0, 0, 3]");
        for (String line : lines) {
            Instruction instruction = parse(line);
            instruction.setBeforeList(registers);
            registers = executePart2(instruction);
        }

        return registers.get(0).getValue();
    }

    private List<Register> executePart2(Instruction  instruction) {
        int a = instruction.getA();
        Register aRegister = instruction.getBeforeList().get(a);
        int b = instruction.getB();
        Register bRegister = instruction.getBeforeList().get(b);
        Register cRegister = instruction.getC();
        int c = cRegister.getNumber();
        int cValue = 0;
        switch (instruction.getOpcode()) {
            case 0:
                //0/ gtri
                cValue = registerImmediate(aRegister, b, c, "gtr");
                break;
            case 1:
                //1/   bani
                cValue = registerImmediate(aRegister, b, c, "ban");
                break;
            case 2:
                //2/ eqrr
                cValue = register(aRegister, bRegister, c, "eqr");
                break;
            case 3:
                //3/ gtir
                cValue = immediateRegister(a, bRegister, c, "gt");
                break;
            case 4:
                //4/ eqir
                cValue = immediateRegister(a, bRegister, c, "eq");
                break;
            case 5:
                //5/ bori
                cValue = registerImmediate(aRegister, b, c, "bor");
                break;
            case 6:
                //6/ seti
                cValue = registerImmediate(aRegister, a, c, "set");
                break;
            case 7:
                //7/ setr
                cValue = register(aRegister, bRegister, c, "set");
                break;
            case 8:
                //8/ addr
                cValue = register(aRegister, bRegister, c, "add");
                break;
            case 9:
                //9/ borr
                cValue = register(aRegister, bRegister, c, "bor");
                break;
            case 10:
                //10/ muli
                cValue = registerImmediate(aRegister, b, c, "mul");
                break;
            case 11:
                //11/ banr
                cValue = register(aRegister, bRegister, c, "ban");
                break;
            case 12:
                //12/ addi
                cValue = registerImmediate(aRegister, b, c, "add");
                break;
            case 13:
                //13/ eqri
                cValue = registerImmediate(aRegister, b, c, "eqr");
                break;
            case 14:
                //14/ mulr
                cValue = register(aRegister, bRegister, c, "mul");
                break;
            case 15:
                //15/gtrr
                cValue = register(aRegister, bRegister, c, "gtr");
                break;
        }
        cRegister.setValue(cValue);
        instruction.getBeforeList().set(cRegister.getNumber(), cRegister);
        return instruction.getBeforeList();
    }
}
