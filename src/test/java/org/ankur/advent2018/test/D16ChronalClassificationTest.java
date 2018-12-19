package org.ankur.advent2018.test;

import org.ankur.advent2018.D16ChronalClassification;
import org.ankur.advent2018.domain.Instruction;
import org.ankur.advent2018.domain.Register;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class D16ChronalClassificationTest {

    private D16ChronalClassification classification = new D16ChronalClassification();

    private static final String TEST_INPUT = "16_test.txt";
    private static final String INPUT = "16_class.txt";
    private static final String INPUT_2 = "16_part_2.txt";

    @Test
    public void threeOrMore() {
        int i = classification.threeOrMore(TEST_INPUT);
        assertEquals("Count is ", 1, i);
        i = classification.threeOrMore(INPUT);
        assertEquals("Count is ", 542, i);
    }

    @Test
    public void parse() {
        Instruction parse = classification.parse("9 2 1 2");
        System.out.println(parse);
    }

    @Test
    public void parseRegister() {
        List<Register> registers = classification.parseRegister("After:  [3, 2, 2, 1]");
        for (Register register: registers) {
            System.out.println(register);
        }

        registers = classification.parseRegister("Before: [3, 2, 1, 1]");
        for (Register register: registers) {
            System.out.println(register);
        }
    }

    @Test
    public void part2() {
        int i = classification.part2(INPUT_2);
        assertEquals("Value is ", 575, i);

    }
}