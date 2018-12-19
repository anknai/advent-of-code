package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Soil;

import java.util.ArrayList;
import java.util.List;

public class D17ReservoirResearch {

    private char[][] reservoir;
    private int fountainPosition;

    private int height;
    private int width;
    private int minY;
    private boolean timeToBreak;

    public int capacity(String fileName, boolean onlyWater) {
        timeToBreak = false;
        init(fileName);
        //display();
        for (int loop = 0; loop < 48; loop++) {
            if (timeToBreak) {
                break;
            }
            down(fountainPosition, 1);
            //display();
        }
        System.out.println();
        //display();
        int waterCapacity = 0;
        int pipes = 0;
        for (int i = 0; i < width; i++) {
            for (int j = minY; j < height; j++) {
                if (reservoir[j][i] == '~') {
                    waterCapacity++;
                } else if (reservoir[j][i] == '|') {
                    pipes ++;
                }
            }
        }
        if (onlyWater) {
            return waterCapacity;
        } else {
            return waterCapacity + pipes;
        }
    }

    private void init(String fileName) {
        List<String> lines = FileReader.readFile(fileName);
        int minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        List<Soil> soilList = new ArrayList<>();
        for (String line : lines) {
            Soil soil = parseSoil(line);
            soilList.add(soil);
            if (soil.getStartX() < minX) {
                minX = soil.getStartX();
            }
            if (soil.getStartY() < minY) {
                minY = soil.getStartY();
            }
            if (soil.getEndX() > maxX) {
                maxX = soil.getEndX();
            }
            if (soil.getEndY() > maxY) {
                maxY = soil.getEndY();
            }
        }
        height = maxY + 1;
        minX--;
        maxX++;
        width = maxX - minX + 1;
        System.out.println("minX " + minX + " minY " + minY + " maxX " + maxX + " maxY " + maxY + " height " + height + " width " + width);

        reservoir = new char[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                reservoir[j][i] = '.';
            }
        }
        //Set 500, 0 to Water fountain
        fountainPosition = 500 - minX;
        reservoir[0][fountainPosition] = '+';
        for (Soil soil : soilList) {
            for (int i = soil.getStartX() - minX; i < soil.getEndX() - minX; i++) {
                reservoir[soil.getStartY()][i] = '#';
            }

            for (int i = soil.getStartY(); i < soil.getEndY() + 1; i++) {
                reservoir[i][soil.getStartX() - minX] = '#';
            }
        }
    }

    private void display() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(reservoir[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void down(int x, int y) {
        while (y < height && reservoir[y][x] != '#' && reservoir[y][x] != '~') {
            reservoir[y ++][x] = '|';
            if (y == height) {
                timeToBreak = true;
                return;
            }
        }
        --y;
        fillWater(x, y);
        left(x, y);
        right(x, y);
    }

    private void left(int x, int y) {
        while (x >= 0 && reservoir[y][x] != '#' && reservoir[y][x] != '~') {
            //porous, go down
            if (reservoir[y + 1][x] == '.' || reservoir[y + 1][x] == '|') {
                down(x, y);
                return;
            } else {
                reservoir[y][x -- ] = '|';
            }
        }
    }

    private void fillWater(int x, int y) {
        //Found a clay, so lets fill water here if there is a clay on the right side and bottom too
        int leftClay = 0;
        int rightClay = 0;
        if (x > 0) {
            for (int i = x; i < width; i++) {
                if (reservoir[y + 1][i] != '#' && reservoir[y + 1][i] != '~') {
                    //Bottom clay not found
                    return;
                }
                if (reservoir[y][i] == '#') {
                    rightClay = i;
                    break;
                }
            }
        }

        if (x < width) {
            for (int i = x; i > 0; i--) {
                if (reservoir[y + 1][i] != '#' && reservoir[y + 1][i] != '~') {
                    //Bottom clay not found
                    return;
                }
                if (reservoir[y][i] == '#') {
                    leftClay = i;
                    break;
                }
            }
        }

        for (int i = leftClay + 1; i < rightClay; i++) {
            reservoir[y][i] = '~';
        }
    }

    private void right(int x, int y) {
        while (x < width && reservoir[y][x] != '#' && reservoir[y][x] != '~') {
            //porous, go down
            if (reservoir[y + 1][x] == '.' || reservoir[y + 1][x] == '|') {
                down(x, y);
                return;
            } else {
                reservoir[y][x ++ ] = '|';
            }
        }
    }

    //x=495, y=2..7
    //y=7, x=495..501
    public Soil parseSoil(String line) {
        int startX;
        int startY;
        int endX;
        int endY;
        if (line.charAt(0) == 'x') {
            endX = Integer.parseInt(line.substring(2, line.indexOf(',')));
            startX = endX;
            startY = Integer.parseInt(line.substring(line.indexOf("y=") + 2, line.indexOf('.')));
            endY = Integer.parseInt(line.substring(line.lastIndexOf('.') + 1));
        } else {
            endY = Integer.parseInt(line.substring(2, line.indexOf(',')));
            startY = endY;
            startX = Integer.parseInt(line.substring(line.indexOf("x=") + 2, line.indexOf('.')));
            endX = Integer.parseInt(line.substring(line.lastIndexOf('.') + 1));
        }

        return Soil.builder()
                .startX(startX)
                .startY(startY)
                .endX(endX)
                .endY(endY)
                .build();
    }
}
