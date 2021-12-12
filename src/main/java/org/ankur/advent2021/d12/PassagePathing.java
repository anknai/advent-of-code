package org.ankur.advent2021.d12;

import org.ankur.advent.util.FileReader;

import java.util.List;

public class PassagePathing {

    public int paths(String input) {
        List<String> strings = FileReader.readFile(input);
        CaveSystem system = new CaveSystem();
        strings.forEach(system::addCave);
        system.display();
        return system.findPath();
    }

    public int paths2(String input) {
        List<String> strings = FileReader.readFile(input);
        CaveSystem system = new CaveSystem();
        strings.forEach(system::addCave);
        system.display();
        return system.findPath2();
    }
}
