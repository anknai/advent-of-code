package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Constellation;
import org.ankur.advent2018.domain.Space;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class D25FourDimensionalAdventure {

    private List<Constellation> constellations;

    public int constellations(String fileName) {
        List<Space> spaces = new ArrayList<>();
        List<String> lines = FileReader.readFile(fileName);
        for (String line : lines) {
            spaces.add(parse(line));
        }

        constellations = new ArrayList<>();
        while (!spaces.isEmpty()) {
            scan(spaces);
        }
        return constellations.size();
    }

    private void scan(List<Space> spaces) {
        Constellation constellation = new Constellation();
        constellations.add(constellation);
        constellation.addSpace(spaces.get(0));
        spaces.remove(0);
        boolean still;
        do {
            still = false;
            Iterator<Space> iterator = spaces.iterator();
            while (iterator.hasNext()) {
                Space other = iterator.next();
                if (other.inSameConstellation(constellation)) {
                    still = true;
                    constellation.addSpace(other);
                    iterator.remove();
                }
            }
        } while (still);

    }

    private Space parse(String line) {
        String[] split = line.split(",");
        return Space.builder()
                .w(Integer.parseInt(split[0]))
                .x(Integer.parseInt(split[1]))
                .y(Integer.parseInt(split[2]))
                .z(Integer.parseInt(split[3]))
                .build();
    }
}
