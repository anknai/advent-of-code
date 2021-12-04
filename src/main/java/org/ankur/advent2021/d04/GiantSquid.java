package org.ankur.advent2021.d04;

import org.ankur.advent.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * Very interesting choice of data structures
 */
public class GiantSquid {

    public int bingo(String input) {
        List<String> strings = FileReader.readFile(input);
        List<String> numbers = Arrays.stream(strings.get(0).split(",")).collect(Collectors.toList());
        List<Board> boards = loadBoards(strings);
        for (String string : numbers) {
            int number = parseInt(string);
            for (Board board : boards) {
                if (board.remove(number)) {
                    return board.score(number);
                }
            }
        }
        return 0;
    }

    public int bingoLoser(String input) {
        List<String> strings = FileReader.readFile(input);
        List<String> numbers = Arrays.stream(strings.get(0).split(",")).collect(Collectors.toList());
        List<Board> boards = loadBoards(strings);
        for (String string : numbers) {
            int number = parseInt(string);
            Board last = boards.get(0);
            boards.removeIf(board -> board.remove(number));
            if (boards.isEmpty()) {
                return last.score(number);
            }
        }
        return 0;
    }

    private List<Board> loadBoards(List<String> strings) {
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < strings.size(); i += 6) {
            Board board = new Board(strings, i);
            boards.add(board);
        }
        return boards;
    }
}
