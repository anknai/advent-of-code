package org.ankur.advent2021.d02;

import org.ankur.advent.util.FileReader;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Dive {

    public int dive(String input) {
        List<String> strings = FileReader.readFile(input);
        int depth = 0;
        int forward = 0;
        for (String command : strings) {
            if (command.startsWith("forward")) {
                String f = StringUtils.substringAfter(command, "forward ");
                forward += Integer.parseInt(f);
            } else if (command.startsWith("down")) {
                String d = StringUtils.substringAfter(command, "down ");
                depth += Integer.parseInt(d);
            } else {
                String u = StringUtils.substringAfter(command, "up ");
                depth -= Integer.parseInt(u);
            }
        }
        return depth * forward;
    }

    public int diveWithAim(String input) {
        List<String> strings = FileReader.readFile(input);
        int depth = 0;
        int forward = 0;
        int aim = 0;
        for (String command : strings) {
            if (command.startsWith("forward")) {
                int f = parseInt(StringUtils.substringAfter(command, "forward "));
                forward += f;
                depth += f * aim;
            } else if (command.startsWith("down")) {
                int d = parseInt(StringUtils.substringAfter(command, "down "));
                aim += d;
            } else {
                int u = parseInt(StringUtils.substringAfter(command, "up "));
                aim -= u;
            }
        }
        return depth * forward;
    }
}
