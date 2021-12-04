package org.ankur.advent2021.d04;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Data
public class Board {
    private List<Set<Integer>> rows = new ArrayList<>();
    private List<Set<Integer>> columns = new ArrayList<>();

    public Board(List<String> strings, int i) {
        for (int j = 0; j < 5; j++) {
            Set<Integer> column = new HashSet<>();
            columns.add(column);
        }
        for (int j = 0; j < 5; j++) {
            String[] numbers = strings.get(i+j).split(" ");
            Set<Integer> row = new HashSet<>();
            int index = 0;
            for (String s : numbers) {
                if (StringUtils.isNotBlank(s)) {
                    int value = parseInt(s);
                    row.add(value);
                    addToColumn(index ++, value);
                }
            }
            rows.add(row);
        }
    }

    private void addToColumn(int index, int value) {
        columns.get(index).add(value);
    }

    public boolean remove(int number) {
        for (Set<Integer> row : rows) {
            row.remove(number);
            if (row.isEmpty()) {
                return true;
            }
        }
        for (Set<Integer> column : columns) {
            column.remove(number);
            if (column.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int score(int number) {
        int totalColumn = columns.stream().mapToInt(column -> column.stream()
                .reduce(0, Integer::sum)).sum();
        int totalRow = rows.stream().mapToInt(row -> row.stream()
                .reduce(0, Integer::sum)).sum();
        return totalColumn < totalRow ? totalColumn * number : totalRow * number;
    }
}
