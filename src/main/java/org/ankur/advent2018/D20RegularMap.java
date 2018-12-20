package org.ankur.advent2018;

import org.ankur.advent2018.domain.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class D20RegularMap {

    private TreeSet<Point> points;

    private Point me;

    private int maxX;

    private int minX;

    public int farthestRoom(String pattern) {
        minX = 0;
        maxX = 0;
        points = new TreeSet<>();
        //First room is at 0, 0 where I am standing
        me = new Point(minX, 0, Point.AreaType.ME);
        points.add(me);
        //Start with the 2nd character, as 1st is ^ to indicate start of the map
        drawMap(pattern.substring(1), 0, 0);
        markAdjacent();
        int distance = calculateDistance();
        if (maxX - minX < 100) {
            display();
        } else {
            visualize(distance);
        }
        return distance;
    }

    public int roomWithDistance(String pattern, int distance) {
        points = new TreeSet<>();
        //First room is at 0, 0
        me = new Point(0, 0, Point.AreaType.ME);
        points.add(me);
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
        if (x < minX) {
            minX = x;
        }
        if (x > maxX) {
            maxX = x;
        }
        Point Point = new Point(x, y, AreaType);
        points.add(Point);
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

        for (Point current : points) {
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
        for (Point current : points) {
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
        for (Point current : points) {
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
        Point ceiling = points.ceiling(point);
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
            room = points.ceiling(point);
            return (null == room) ? Optional.empty() : Optional.of(room);
        } else {
            return Optional.empty();
        }
    }

    private void display() {
        Point first = points.first();
        Point last = points.last();
        int minX = this.minX - 1;
        int minY = first.getY() - 1;
        int maxX = this.maxX + 1;
        int maxY = last.getY() + 1;

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                Point point = new Point(x, y, Point.AreaType.WALL);
                Point ceiling = points.ceiling(point);
                if (null == ceiling || !(ceiling.equals(point))) {
                    System.out.print(point.getAreaType().getType());
                } else {
                    System.out.print(ceiling.getAreaType().getType());
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    private void visualize(int max) {
        Point first = points.first();
        Point last = points.last();
        int minY = first.getY();
        int maxY = last.getY();
        //first char
        char a = 65;
        int changeAt = max / 26 + 1;
        int xIncrement = 1;
        int yIncrement = 1;
        if (maxX - minX > 120) {
            xIncrement = (maxX - minX) / 120 + 1;
        }
        if (maxY - minY > 50) {
            yIncrement = (maxY - minY) / 50 + 1;
        }
        for (int y = minY; y <= maxY; y += yIncrement) {
            for (int x = minX; x <= maxX; x += xIncrement) {
                Point point = new Point(x, y, Point.AreaType.WALL);
                Point ceiling = points.ceiling(point);
                if (null == ceiling || !(ceiling.equals(point))) {
                    System.out.print(' ');
                } else if (Point.AreaType.ROOM == ceiling.getAreaType()) {
                    System.out.print((char)(a + ceiling.getDistance() / changeAt));
                } else if (Point.AreaType.ME == ceiling.getAreaType()) {
                    System.out.print('#');
                } else {
                    System.out.print(ceiling.getAreaType().getType());
                }
            }
            System.out.println();
        }

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
