package org.ankur.advent2019.d18;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.Dijkstra;
import org.ankur.advent2018.domain.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Day18 {

    private char[][] vaultMap;

    private List<Vault> vaults;

    private Vault me;

    public int part1(String fileName) {
        List<String> strings = FileReader.readFile(fileName);
        init(strings);
        print();
        return calculate();
    }

    public int part2(String fileName) {
        FileReader.readFile(fileName);
        return 0;
    }

    private int calculate() {
        int total = 0;
        while (true) {
            shortest();
            int shortest = Integer.MAX_VALUE;
            Vault closest = null;
            for (Vault vault : vaults) {
                if (vault.getAreaType() == Point.AreaType.KEY) {
                    int distance = vault.getDistance();
                    if (distance < shortest) {
                        shortest = distance;
                        closest = vault;
                    }
                    System.out.println("Vault " + vault + " is " + vault.getDistance());
                }
            }
            if (null != closest) {
                total += shortest;
                vaultMap[me.getX()][me.getY()] = '.';
                vaultMap[closest.getX()][closest.getY()] = '@';
                for (Vault vault : vaults) {
                    if (vault.getAreaType() == Point.AreaType.DOOR && vault.getAssortment() == closest.getAssortment() - 32) {
                        vault.open();
                        vaultMap[vault.getX()][vault.getY()] = '.';
                    }
                }
                me.swap(closest);
                closest.open();
                print();
            } else {
                System.out.println("No more path available");
                break;
            }
        }
        return total;
    }

    private void print() {
        System.out.println("=====================================================");
        for (int y = 0; y < vaultMap[0].length; y++) {
            for (char[] chars : vaultMap) {
                System.out.print(chars[y]);
            }
            System.out.println();
        }
    }

    private void init(List<String> strings) {
        vaultMap = new char[strings.get(0).length()][strings.size()];
        vaults = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);
            char[] charArray = string.toCharArray();
            for (int i1 = 0; i1 < charArray.length; i1++) {
                char c = charArray[i1];
                vaultMap[i1][i] = c;
                Vault vault = null;
                if (c >= 'a' && c <= 'z') {
                    vault = new Vault(i1, i, Point.AreaType.KEY, c);
                } else if (c >= 'A' && c <= 'Z') {
                    vault = new Vault(i1, i, Point.AreaType.DOOR, c);
                } else if (c == '@') {
                    vault = new Vault(i1, i, Point.AreaType.ME, c);
                    me = vault;
                } else if (c == '.') {
                    vault = new Vault(i1, i, Point.AreaType.ROOM, c);
                }
                if (null != vault) {
                    vaults.add(vault);
                }
            }
        }
    }

    private void shortest() {
        for (Vault vault : vaults) {
            vault.setDistance(Integer.MAX_VALUE);
            vault.setAdjacentNodes(new TreeMap<>());
            vault.setShortestPath(new LinkedList<>());
        }
        for (Vault vault : vaults) {
            for (Vault other : vaults) {
                if (other != vault) {
                    if (vault.isAdjacent(other)) {
                        vault.addDestination(other, 1);
                    }
                }
            }
        }
        Dijkstra.calculateShortestPathFromSource(me);
    }
}
