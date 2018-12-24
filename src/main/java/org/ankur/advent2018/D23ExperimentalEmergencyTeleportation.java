package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Bot;

import java.util.ArrayList;
import java.util.List;

public class D23ExperimentalEmergencyTeleportation {

    public int max(String fileName) {
        List<String> lines = FileReader.readFile(fileName);
        List<Bot> bots = new ArrayList<>();
        for (String line : lines) {
            bots.add(parse(line));
        }

        for (int i = 0; i < bots.size(); i ++) {
            Bot me = bots.get(i);
            for (Bot nano : bots) {
                if (me.inRange(nano)) {
                    me.addNeighbour();
                }
            }
        }

        int max = 0;
        Bot closest = null;
        for (Bot bot : bots) {
            if (bot.getRadii() > max) {
                max = bot.getRadii();
                closest = bot;
            }
        }
        System.out.println("Closest is " + closest);

        return closest.getNear();
    }

    public Bot parse(String line) {
        //pos=<1,1,2>, r=1
        line = line.replace("pos=<", "");
        line = line.replace(">, r=", ",");
        String[] pos = line.split(",");
        return Bot.builder()
                .x(Integer.parseInt(pos[0]))
                .y(Integer.parseInt(pos[1]))
                .z(Integer.parseInt(pos[2]))
                .radii(Integer.parseInt(pos[3]))
                .build();
    }
}
