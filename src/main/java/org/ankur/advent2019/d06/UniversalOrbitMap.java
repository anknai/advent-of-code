package org.ankur.advent2019.d06;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversalOrbitMap {

    public int readFile1(String file) {
        List<String> strings = FileReader.readFile(file);
        Map<String, Orbit> orbitMap = populate(strings);
        calculateOrbits(orbitMap);
        return count(orbitMap);
    }

    public int readFile2(String file) {
        List<String> strings = FileReader.readFile(file);
        Map<String, Orbit> orbitMap = populate(strings);
        return calculateOrbits2(orbitMap);
    }

    private Map<String, Orbit> populate(List<String> strings) {
        Map<String, Orbit> orbitMap = new HashMap<>();
        for (String string : strings) {
            String[] split = string.split("\\)");
            String orbitee = split[0];
            String orbitter = split[1];
            Orbit orbit = new Orbit(orbitter, orbitee);
            orbitMap.put(orbitter, orbit);
        }

        return orbitMap;
    }

    private void calculateOrbits(Map<String, Orbit> orbitMap) {
        for (String orbitter : orbitMap.keySet()) {
            //System.out.println("orbitter = " + orbitter);
            Orbit direct = orbitMap.get(orbitter);
            Orbit orbit = direct;
            while (true) {
                orbit = orbitMap.get(orbit.getOrbitee());
                if (null != orbit) {
                    direct.incOrbits();
                    //System.out.println(orbit);
                } else {
                    break;
                }
            }
        }
    }

    private int calculateOrbits2(Map<String, Orbit> orbitMap) {
        List<String> myOrbits = orbits(orbitMap, "YOU");
        List<String> santaOrbits = orbits(orbitMap, "SAN");
        System.out.println("myOrbits.toArray() = " + Arrays.toString(myOrbits.toArray()));
        System.out.println("santaOrbits.toArray() = " + Arrays.toString(santaOrbits.toArray()));
        for (int i = 0; i < myOrbits.size(); i++) {
            String myOrbit = myOrbits.get(i);
            for (int j = 0; j < santaOrbits.size(); j++) {
                String santaOrbit = santaOrbits.get(j);
                if (myOrbit.equals(santaOrbit)) {
                    System.out.println("myOrbit = " + myOrbit + " i " + i);
                    System.out.println("santaOrbit = " + santaOrbit + " j " + j);
                    return i + j;
                }
            }
        }
        return -1;

    }

    private List<String> orbits(Map<String, Orbit> orbitMap, String orbitter) {
        List<String> orbits = new ArrayList<>();
        Orbit orb = orbitMap.get(orbitter);
        while (null != orb) {
            orbits.add(orb.getOrbitee());
            orb = orbitMap.get(orb.getOrbitee());
        }
        return orbits;
    }

    private int count(Map<String, Orbit> orbitMap) {
        int count = 0;
        for (String orbitter : orbitMap.keySet()) {
            Orbit orbit = orbitMap.get(orbitter);
            count += orbit.getNrOfOrbits();
        }
        return count;
    }
}
