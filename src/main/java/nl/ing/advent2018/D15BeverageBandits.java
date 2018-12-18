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

    private boolean targetFound;

    private int round;

    public int score(String fileName) {
        units = init(fileName);
        round = 0;
        targetFound = true;
        while (targetFound){
            targetFound = false;
            round++;
            for (int unitIndex = 0; unitIndex < units.size(); unitIndex ++) {
                Optional<Unit> diedOptional = takeTurn(units.get(unitIndex));
                if (diedOptional.isPresent()) {
                    Unit died = diedOptional.get();
                    for (int k = 0; k < unitIndex; k++) {
                        //if the unit died is before the current element, move everything up
                        if (units.get(k) == died) {
                            unitIndex--;
                            break;
                        }
                    }
                    units.remove(died);
                    display();
                }
            }

            Collections.sort(units);
        }

        int sum = 0;
        for (Unit unit: units) {
            System.out.println(unit);
            sum += unit.getHitPoint();
        }
        --round;
        System.out.println("Sum of hit power " + sum + " round number " + round);
        display();
        return sum * round;
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

    private Optional<Unit> takeTurn(Unit unit) {
        Optional<Unit> adjacentEnemy = unit.getAdjacentEnemy(units);
        if (adjacentEnemy.isPresent()) {
            targetFound = true;
            return attack(unit, adjacentEnemy.get());
        } else {
            return moveAndAttack(unit);
        }
    }

    private Optional<Unit> moveAndAttack(Unit unit) {
        //Reset the distance every time
        for (Point point : points) {
            point.setDistance(Integer.MAX_VALUE);
            point.setShortestPath(new LinkedList<>());
            point.setAdjacentNodes(new TreeMap<>());
        }
        Point current = unit.getPoint();
        for (Point point : points) {
            if (current.isAdjacent(point)) {
                current.addDestination(point, 1);
            }
            for (Point other : points) {
                if (other != point) {
                    if (point.isAdjacent(other)) {
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
            adjacents.addAll(enemy.getAdjacent(points));
        }

        int min = Integer.MAX_VALUE;
        Point closest = null;
        for (Point adj : adjacents) {
            if (adj.getDistance() < min) {
                min = adj.getDistance();
                closest = adj;
            }
        }

        current.setAdjacentNodes(new TreeMap<>());
        current.setShortestPath(new LinkedList<>());
        current.setDistance(Integer.MAX_VALUE);

        //Found something
        if (null != closest) {
            //Remove the first one
            closest.getShortestPath().removeFirst();
            Point shortest;
            if (!closest.getShortestPath().isEmpty()) {
                shortest = closest.getShortestPath().removeFirst();
            } else {
                shortest = closest;
            }
            Point.PointType elfOrGob = current.getType();
            unit.setPoint(shortest);
            shortest.setType(elfOrGob);
            shortest.setAdjacentNodes(new TreeMap<>());
            shortest.setShortestPath(new LinkedList<>());
            current.setType(Point.PointType.OPEN);
            points.remove(shortest);
            points.add(current);
            battleground[current.getY()][current.getX()] = Point.PointType.OPEN.getType();
            battleground[shortest.getY()][shortest.getX()] = elfOrGob.getType();
            Optional<Unit> adjacentEnemy = unit.getAdjacentEnemy(units);
            targetFound = true;
            if (adjacentEnemy.isPresent()) {
                return attack(unit, adjacentEnemy.get());
            }
        }
        return Optional.empty();
    }

    private Optional<Unit> attack(Unit attacker, Unit enemy) {
        //System.out.println(attacker + " is attacking " + enemy);
        enemy.getHit(attacker.getAttackPower());
        if (enemy.getHitPoint() <= 0) {
            System.out.println("Enemy " + enemy + " died in round " + round);
            points.add(enemy.getPoint());
            battleground[enemy.getPoint().getY()][enemy.getPoint().getX()] = Point.PointType.OPEN.getType();
            return Optional.of(enemy);
        }
        return Optional.empty();
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
