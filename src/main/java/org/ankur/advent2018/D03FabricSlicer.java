package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.FabricSlice;

import java.util.ArrayList;
import java.util.List;

public class D03FabricSlicer {

    private static final int FABRIC_SIZE = 1000;

    private int[][] fabricMatrix = new int[FABRIC_SIZE][FABRIC_SIZE];

    private List<FabricSlice> fabricSlices;

    private int overlappedArea = 0;

    private void init(String input) {
        overlappedArea = 0;
        List<String> claimList = FileReader.readFile(input);

        //initialize fabricMatrix
        for (int i = 0; i < FABRIC_SIZE; i++) {
            for (int j = 0; j < FABRIC_SIZE; j++) {
                fabricMatrix[i][j] = 0;
            }
        }

        fabricSlices = new ArrayList<>();
        for (String claim: claimList) {
            FabricSlice slice = getFabricSlice(claim);
            fabricSlices.add(slice);
            markFabric(slice);
        }
    }

    public int overlappedArea(String input) {
        init(input);
        return overlappedArea;
    }

    public int noOverlappedClaim(String input) {
        init(input);
        return noOverlap();
    }

    //#414 @ 967,574: 12x13
    public FabricSlice getFabricSlice(String claim) {
        int at = claim.indexOf('@');
        int coma = claim.indexOf(',');
        int colon = claim.indexOf(':');
        int x = claim.indexOf('x');
        int claimer = Integer.parseInt(claim.substring(1, at - 1));
        int left = Integer.parseInt(claim.substring(at + 2, coma));
        int top = Integer.parseInt(claim.substring(coma + 1, colon));
        int wide = Integer.parseInt(claim.substring(colon + 2, x));
        int tall = Integer.parseInt(claim.substring(x + 1));
        return new FabricSlice(claimer, left, top, wide, tall);
    }

    private void markFabric(FabricSlice slice) {
        for (int i = slice.getLeft(); i < slice.getLeft() + slice.getWidth(); i++) {
            for (int j = slice.getTop(); j < slice.getTop() + slice.getHeight(); j++) {
                if (fabricMatrix[i][j] > 0) {
                    fabricMatrix[i][j] = -1;
                    overlappedArea++;
                } else if (fabricMatrix[i][j] != -1) {
                    fabricMatrix[i][j] = slice.getClaimer();
                }
            }
        }
    }

    private int noOverlap() {
        final int TOTAL_CLAIMANTS = fabricSlices.size();
        int[] claimants = new int[TOTAL_CLAIMANTS + 1];
        for (int j = 0; j < TOTAL_CLAIMANTS; j++) {
            claimants[j] = 0;
        }

        for (int i = 0; i < FABRIC_SIZE; i++) {
            for (int j = 0; j < FABRIC_SIZE; j++) {
                if (fabricMatrix[i][j] > 0) {
                    claimants[fabricMatrix[i][j]]++;
                }
            }
        }

        for (int i = 1; i <= TOTAL_CLAIMANTS; i++) {
            FabricSlice slice = fabricSlices.get(i - 1);
            if (slice.getArea() == claimants[i]) {
                return i;
            }
        }
        return 0;
    }
}
