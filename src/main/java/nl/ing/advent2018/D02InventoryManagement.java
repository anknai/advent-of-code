package nl.ing.advent2018;

import nl.ing.advent.util.FileReader;

import java.util.List;

import static nl.ing.advent.util.LogWriter.log;

public class D02InventoryManagement {

    private static final int TOTAL_ALPHABETS = 26;

    private static final int START_ASCII = 97;

    private int twos = 0;

    private int threes = 0;

    public int checksum(String input) {
        twos = 0;
        threes = 0;
        List<String> inventoryList = FileReader.readFile(input);
        for (String id: inventoryList) {
            countCharacters(id);
        }
        log("Twos " + twos + "\tThrees " + threes);
        return twos * threes;
    }

    public String closest(String input) {
        List<String> inventoryList = FileReader.readFile(input);
        for (String id: inventoryList) {
            int match = matchPosition(id, inventoryList);
            if (-1 != match) {
                String closest = id.substring(0, match) + id.substring(match + 1);
                log("Matching id " + id + " at position " + match + " is " + closest);
                return closest;
            }
        }
        return null;
    }

    private void countCharacters(String id) {

        int[] count = new int[TOTAL_ALPHABETS];
        for (int i = 0; i < TOTAL_ALPHABETS; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            count[(int) c - START_ASCII]++;
        }

        for (int i = 0; i < TOTAL_ALPHABETS; i++) {
            if (count[i] == 2) {
                twos ++;
                break;
            }
        }

        for (int i = 0; i < TOTAL_ALPHABETS; i++) {
            if (count[i] == 3) {
                threes ++;
                break;
            }
        }
    }

    private int matchPosition(String id, List<String> inventory) {
        int mismatchPosition = -1;
        for (String inv: inventory) {
            if (id.equals(inv)) {
                continue;
            }

            int length = id.length();
            boolean mismatchFound = false;

            for (int i = 0; i < length; i++) {
                if (id.charAt(i) == inv.charAt(i)) {
                    continue;
                } else if (!mismatchFound) {
                    mismatchPosition = i;
                    mismatchFound = true;
                } else {
                    //too many mismatches
                    mismatchFound = false;
                    break;
                }
            }

            if (mismatchFound) {
                return mismatchPosition;
            }
        }
        return -1;
    }
}
