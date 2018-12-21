package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class D21ChronalConversion {

    public int findHalt(String fileName, boolean part1) {
        Set<Integer> seen = new LinkedHashSet<>();
        int c = 0;
        int lastUniqueC = -1;
        List<String> lines = FileReader.readFile(fileName);
        int magicNumber = Integer.parseInt(lines.get(8).split(" ")[1]);
        int and0 = Integer.parseInt(lines.get(7).split(" ")[2]);
        int and1 = Integer.parseInt(lines.get(9).split(" ")[2]);
        int and2 = Integer.parseInt(lines.get(11).split(" ")[2]);
        int and3 = Integer.parseInt(lines.get(12).split(" ")[2]);
        while(true) {
            int a = c | and0;
            c = magicNumber;
            while(true) {
                c = (((c + (a & and1)) & and2) * and3) & and2;
                if (and1 + 1 > a) {
                    if (part1) {
                        System.out.println("Found " + c);
                        return c;
                    } else {
                        if (!seen.contains(c)){
                            seen.add(c);
                            lastUniqueC = c;
                            break;
                        } else {
                            System.out.println("Found " + lastUniqueC);
                            return lastUniqueC;
                        }
                    }
                } else {
                    a /= (and1 + 1);
                }
            }
        }
    }
}
