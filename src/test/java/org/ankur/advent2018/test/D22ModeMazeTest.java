package org.ankur.advent2018.test;

import org.ankur.advent2018.D22ModeMaze;
import org.junit.Test;

import static org.junit.Assert.*;

public class D22ModeMazeTest {
    private D22ModeMaze maze = new D22ModeMaze();

    @Test
    public void getRiskLevel() throws Exception {
        int riskLevel = maze.getRiskLevel(510, 10, 10);
        assertEquals(114, riskLevel);

        riskLevel = maze.getRiskLevel(10647, 7, 770);
        assertEquals(6208, riskLevel);
    }

    @Test
    public void getShortestPath() throws Exception {
        int shortestPath = maze.getShortestPath(510, 10, 10);
        assertEquals(45, shortestPath);

        shortestPath = maze.getShortestPath(10647, 7, 770);
        assertEquals(1039, shortestPath);
    }
}