package org.ankur.advent2018.test;

import org.ankur.advent2018.D24ImmuneSystemSimulator;
import org.ankur.advent2018.domain.Group;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.ankur.advent2018.domain.Group.AttackType.BLUDGEONING;
import static org.ankur.advent2018.domain.Group.AttackType.COLD;
import static org.ankur.advent2018.domain.Group.AttackType.FIRE;
import static org.ankur.advent2018.domain.Group.AttackType.RADIATION;
import static org.ankur.advent2018.domain.Group.AttackType.SLASHING;
import static org.ankur.advent2018.domain.Group.GroupType.IMMUNE_SYSTEM;
import static org.ankur.advent2018.domain.Group.GroupType.INFECTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

class D24ImmuneSystemSimulatorTest {

    private D24ImmuneSystemSimulator simulator = new D24ImmuneSystemSimulator();

    @Test
    void winner() {
        Group group1 = Group.builder()
                .name("S1")
                .groupType(IMMUNE_SYSTEM)
                .units(17)
                .hitPoints(5390)
                .attackDamage(4507)
                .attackType(FIRE)
                .initiative(2)
                .build();
        group1.addWeakness(RADIATION);
        group1.addWeakness(BLUDGEONING);

        Group group2 = Group.builder()
                .name("S2")
                .groupType(IMMUNE_SYSTEM)
                .units(989)
                .hitPoints(1274)
                .attackDamage(25)
                .attackType(SLASHING)
                .initiative(3)
                .build();
        group2.addImmunity(FIRE);
        group2.addWeakness(BLUDGEONING);
        group2.addWeakness(SLASHING);

        Group group3 = Group.builder()
                .name("I1")
                .groupType(INFECTION)
                .units(801)
                .hitPoints(4706)
                .attackDamage(116)
                .attackType(BLUDGEONING)
                .initiative(1)
                .build();
        group3.addWeakness(RADIATION);

        Group group4 = Group.builder()
                .name("I2")
                .groupType(INFECTION)
                .units(4485)
                .hitPoints(2961)
                .attackDamage(12)
                .attackType(SLASHING)
                .initiative(4)
                .build();
        group4.addImmunity(RADIATION);
        group4.addWeakness(FIRE);
        group4.addWeakness(COLD);

        List<Group> immuneArmy = new ArrayList<>();
        immuneArmy.add(group1);
        immuneArmy.add(group2);

        List<Group> infectionArmy = new ArrayList<>();
        infectionArmy.add(group3);
        infectionArmy.add(group4);

        int winner = simulator.winner(immuneArmy, infectionArmy);
        assertEquals(5216, winner);
    }

    @Test
    void winner2() {
        Group group1 = Group.builder()
                .name("S1")
                .groupType(IMMUNE_SYSTEM)
                .units(17)
                .hitPoints(5390)
                .attackDamage(6077)
                .attackType(FIRE)
                .initiative(2)
                .build();
        group1.addWeakness(RADIATION);
        group1.addWeakness(BLUDGEONING);

        Group group2 = Group.builder()
                .name("S2")
                .groupType(IMMUNE_SYSTEM)
                .units(989)
                .hitPoints(1274)
                .attackDamage(1595)
                .attackType(SLASHING)
                .initiative(3)
                .build();
        group2.addImmunity(FIRE);
        group2.addWeakness(BLUDGEONING);
        group2.addWeakness(SLASHING);

        Group group3 = Group.builder()
                .name("I1")
                .groupType(INFECTION)
                .units(801)
                .hitPoints(4706)
                .attackDamage(116)
                .attackType(BLUDGEONING)
                .initiative(1)
                .build();
        group3.addWeakness(RADIATION);

        Group group4 = Group.builder()
                .name("I2")
                .groupType(INFECTION)
                .units(4485)
                .hitPoints(2961)
                .attackDamage(12)
                .attackType(SLASHING)
                .initiative(4)
                .build();
        group4.addImmunity(RADIATION);
        group4.addWeakness(FIRE);
        group4.addWeakness(COLD);

        List<Group> immuneArmy = new ArrayList<>();
        immuneArmy.add(group1);
        immuneArmy.add(group2);

        List<Group> infectionArmy = new ArrayList<>();
        infectionArmy.add(group3);
        infectionArmy.add(group4);

        int winner = simulator.winner(immuneArmy, infectionArmy);
        assertEquals(51, winner);
    }


    @Test
    void winnerInput() {
        int winner = simulator.winner(immune(), infection());
        assertEquals(15470, winner);
    }

    @Test
    void winnerPart2() {
        int winner = simulator.part2(immune(), infection());
        assertEquals(5742, winner);
    }


    private List<Group> immune() {
        List<Group> immune = new ArrayList<>();
        Group group1 = Group.builder()
                .name("S1")
                .groupType(IMMUNE_SYSTEM)
                .units(2749)
                .hitPoints(8712)
                .attackDamage(30)
                .attackType(RADIATION)
                .initiative(18)
                .build();
        group1.addImmunity(RADIATION);
        group1.addImmunity(COLD);
        group1.addWeakness(FIRE);
        immune.add(group1);

        group1 = Group.builder()
                .name("S2")
                .groupType(IMMUNE_SYSTEM)
                .units(704)
                .hitPoints(1890)
                .attackDamage(26)
                .attackType(FIRE)
                .initiative(17)
                .build();
        immune.add(group1);

        group1 = Group.builder()
                .name("S3")
                .groupType(IMMUNE_SYSTEM)
                .units(1466)
                .hitPoints(7198)
                .attackDamage(44)
                .attackType(BLUDGEONING)
                .initiative(6)
                .build();
        group1.addImmunity(BLUDGEONING);
        group1.addWeakness(COLD);
        group1.addWeakness(SLASHING);
        immune.add(group1);

        group1 = Group.builder()
                .name("S4")
                .groupType(IMMUNE_SYSTEM)
                .units(6779)
                .hitPoints(11207)
                .attackDamage(13)
                .attackType(COLD)
                .initiative(4)
                .build();
        immune.add(group1);

        group1 = Group.builder()
                .name("S5")
                .groupType(IMMUNE_SYSTEM)
                .units(1275)
                .hitPoints(11747)
                .attackDamage(66)
                .attackType(COLD)
                .initiative(2)
                .build();
        immune.add(group1);

        group1 = Group.builder()
                .name("S6")
                .groupType(IMMUNE_SYSTEM)
                .units(947)
                .hitPoints(5442)
                .attackDamage(49)
                .attackType(RADIATION)
                .initiative(3)
                .build();
        immune.add(group1);

        group1 = Group.builder()
                .name("S7")
                .groupType(IMMUNE_SYSTEM)
                .units(4319)
                .hitPoints(2144)
                .attackDamage(4)
                .attackType(FIRE)
                .initiative(9)
                .build();
        group1.addWeakness(BLUDGEONING);
        group1.addWeakness(FIRE);
        immune.add(group1);

        group1 = Group.builder()
                .name("S8")
                .groupType(IMMUNE_SYSTEM)
                .units(6315)
                .hitPoints(5705)
                .attackDamage(7)
                .attackType(COLD)
                .initiative(16)
                .build();
        immune.add(group1);

        group1 = Group.builder()
                .name("S9")
                .groupType(IMMUNE_SYSTEM)
                .units(8790)
                .hitPoints(10312)
                .attackDamage(10)
                .attackType(FIRE)
                .initiative(5)
                .build();
        immune.add(group1);

        group1 = Group.builder()
                .name("S10")
                .groupType(IMMUNE_SYSTEM)
                .units(3242)
                .hitPoints(4188)
                .attackDamage(11)
                .attackType(BLUDGEONING)
                .initiative(14)
                .build();
        group1.addImmunity(RADIATION);
        group1.addWeakness(COLD);
        immune.add(group1);

        return immune;
    }

    private List<Group> infection() {
        List<Group> infection = new ArrayList<>();
        Group group1 = Group.builder()
                .name("I1")
                .groupType(INFECTION)
                .units(1230)
                .hitPoints(11944)
                .attackDamage(17)
                .attackType(BLUDGEONING)
                .initiative(1)
                .build();
        group1.addWeakness(COLD);
        infection.add(group1);

        group1 = Group.builder()
                .name("I2")
                .groupType(INFECTION)
                .units(7588)
                .hitPoints(53223)
                .attackDamage(13)
                .attackType(COLD)
                .initiative(12)
                .build();
        group1.addImmunity(BLUDGEONING);
        infection.add(group1);

        group1 = Group.builder()
                .name("I3")
                .groupType(INFECTION)
                .units(1887)
                .hitPoints(40790)
                .attackDamage(43)
                .attackType(FIRE)
                .initiative(15)
                .build();
        group1.addImmunity(RADIATION);
        group1.addImmunity(COLD);
        group1.addImmunity(SLASHING);
        infection.add(group1);

        group1 = Group.builder()
                .name("I4")
                .groupType(INFECTION)
                .units(285)
                .hitPoints(8703)
                .attackDamage(60)
                .attackType(SLASHING)
                .initiative(7)
                .build();
        group1.addImmunity(SLASHING);
        infection.add(group1);

        group1 = Group.builder()
                .name("I5")
                .groupType(INFECTION)
                .units(1505)
                .hitPoints(29297)
                .attackDamage(38)
                .attackType(FIRE)
                .initiative(8)
                .build();
        infection.add(group1);

        group1 = Group.builder()
                .name("I6")
                .groupType(INFECTION)
                .units(191)
                .hitPoints(24260)
                .attackDamage(173)
                .attackType(COLD)
                .initiative(20)
                .build();
        group1.addImmunity(BLUDGEONING);
        group1.addWeakness(SLASHING);
        infection.add(group1);

        group1 = Group.builder()
                .name("I7")
                .groupType(INFECTION)
                .units(1854)
                .hitPoints(12648)
                .attackDamage(13)
                .attackType(BLUDGEONING)
                .initiative(13)
                .build();
        group1.addWeakness(COLD);
        group1.addWeakness(FIRE);
        infection.add(group1);

        group1 = Group.builder()
                .name("I8")
                .groupType(INFECTION)
                .units(1541)
                .hitPoints(49751)
                .attackDamage(62)
                .attackType(SLASHING)
                .initiative(19)
                .build();
        group1.addWeakness(COLD);
        group1.addWeakness(BLUDGEONING);
        infection.add(group1);

        group1 = Group.builder()
                .name("I9")
                .groupType(INFECTION)
                .units(3270)
                .hitPoints(22736)
                .attackDamage(13)
                .attackType(SLASHING)
                .initiative(10)
                .build();
        infection.add(group1);

        group1 = Group.builder()
                .name("I10")
                .groupType(INFECTION)
                .units(1211)
                .hitPoints(56258)
                .attackDamage(73)
                .attackType(BLUDGEONING)
                .initiative(11)
                .build();
        group1.addImmunity(SLASHING);
        group1.addImmunity(COLD);
        infection.add(group1);

        return infection;
    }
}