package org.ankur.advent2019.d13;

import org.ankur.advent.util.FileReader;
import org.ankur.advent.util.IntCodeComputer;

import java.util.HashSet;
import java.util.Set;

public class CarePackage {

    public long blockTiles(String inputFile) {
        String s = FileReader.readFileAsString(inputFile);
        return run(s);
    }

    public long score(String inputFile) {
        String s = FileReader.readFileAsString(inputFile);
        return run2(s);
    }

    long run2(String inputStr) {
        IntCodeComputer computer = new IntCodeComputer(inputStr);
        Set<Item> items = new HashSet<>();
        Item ball = null;
        Item paddle = null;
        long score = 0;
        computer.update(0, 2);
        while (computer.running()) {
            computer.run();
            while (computer.hasOutput()) {
                long x = computer.output();
                long y = computer.output();
                long z = computer.output();
                if (z == 4) {
                    //System.out.println("BBBBBBBBB Ball at " + x + " " + y);
                    ball = new Item(x, y, 'b');
                    if (items.contains(ball)) {
                        System.out.println("Removing block from " + x + " " + y);
                        items.remove(ball);
                    }
                } else if (z == 3) {
                    //System.out.println("PPPPPPPPP Paddel at " + x + " " + y);
                    paddle = new Item(x, y, 'p');
                } else if (z == 2) {
                    //System.out.println("Block at " + x + " " + y);
                    Item block = new Item(x, y, '#');
                    items.add(block);
                }

                if (x == -1 && y == 0) {
                    System.out.println("New Score " + score);
                    score = z;
                }
            }
            if (null != paddle && null != ball) {
                computer.addInput((long)Long.compare(ball.getX(), paddle.getX()));
            }
        }
        return score;
    }

    long run(String inputStr) {
        IntCodeComputer computer = new IntCodeComputer(inputStr);
        int count = 0;
        computer.run();
        long output = 0;
        //inputs.add((long) 0);
        while (output != -1) {
            //x position
            computer.output();
            //y position
            computer.output();
            output = computer.output();
            if (output == 2) {
                count++;
            }
        }
        return count;
    }
}
