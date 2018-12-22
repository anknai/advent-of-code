package org.ankur.advent2018;

import java.util.Arrays;

public class D22ModeMaze {

    private int targetX;

    private int targetY;

    private int maxX;

    private int maxY;

    private int depth;

    private int erosionLevel[][];

    public int getRiskLevel(int depth, int targetX, int targetY) {
        this.depth = depth;
        this.targetX = targetX;
        this.targetY = targetY;
        this.maxX = targetX;
        this.maxY = targetY;
        erosionLevel = new int[maxX + 1][maxY + 1];
        return riskLevel();
    }

    public int getShortestPath(int depth, int targetX, int targetY) {
        this.depth = depth;
        this.targetX = targetX;
        this.targetY = targetY;
        maxX = targetX + 5;
        maxY = targetY + 5;
        erosionLevel = new int[maxX + 1][maxY + 1];
        riskLevel();
        return -1;
    }

    private int riskLevel() {
        int total = 0;
        for (int y = 0; y <= maxY; y ++) {
            for (int x = 0; x <= maxX; x ++) {
                int risk = riskLevel(x, y);
                display(x, y, risk);
                total += risk;
            }
            System.out.println();
        }
        return total;
    }

    private void display(int x, int y, int risk) {
        char l = '.';
        if (x == 0 && y == 0) {
            l = 'M';
        } else if (x == targetX && y == targetY) {
            l = 'T';
        } else if (risk == 0) {
            l = '.';
        } else if (risk == 1) {
            l = '=';
        } else if (risk == 2) {
            l = '|';
        }
        System.out.print(l);
    }

    private int geologicalIndex(int x, int y) {
        //The region at 0,0 (the mouth of the cave) has a geologic index of 0.
        //The region at the coordinates of the target has a geologic index of 0.
        //If the region's Y coordinate is 0, the geologic index is its X coordinate times 16807.
        //If the region's X coordinate is 0, the geologic index is its Y coordinate times 48271.
        //Otherwise, the region's geologic index is the result of multiplying the erosion levels of the regions at X-1,Y and X,Y-1.
        if (x == 0 && y == 0) {
            return 0;
        }
        if (x == targetX && y == targetY) {
            return 0;
        }
        if (y == 0) {
            return x * 16807;
        }
        if (x == 0) {
            return y * 48271;
        }
        return erosionLevel[x - 1][y] * erosionLevel[x][y - 1];
    }

    private int erosionLevel(int x, int y) {
        int level = (geologicalIndex(x, y) + depth) % 20183;
        erosionLevel[x][y] = level;
        return level;
    }

    private int riskLevel(int x, int y) {
        return erosionLevel(x, y) % 3;
    }

    enum RegionType {
        ROCKY(0),
        WET(1),
        NARROW(2);

        RegionType(int c) {

        }
    }
}
