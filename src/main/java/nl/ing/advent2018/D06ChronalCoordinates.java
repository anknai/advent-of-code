package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;
import nl.ing.advent2018.domain.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static nl.ing.advent.util.LogWriter.log;

public class D06ChronalCoordinates {

    private int[][] coordinateMap;
    private int maxX;
    private int maxY;

    public int largestManhattan(String fileName) {
        List<Coordinate> coordinates = init(fileName);

        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                if (coordinateMap[x][y] == -1) {
                    coordinateMap[x][y] = closest(x, y, coordinates);
                }
            }
        }

        for (int x = 0; x <= maxX; x++) {
            if (coordinateMap[x][0] != 0 && coordinateMap[x][0] < 100) {
                Coordinate coordinate = coordinates.get(coordinateMap[x][0] - 1);
                coordinate.setInfinite(true);
            }

            if (coordinateMap[x][maxY] != 0 && coordinateMap[x][maxY] < 100) {
                Coordinate coordinate = coordinates.get(coordinateMap[x][maxY] - 1);
                coordinate.setInfinite(true);
            }
        }

        for (int y = 0; y <= maxY; y++) {
            if (coordinateMap[0][y] != 0 && coordinateMap[0][y] < 100) {
                Coordinate coordinate = coordinates.get(coordinateMap[0][y] - 1);
                coordinate.setInfinite(true);
            }

            if (coordinateMap[maxX][y] != 0 && coordinateMap[maxX][y] < 100) {
                Coordinate coordinate = coordinates.get(coordinateMap[maxX][y] - 1);
                coordinate.setInfinite(true);
            }
        }

        for (Coordinate coordinate: coordinates) {
            if (coordinate.isInfinite()) {
                continue;
            }
            for (int x = 0; x <= maxX; x++) {
                for (int y = 0; y <= maxY; y++) {
                    if (coordinateMap[x][y] == coordinate.getCoordinateId() || coordinateMap[x][y] == coordinate.getCoordinateId() + 101) {
                        coordinate.addArea();
                    }
                }
            }
        }

        int maxArea = 0;
        int maxCoordinate = 0;
        for (Coordinate coordinate: coordinates) {
            if (maxArea < coordinate.getArea()) {
                maxArea = coordinate.getArea();
                maxCoordinate = coordinate.getCoordinateId();
            }
        }

        log("Maximum area is " + maxArea + " for coordinate " + maxCoordinate);
        return maxArea;
    }

    private List<Coordinate> init(String fileName) {
        List<String> coordinateList = FileReader.readFile(fileName);
        List<Coordinate> coordinates = new ArrayList<>();
        for (String coordinate: coordinateList) {
            Coordinate co = parseString(coordinate);
            coordinates.add(co);
            co.setCoordinateId(coordinates.size());
            if (maxX < co.getX()) {
                maxX = co.getX();
            }
            if (maxY < co.getY()) {
                maxY = co.getY();
            }
        }
        log("The dimensions are " + maxX + "\t" + maxY);


        coordinateMap = new int[maxX + 1][maxY + 1];
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                coordinateMap[x][y] = -1;
            }
        }

        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate coordinate = coordinates.get(i);
            coordinateMap[coordinate.getX()][coordinate.getY()] = i + 101;
        }

        return coordinates;
    }

    //247, 302
    public Coordinate parseString(String string) {
        int x = Integer.parseInt(string.substring(0, string.indexOf(',')));
        int y = Integer.parseInt(string.substring(string.indexOf(',') + 2));
        return Coordinate.builder()
                .x(x)
                .y(y).build();
    }

    private int closest(int x, int y, List<Coordinate> coordinates) {
        int closest = Integer.MAX_VALUE;
        int closestCoordinate = -1;
        for (Coordinate coordinate: coordinates) {
            int cx = coordinate.getX();
            int cy = coordinate.getY();
            int distance = Math.abs(cx - x) + Math.abs(cy - y);
            if (closest > distance) {
                closest = distance;
                closestCoordinate = coordinate.getCoordinateId();
            } else if (closest == distance) {
                closestCoordinate = 0;
            }
        }
        return closestCoordinate;
    }

    public int closestRegion(String fileName, int maxSize) {
        List<Coordinate> coordinates = init(fileName);

        int regionSize = 0;
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                int distance = 0;
                for (Coordinate coordinate : coordinates) {
                    int cx = coordinate.getX();
                    int cy = coordinate.getY();
                    distance += Math.abs(cx - x) + Math.abs(cy - y);
                    if (distance > maxSize) {
                        coordinateMap[x][y] = -1;
                        break;
                    }
                }

                if (distance < maxSize) {
                    coordinateMap[x][y] = distance;
                    regionSize++;
                }
            }
        }

        return regionSize;
    }
}
