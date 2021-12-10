package org.ankur.advent2021.d10;

import lombok.Data;

import java.util.Stack;

@Data
public class Syntax {
    private Stack<Character> open = new Stack<>();

    public int getCorruptedScore(char current) {
        switch (current) {
            case '{':
            case '(':
            case '[':
            case '<':
                open.add(current);
                break;
            default:
                char pop = open.pop();
                if ((current == '}' && pop != '{') || (current == ']' && pop != '[') || (current == ')' && pop != '(') || (current == '>' && pop != '<')) {
                    System.out.println("Popped " + pop + " but is " + current);
                    return score(current);
                }
                break;
        }
        return 0;
    }

    private int score(char c) {
        switch (c) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return 0;
        }
    }

    public boolean add(char current) {
        switch (current) {
            case '{':
            case '(':
            case '[':
            case '<':
                open.add(current);
                break;
            default:
                char pop = open.pop();
                if ((current == '}' && pop != '{') || (current == ']' && pop != '[') || (current == ')' && pop != '(') || (current == '>' && pop != '<')) {
                    return true;
                }
                break;
        }
        return false;
    }

    public long fix() {
        long total = 0;
        while (!open.empty()) {
            char pop = open.pop();
            int score;
            switch (pop) {
                case '(':
                    score = 1;
                    break;
                case '[':
                    score = 2;
                    break;
                case '{':
                    score = 3;
                    break;
                case '<':
                    score = 4;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + pop);
            }
            total = total(total, score);
        }
        System.out.println("total is " + total);
        return total;
    }

    private long total(long total, int add) {
        return total * 5 + add;
    }
}
