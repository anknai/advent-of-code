package org.ankur.advent2019.d10;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class MonitoringStation {

    private int[][] map;

    private List<Asteroid> asteroids;

    private int maxX = 0;

    private int maxY = 0;

    public int readFile1(String inputFile) {
        List<String> s = FileReader.readFile(inputFile);
        init(s);
        return iterate();
    }

    public void readFile2(String inputFile, int x, int y) {
        List<String> s = FileReader.readFile(inputFile);
        init(s);
        vaporize(x, y);
    }

    private void vaporize(int x, int y) {
        int i = map[x][y];
        Asteroid asteroid = asteroids.get(i - 1);
        calculate(asteroid);
        print(asteroid);
        SortedSet<Asteroid> sorted = new TreeSet<>();
        for (Asteroid asteroid1 : asteroids) {
            if (asteroid1.isInSight()) {
                sorted.add(asteroid1);
            }
        }
        for (Asteroid asteroid1 : sorted) {
            System.out.println(asteroid1);
        }
    }

    private void print(Asteroid asteroid) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                int i = map[x][y];
                char c = '.';
                if (i > 0) {
                    Asteroid other = asteroids.get(i - 1);
                    if (other == asteroid) {
                        c = 'X';
                    }
                    else if (other.isInSight()) {
                        c = '#';
                    }
                }
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private void slope(Asteroid asteroid, Asteroid other) {
        int x = asteroid.getX();
        int y = asteroid.getY();
        other.setSlope(Math.atan2(other.getX() - x, y - other.getY()));
    }

    private int iterate() {
        int max = Integer.MIN_VALUE;
        for (Asteroid asteroid : asteroids) {
            System.out.println("scanning " + asteroid);
            int count = calculate(asteroid);
            System.out.println("Asteroids in sight are " + count);
            max = Math.max(max, count);
        }
        return max;
    }

    private int calculate(Asteroid asteroid) {
        int count = asteroids.size() - 1;
        for (Asteroid other : asteroids) {
            slope(asteroid, other);
            if (other == asteroid) {
                continue;
            }
            other.setInSight(true);
        }
        for (Asteroid other : asteroids) {
            if (other == asteroid) {
                continue;
            }
            findInSight(asteroid, other);
        }

        for (Asteroid other : asteroids) {
            if (other == asteroid) {
                continue;
            }
            if (!other.isInSight()) {
                count--;
            }
        }
        return count;
    }

    private void findInSight(Asteroid asteroid, Asteroid other) {
        int otherX = other.getX();
        int otherY = other.getY();
        int thisX = asteroid.getX();
        int thisY = asteroid.getY();
        if (otherY == thisY) {
            if (otherX < thisX) {
                for (int x = thisX - 1; x > otherX; x--) {
                    if (found(x, otherY, other)) {
                        return;
                    }
                }
            } else {
                for (int x = thisX + 1; x < otherX; x++) {
                    if (found(x, otherY, other)) {
                        return;
                    }
                }
            }
        } else if (otherX == thisX) {
            if (otherY < thisY) {
                for (int y = thisY - 1; y > otherY; y--) {
                    if (found(otherX, y, other)) {
                        return;
                    }
                }
            } else {
                for (int y = thisY + 1; y < otherY; y++) {
                    if (found(otherX, y, other)) {
                        return;
                    }
                }
            }
        } else {
            int diffX = Math.abs(otherX - thisX);
            int diffY = Math.abs(otherY - thisY);
            int x;
            int y;
            int gcd = gcdByBruteForce(diffX, diffY);
            diffX = diffX / gcd;
            diffY = diffY / gcd;
            if (otherX < thisX && otherY < thisY) {
                y = otherY - diffY;
                for (x = otherX - diffX; x >= 0; x -= diffX) {
                    if (y >= 0) {
                        outOfSight(x, y);
                    }
                    y -= diffY;
                }
            } else if (otherX < thisX) {
                y = otherY + diffY;
                for (x = otherX - diffX; x >= 0; x -= diffX) {
                    if (y < maxY) {
                        outOfSight(x, y);
                    }
                    y += diffY;
                }
            } else if (otherY > thisY) {
                y = otherY + diffY;
                for (x = otherX + diffX; x < maxX; x += diffX) {
                    if (y < maxY) {
                        outOfSight(x, y);
                    }
                    y += diffY;
                }
            } else {
                y = otherY - diffY;
                for (x = otherX + diffX; x < maxX; x += diffX) {
                    if (y >= 0) {
                        outOfSight(x, y);
                    }
                    y -= diffY;
                }
            }
        }
    }

    private boolean found(int x, int y, Asteroid other) {
        if (map[x][y] != 0) {
            //System.out.println("Found someone at " + x + " " + y + " blocking " + other);
            other.setInSight(false);
            return true;
        }
        return false;
    }

    private void outOfSight(int x, int y) {
        int i = map[x][y];
        if (i != 0) {
            Asteroid other = asteroids.get(i - 1);
            //System.out.println(other + " is out of sight");
            other.setInSight(false);
        }
    }

    private void init(List<String> list) {
        asteroids = new ArrayList<>();
        maxX = list.get(0).length();
        maxY = list.size();
        map = new int[maxX][maxY];
        int count = 0;
        for (int y = 0; y < list.size(); y++) {
            String s = list.get(y);
            char[] charArray = s.toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                char c = charArray[x];
                if (c == '#') {
                    Asteroid asteroid = new Asteroid(x, y);
                    asteroids.add(asteroid);
                    map[x][y] = ++count;
                } else {
                    map[x][y] = 0;
                }
            }
        }
    }

    int gcdByBruteForce(int n1, int n2) {
        int gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }
}
