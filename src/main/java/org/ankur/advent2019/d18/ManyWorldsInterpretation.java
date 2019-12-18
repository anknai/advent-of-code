package org.ankur.advent2019.d18;

import org.ankur.advent.util.FileReader;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ManyWorldsInterpretation {

    private char[][] vaultMap;
    Queue<State> states;
    Set<Character> allKeys;
    Set<Key> seen;
    int maxX;
    int maxY;

    public void part(String fileName) {
        List<String> strings = FileReader.readFile(fileName);
        states = new LinkedList<>();
        allKeys = new HashSet<>();
        seen = new HashSet<>();
        init(strings);
        run();
    }

    private void run() {
        while (states.size() > 0) {
            State state = states.poll();
            SortedSet<Character> newKeys = state.getKeys();
            int x = state.getX();
            int y = state.getY();
            Key key = new Key(x, y, newKeys);
            if (seen.contains(key)) {
                System.out.println("Visited");
                continue;
            }
            seen.add(key);
            if (seen.size() % 100000 == 0) {
                System.out.println(seen.size());
            }

            if (x < 0 || x >= maxX || y < 0 || y >= maxY || vaultMap[x][y] == '#') {
                continue;
            }

            char c = vaultMap[x][y];
            if (c >= 'A' && c <= 'Z' && !newKeys.contains((char) (c + 32))) {
                continue;
            }

            if (c >= 'a' && c <= 'z') {
                newKeys.add(c);
            }
            if (newKeys.size() == allKeys.size()) {
                System.out.println("Distance is " + state.getDistance());
            }

            states.add(new State(x, y + 1, newKeys, state.getDistance() + 1));
            states.add(new State(x, y - 1, newKeys, state.getDistance() + 1));
            states.add(new State(x + 1, y, newKeys, state.getDistance() + 1));
            states.add(new State(x - 1, y + 1, newKeys, state.getDistance() + 1));
        }
    }

    private void init(List<String> strings) {
        maxX = strings.get(0).length();
        maxY = strings.size();
        vaultMap = new char[maxX][maxY];
        for (int y = 0; y < maxY; y++) {
            String string = strings.get(y);
            char[] charArray = string.toCharArray();
            for (int x = 0; x < maxX; x++) {
                char c = charArray[x];
                vaultMap[x][y] = c;
                if (c >= 'a' && c <= 'z') {
                    allKeys.add(c);
                } else if (c == '@') {
                    states.add(new State(x, y, new TreeSet<>(), 0));
                }
            }
        }
    }
}
