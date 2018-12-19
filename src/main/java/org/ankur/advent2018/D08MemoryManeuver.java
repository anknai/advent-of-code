package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class D08MemoryManeuver {

    public int getMetadataCount(String fileName) {
        String content = FileReader.readFileAsString(fileName);

        int total = 0;
        //2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2
        assert content != null;
        int[] iterator = Arrays.stream(content.split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> list = new ArrayList<>();
        for (int anIterator : iterator) {
            list.add(anIterator);
        }

        Iterator<Integer> iterator1 = list.iterator();
        while (iterator1.hasNext()) {
            total += metadata(iterator1);
        }

        return total;
    }

    private int metadata(Iterator<Integer> iterator) {
        int metadata = 0;
        int children = iterator.next();
        int meta = iterator.next();

        for (int i = 0; i < children; i++) {
            metadata += metadata(iterator);
        }

        for (int i = 0; i < meta; i++) {
            metadata += iterator.next();
        }

        return metadata;
    }

    public int rootValue(String fileName) {
        String content = FileReader.readFileAsString(fileName);

        assert content != null;
        int[] iterator = Arrays.stream(content.split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> list = new ArrayList<>();
        for (int anIterator : iterator) {
            list.add(anIterator);
        }

        Iterator<Integer> iterator1 = list.iterator();

        int sum = 0;
        while (iterator1.hasNext()) {
            int value = value(iterator1);
            sum += value;
        }
        return sum;
    }

    private int value(Iterator<Integer> input) {
        int children = input.next();
        int metadataEntries = input.next();
        int[] childValues = new int[children];
        for (int i = 0; i < children; i ++) {
            childValues[i] = value(input);
        }

        List<Integer> valuesToUse = new ArrayList<>();
        for (int i = 0; i < metadataEntries; i ++) {
            valuesToUse.add(input.next());
        }

        if(children > 0) {
            int sum = 0;
            for (int value : valuesToUse) {
                if (value <= children) {
                    sum += childValues[value - 1];
                }
            }
            return sum;
        } else {
            return valuesToUse.stream().mapToInt(Integer::intValue).sum();
        }
    }
}
