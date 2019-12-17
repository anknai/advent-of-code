package org.ankur.advent2019.d15;

import org.ankur.advent.util.FileReader;
import org.ankur.advent.util.IntCodeComputer;
import org.ankur.advent2018.Dijkstra;
import org.ankur.advent2018.domain.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import static org.ankur.advent2018.domain.Point.AreaType.OXYGEN;
import static org.ankur.advent2018.domain.Point.AreaType.ROOM;
import static org.ankur.advent2018.domain.Point.AreaType.WALL;

public class OxygenSystem {

    private char[][] system;

    private static final int maxX = 50;
    private static final int maxY = 50;

    private int x;
    private int y;

    private List<OxygenPoint> points;

    public long distance(String inputFile) {
        String s = FileReader.readFileAsString(inputFile);
        long output = run(s);
        print();
        return output;
    }

    public long timeTaken(String inputFile) {
        String s = FileReader.readFileAsString(inputFile);
        run(s);
        int count = 0;
        boolean none = false;
        while (!none) {
            none = true;
            List<OxygenPoint> adjacent = new ArrayList<>();
            for (OxygenPoint point : points) {
                if (point.getAreaType() != OXYGEN) {
                    continue;
                }
                for (OxygenPoint other : points) {
                    if (other == point) {
                        continue;
                    }
                    if (point.getAreaType() == OXYGEN
                            && point.isAdjacent(other)
                            && other.getAreaType() != OXYGEN) {
                        if (none) {
                            count++;
                        }
                        none = false;
                        adjacent.add(other);
                    }
                }
            }
            for (Point point : adjacent) {
                point.setAreaType(OXYGEN);
                system[point.getX()][point.getY()] = 'O';
            }
        }
        print();
        return count;
    }

    long run(String inputStr) {
        IntCodeComputer computer = new IntCodeComputer(inputStr);
        system = new char[maxX][maxY];
        x = maxX / 2;
        y = maxY / 2;
        system[x][y] = 'D';
        Queue<Long> inputs = new LinkedList<>();
        inputs.add(1L);
        int direction = 1;
        int count = 0;
        while (computer.running() && count < 10000) {
            count++;
            computer.run(inputs);
            int status = (int) computer.output();
            int newX = x;
            int newY = y;
            switch (direction) {
                case 1:
                    newY--;
                    break;
                case 2:
                    newY++;
                    break;
                case 3:
                    newX++;
                    break;
                case 4:
                    newX--;
                    break;
                default:
                    System.out.println("Wrong direction at " + x + " " + y);
                    break;
            }
            if (status == 0) {
                system[newX][newY] = '#';
            }
            else if (status == 1 || status == 2) {
                if (system[newX][newY] != 'x') {
                    system[newX][newY] = '.';
                }
                x = newX;
                y = newY;
            }
            if (status == 2) {
                system[newX][newY] = 'O';
                System.out.println("Found ya " + newX + " " + newY);
                break;
            }
            direction = changeDirection();
            inputs.add((long)direction);
        }
        System.out.println("count " + count);
        return calculate();
    }

    private int calculate() {
        points = new ArrayList<>();
        Point start = null;
        Point oxygen = null;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (system[x][y] == '\0' || system[x][y] == '#') {
                    continue;
                }
                OxygenPoint point = new OxygenPoint();
                point.setX(x);
                point.setY(y);
                if (system[x][y] == '#') {
                    point.setAreaType(WALL);
                } else {
                    point.setAreaType(ROOM);
                }
                if (system[x][y] == 'D') {
                    start = point;
                }
                if (system[x][y] == 'O') {
                    oxygen = point;
                    oxygen.setAreaType(OXYGEN);
                }
                points.add(point);
            }
        }

        assert start != null;
        assert oxygen != null;

        for (Point point : points) {
            point.setDistance(Integer.MAX_VALUE);
            point.setShortestPath(new LinkedList<>());
            point.setAdjacentNodes(new TreeMap<>());
        }
        for (Point point : points) {
            if (start.isAdjacent(point)) {
                start.addDestination(point, 1);
            }
            for (Point other : points) {
                if (other != point) {
                    if (point.isAdjacent(other)) {
                        point.addDestination(other, 1);
                    }
                }
            }
        }
        Dijkstra.calculateShortestPathFromSource(start);
        for (Point point : oxygen.getShortestPath()) {
            system[point.getX()][point.getY()] = '\u2588';
        }
        return oxygen.getDistance();
    }

    private int changeDirection() {
        int direction = 0;
        if (exists(x + 1, y) && system[x + 1][y] == '\0') {
            direction = 3;
        } else if (exists(x - 1, y) && system[x - 1][y] == '\0') {
            direction = 4;
        } else if (exists(x, y + 1) && system[x][y + 1] == '\0') {
            direction = 2;
        } else if (exists(x, y - 1) && system[x][y - 1] == '\0') {
            direction = 1;
        } else {
            //no empty cell found
            system[x][y] = 'x';
            if (exists(x + 1, y) && system[x + 1][y] == '.') {
                direction = 3;
            } else if (exists(x - 1, y) && system[x - 1][y] == '.') {
                direction = 4;
            } else if (exists(x, y + 1) && system[x][y + 1] == '.') {
                direction = 2;
            } else if (exists(x, y - 1) && system[x][y - 1] == '.') {
                direction = 1;
            } else {
                //System.out.println("Nothing interesting at " + x + " " + y);
                for (int j = 0; j < maxY; j++) {
                    for (int i = 0; i < maxX; i++) {
                        if (system[i][j] == '.' || system[i][j] == 'x') {
                            x = i;
                            y = j;
                            //System.out.println("found " + x + " " + y) ;
                            return changeDirection();
                        }
                    }
                }
            }
        }
        //System.out.println("Moving to " + direction + " from " + x + " " + y);
        return direction;
    }

    private boolean exists(int i, int j) {
        return i >= 0 && j >= 0 && i < maxX && j < maxY;
    }

    private void print() {
        System.out.println("=====================================================");
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                System.out.print(system[x][y]);
            }
            System.out.println();
        }
    }
}
