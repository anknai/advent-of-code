package org.ankur.advent2021.d04;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class Board {
    private List<Set<Integer>> rows = new ArrayList<>();
    private List<Set<Integer>> columns = new ArrayList<>();

    public void addRow(Set<Integer> row) {
        rows.add(row);
    }

    public void addColumn(Set<Integer> column) {
        columns.add(column);
    }

    private Set<Integer> getColumn(int index) {
        return columns.get(index);
    }

    public void addToColumn(int index, int value) {
        getColumn(index).add(value);
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
        int totalColumn = 0;
        for (Set<Integer> column : columns) {
            totalColumn += column.stream()
                    .reduce(0, Integer::sum);
        }
        int totalRow = 0;
        for (Set<Integer> row : rows) {
            totalRow += row.stream()
                    .reduce(0, Integer::sum);
        }
        if (totalColumn < totalRow) {
            return totalColumn * number;
        }
        return totalRow * number;
    }
}
