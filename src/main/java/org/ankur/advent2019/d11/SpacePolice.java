package org.ankur.advent2019.d11;

import org.ankur.advent.util.FileReader;
import org.ankur.advent.util.IntCodeComputer;

import java.util.HashMap;
import java.util.Map;

public class SpacePolice {

    private Map<Integer, Panel> indexMap;
    private int max = 160;

    public long count(String inputFile) {
        indexMap = new HashMap<>();
        String s = FileReader.readFileAsString(inputFile);
        return run(s, 0);
    }

    public void registration(String inputFile) {
        indexMap = new HashMap<>();
        String s = FileReader.readFileAsString(inputFile);
        run(s, 1);
        char[][] map = new char[max][max];
        for (int y = 0; y < max; y++) {
            for (int x = 0; x < max; x++) {
                map[x][y] = ' ';
            }
        }
        for (Map.Entry<Integer, Panel> integerPanelEntry : indexMap.entrySet()) {
            Integer integer = integerPanelEntry.getKey();
            int x = integer / 1000;
            int y = integer % 1000;
            //System.out.println(integer);
            if (integerPanelEntry.getValue().getColor() == 1) {
                map[x][y] = '\u2588';
            }
        }

        for (int y = 0; y < max; y++) {
            for (int x = 0; x < max; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }

    long run(String inputStr, long input) {
        IntCodeComputer computer = new IntCodeComputer(inputStr);

        int x = max/2;
        int y = max/2;
        Panel panel = new Panel(x, y, false, (int)input);
        //panels.add(panel);
        indexMap.put(x * 1000 + y, panel);
        //direction UP, LEFT, RIGHT, DOWN
        char direction = '^';
        while(computer.running()) {
            computer.addInput((long)panel.getColor());
            computer.run();
            long output = computer.output();
            //System.out.println("Working on panel " + panel + " direction " + direction + " index " + index + " " + copy[index]);
            panel.setColor((int)output);
            output = computer.output();
            //direction
            if (output == 0) {
                switch (direction) {
                    case '^':
                        direction = '<';
                        x--;
                        break;
                    case '<':
                        direction = 'v';
                        y++;
                        break;
                    case 'v':
                        direction = '>';
                        x++;
                        break;
                    case '>':
                        direction = '^';
                        y--;
                        break;
                }
            } else if (output == 1) {
                switch (direction) {
                    case '^':
                        direction = '>';
                        x++;
                        break;
                    case '>':
                        direction = 'v';
                        y++;
                        break;
                    case 'v':
                        direction = '<';
                        x--;
                        break;
                    case '<':
                        direction = '^';
                        y--;
                        break;
                }
            }
            int index = x * 1000 + y;
            panel = indexMap.get(index);
            if (panel == null) {
                panel = new Panel(x, y, false, 0);
                indexMap.put(x * 1000 + y, panel);
            } else {
                panel.setPainted(true);
            }
        }
        return indexMap.size();
    }
}
