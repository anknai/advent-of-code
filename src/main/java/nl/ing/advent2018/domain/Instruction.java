package nl.ing.advent2018.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class Instruction {

    private int opcode;

    private String code;

    private int a;

    private int b;

    private Register c;

    List<Register> beforeList;

    List<Register> afterList;

    public String toString() {
        return code + " " + a + " " + b + " " + c.getNumber();
    }
}
