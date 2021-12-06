package org.ankur.advent2021.d05;

import static java.lang.Integer.parseInt;

public class Cloud {

    private int[][] vents;

    private final int dimension;

    public Cloud(int dimension) {
        this.dimension = dimension;
        vents = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                vents[i][j] = 0;
            }
        }
    }

    public void addLine(String line, boolean diagnoal) {
        //0,9 -> 5,9
        String[] split = line.split(" -> ");
        String[] first = split[0].split(",");
        int x1 = parseInt(first[0]);
        int y1 = parseInt(first[1]);
        String[] second = split[1].split(",");
        int x2 = parseInt(second[0]);
        int y2 = parseInt(second[1]);
        if (x1 == x2) {
            if (y1 < y2) {
                addVentY(x1, y1, y2);
            } else if (y2 < y1) {
                addVentY(x1, y2, y1);
            }
        } else if (y1 == y2) {
            if (x1 < x2) {
                addVentX(y1, x1, x2);
            } else {
                addVentX(y1, x2, x1);
            }
        } else if (diagnoal && (Math.abs(x1 - x2) == Math.abs(y1 - y2))) {
            int max = Math.abs(x1 - x2);
            int dx = 1;
            int dy = 1;
            if (x1 > x2) {
                dx = -1;
            }
            if (y1 > y2) {
                dy = -1;
            }
            for (int i = 0; i <= max; i++) {
                vents[x1 + dx * i][y1 + dy * i]++;
            }
        }
    }

    private void addVentY(int x1, int from, int to) {
        for (int i = from; i <= to; i++) {
            vents[x1][i]++;
        }
    }

    private void addVentX(int y, int from, int to) {
        for (int i = from; i <= to; i++) {
            vents[i][y]++;
        }
    }

    public int countMax() {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (vents[i][j] > 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
