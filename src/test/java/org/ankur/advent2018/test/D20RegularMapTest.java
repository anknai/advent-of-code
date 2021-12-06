package org.ankur.advent2018.test;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.D20RegularMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D20RegularMapTest {

    private D20RegularMap regularMap = new D20RegularMap();

    @Test
    void farthestRoom() {
        int distance = regularMap.farthestRoom("^WNE$");
        assertEquals(3, distance);
        distance = regularMap.farthestRoom("^ENWWW(NEEE|SSE(EE|N))$");
        assertEquals(10, distance);
        distance = regularMap.farthestRoom("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$");
        assertEquals(18, distance);
        distance = regularMap.farthestRoom("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$");
        assertEquals(23, distance);
        distance = regularMap.farthestRoom("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$");
        assertEquals(31, distance);
        distance = regularMap.farthestRoom("^WWWW(SSSSS|)WWWWW$");
        assertEquals(9, distance);
        distance = regularMap.farthestRoom("^NNNNN(EEEEE|NNN)NNNNN$");
        assertEquals(10, distance);
        String regex = FileReader.readFileAsString("20_rooms.txt");
        distance = regularMap.farthestRoom(regex);
        assertEquals(3806, distance);
    }

    @Test
    void roomsWithDistance() {
        int rooms = regularMap.roomWithDistance("^WNE$", 2);
        assertEquals(2, rooms);
        rooms = regularMap.roomWithDistance("^ENWWW(NEEE|SSE(EE|N))$", 5);
        assertEquals(11, rooms);
        rooms = regularMap.roomWithDistance("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$", 10);
        assertEquals(13, rooms);
        rooms = regularMap.roomWithDistance("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$", 20);
        assertEquals(6, rooms);
        rooms = regularMap.roomWithDistance("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$", 25);
        assertEquals(20, rooms);
        String regex = FileReader.readFileAsString("20_rooms.txt");
        rooms = regularMap.roomWithDistance(regex, 1000);
        assertEquals(8354, rooms);
    }

    @Test
    void branch() {
        char[] array = "^ENWWW(NEEE|SSE(EE|N))$".toCharArray();
        int matchingParen = regularMap.findClosingParen(array, 6);
        assertEquals(21, matchingParen);

        String[] matchingBrace = regularMap.matchingBrace("NEEE|SSE(EE|N)$");
        assertEquals("EE|N", matchingBrace[0]);
        assertEquals("$", matchingBrace[1]);

        matchingBrace = regularMap.matchingBrace("^ENWWW(NEEE|SSE(EE|N))(EE)$");
        assertEquals("NEEE|SSE(EE|N)", matchingBrace[0]);
        assertEquals("(EE)$", matchingBrace[1]);

        String[] branches = regularMap.splitBranches("NEEE|SSE(EE|N)|(EE)");
        assertEquals("NEEE", branches[0]);
        assertEquals("SSE(EE|N)", branches[1]);
        assertEquals("(EE)", branches[2]);
    }
}