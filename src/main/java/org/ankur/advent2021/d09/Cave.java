package org.ankur.advent2021.d09;

import lombok.Data;

@Data
public class Cave {
    private int x;

    private int y;

    private boolean terminal;

    private int value;

    private boolean tagged;

    public Cave(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.value = height;
        char print = ' ';
        if (height == 9) {
            terminal = true;
            print = '█';
        }
        System.out.print(print);
    }
}
