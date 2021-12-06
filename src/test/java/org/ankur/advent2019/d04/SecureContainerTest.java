package org.ankur.advent2019.d04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecureContainerTest extends SecureContainer {

    @Test
    void count() {
        assertEquals(966, super.count1(264793, 803935));
    }

    @Test
    void isValid() {
        assertTrue(super.isValid(111111));
        assertFalse(super.isValid(223450));
        assertFalse(super.isValid(123789));

    }

    @Test
    void count2() {
        assertEquals(100, super.count2(264793, 803935));
    }

    @Test
    void isValid2() {
        assertTrue(super.isValidPart2(112233));
        assertFalse(super.isValidPart2(123444));
        assertTrue(super.isValidPart2(111122));

    }
}