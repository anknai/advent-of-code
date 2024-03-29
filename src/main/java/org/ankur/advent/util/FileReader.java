package org.ankur.advent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class FileReader {
///Users/anknai/IdeaProjects/advent-of-code/src/test/resources/2019_08.txt
    private static final String PATH = "/Users/xd28up/projects/advent-of-code/src/test/resources/";

    private FileReader() {

    }

    public static List<String> readFile(String fileName) {
        File file = new File(PATH + fileName);
        List<String> frequencyList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                frequencyList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencyList;
    }

    public static String readFileAsString(String fileName) {
        File file = new File(PATH + fileName);
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            if ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static List<TreeSet<Integer>> readFileAsListOfIntegerSets(String fileName) {
        File file = new File(PATH + fileName);
        List<TreeSet<Integer>> lists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TreeSet<Integer> set = new TreeSet<>();
                String[] split = line.split("\t");
                for (String s: split) {
                    set.add(Integer.parseInt(s));
                }
                lists.add(set);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }
}
