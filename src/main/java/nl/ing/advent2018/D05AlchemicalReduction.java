package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;

import java.util.TreeSet;

public class D05AlchemicalReduction {

    public int polymerUnits(String fileName) {
        String polymer = FileReader.readFileAsString(fileName);
        assert polymer != null;
        return findReactivity(polymer);
    }

    private int findReactivity(String polymer) {
        boolean matchFound;
        do {
            matchFound = false;
            StringBuilder polymerBuilder = new StringBuilder();
            char[] polymerArray = polymer.toCharArray();
            for (int i = 0; i < polymerArray.length; i++) {
                char current = polymerArray[i];
                char next;
                if (i < polymerArray.length - 1) {
                    next = polymerArray[i + 1];
                } else {
                    next = '\0';
                }
                if (Math.abs((int) current - (int) next) == 32) {
                    matchFound = true;
                    i++;
                } else {
                    polymerBuilder.append(current);
                }
            }
            polymer = polymerBuilder.toString();
        } while (matchFound);
        return polymer.length();
    }

    public int improvePolymer(String fileName) {
        String polymer = FileReader.readFileAsString(fileName);
        assert polymer != null;

        TreeSet<Integer> reactivitySet = new TreeSet<>();
        for (int i = 65; i < 90; i++) {
            String newPolymer = polymer.replace(Character.toString((char) i), "");
            newPolymer = newPolymer.replace(Character.toString((char) (i + 32)), "");
            int reactivity = findReactivity(newPolymer);
            reactivitySet.add(reactivity);
        }
        return reactivitySet.first();
    }
}
