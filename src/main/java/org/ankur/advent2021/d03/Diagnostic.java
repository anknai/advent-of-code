package org.ankur.advent2021.d03;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Diagnostic {
    private int position;
    private int zeros;
    private int ones;

    public Diagnostic(int position) {
        this.position = position;
    }

    public void zeroPlusPlus() {
        zeros++;
    }

    public void onePlusPlus() {
        ones++;
    }
}
