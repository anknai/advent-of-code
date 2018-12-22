package org.ankur.advent2018.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

import static org.ankur.advent2018.domain.Region.Gear.*;
import static org.ankur.advent2018.domain.Region.RegionType.*;

@Getter
@Setter
@ToString(exclude = {"shortestPath", "adjacentNodes", "distance"})
@NoArgsConstructor
public class Region {

    private int x;

    private int y;

    private RegionType regionType;

    private LinkedList<Region> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Region, Integer> adjacentNodes = new HashMap<>();

    private Gear gear;

    public Region(int x, int y, RegionType regionType) {
        this.x = x;
        this.y = y;
        this.regionType = regionType;
    }

    @Override
    public int hashCode() {
        return x* 5 + y*13 + 29;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Region)) {
            return false;
        }
        Region other = (Region) o;
        return (other.x == this.x && other.y == this.y
                && this.regionType == other.regionType && this.gear == other.gear);
    }

    private void addDestination(Region destination, int distance) {
        adjacentNodes.put(destination, distance);
        //destination.setGear(gear);
        //System.out.println("From " + this + " Destination " + destination + " Distance " + distance);
    }

    public void addDestination(Region destination) {
        if (null == destination || null == this.gear) {
            return;
        }

        if (destination.regionType == this.regionType) {
            if (destination.gear == this.gear) {
                addDestination(destination, 1);
            } else {
                addDestination(destination, 8);
            }
            return;
        }

        switch (this.regionType) {
            case ROCKY:
                if (gear == TORCH) {
                    if (destination.getRegionType() == NARROW && destination.gear == gear) {
                        addDestination(destination, 1);
                    }
                } else if (gear == CLIMBING_GEAR) {
                    if (destination.getRegionType() == WET && destination.gear == gear) {
                        addDestination(destination, 1);
                    }
                }
                break;
            case WET:
                if (gear == CLIMBING_GEAR) {
                    if (destination.getRegionType() == ROCKY && destination.gear == gear) {
                        addDestination(destination, 1);
                    } else if (destination.getRegionType() == NARROW && destination.gear == NEITHER) {
                        addDestination(destination, 8);
                    }
                } else if (gear == NEITHER) {
                    if (destination.getRegionType() == ROCKY && destination.gear == CLIMBING_GEAR) {
                        addDestination(destination, 8);
                    } else if (destination.getRegionType() == NARROW && destination.gear == gear) {
                        addDestination(destination, 1);
                    }
                }
                break;
            case NARROW:
                if (gear == TORCH) {
                    if (destination.getRegionType() == ROCKY && destination.gear == gear) {
                        addDestination(destination, 1);
                    } else if (destination.getRegionType() == WET && destination.gear == NEITHER) {
                        addDestination(destination, 8);
                    }
                } else if (gear == NEITHER) {
                    if (destination.getRegionType() == ROCKY && destination.gear == TORCH) {
                        addDestination(destination, 8);
                    } else if (destination.getRegionType() == WET && destination.gear == gear) {
                        addDestination(destination, 1);
                    }
                }
                break;
        }
    }

    public enum RegionType {
        ROCKY(0),
        WET(1),
        NARROW(2);

        private int type;

        RegionType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    public enum Gear {
        TORCH,
        CLIMBING_GEAR,
        NEITHER;
    }
}
