package org.ankur.advent2018.test;

import org.ankur.advent2018.D16ChronalClassification;
import org.ankur.advent2018.domain.Instruction;
import org.ankur.advent2018.domain.Register;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D16ChronalClassificationTest {

    private final D16ChronalClassification classification = new D16ChronalClassification();

    private static final String TEST_INPUT = "16_test.txt";
    private static final String INPUT = "16_class.txt";
    private static final String INPUT_2 = "16_part_2.txt";

    @Test
    void threeOrMore() {
        int i = classification.threeOrMore(TEST_INPUT);
        assertEquals(1, i);
        i = classification.threeOrMore(INPUT);
        assertEquals(542, i);
    }

    @Test
    void parse() {
        Instruction parse = classification.parse("9 2 1 2");
        assertEquals("null 2 1 2", parse.toString());
    }

    @Test
    void parseRegister() {
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
    void part2() {
        int i = classification.part2(INPUT_2);
        assertEquals(575, i);
    }
}