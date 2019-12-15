package org.ankur.advent2019.d15;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.Dijkstra;
import org.ankur.advent2018.domain.Point;
import org.ankur.advent2019.d13.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import static org.ankur.advent2018.domain.Point.AreaType.*;

public class OxygenSystem {

    private int index = 0;

    private int relativeBase = 0;

    private boolean halt;

    private char[][] system;

    private static final int maxX = 50;
    private static final int maxY = 50;

    private int x;
    private int y;

    List<OxygenPoint> points;

    public long alarm(String inputFile) {
        index = 0;
        relativeBase = 0;
        String s = FileReader.readFileAsString(inputFile);
        return alarmString(s);
    }

    public long alarm2(String inputFile) {
        index = 0;
        relativeBase = 0;
        String s = FileReader.readFileAsString(inputFile);
        alarmString(s);
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

    long alarmString(String inputStr) {
        String[] split = inputStr.split(",");
        long[] array = Arrays.stream(split).mapToLong(Long::parseLong).toArray();
        long[] copy = new long[array.length + 1000000];
        System.arraycopy(array, 0, copy, 0, array.length);
        system = new char[maxX][maxY];
        x = maxX / 2;
        y = maxY / 2;
        system[x][y] = 'D';
        int direction = 1;
        halt = false;
        int count = 0;
        while (!halt && count < 10000) {
            count++;
            int status = (int) opCode(copy, direction);
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
        }
        System.out.println("count " + count);
        print();
        return calculate();
    }

    private int calculate() {
        points = new ArrayList<>();
        Point start = null;
        Point oxygen = null;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (system[x][y] == '\0') {
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
        System.out.println("New round");
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                System.out.print(system[x][y]);
            }
            System.out.println();
        }
    }

    private long opCode(long[] array, long input) {
        if (array[index] == 99) {
            System.out.println("Halting");
            halt = true;
            return -1L;
        }
        while (array[index] != 99) {
            long opcodeStr = array[index];
            long opcode = opcodeStr % 100;
            if (opcode == 99) {
                halt = true;
                System.out.println("Breaking up now");
                return -1;
            }
            int modes = (int) opcodeStr / 100;
            int mode1 = modes % 10;
            int mode2 = (modes / 10) % 10;
            int mode = (modes / 100 );
            long value = 0;

            if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
                long firstValue = parameter(mode1, array, ++index, relativeBase);
                long secondValue = parameter(mode2, array, ++index, relativeBase);

                if (opcode == 1) {
                    value = firstValue + secondValue;
                } else if (opcode == 2) {
                    value = firstValue * secondValue;
                } else if (opcode == 5) {
                    if (firstValue != 0) {
                        index = (int) secondValue;
                        continue;
                    }
                } else if (opcode == 6) {
                    if (firstValue == 0) {
                        index = (int) secondValue;
                        continue;
                    }
                } else if (opcode == 7) {
                    if (firstValue < secondValue) {
                        value = 1;
                    }
                } else {
                    if (firstValue == secondValue) {
                        value = 1;
                    }
                }
            }

            if (opcode == 3) {
                mode = mode1;
                value = input;
            } else if (opcode == 4) {
                input = parameter(mode1, array, ++index, relativeBase);
                ++index;
                return input;
            } else if (opcode == 9) {
                relativeBase += parameter(mode1, array, ++index, relativeBase);;
            }

            if (opcode != 5 && opcode != 6 && opcode != 9) {
                int valuePointer = (int) array[++index];
                if (mode == 2) {
                    valuePointer += relativeBase;
                }
                assert valuePointer < array.length;
                assert valuePointer >= 0;

                array[valuePointer] = value;
            }

            if (opcode < 1 || opcode > 9) {
                System.out.println("Something went wrong, unexpected opcode " + opcode + " at position " + index);
            }

            index++;
        }

        return input;
    }

    private long parameter(int mode, long[] array, int index, int relativeBase) {
        int pointer;
        long value;
        if (mode == 0) {
            pointer = (int) array[index];
            assert pointer < array.length;
            value = array[pointer];
        } else if (mode == 1) {
            value = array[index];
        } else {
            pointer = (int) array[index] + relativeBase;
            assert pointer < array.length;
            assert pointer >= 0;
            value = array[pointer];
        }
        return value;
    }

}
