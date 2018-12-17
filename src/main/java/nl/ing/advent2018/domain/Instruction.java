package nl.ing.advent2018.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(exclude = {"beforeList", "afterList"})
public class Instruction {

    private int opcode;

    private int a;

    private int b;

    private Register c;

    List<Register> beforeList;

    List<Register> afterList;
}
