package org.ankur.advent2018.test;

import org.ankur.advent2018.D22ModeMaze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D22ModeMazeTest {
    private D22ModeMaze maze = new D22ModeMaze();

    @Test
    void getRiskLevel() {
        int riskLevel = maze.getRiskLevel(510, 10, 10);
        assertEquals(114, riskLevel);

        riskLevel = maze.getRiskLevel(10647, 7, 770);
        assertEquals(6208, riskLevel);
    }

    @Test
    void getShortestPath() {
        int shortestPath = maze.getShortestPath(510, 10, 10);
        assertEquals(45, shortestPath);

        shortestPath = maze.getShortestPath(10647, 7, 770);
        assertEquals(1039, shortestPath);
    }
}