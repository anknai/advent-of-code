package org.ankur.advent2019.d18;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.Dijkstra;
import org.ankur.advent2018.domain.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.ankur.advent2018.domain.Point.AreaType.KEY;
import static org.ankur.advent2018.domain.Point.AreaType.ME;
import static org.ankur.advent2018.domain.Point.AreaType.ROOM;

public class Day18 {

    private char[][] vaultMap;

    private List<Vault> vaults;

    private Vault me;

    private List<List<Path>> allPaths;

    private Map<Character, List<FullPath>> shortest;

    int i = 0;

    public int part(String fileName) {
        shortest = new HashMap<>();
        List<String> strings = FileReader.readFile(fileName);
        init(strings);
        print();
        shortest();
        allPaths = new ArrayList<>();
        List<Path> path = new ArrayList<>();
        allPaths.add(path);
        traverse(me.getAssortment(), path);
        int minimum = Integer.MAX_VALUE;
        List<Path> shortestPath = null;
        i = 0;
        for (List<Path> allPath : allPaths) {
            Set<Character> characters = new HashSet<>(shortest.keySet());
            characters.remove('@');
            int distance = 0;
            for (Path path1 : allPath) {
                //System.out.print(path1.getKey() + "[" + path1.getDistance() + "], ");
                characters.remove(path1.getKey());
                distance += path1.getDistance();
            }
            if (characters.size() > 0) {
                System.out.println("Incomplete" + Arrays.toString(characters.toArray()));
                continue;
            }
            //System.out.println(distance);
            if (distance < minimum) {
                minimum = distance;
                shortestPath = allPath;
            }
        }
        if (null != shortestPath) {
            for (Path path1 : shortestPath) {
                System.out.print(path1.getKey() + ",");
            }
        }
        return minimum;
    }

    private void traverse(Character from, List<Path> paths) {
        i++;
        if (i % 10000 == 0) {
            System.err.print(".");
        }
        if (i % 1000000 == 0) {
            System.err.println(".");
        }
        List<Character> traversed = getTraversed(paths);
        if (similar(paths, traversed)) {
            return;
        }
        List<Path> local = new ArrayList<>();
        //System.out.println("Traversing from " + from + " " + Arrays.toString(traversed.toArray()));
        List<FullPath> fullPaths = shortest.get(from);
        for (FullPath fullPath : fullPaths) {
            if (traversed.contains(fullPath.getTo())) {
                continue;
            }
            List<Character> path = new ArrayList<>(fullPath.getPath());
            path.removeAll(traversed);
            if (path.size() == 0) {
                //System.out.println(fullPath.getTo() + " is " + fullPath.getDistance() + " from " + from);
                local.add(new Path(fullPath.getTo(), fullPath.getDistance()));
            }
        }

        if (local.size() == 0) {
            //System.out.println("Nothing");
            return;
        }

        Path current = local.get(0);
        local.remove(0);

        for (Path others : local) {
            //System.out.println("Branching off to " + others + " " + Arrays.toString(traversed.toArray()));
            List<Path> localPath = new ArrayList<>(paths);
            allPaths.add(localPath);
            localPath.add(new Path(others.getKey(), others.getDistance()));
            traverse(others.getKey(), localPath);
        }

        paths.add(new Path(current.getKey(), current.getDistance()));
        traverse(current.getKey(), paths);
    }

    private List<Character> getTraversed(List<Path> paths) {
        List<Character> traversed = new ArrayList<>();
        for (Path path : paths) {
            traversed.add((char)(path.getKey() - 32));
            traversed.add(path.getKey());
        }
        return traversed;
    }

    private boolean similar(List<Path> path, List<Character> traversed) {
        int total = 0;
        for (Path path1 : path) {
            total += path1.getDistance();
        }
        if (total > 136) {
            //System.out.println("too large");
            return true;
        }
        for (List<Path> allPath : allPaths) {
            if (allPath == path) {
                continue;
            }
            if (allPath.size() != path.size()) {
                continue;
            }
            if (path.get(path.size() - 1).getKey() != traversed.get(traversed.size() - 1)) {
                continue;
            }
            int totalOther = 0;
            List<Character> p = new ArrayList<>(traversed);
            for (Path path1 : allPath) {
                p.remove(path1.getKey());
                p.remove(new Character((char)(path1.getKey() - 32)));
                totalOther += path1.getDistance();
            }
            if (p.size() == 0) {
                if (total >= totalOther) {
                    //System.out.println("Shorter short path exists " + total + " " + totalOther + " " + Arrays.toString(traversed.toArray()));
                    return true;
                }
            }
        }
        return false;
    }

    /*public int part1(String fileName) {
        List<String> strings = FileReader.readFile(fileName);
        init(strings);
        print();
        allPaths = new ArrayList<>();
        allTraversed = new ArrayList<>();
        List<Path> path = new ArrayList<>();
        List<Character> traversed = new ArrayList<>();
        allPaths.add(path);
        allTraversed.add(traversed);
        calculate(me, traversed, path);
        int minimum = Integer.MAX_VALUE;
        List<Path> shortest = null;
        for (List<Path> allPath : allPaths) {
            int distance = 0;
            for (Path path1 : allPath) {
                System.out.print(path1.getKey().getAssortment() + "[" + path1.getDistance() + "], ");
                distance += path1.getDistance();
            }
            System.out.println(distance);
            if (distance < minimum) {
                minimum = distance;
                shortest = allPath;
            }
        }
        if (null != shortest) {
            for (Path path1 : shortest) {
                System.out.print(path1.getKey().getAssortment() + ",");
            }
        }
        return minimum;
    }*/

    public int part2(String fileName) {
        FileReader.readFile(fileName);
        return 0;
    }

    /*private void calculate(Vault from, List<Character> traversed, List<Path> paths) {
        //System.out.println(from + " " + Arrays.toString(traversed.toArray()));
        shortest(from, traversed);
        List<Path> local = new ArrayList<>();
        for (Vault key : vaults) {
            if (key.getAreaType() == KEY && !traversed.contains(key.getAssortment())) {
                int distance = key.getDistance();
                if (distance < Integer.MAX_VALUE) {
                    //System.out.println(key + " is " + key.getDistance() + " from " + from);
                    local.add(new Path(key, key.getDistance()));
                }
            }
        }
        if (local.size() == 0) {
            return;
        }

        Path current = local.get(0);
        local.remove(0);

        for (Path others : local) {
            //System.out.println("Branching off to " + others + " " + Arrays.toString(traversed.toArray()));
            List<Character> localTraversed = new ArrayList<>(traversed);
            allTraversed.add(localTraversed);
            List<Path> localPath = new ArrayList<>(paths);
            allPaths.add(localPath);
            localPath.add(new Path(others.getKey(), others.getDistance()));
            traverse(others.getKey(), localTraversed);
            calculate(others.getKey(), localTraversed, localPath);
        }

        Vault key = current.getKey();
        traverse(key, traversed);
        paths.add(new Path(key, current.getDistance()));
        calculate(key, traversed, paths);
    }

    private void traverse(Vault key, List<Character> traversed) {
        for (Vault vault : vaults) {
            if (vault.getAreaType() == Point.AreaType.DOOR && vault.getAssortment() == key.getAssortment() - 32) {
                traversed.add(vault.getAssortment());
                //vaultMap[vault.getX()][vault.getY()] = '.';
                continue;
            }
            if (vault.getAreaType() == Point.AreaType.KEY && vault.getAssortment() == key.getAssortment()) {
                traversed.add(vault.getAssortment());
                //vaultMap[vault.getX()][vault.getY()] = '.';
            }
        }
    }*/

    private void print() {
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
                    vault = new Vault(i1, i, KEY, c);
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

    private void shortest(Vault from, List<Character> traversed) {
        for (Vault vault : vaults) {
            vault.setDistance(Integer.MAX_VALUE);
            vault.setAdjacentNodes(new TreeMap<>());
            vault.setShortestPath(new LinkedList<>());
        }
        for (Vault vault : vaults) {
            for (Vault other : vaults) {
                if (other != vault) {
                    if (vault.isAdjacent(other, traversed)) {
                        //System.out.println(vault + " is adjacent to " + other);
                        vault.addDestination(other, 1);
                    }
                }
            }
        }
        Dijkstra.calculateShortestPathFromSource(from);
    }

    private void shortest() {
        for (Vault vault : vaults) {
            if (vault.getAreaType() == KEY || vault.getAreaType() == ME) {
                List<FullPath> fullPaths = new ArrayList<>();
                //System.out.println("================" + vault.getAssortment() + "===================");
                shortest.put(vault.getAssortment(), fullPaths);
                shortest(vault);
                for (Vault other : vaults) {
                    if (other == vault) {
                        continue;
                    }
                    if (other.getAreaType() == KEY) {
                        FullPath fullPath = new FullPath(other.getAssortment(), other.getDistance());
                        fullPaths.add(fullPath);
                        LinkedList<Point> path = other.getShortestPath();
                        for (Point point : path) {
                            if (point.getAreaType() != ROOM) {
                                Vault v = (Vault) point;
                                if (v.getAssortment() != vault.getAssortment() && v.getAssortment() != '@') {
                                    fullPath.add(((Vault)point).getAssortment());
                                }
                            }
                        }
                        //System.out.println(fullPath);
                    }
                }
            }
        }
    }

    private void shortest(Vault me) {
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
