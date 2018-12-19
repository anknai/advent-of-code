package org.ankur.advent2018.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.TreeSet;

@Getter
@Setter
public class Step implements Comparable<Step> {

    private char stepName;

    private TreeSet<Step> predecessors = new TreeSet<>();

    private boolean complete;

    private int taskLength;

    private int startedAt = -1;

    public boolean isAvailable() {
        boolean available = true;
        for (Step step : predecessors) {
            if (!step.isComplete()) {
                available = false;
            }
        }
        return available && startedAt == -1;
    }

    public void addPredecessor(Step step) {
        predecessors.add(step);
    }

    public void removePredecessor(Step step) {
        predecessors.remove(step);
    }

    @Override
    public int hashCode() {
        return stepName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Step)) {
            return false;
        }
        Step other = (Step) o;
        return other.stepName == this.stepName;
    }

    @Override
    public int compareTo(Step other) {
        return Character.compare(this.stepName, other.stepName);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(stepName + "\t");
        for (Step step: predecessors) {
            string.append(step.getStepName());
            string.append(" ");
        }
        return string.toString();
    }
}
