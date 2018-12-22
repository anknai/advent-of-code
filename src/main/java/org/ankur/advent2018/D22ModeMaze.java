package org.ankur.advent2018;

import org.ankur.advent2018.domain.Region;
import org.ankur.advent2018.domain.Region.RegionType;

import java.util.ArrayList;
import java.util.List;

import static org.ankur.advent2018.domain.Region.Gear.CLIMBING_GEAR;
import static org.ankur.advent2018.domain.Region.Gear.NEITHER;
import static org.ankur.advent2018.domain.Region.Gear.TORCH;
import static org.ankur.advent2018.domain.Region.RegionType.*;

public class D22ModeMaze {

    private int targetX;

    private int targetY;

    private int maxX;

    private int maxY;

    private int depth;

    private int erosionLevel[][];

    private List<Region[]> regionList;

    public int getRiskLevel(int depth, int targetX, int targetY) {
        this.depth = depth;
        this.targetX = targetX;
        this.targetY = targetY;
        this.maxX = targetX;
        this.maxY = targetY;
        regionList = new ArrayList<>();
        erosionLevel = new int[maxX + 1][maxY + 1];
        return riskLevel();
    }

    public int getShortestPath(int depth, int targetX, int targetY) {
        this.depth = depth;
        this.targetX = targetX;
        this.targetY = targetY;
        maxX = targetX + 30;
        maxY = targetY + 10;
        regionList = new ArrayList<>();
        erosionLevel = new int[maxX + 1][maxY + 1];
        riskLevel();
        return shortest();
    }

    private int shortest() {
        for (Region[] region: regionList) {
            //System.out.println(region);
            int x = region[0].getX();
            int y = region[0].getY();
            add(region[0], x, y);
            add(region[1], x, y);
        }
        RegionDijkstra.calculateShortestPathFromSource(getRegion(0, 0)[0]);
        Region region = getRegion(targetX, targetY)[0];
        assert region != null;
        for (Region neighbour: region.getShortestPath()) {
            System.out.println(neighbour + " " + neighbour.getDistance());
        }
        return region.getDistance();
    }

    private void add(Region region, int x, int y) {
        if (null == region) {
            return;
        }
        region.addDestination(getRegion(x - 1, y)[0]);
        region.addDestination(getRegion(x - 1, y)[1]);
        region.addDestination(getRegion(x, y - 1 )[0]);
        region.addDestination(getRegion(x, y - 1 )[1]);
        region.addDestination(getRegion(x, y + 1)[0]);
        region.addDestination(getRegion(x, y + 1)[1]);
        region.addDestination(getRegion(x + 1, y)[0]);
        region.addDestination(getRegion(x + 1, y)[1]);
    }

    private Region[] getRegion(int x, int y) {
        if (x < 0 || y < 0 || x > maxX || y > maxY) {
            return new Region[]{null, null};
        }
        int index = (y * (maxX + 1) + x);

        return regionList.get(index);
    }

    private int riskLevel() {
        int total = 0;
        for (int y = 0; y <= maxY; y ++) {
            for (int x = 0; x <= maxX; x ++) {
                int risk = riskLevel(x, y);
                RegionType regionType = getRegionType(risk);
                Region region1 = new Region(x, y, regionType);
                Region region2 = new Region(x, y, regionType);
                switch (regionType) {
                    case ROCKY:
                        region1.setGear(TORCH);
                        region2.setGear(CLIMBING_GEAR);
                        break;
                    case NARROW:
                        region1.setGear(TORCH);
                        region2.setGear(NEITHER);
                        break;
                    case WET:
                        region1.setGear(NEITHER);
                        region2.setGear(CLIMBING_GEAR);
                        break;
                }
                if (x == 0 && y == 0 || x == targetX && y == targetY) {
                    regionList.add(new Region[]{region1, null});
                } else {
                    regionList.add(new Region[]{region1, region2});
                }
                display(x, y, risk);
                total += risk;
            }
            System.out.println();
        }
        System.out.println("total items in the list " + regionList.size());
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

    private RegionType getRegionType(int riskLevel) {
        if (riskLevel == 0) {
            return ROCKY;
        }
        if (riskLevel == 1) {
            return WET;
        }
        return NARROW;
    }
}
