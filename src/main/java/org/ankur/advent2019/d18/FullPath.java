package org.ankur.advent2019.d18;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FullPath {

    private Character to;

    private int distance;

    private List<Character> path;

    public FullPath(Character to, int distance) {
        this.to = to;
        this.distance = distance;
        path = new ArrayList<>();
    }

    public void add(Character character) {
        path.add(character);
    }
}
