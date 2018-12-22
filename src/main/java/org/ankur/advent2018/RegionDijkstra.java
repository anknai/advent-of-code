package org.ankur.advent2018;

import org.ankur.advent2018.domain.Region;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RegionDijkstra {
    public static void calculateShortestPathFromSource(Region source) {

        source.setDistance(0);

        Set<Region> settledRegions = new TreeSet<>();
        Set<Region> unsettledRegions = new TreeSet<>();
        unsettledRegions.add(source);

        while (unsettledRegions.size() != 0) {
            Region currentRegion = getLowestDistanceRegion(unsettledRegions);
            unsettledRegions.remove(currentRegion);
            for (Map.Entry<Region, Integer> adjacencyPair : currentRegion.getAdjacentNodes().entrySet()) {
                Region adjacentRegion = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledRegions.contains(adjacentRegion)) {
                    calculateMinimumDistance(adjacentRegion, edgeWeigh, currentRegion);
                    unsettledRegions.add(adjacentRegion);
                }
            }
            settledRegions.add(currentRegion);
        }
    }

    private static void calculateMinimumDistance(Region evaluationRegion, Integer edgeWeigh, Region sourceRegion) {
        Integer sourceDistance = sourceRegion.getDistance();
        if (sourceDistance + edgeWeigh < evaluationRegion.getDistance()) {
            evaluationRegion.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Region> shortestPath = new LinkedList<>(sourceRegion.getShortestPath());
            shortestPath.add(sourceRegion);
            evaluationRegion.setShortestPath(shortestPath);
        }
    }

    private static Region getLowestDistanceRegion(Set<Region> unsettledRegions) {
        Region lowestDistanceRegion = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Region Region : unsettledRegions) {
            int RegionDistance = Region.getDistance();
            if (RegionDistance < lowestDistance) {
                lowestDistance = RegionDistance;
                lowestDistanceRegion = Region;
            }
        }
        return lowestDistanceRegion;
    }

}
