package org.ankur.advent2021.d12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CaveSystem {

    private final Map<String, Cave> caves = new HashMap<>();

    private int total = 0;

    private final Deque<List<String>> paths = new ArrayDeque<>();

    public void addCave(String string) {
        String[] split = string.split("-");
        Cave from = new Cave(split[0]);
        caves.putIfAbsent(from.getName(), from);
        from = caves.get(from.getName());
        Cave to = new Cave(split[1]);
        caves.putIfAbsent(to.getName(), to);
        to = caves.get(to.getName());
        to.addNeighbour(from);
        from.addNeighbour(to);
    }

    public void display() {
        caves.keySet().stream().map(caves::get).forEach(System.out::println);
        System.out.println();
    }

    public int findPath() {
        List<String> path = new ArrayList<>();
        path.add("start");
        paths.push(path);
        while (!paths.isEmpty()) {
            path = paths.pop();
            resetTraversed(path);
            find(last(path), path);
        }
        return total;
    }

    private void find(Cave cave, List<String> path) {
        if (cave.isEnd()) {
            cave.setTraversed(true);
            total++;
            System.out.println("Path " + display(path));
            return;
        }
        if (!cave.isTraversed()) {
            cave.setTraversed(true);
            for (Cave n : cave.getNeighbours()) {
                if (!n.isTraversed()) {
                    copyAndPush(path, n.getName());
                }
            }
            List<String> peek = paths.pop();
            find(last(peek), peek);
        }
    }

    private void resetTraversed(List<String> path) {
        caves.keySet().stream().map(caves::get).forEach(Cave::resetTraverse);
        path.forEach(name -> caves.get(name).setTraversed(true));
        last(path).setTraversed(false);
    }

    private void copyAndPush(List<String> path, String name) {
        List<String> copy = new ArrayList<>(path);
        copy.add(name);
        paths.push(copy);
    }

    private Cave last(List<String> path) {
        return caves.get(path.get(path.size() - 1));
    }

    private String display(List<String> path) {
        StringBuilder print = new StringBuilder();
        for (String s : path) {
            print.append(s).append(" -> ");
        }
        return print.toString();
    }

    public int findPath2() {
        List<String> path = new ArrayList<>();
        path.add("start");
        paths.push(path);
        while (!paths.isEmpty()) {
            path = paths.pop();
            resetTraversed(path);
            find2(last(path), path);
        }
        return total;
    }

    private void find2(Cave cave, List<String> path) {
        if (cave.isEnd()) {
            cave.setTraversed(true);
            if (isNotTwiceTraversed(path)) {
                total++;
            }
            return;
        }
        if (isNotTwiceTraversed(path)) {
            cave.setTraversed(true);
            for (Cave n : cave.getNeighbours()) {
                if (!n.isTwiceTraversed()) {
                    copyAndPush(path, n.getName());
                }
            }
            List<String> pop = paths.pop();
            find2(last(pop), pop);
        }
    }

    private boolean isNotTwiceTraversed(List<String> path) {
        Set<String> twice = new HashSet<>();
        boolean found = false;
        for (String s : path) {
            if (caves.get(s).isTwiceTraversed()) {
                boolean add = twice.add(s);
                if (!add && found) {
                    return false;
                }
                if (!add) {
                    found = true;
                }
            }
        }
        return true;
    }
}
