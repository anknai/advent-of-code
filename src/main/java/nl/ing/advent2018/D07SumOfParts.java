package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;
import nl.ing.advent2018.domain.Step;
import nl.ing.advent2018.domain.Worker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import static nl.ing.advent.util.LogWriter.log;

public class D07SumOfParts {

    private Map<Character, Step> stepMap = new TreeMap<>();

    public String sequence(String fileName) {
        List<String> stepInstructions = FileReader.readFile(fileName);
        stepMap = new TreeMap<>();
        for (String instr: stepInstructions) {
            Step step = parseInstruction(instr);
            stepMap.put(step.getStepName(), step);
        }

        StringBuilder sequence = new StringBuilder();
        Iterator<Map.Entry<Character, Step>> iterator = stepMap.entrySet().iterator();
        while (iterator.hasNext()) {
            char c = sequence(iterator.next().getValue());
            if (c == '\0') {
                continue;
            }
            sequence.append(c);
            iterator = stepMap.entrySet().iterator();
        }

        return sequence.toString();
    }

    public Step parseInstruction(String instr) {
        //Step C must be finished before step A can begin.
        char stepToStart = instr.charAt(36);
        char stepToFinish = instr.charAt(5);
        Step startStep = stepMap.get(stepToStart);
        if (null == startStep) {
            startStep = new Step();
            startStep.setStepName(stepToStart);
        }

        Step finishStep = stepMap.get(stepToFinish);
        if (null == finishStep) {
            finishStep = new Step();
            finishStep.setStepName(stepToFinish);
            stepMap.put(stepToFinish, finishStep);
        }
        startStep.addPredecessor(finishStep);
        return startStep;
    }

    private char sequence(Step startStep) {
        TreeSet<Step> predecessors = startStep.getPredecessors();
        if (null == predecessors || predecessors.isEmpty()) {
            Step removed = stepMap.remove(startStep.getStepName());
            for (Map.Entry<Character, Step> characterStepEntry : stepMap.entrySet()) {
                characterStepEntry.getValue().removePredecessor(removed);
            }
            return startStep.getStepName();
        }
        return '\0';
    }

    public int timeTaken(String fileName, int noOfWorkers, int time) {
        List<String> stepInstructions = FileReader.readFile(fileName);
        stepMap = new TreeMap<>();
        for (String instr: stepInstructions) {
            Step step = parseInstruction(instr);
            stepMap.put(step.getStepName(), step);
        }

        StringBuilder sequence = new StringBuilder();
        Iterator<Map.Entry<Character, Step>> iterator = stepMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Step startStep = iterator.next().getValue();
            TreeSet<Step> predecessors = startStep.getPredecessors();
            if (null == predecessors || predecessors.isEmpty()) {
                Step removed = stepMap.get(startStep.getStepName());
                if (sequence.indexOf(String.valueOf(startStep.getStepName())) == -1) {
                    sequence.append(startStep.getStepName());
                }
                for (Map.Entry<Character, Step> characterStepEntry : stepMap.entrySet()) {
                    Step value = characterStepEntry.getValue();
                    value.removePredecessor(removed);
                    if (value.getPredecessors().isEmpty() && sequence.indexOf(String.valueOf(value.getStepName())) == -1) {
                        sequence.append(value.getStepName());
                    }
                }
                stepMap.remove(startStep.getStepName());
            } else {
                continue;
            }

            iterator = stepMap.entrySet().iterator();
        }

        log(sequence.toString());

        //reset the step map
        stepMap = new TreeMap<>();
        for (String instr: stepInstructions) {
            Step step = parseInstruction(instr);
            stepMap.put(step.getStepName(), step);
        }

        iterator = stepMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Step step = iterator.next().getValue();
            step.setTaskLength((int)step.getStepName() + time - 64);
        }

        int startTime = 0;
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < noOfWorkers; i++) {
            workers.add(new Worker(i));
        }

        while (true) {
            iterator = stepMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Step task = iterator.next().getValue();
                assign(task, workers, startTime);

            }

            if (completeAllTasks(workers, startTime)) {
                break;
            }
            startTime++;
        }

        return startTime;
    }

    private void assign(Step task, List<Worker> workers, int startTime) {
        if (!task.isAvailable()) {
            return;
        }
        for (Worker worker: workers) {
            if (worker.isFree()) {
                task.setStartedAt(startTime);
                worker.setCurrentTask(task);
                return;
            }
        }
    }

    private boolean completeAllTasks(List<Worker> workers, int currentTime) {
        boolean allComplete = true;
        for (Map.Entry<Character, Step> stepEntry : stepMap.entrySet()) {
            Step task = stepEntry.getValue();
            if (task.isComplete()) {
                continue;
            }

            if (task.getStartedAt() != -1 && (task.getStartedAt() + task.getTaskLength() == currentTime + 1)) {
                task.setComplete(true);
                for (Worker worker: workers) {
                    if (worker.getCurrentTask() != null && worker.getCurrentTask().getStepName() == task.getStepName()) {
                        worker.setCurrentTask(null);
                        break;
                    }
                }
            }
            allComplete = false;
        }
        return allComplete;
    }
}
