package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Star;

import java.util.ArrayList;
import java.util.List;

import static org.ankur.advent.util.LogWriter.log;
import static org.ankur.advent.util.LogWriter.logSameLine;
import static org.ankur.advent.util.LogWriter.newLine;

public class D10StarsAlignment {

    private List<Star> starList;

    private int maxX = Integer.MIN_VALUE;
    private int minX = Integer.MAX_VALUE;
    private int maxY = Integer.MIN_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int xLength;
    private int yLength;

    public int message(String fileName, int count) {
        starList = init(fileName);
        int seconds = 0;
        do {
            seconds++;
            for (Star star: starList) {
                star.setX(star.getX() + star.getXVelocity());
                star.setY(star.getY() + star.getYVelocity());
            }
        } while (count < minimize(starList));

        for (Star star: starList) {
            star.setX(star.getX() + star.getXVelocity());
            star.setY(star.getY() + star.getYVelocity());
        }
        displayCurrentPosition(starList);
        return ++ seconds;
    }

    private List<Star> init(String fileName) {
        List<String> stars = FileReader.readFile(fileName);
        starList = new ArrayList<>();

        for (String s: stars) {
            Star star = parseStar(s);
            starList.add(star);
        }

        return starList;
    }

    private int minimize(List<Star> starList) {
        maxX = Integer.MIN_VALUE;
        minX = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        for (Star star: starList) {
            if (maxX < star.getX()) {
                maxX = star.getX();
            }

            if (maxY < star.getY()) {
                maxY = star.getY();
            }

            if (minX > star.getX()) {
                minX = star.getX();
            }

            if (minY > star.getY()) {
                minY = star.getY();
            }
        }

        xLength = maxX - minX + 1;
        yLength = maxY - minY + 1;

        return xLength + yLength;
    }

    private void displayCurrentPosition(List<Star> starList) {
        minimize(starList);
        log(minX + "\t" + maxX + "\t" + minY + "\t" + maxY);

        String[][] universe = new String[yLength][xLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                universe[i][j] = ".";
            }
        }

        for (Star star: starList) {
            universe[star.getY() - minY][star.getX() - minX] = "#";
        }

        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                logSameLine(universe[i][j] + " ");
            }
            newLine();
        }
    }

    //position=< 3, -2> velocity=<-1,  1>
    public Star parseStar(String text) {
        int x = Integer.parseInt(text.substring(10, text.indexOf(',')).trim());
        int y = Integer.parseInt(text.substring(text.indexOf(',') + 1, text.indexOf('>')).trim());
        text = text.substring(text.indexOf("velocity"));
        int xVelocity = Integer.parseInt(text.substring(10, text.indexOf(',')).trim());
        int yVelocity = Integer.parseInt(text.substring(text.indexOf(',') + 1, text.indexOf('>')).trim());
        return Star.builder()
                .x(x)
                .y(y)
                .xVelocity(xVelocity)
                .yVelocity(yVelocity)
                .build();
    }
}
