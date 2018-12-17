package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;
import nl.ing.advent2018.domain.Soil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.ing.advent.util.LogWriter.newLine;

public class D17ReservoirResearch {

    private List<Soil> soilList;
    private char[][] reservoir;
    private int fountainPosition;

    public int capacity(String fileName) {
        init(fileName);
        //display();
        newLine();
        openTap();
        display();
        return -1;

    }

    private void init(String fileName) {
        List<String> lines = FileReader.readFile(fileName);
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        soilList = new ArrayList<>();
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
        System.out.println("minX " + minX + " minY" + minY + " maxX " + maxX + " maxY " + maxY);
        int height = maxY + 1;
        int width = maxX - minX + 1;
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
        for (char[] aReservoir : reservoir) {
            System.out.println(aReservoir);
        }
    }

    private void openTap() {
        int y = 1;
        while (reservoir[y][fountainPosition] != '#') {
            reservoir[y ++][fountainPosition] = '|';
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
