package nl.ing.advent2018;

import org.magicwerk.brownies.collections.primitive.IntGapList;

public class D09MarbleMania {

    private long[] scores;

    public long highScore(int players, int maxMarbles) {
        scores = new long[players];
        for (int i = 0; i < players; i++) {
            scores[i] = 0;
        }

        IntGapList marbleArrangement = new IntGapList();
        marbleArrangement.add(0);

        marbleArrangement.add(1);

        int currentMarble = 1;
        int currentPlayer = 1;

        for (int i = 2; i <= maxMarbles; i++) {
            currentMarble = place(currentMarble, i, ++currentPlayer % players, marbleArrangement);
        }

        long highScore = 0;
        for (int i = 0; i < players; i++) {
            if (scores[i] > highScore) {
                highScore = scores[i];
            }
        }
        return highScore;
    }

    private int place(int current, int nextMarble, int player, IntGapList marbleList) {

        int nextPos;
        if (nextMarble % 23 == 0) {
            scores[player] += nextMarble;
            nextPos = current - 7;
            if (nextPos < 0) {
                nextPos += marbleList.size();
            }
            Integer remove = marbleList.remove(nextPos);
            scores[player] += remove;
        }
        else {
            nextPos = current + 2;
            if (nextPos > marbleList.size()) {
                nextPos -= marbleList.size();
            }
            marbleList.add(nextPos, nextMarble);
        }

        return nextPos;
    }
}
