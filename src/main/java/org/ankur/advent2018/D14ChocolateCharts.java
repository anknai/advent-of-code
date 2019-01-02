package org.ankur.advent2018;

import org.magicwerk.brownies.collections.primitive.IntGapList;

import static org.ankur.advent.util.LogWriter.logSameLine;
import static org.ankur.advent.util.LogWriter.newLine;

public class D14ChocolateCharts {

    private IntGapList recipeList;

    private static final int NEXT_RECIPE = 10;

    public String nextTen(int recipeCount) {
        int elf1 = 0;
        int elf2 = 1;
        recipeList = new IntGapList();
        recipeList.add(3);
        recipeList.add(7);
        displayRecipes(elf1, elf2);
        while (recipeList.size() < recipeCount + NEXT_RECIPE) {
            int nextRecipes = recipeList.get(elf1) + recipeList.get(elf2);
            if (nextRecipes > 9) {
                recipeList.add(nextRecipes / 10);
                recipeList.add(nextRecipes % 10);
            } else {
                recipeList.add(nextRecipes);
            }
            elf1 = moveElf(elf1);
            elf2 = moveElf(elf2);
            displayRecipes(elf1, elf2);
        }
        StringBuilder returns = new StringBuilder();
        for (int i = recipeCount; i < recipeCount + NEXT_RECIPE; i++) {
            returns.append(recipeList.get(i));
        }
        return returns.toString();
    }

    public int firstOccurrence(String pattern) {
        int elf1 = 0;
        int elf2 = 1;
        recipeList = new IntGapList();
        recipeList.add(3);
        recipeList.add(7);
        displayRecipes(elf1, elf2);
        StringBuilder returns = new StringBuilder();

        while (true) {
            int nextRecipes = recipeList.get(elf1) + recipeList.get(elf2);
            if (nextRecipes > 9) {
                recipeList.add(nextRecipes / 10);
                returns.append(nextRecipes / 10);
                recipeList.add(nextRecipes % 10);
                returns.append(nextRecipes % 10);
            } else {
                recipeList.add(nextRecipes);
                returns.append(nextRecipes);
            }
            elf1 = moveElf(elf1);
            elf2 = moveElf(elf2);
            displayRecipes(elf1, elf2);

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


    private void displayRecipes(int elf1, int elf2) {
        if (elf1 > 20 || elf2 > 20) {
            return;
        }
        for (int i = 0; i < recipeList.size(); i++) {
            if (i == elf1) {
                logSameLine("(" + recipeList.get(i) + ")");
            } else if (i == elf2) {
                logSameLine("[" + recipeList.get(i) + "]");
            } else {
                logSameLine(" " + recipeList.get(i) + " ");
            }
        }
        newLine();
    }

    private int moveElf(int elf) {
        elf += recipeList.get(elf) + 1;
        int size = recipeList.size();
        while (elf >= size) {
            elf = elf % size;
        }
        return elf;
    }
}
