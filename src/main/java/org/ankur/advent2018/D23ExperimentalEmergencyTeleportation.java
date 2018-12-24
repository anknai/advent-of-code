package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Bot;
import org.ankur.advent2018.domain.Group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class D23ExperimentalEmergencyTeleportation {

    private int minX, maxX, minY, maxY, minZ, maxZ;

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

    public int closest(String fileName) {
        minX = Integer.MAX_VALUE;
        minY = minX;
        minZ = minX;
        maxX = Integer.MIN_VALUE;
        maxY = maxX;
        maxZ = maxX;

        List<String> lines = FileReader.readFile(fileName);
        List<Bot> bots = new ArrayList<>();
        for (String line : lines) {
            bots.add(parse(line));
        }

        System.out.println("(" + minX + "," + minY + "," + minZ + "), (" + maxX + "," + maxY + "," + maxZ + ")");

        Bot min = new Bot(minX, minY, minZ);
        Bot max = new Bot(maxX, maxY, maxZ);
        howMany(bots, min, max);

        for (int i = 0; i < 1; i ++) {
            int midX = minX + (minX + maxX) / 2;
            int midY = minY + (minY + maxY) / 2;
            int midZ = minZ + (minZ + maxZ) / 2;
            howMany(bots, new Bot(minX, minY, minZ), new Bot(midX, midY, midZ));
            howMany(bots, new Bot(minX, minY, midZ), new Bot(midX, midY, maxZ));
            howMany(bots, new Bot(minX, midY, minZ), new Bot(midX, maxY, midZ));
            howMany(bots, new Bot(minX, midY, midZ), new Bot(midX, maxY, maxZ));
            howMany(bots, new Bot(midX, minY, minZ), new Bot(maxX, midY, midZ));
            howMany(bots, new Bot(midX, minY, midZ), new Bot(maxX, midY, maxZ));
            howMany(bots, new Bot(midX, midY, minZ), new Bot(maxX, maxY, midZ));
            howMany(bots, new Bot(midX, midY, midZ), new Bot(maxX, maxY, maxZ));

        }
        return -1;
    }

    private int howMany(List<Bot> original, Bot min, Bot max) {
        List<Bot> bots = original.stream().map(Bot::new).collect(Collectors.toList());
        int count = 0;
        Iterator<Bot> iterator = bots.iterator();
        while (iterator.hasNext()) {
            Bot bot = iterator.next();
            if (bot.centerInGrid(min, max)) {
                iterator.remove();
                count++;
            }
        }
        System.out.println("Center in " + min + " " + max + "=" + count);
        for (int i = min.getX(); i < max.getX(); i ++) {
            Bot bot = new Bot(i, min.getY(), min.getZ());
            iterator = bots.iterator();
            while (iterator.hasNext()) {
                Bot nano = iterator.next();
                if (nano.inRange(bot)) {
                    iterator.remove();
                    count++;
                }
            }
        }
        System.out.println("In range " + min + " " + max + "=" + count);
        return count;
    }

    public Bot parse(String line) {
        //pos=<1,1,2>, r=1
        line = line.replace("pos=<", "");
        line = line.replace(">, r=", ",");
        String[] pos = line.split(",");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);
        int z = Integer.parseInt(pos[2]);
        if (x < minX) {
            minX = x;
        }
        if (x > maxX) {
            maxX = x;
        }
        if (y < minY) {
            minY = y;
        }
        if (y > maxY) {
            maxY = y;
        }
        if (z < minZ) {
            minZ = z;
        }
        if (z > maxZ) {
            maxZ = z;
        }
        return Bot.builder()
                .x(x)
                .y(y)
                .z(z)
                .radii(Integer.parseInt(pos[3]))
                .build();
    }
}
