package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;
import nl.ing.advent.util.LogWriter;
import nl.ing.advent2018.domain.Point;
import nl.ing.advent2018.domain.Unit;

import java.util.*;

import static nl.ing.advent.util.LogWriter.log;

public class D15BeverageBandits {

    private char[][] battleground;

    private List<Unit> units;

    private List<Point> points;

    public int score(String fileName) {
        units = init(fileName);
        for (int i = 0; i < 3; i++) {
            System.out.println("Round  " + (i + 1));
            for (Unit unit : units) {
                takeTurn(unit);
            }
            Collections.sort(units);
        }
        //takeTurn(units.get(3));
        return -1;
    }

    private List<Unit> init(String fileName) {
        List<String> strings = FileReader.readFile(fileName);
        int width = strings.get(0).length();
        int height = strings.size();
        battleground = new char[height][width];
        units = new ArrayList<>();
        points = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            battleground[i] = s.toCharArray();
            for (int j = 0; j < s.length(); j ++) {
                if (s.charAt(j) == '#') {
                    continue;
                }
                Point point = new Point();
                point.setX(j);
                point.setY(i);
                if (s.charAt(j) == 'E' || s.charAt(j) == 'G') {
                    Unit unit = new Unit();
                    unit.setPoint(point);
                    if (s.charAt(j) == 'E') {
                        point.setType(Point.PointType.ELF);
                    } else {
                        point.setType(Point.PointType.GOBLIN);
                    }
                    units.add(unit);
                    log(unit.toString());
                } else {
                    point.setType(Point.PointType.OPEN);
                    points.add(point);
                }
            }
        }
        display();
        return units;
    }

    private void takeTurn(Unit unit) {
        Optional<Unit> adjacentEnemy = unit.getAdjacentEnemy(units);
        if (adjacentEnemy.isPresent()) {
            System.out.println("Adjacent enemy found for " + unit);
            attack();
            return;
        }
        move(unit);

    }

    private void move(Unit unit) {
        System.out.println("Finding closest enemy for " + unit);
        //Reset the distance every time
        for (Point point : points) {
            point.setDistance(Integer.MAX_VALUE);
            point.setShortestPath(new LinkedList<>());
            point.setAdjacentNodes(new HashMap<>());
        }
        Point current = unit.getPoint();
        for (Point point : points) {
            if (current.isAdjacent(point)) {
                //System.out.println("Adding " + current + " to adjacent of " + point);
                current.addDestination(point, 1);
            }
            for (Point other : points) {
                if (other != point) {
                    if (point.isAdjacent(other)) {
                        //System.out.println("Adding " + point + " to adjacent of " + other);
                        point.addDestination(other, 1);
                    }
                }
            }
        }

        Dijkstra.calculateShortestPathFromSource(current);
        List<Point> adjacents = new ArrayList<>();
        for (Unit enemy : units) {
            if (unit.getType() == enemy.getType()) {
                continue;
            }
            //System.out.println("Enemy found at " + enemy);
            adjacents.addAll(enemy.getAdjacent(points));
        }

        int min = Integer.MAX_VALUE;
        Point closest = null;
        for (Point adj : adjacents) {
            if (adj.getDistance() < min) {
                min = adj.getDistance();
                closest = adj;
            }
            //System.out.println(adj);
        }

        current.setAdjacentNodes(new HashMap<>());
        current.setShortestPath(new LinkedList<>());
        current.setDistance(Integer.MAX_VALUE);

        if (null != closest) {
            //System.out.println("The closest point is " + closest);
            Point shortest = closest.getShortestPath().removeFirst();
            if (!closest.getShortestPath().isEmpty()) {
                shortest = closest.getShortestPath().removeFirst();
            } else {
                shortest = closest;
            }
            System.out.println("Shortest is via " + shortest);
            Point.PointType elfOrGob = current.getType();
            unit.setPoint(shortest);
            shortest.setType(elfOrGob);
            current.setType(Point.PointType.OPEN);
            points.remove(shortest);
            points.add(current);
            battleground[current.getY()][current.getX()] = Point.PointType.OPEN.getType();
            battleground[shortest.getY()][shortest.getX()] = elfOrGob.getType();
            display();
        }
    }

    private void attack() {

    }

    private void display() {
        for (int i = 0; i < battleground.length; i ++) {
            for (int j = 0; j < battleground[0].length; j ++) {
                LogWriter.logSameLine(battleground[i][j] + "");
            }
            LogWriter.newLine();
        }
    }
}
