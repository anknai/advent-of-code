package org.ankur.advent2018;

public class D11ChronalCharge {

    private static final int CELL_SIZE = 300;

    private int[][] powerGrid = new int[CELL_SIZE + 1][CELL_SIZE + 1];

    public String max(int gridSerialNo, int size) {
        init(gridSerialNo);

        int maxGridValue = 0;
        int maxX = 0;
        int maxY = 0;
        for (int y = 1; y <= CELL_SIZE - size + 1; y++) {
            for (int x = 1; x <= CELL_SIZE - size + 1; x++) {
                int currentGridValue = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        currentGridValue += powerGrid[x + j][y + i];
                    }
                }
                if (currentGridValue > maxGridValue) {
                    maxGridValue = currentGridValue;
                    maxX = x;
                    maxY = y;
                }
            }
        }

        return maxX + "," + maxY;
    }

    private void init(int gridSerialNo) {
        for (int y = 1; y <= CELL_SIZE; y++) {
            for (int x = 1; x <= CELL_SIZE; x++) {
                powerGrid[x][y] = fuelCharge(x, y, gridSerialNo);
            }
        }
    }

    public String maxOfAnySize(int gridSerialNo) {
        init(gridSerialNo);

        int maxGridValue = 0;
        int maxX = 0;
        int maxY = 0;
        int size = 0;
        for (int y = 1; y <= CELL_SIZE; y++) {
            for (int x = 1; x <= CELL_SIZE; x++) {
                int maxGridSize = CELL_SIZE - x + 1;
                if (y > x) {
                    maxGridSize = CELL_SIZE - y + 1;
                }
                int previousGridValue = powerGrid[x][y];

                if (previousGridValue > maxGridValue) {
                    maxGridValue = previousGridValue;
                    maxX = x;
                    maxY = y;
                    size = 1;
                }
                for (int s = 2; s < maxGridSize; s++) {
                    int currentGridValue = previousGridValue;
                    for (int i = x; i < s + x; i++) {
                        currentGridValue += powerGrid[i][y + s - 1];
                    }
                    for (int i = y; i < s + y; i++) {
                        currentGridValue += powerGrid[x + s - 1][i];
                    }
                    currentGridValue -= powerGrid[x + s - 1][y + s - 1];
                    previousGridValue = currentGridValue;

                    if (currentGridValue > maxGridValue) {
                        maxGridValue = currentGridValue;
                        maxX = x;
                        maxY = y;
                        size = s;
                    }
                }
            }
        }

        return maxX + "," + maxY + "," + size;
    }

    public int fuelCharge(int x, int y, int gridSerialNo) {
        // The rack ID is 3 + 10 = 13.
        // The power level starts at 13 * 5 = 65.
        // Adding the serial number produces 65 + 8 = 73.
        // Multiplying by the rack ID produces 73 * 13 = 949.
        // The hundreds digit of 949 is 9.
        // Subtracting 5 produces 9 - 5 = 4.

        int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel += gridSerialNo;
        powerLevel *= rackId;
        powerLevel %= 1000;
        powerLevel /= 100;
        return powerLevel - 5;
    }
}
