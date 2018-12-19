package org.ankur.advent2018;

import org.magicwerk.brownies.collections.primitive.IntGapList;

import static org.ankur.advent.util.LogWriter.logSameLine;
import static org.ankur.advent.util.LogWriter.newLine;

public class D14ChocolateCharts {

    private IntGapList recipeList;

    private static final int NEXT_RECIPE = 10;

    public String nextTen(int recipeCount) {
        int elv1 = 0;
        int elv2 = 1;
        recipeList = new IntGapList();
        recipeList.add(3);
        recipeList.add(7);
        displayRecipes(elv1, elv2);
        while (recipeList.size() < recipeCount + NEXT_RECIPE) {
            int nextRecipes = recipeList.get(elv1) + recipeList.get(elv2);
            if (nextRecipes > 9) {
                recipeList.add(nextRecipes / 10);
                recipeList.add(nextRecipes % 10);
            } else {
                recipeList.add(nextRecipes);
            }
            elv1 = moveElf(elv1);
            elv2 = moveElf(elv2);
            displayRecipes(elv1, elv2);
        }
        StringBuilder returns = new StringBuilder();
        for (int i = recipeCount; i < recipeCount + NEXT_RECIPE; i++) {
            returns.append(recipeList.get(i));
        }
        return returns.toString();
    }

    public int firstOccurrence(String pattern) {
        int elv1 = 0;
        int elv2 = 1;
        recipeList = new IntGapList();
        recipeList.add(3);
        recipeList.add(7);
        displayRecipes(elv1, elv2);
        StringBuilder returns = new StringBuilder();

        while (true) {
            int nextRecipes = recipeList.get(elv1) + recipeList.get(elv2);
            if (nextRecipes > 9) {
                recipeList.add(nextRecipes / 10);
                returns.append(nextRecipes / 10);
                recipeList.add(nextRecipes % 10);
                returns.append(nextRecipes % 10);
            } else {
                recipeList.add(nextRecipes);
                returns.append(nextRecipes);
            }
            elv1 = moveElf(elv1);
            elv2 = moveElf(elv2);
            displayRecipes(elv1, elv2);

            if (returns.indexOf(pattern) > -1) {
                int last = recipeList.get(recipeList.size() - 1);
                char lastChar = pattern.charAt(pattern.length() - 1);
                if (last == (lastChar - '0')) {
                    return recipeList.size() - pattern.length();
                } else {
                    return recipeList.size() - pattern.length() - 1;
                }
            } else if (returns.length() > pattern.length() * 3) {
                //Just shorten the string to compare else it takes a lot of time
                returns.delete(0, pattern.length());
            }
        }
    }


    private void displayRecipes(int elv1, int elv2) {
        if (elv1 > 20 || elv2 > 20) {
            return;
        }
        for (int i = 0; i < recipeList.size(); i++) {
            if (i == elv1) {
                logSameLine("(" + recipeList.get(i) + ")");
            } else if (i == elv2) {
                logSameLine("[" + recipeList.get(i) + "]");
            } else {
                logSameLine(" " + recipeList.get(i) + " ");
            }
        }
        newLine();
    }

    private int moveElf(int elv) {
        elv += recipeList.get(elv) + 1;
        int size = recipeList.size();
        while (elv >= size) {
            elv = elv % size;
        }
        return elv;
    }
}
