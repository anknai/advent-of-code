package org.ankur.advent2018.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString (exclude = {"attacker", "target"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private String name;

    private GroupType groupType;

    private int units;

    private int hitPoints;

    private int attackDamage;

    private AttackType attackType;

    private int initiative;

    private List<AttackType> weakness;

    private List<AttackType> immunity;

    private Group target;

    private Group attacker;

    public Group(Group group) {
        this.name = group.name;
        this.groupType = group.groupType;
        this.units = group.units;
        this.hitPoints = group.hitPoints;
        this.attackDamage = group.attackDamage;
        this.attackType = group.attackType;
        this.initiative = group.initiative;
        this.weakness = group.weakness;
        this.immunity = group.immunity;
        this.target = group.target;
        this.attacker = group.attacker;
    }

    private List<AttackType> getWeakness() {
        if (null == weakness) {
            weakness = new ArrayList<>();
        }
        return weakness;
    }

    private List<AttackType> getImmunity() {
        if (null == immunity) {
            immunity = new ArrayList<>();
        }
        return immunity;
    }

    public void addWeakness(AttackType type) {
        if (null == weakness) {
            weakness = new ArrayList<>();
        }
        weakness.add(type);
    }

    public void addImmunity(AttackType type) {
        if (null == immunity) {
            immunity = new ArrayList<>();
        }
        immunity.add(type);
    }

    public int effectivePower() {
        return units * attackDamage;
    }

    public int mostPossibleDamage(Group target) {
        if (target.getImmunity().contains(this.attackType)) {
            return 0;
        }
        int effPower = effectivePower();
        if (target.getWeakness().contains(this.attackType)) {
            return effPower * 2;
        }
        return effPower;
    }

    public void loseUnits(int number) {
        this.units -= number;
    }

    public enum GroupType {
        IMMUNE_SYSTEM,
        INFECTION
    }

    public enum AttackType {
        FIRE,
        COLD,
        SLASHING,
        RADIATION,
        BLUDGEONING
    }
}
