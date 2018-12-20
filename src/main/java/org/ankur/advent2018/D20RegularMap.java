package org.ankur.advent2018;

import org.ankur.advent2018.domain.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public class D20RegularMap {

    private TreeSet<Node> nodes;

    private Node me;

    public int farthestRoom(String pattern) {
        nodes = new TreeSet<>();
        //First room is at 0, 0
        me = new Node(0, 0, Node.NodeType.ME);
        nodes.add(me);
        //Start with the 2nd character, as 1st is ^ to indicate start of the map
        drawMap(pattern.substring(1), 0, 0);
        markAdjacent();
        int distance = calculateDistance();
        fillTheGrid();
        display();
        return distance;
    }

    public int roomWithDistance(String pattern, int distance) {
        nodes = new TreeSet<>();
        //First room is at 0, 0
        me = new Node(0, 0, Node.NodeType.ME);
        nodes.add(me);
        //Start with the 2nd character, as 1st is ^ to indicate start of the map
        drawMap(pattern.substring(1), 0, 0);
        markAdjacent();
        return minimum(distance);
    }

    //^WNE$
    private void drawMap(String regex, int x, int y) {
        char[] reg = regex.toCharArray();
        //System.out.println("Handling " + regex);
        for (char c : reg) {
            switch (c) {
                case 'W':
                    add(--x, y, Node.NodeType.VER_DOOR);
                    add(--x, y, Node.NodeType.ROOM);
                    break;
                case 'N':
                    add(x, --y, Node.NodeType.HOR_DOOR);
                    add(x, --y, Node.NodeType.ROOM);
                    break;
                case 'E':
                    add(++x, y, Node.NodeType.VER_DOOR);
                    add(++x, y, Node.NodeType.ROOM);
                    break;
                case 'S':
                    add(x, ++y, Node.NodeType.HOR_DOOR);
                    add(x, ++y, Node.NodeType.ROOM);
                    break;
                case '$':
                    //System.out.println("End of map ");
                    return;
                case '(':
                    //System.out.println("Branching at " + i + " for " + regex);
                    String[] strings = matchingBrace(regex);
                    String[] branches = splitBranches(strings[0]);
                    for (String branch : branches) {
                        drawMap(branch, x, y);
                    }
                    drawMap(strings[1], x, y);
                    return;
                case ')':
                    //System.out.println("Branch ending at " + i + " for " + regex);
                    break;
            }
        }
        //System.out.println("Done with " + regex);
    }

    private void add(int x, int y, Node.NodeType nodeType) {
        Node node = new Node(x, y, nodeType);
        nodes.add(node);
    }

    private void fillTheGrid() {
        Node first = nodes.first();
        Node last = nodes.last();
        for (int x = first.getX() - 1; x <= last.getX() + 1; x++) {
            for (int y = first.getY() - 1; y <= last.getY() + 1; y++) {
                Node node = new Node(x, y, Node.NodeType.WALL);
                Node ceiling = nodes.ceiling(node);
                if (null == ceiling || !(ceiling.equals(node))) {
                    nodes.add(node);
                }
            }
        }
    }

    private void markAdjacent() {
        int x = 0;
        int y = 0;
        Optional<Node> room = isAdjacent(x-1, y, Direction.LEFT);
        room.ifPresent(node -> me.addDestination(node, 1));
        room = isAdjacent(x+1, y, Direction.RIGHT);
        room.ifPresent(node -> me.addDestination(node, 1));
        room = isAdjacent(x, y-1, Direction.UP);
        room.ifPresent(node -> me.addDestination(node, 1));
        room = isAdjacent(x, y+1, Direction.DOWN);
        room.ifPresent(node -> me.addDestination(node, 1));

        for (Node current : nodes) {
            if (current.getType() == Node.NodeType.ROOM) {
                x = current.getX();
                y = current.getY();
                room = isAdjacent(x - 1, y, Direction.LEFT);
                room.ifPresent(node -> current.addDestination(node, 1));
                room = isAdjacent(x + 1, y, Direction.RIGHT);
                room.ifPresent(node -> current.addDestination(node, 1));
                room = isAdjacent(x, y - 1, Direction.UP);
                room.ifPresent(node -> current.addDestination(node, 1));
                room = isAdjacent(x, y + 1, Direction.DOWN);
                room.ifPresent(node -> current.addDestination(node, 1));
            }
        }
    }

    private int calculateDistance() {
        NodeDijkstra.calculateShortestPathFromSource(me);
        int max = 0;
        Node farthest = null;
        for (Node current : nodes) {
            if (current.getType() == Node.NodeType.ROOM) {
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
        NodeDijkstra.calculateShortestPathFromSource(me);
        int count = 0;
        for (Node current : nodes) {
            if (current.getType() == Node.NodeType.ROOM) {
                int distance = current.getDistance();
                if (distance >= min) {
                    count ++;
                }
            }
        }
        System.out.println(count + " rooms found with at least " + min + " distance ");
        return count;
    }

    private Optional<Node> isAdjacent(int x, int y, Direction direction) {
        Node node = new Node(x, y, Node.NodeType.ROOM);
        Node ceiling = nodes.ceiling(node);
        //There is a door on adjacent cell
        if (null != ceiling && ceiling.equals(node) && (ceiling.getType() == Node.NodeType.HOR_DOOR || ceiling.getType() == Node.NodeType.VER_DOOR)) {
            //find the room and return it
            Node room;
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
            node = new Node(x, y, Node.NodeType.ROOM);
            room = nodes.ceiling(node);
            return Optional.of(room);
        } else {
            return Optional.empty();
        }
    }

    private void display() {
        Iterator<Node> iterator = nodes.iterator();
        Node previous = iterator.next();
        System.out.print(previous.getType().getType());
        while (iterator.hasNext()) {
            Node current = iterator.next();
            if (current.getY() != previous.getY()) {
                System.out.println();
            }
            System.out.print(current.getType().getType());
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
        //System.out.println("String to split is " + string);
        String[] splits = string.split("\\|");
        for (int i = 0; i < splits.length; i++) {
            String split = splits[i];
            //System.out.println(split);
            if (countChar(split, ')') == countChar(split, '(')) {
                splitList.add(split);
            } else {
                splits[i + 1] = splits[i] + "|" + splits[i + 1];
            }
        }
        return splitList.toArray(new String[0]);
    }

    private int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
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
