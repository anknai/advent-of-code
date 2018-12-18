package nl.ing.advent2018;

import nl.ing.advent2018.domain.Point;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    public static void calculateShortestPathFromSource(Point source) {

        source.setDistance(0);

        Set<Point> settledPoints = new HashSet<>();
        Set<Point> unsettledPoints = new HashSet<>();
        unsettledPoints.add(source);

        while (unsettledPoints.size() != 0) {
            Point currentPoint = getLowestDistancePoint(unsettledPoints);
            unsettledPoints.remove(currentPoint);
            for (Map.Entry<Point, Integer> adjacencyPair : currentPoint.getAdjacentNodes().entrySet()) {
                Point adjacentPoint = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledPoints.contains(adjacentPoint)) {
                    calculateMinimumDistance(adjacentPoint, edgeWeigh, currentPoint);
                    unsettledPoints.add(adjacentPoint);
                }
            }
            settledPoints.add(currentPoint);
        }
    }

    private static void calculateMinimumDistance(Point evaluationPoint, Integer edgeWeigh, Point sourcePoint) {
        Integer sourceDistance = sourcePoint.getDistance();
        if (sourceDistance + edgeWeigh < evaluationPoint.getDistance()) {
            evaluationPoint.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Point> shortestPath = new LinkedList<>(sourcePoint.getShortestPath());
            shortestPath.add(sourcePoint);
            evaluationPoint.setShortestPath(shortestPath);
        }
    }

    private static Point getLowestDistancePoint(Set<Point> unsettledPoints) {
        Point lowestDistancePoint = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Point Point : unsettledPoints) {
            int PointDistance = Point.getDistance();
            if (PointDistance < lowestDistance) {
                lowestDistance = PointDistance;
                lowestDistancePoint = Point;
            }
        }
        return lowestDistancePoint;
    }

}
