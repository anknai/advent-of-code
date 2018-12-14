package nl.ing.advent2018.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Worker {
    private int workerId;

    private Step currentTask;

    public Worker(int workerId) {
        this.workerId = workerId;
    }

    public boolean isFree() {
        return currentTask == null;
    }
}
