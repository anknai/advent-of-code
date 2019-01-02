package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class D23ExperimentalEmergencyTeleportation {

    private int minX, maxX, minY, maxY, minZ, maxZ;

    private PriorityQueue<Bot> queue;

    private List<Bot> bots;

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

        assert closest != null;
        return closest.getNear();
    }

    public int closest(String fileName) {
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        minZ = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
        maxZ = Integer.MIN_VALUE;

        List<String> lines = FileReader.readFile(fileName);

        bots = new ArrayList<>();
        for (String line : lines) {
            bots.add(parse(line));
        }

        System.out.println("(" + minX + "," + minY + "," + minZ + "), (" + maxX + "," + maxY + "," + maxZ + ")");

        int radii = Math.abs(maxX - minX) + Math.abs(maxY - minY) + Math.abs(maxZ - minZ);

        queue = new PriorityQueue<>();
        //calculate(11382526,29059462,39808805, 0);
        calculate(minX + (maxX - minX) / 2, minY + (maxY - minY) / 2, minZ + (maxZ - minZ) / 2, radii);
        Bot center;
        do {
            center = queue.poll();
            //System.out.println("Queue size " + queue.size() + " " + center);
            assert center != null;
            int x = center.getX();
            int y = center.getY();
            int z = center.getZ();
            radii = center.getRadii();
            calculate(x - radii / 2, y - radii / 2, z - radii / 2, radii / 2);
            calculate(x - radii / 2, y - radii / 2, z + radii / 2, radii / 2);
            calculate(x - radii / 2, y + radii / 2, z - radii / 2, radii / 2);
            calculate(x - radii / 2, y + radii / 2, z + radii / 2, radii / 2);
            calculate(x + radii / 2, y - radii / 2, z - radii / 2, radii / 2);
            calculate(x + radii / 2, y - radii / 2, z + radii / 2, radii / 2);
            calculate(x + radii / 2, y + radii / 2, z - radii / 2, radii / 2);
            calculate(x + radii / 2, y + radii / 2, z + radii / 2, radii / 2);
            calculate(x, y, z, radii / 2);
        } while (queue.size() > 0 && radii > 1);
        return center.getX() + center.getY() + center.getZ();
    }

    private void calculate(int x, int y, int z, int radii) {
        Bot center = Bot.builder()
                .x(x)
                .y(y)
                .z(z)
                .radii(radii)
                .build();
        int count = 0;
        for (Bot bot: bots) {
            if (bot.intersects(center)) {
                count++;
            }
        }
        center.setNear(count);
        if (count > 950) {
            for (Bot bot: queue) {
                if (bot.equals(center)) {
                    return;
                }
            }
            //System.out.println(center + " added to the queue");
            queue.add(center);
        }
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
