package org.ankur.advent2018;

import org.ankur.advent2018.domain.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class D20RegularMap {

    private TreeSet<Point> Points;

    private Point me;

    public int farthestRoom(String pattern) {
        Points = new TreeSet<>();
        //First room is at 0, 0
        me = new Point(0, 0, Point.AreaType.ME);
        Points.add(me);
        //Start with the 2nd character, as 1st is ^ to indicate start of the map
        drawMap(pattern.substring(1), 0, 0);
        markAdjacent();
        int distance = calculateDistance();
        fillTheGrid();
        display();
        return distance;
    }

    public int roomWithDistance(String pattern, int distance) {
        Points = new TreeSet<>();
        //First room is at 0, 0
        me = new Point(0, 0, Point.AreaType.ME);
        Points.add(me);
        //Start with the 2nd character, as 1st is ^ to indicate start of the map
        drawMap(pattern.substring(1), 0, 0);
        markAdjacent();
        return minimum(distance);
    }

    private void drawMap(String regex, int x, int y) {
        char[] reg = regex.toCharArray();
        for (char c : reg) {
            switch (c) {
                case 'W':
                    add(--x, y, Point.AreaType.VER_DOOR);
                    add(--x, y, Point.AreaType.ROOM);
                    break;
                case 'N':
                    add(x, --y, Point.AreaType.HOR_DOOR);
                    add(x, --y, Point.AreaType.ROOM);
                    break;
                case 'E':
                    add(++x, y, Point.AreaType.VER_DOOR);
                    add(++x, y, Point.AreaType.ROOM);
                    break;
                case 'S':
                    add(x, ++y, Point.AreaType.HOR_DOOR);
                    add(x, ++y, Point.AreaType.ROOM);
                    break;
                case '$':
                    return;
                case '(':
                    String[] strings = matchingBrace(regex);
                    String[] branches = splitBranches(strings[0]);
                    for (String branch : branches) {
                        drawMap(branch, x, y);
                    }
                    drawMap(strings[1], x, y);
                    return;
                default:
                    break;
            }
        }
    }

    private void add(int x, int y, Point.AreaType AreaType) {
        Point Point = new Point(x, y, AreaType);
        Points.add(Point);
    }

    private void fillTheGrid() {
        Point first = Points.first();
        Point last = Points.last();
        for (int x = first.getX() - 1; x <= last.getX() + 1; x++) {
            for (int y = first.getY() - 1; y <= last.getY() + 1; y++) {
                Point point = new Point(x, y, Point.AreaType.WALL);
                Point ceiling = Points.ceiling(point);
                if (null == ceiling || !(ceiling.equals(point))) {
                    Points.add(point);
                }
            }
        }
    }

    private void markAdjacent() {
        int x = 0;
        int y = 0;
        Optional<Point> room = isAdjacent(x-1, y, Direction.LEFT);
        room.ifPresent(Point -> me.addDestination(Point, 1));
        room = isAdjacent(x+1, y, Direction.RIGHT);
        room.ifPresent(Point -> me.addDestination(Point, 1));
        room = isAdjacent(x, y-1, Direction.UP);
        room.ifPresent(Point -> me.addDestination(Point, 1));
        room = isAdjacent(x, y+1, Direction.DOWN);
        room.ifPresent(Point -> me.addDestination(Point, 1));

        for (Point current : Points) {
            if (current.getAreaType() == Point.AreaType.ROOM) {
                x = current.getX();
                y = current.getY();
                room = isAdjacent(x - 1, y, Direction.LEFT);
                room.ifPresent(Point -> current.addDestination(Point, 1));
                room = isAdjacent(x + 1, y, Direction.RIGHT);
                room.ifPresent(Point -> current.addDestination(Point, 1));
                room = isAdjacent(x, y - 1, Direction.UP);
                room.ifPresent(Point -> current.addDestination(Point, 1));
                room = isAdjacent(x, y + 1, Direction.DOWN);
                room.ifPresent(Point -> current.addDestination(Point, 1));
            }
        }
    }

    private int calculateDistance() {
        Dijkstra.calculateShortestPathFromSource(me);
        int max = 0;
        Point farthest = null;
        for (Point current : Points) {
            if (current.getAreaType() == Point.AreaType.ROOM) {
                int distance = current.getDistance();
                if (distance != Integer.MAX_VALUE && max < distance) {
                    max = distance;
                    farthest = current;
                }
            }
        }

        System.out.println("Farthest is " + farthest + " with " + max + " distance");
        return max;
    }

    private int minimum(int min) {
        Dijkstra.calculateShortestPathFromSource(me);
        int count = 0;
        for (Point current : Points) {
            if (current.getAreaType() == Point.AreaType.ROOM) {
                int distance = current.getDistance();
                if (distance >= min) {
                    count ++;
                }
            }
        }
        System.out.println(count + " rooms found with at least " + min + " distance ");
        return count;
    }

    private Optional<Point> isAdjacent(int x, int y, Direction direction) {
        Point point = new Point(x, y, Point.AreaType.ROOM);
        Point ceiling = Points.ceiling(point);
        //There is a door on adjacent cell
        if (null != ceiling && ceiling.equals(point) &&
                (ceiling.getAreaType() == Point.AreaType.HOR_DOOR || ceiling.getAreaType() == Point.AreaType.VER_DOOR)) {
            //find the room and return it
            Point room;
            switch (direction) {
                case LEFT:
                    --x;
                    break;
                case RIGHT:
                    ++x;
                    break;
                case DOWN:
                    ++y;
                    break;
                case UP:
                    --y;
                    break;

            }
            point = new Point(x, y, Point.AreaType.ROOM);
            room = Points.ceiling(point);
            return (null == room) ? Optional.empty() : Optional.of(room);
        } else {
            return Optional.empty();
        }
    }

    private void display() {
        Iterator<Point> iterator = Points.iterator();
        Point previous = iterator.next();
        System.out.print(previous.getAreaType().getType());
        while (iterator.hasNext()) {
            Point current = iterator.next();
            if (current.getY() != previous.getY()) {
                System.out.println();
            }
            System.out.print(current.getAreaType().getType());
            previous = current;
        }
        System.out.println();
        System.out.println();
    }

    public String[] matchingBrace(String pattern) {
        int open = pattern.indexOf('(');
        int close = findClosingParen(pattern.toCharArray(), open);
        String inner = pattern.substring(open + 1, close);
        String outer = pattern.substring(close + 1);
        return new String[] {inner, outer};
    }

    public int findClosingParen(char[] text, int openPos) {
        int closePos = openPos;
        int counter = 1;
        while (counter > 0) {
            char c = text[++closePos];
            if (c == '(') {
                counter++;
            }
            else if (c == ')') {
                counter--;
            }
        }
        return closePos;
    }

    public String[] splitBranches(String string) {
        List<String> splitList = new ArrayList<>();
        String[] splits = string.split("\\|");
        for (int i = 0; i < splits.length; i++) {
            String split = splits[i];
            if (countChar(split, ')') == countChar(split, '(')) {
                splitList.add(split);
            } else {
                splits[i + 1] = splits[i] + "|" + splits[i + 1];
            }
        }
        return splitList.toArray(new String[0]);
    }

    private int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == c) {
                count++;
            }
        }

        return count;
    }

    enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
    }
}
