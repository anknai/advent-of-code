package org.ankur.advent2019.d03;

import org.ankur.advent.util.FileReader;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class CrossedWires {

    public int readFile(String input) {
        List<String> strings = FileReader.readFile(input);
        SortedSet<WirePoint> wire1 = addWirePoints(strings.get(0));
        SortedSet<WirePoint> wire2 = addWirePoints(strings.get(1));
        return closest(wire1, wire2);
    }

    public int readFileShortest(String input) {
        List<String> strings = FileReader.readFile(input);
        SortedSet<WirePoint> wire1 = addWirePoints(strings.get(0));
        SortedSet<WirePoint> wire2 = addWirePoints(strings.get(1));
        return shortest(wire1, wire2);
    }

    public int readString(String input1, String input2) {
        SortedSet<WirePoint> wire1 = addWirePoints(input1);
        SortedSet<WirePoint> wire2 = addWirePoints(input2);
        return closest(wire1, wire2);
    }

    public int readStringShortest(String input1, String input2) {
        SortedSet<WirePoint> wire1 = addWirePoints(input1);
        SortedSet<WirePoint> wire2 = addWirePoints(input2);
        return shortest(wire1, wire2);
    }

    private int closest(SortedSet<WirePoint> wire1, SortedSet<WirePoint> wire2) {
        for (WirePoint wirePoint : wire1) {
            if (wire2.contains(wirePoint)) {
                System.out.println("Match at " + wirePoint);
                return wirePoint.distance();
            }
        }
        return -1;
    }

    private int shortest(SortedSet<WirePoint> wire1, SortedSet<WirePoint> wire2) {
        int shortest = Integer.MAX_VALUE;
        for (WirePoint wirePoint1 : wire1) {
            for (WirePoint wirePoint2 : wire2) {
                if (wirePoint1.equals(wirePoint2)) {
                    int distance = wirePoint1.getSteps() + wirePoint2.getSteps();
                    if (distance < shortest) {
                        shortest = distance;
                    }
                    System.out.println("Match at " + wirePoint1 + " " + wirePoint2 + " " + distance);
                }
            }

        }
        return shortest;
    }

    private SortedSet<WirePoint> addWirePoints(String wire) {
        String[] points = wire.split(",");
        SortedSet<WirePoint> set = new TreeSet<>();
        int totalSteps = 0;
        WirePoint previous = new WirePoint(0, 0, totalSteps);
        for (String point : points) {
            char side = point.charAt(0);
            int steps = Integer.parseInt(point.substring(1));
            WirePoint current;
            int x = previous.getX();
            int y = previous.getY();
            switch (side) {
                case 'L':
                    for (int i = x - 1; i >= x - steps; i--) {
                        set.add(new WirePoint(i, y, ++totalSteps));
                    }
                    x -= steps;
                    break;
                case 'R':
                    for (int i = x + 1; i <= x + steps; i++) {
                        set.add(new WirePoint(i, y, ++totalSteps));
                    }
                    x += steps;
                    break;
                case 'U':
                    for (int i = y + 1; i <= y + steps; i++) {
                        set.add(new WirePoint(x, i, ++totalSteps));
                    }
                    y += steps;
                    break;
                case 'D':
                    for (int i = y - 1; i >= y - steps; i--) {
                        set.add(new WirePoint(x, i, ++totalSteps));
                    }
                    y -= steps;
                    break;
            }
            current = new WirePoint(x, y, totalSteps);
            previous = current;
        }
        return set;
    }
}
