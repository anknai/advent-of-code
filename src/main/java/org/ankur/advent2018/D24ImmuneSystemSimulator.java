package org.ankur.advent2018;

import org.ankur.advent2018.domain.Group;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class D24ImmuneSystemSimulator {

    private int immuneCount;
    private int infectionCount;
    private int round;
    private boolean targetFound;

    public int winner(List<Group> immune, List<Group> infection) {
        immuneCount = immune.size();
        infectionCount = infection.size();
        List<Group> armies = new ArrayList<>(immune);
        armies.addAll(infection);
        round = 1;
        targetFound = true;
        simulate(armies);
        int remaining = 0;
        for (Group group : armies) {
            remaining += group.getUnits();
        }
        return remaining;
    }

    public int part2(List<Group> immune, List<Group> infection) {
        int boost = 1;
        int remaining = 0;
        List<Group> originalImmune = immune.stream().map(Group::new).collect(Collectors.toList());
        List<Group> originalInfection = infection.stream().map(Group::new).collect(Collectors.toList());
        int lastFailure = -1;
        int lastSuccess = -1;
        while (lastSuccess - lastFailure != 1) {
            immuneCount = immune.size();
            infectionCount = infection.size();
            if (lastSuccess == -1) {
                boost *= 2;
            } else {
                boost = (lastFailure + lastSuccess) / 2;
            }
            for (Group immunity : immune) {
                immunity.setAttackDamage(immunity.getAttackDamage() + boost);
            }

            List<Group> armies = new ArrayList<>(immune);
            armies.addAll(infection);

            round = 1;
            targetFound = true;
            simulate(armies);
            System.out.println("Using boost " + boost + " took " + round + " rounds " + immuneCount + " " + infectionCount);
            if (infectionCount > 0) {
                lastFailure = boost;
            } else {
                lastSuccess = boost;
                remaining = 0;
                for (Group group : armies) {
                    remaining += group.getUnits();
                }
            }

            immune = originalImmune.stream().map(Group::new).collect(Collectors.toList());
            infection = originalInfection.stream().map(Group::new).collect(Collectors.toList());
        }

        return remaining;
    }

    private void simulate(List<Group> armies) {
        while (targetFound) {
            targetFound = false;
            targetSelection(armies);
            attack(armies);
            round++;
        }
    }

    private void targetSelection(List<Group> army) {
        Comparator<Group> targetComparator = new EffectivePowerComparator();
        TreeSet<Group> targetSelectionGroups = new TreeSet<>(targetComparator);

        targetSelectionGroups.addAll(army);

        //Reset
        for (Group group: targetSelectionGroups) {
            group.setTarget(null);
            group.setAttacker(null);
        }

        for (Group attacker: targetSelectionGroups) {
            int damageToTarget = 0;
            int effPowerOfTarget = 0;
            int initiativeOfTarget = 0;
            Group target = null;
            for (Group enemy : targetSelectionGroups) {
                if (enemy == attacker || enemy.getGroupType() == attacker.getGroupType() || null != enemy.getAttacker()) {
                    continue;
                }

                int damageToEnemy = attacker.mostPossibleDamage(enemy);
                if (damageToEnemy > damageToTarget) {
                    target = enemy;
                    damageToTarget = damageToEnemy;
                    effPowerOfTarget = enemy.effectivePower();
                    initiativeOfTarget = enemy.getInitiative();
                } else if (damageToEnemy != 0 && damageToEnemy == damageToTarget) {
                    if (enemy.effectivePower() > effPowerOfTarget) {
                        target = enemy;
                        effPowerOfTarget = enemy.effectivePower();
                        initiativeOfTarget = enemy.getInitiative();
                    } else if (enemy.effectivePower() == effPowerOfTarget) {
                        if (enemy.getInitiative() > initiativeOfTarget) {
                            target = enemy;
                            initiativeOfTarget = enemy.getInitiative();
                        }
                    }
                }

            }
            attacker.setTarget(target);
            if (null != target) {
                target.setAttacker(attacker);
            }
        }
    }

    private void attack(List<Group> army) {
        Comparator<Group> initiativeComparator = new InitiativeComparator();
        TreeSet<Group> attackers = new TreeSet<>(initiativeComparator);
        attackers.addAll(army);

        for (Group attacker: attackers) {
            Group target = attacker.getTarget();
            if (null == target || attacker.getUnits() <= 0) {
                continue;
            }

            int damage = attacker.mostPossibleDamage(target);
            int lose = damage / target.getHitPoints();
            target.loseUnits(lose);
            //Only if you are able to inflict damage, mark targetFound as true
            if (lose > 0) {
                targetFound = true;
            }
            if (target.getUnits() <= 0) {
                army.remove(target);
                if (target.getGroupType() == Group.GroupType.IMMUNE_SYSTEM) {
                    immuneCount--;
                } else {
                    infectionCount--;
                }
            }
        }
    }
}

class EffectivePowerComparator implements Comparator<Group> {
    @Override
    public int compare(Group group, Group other) {
        if (group == other) {
            return 0;
        }
        if (group.effectivePower() > other.effectivePower()) {
            return -1;
        } else if (group.effectivePower() == other.effectivePower()) {
            return Integer.compare(other.getInitiative(), group.getInitiative());
        } else {
            return 1;
        }
    }
}

class InitiativeComparator implements Comparator<Group> {
    @Override
    public int compare(Group group, Group other) {
        if (group == other) {
            return 0;
        }
        return Integer.compare(other.getInitiative(), group.getInitiative());
    }
}
