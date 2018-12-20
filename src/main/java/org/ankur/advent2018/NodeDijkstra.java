package org.ankur.advent2018;

import org.ankur.advent2018.domain.Node;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class NodeDijkstra {
    static void calculateShortestPathFromSource(Node source) {

        source.setDistance(0);

        Set<Node> settledNodes = new TreeSet<>();
        Set<Node> unsettledNodes = new TreeSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node Node : unsettledNodes) {
            int NodeDistance = Node.getDistance();
            if (NodeDistance < lowestDistance) {
                lowestDistance = NodeDistance;
                lowestDistanceNode = Node;
            }
        }
        return lowestDistanceNode;
    }

}
