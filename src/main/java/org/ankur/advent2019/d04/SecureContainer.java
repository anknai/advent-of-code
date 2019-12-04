package org.ankur.advent2019.d04;

import org.apache.commons.lang3.StringUtils;

public class SecureContainer {

    public int count1(int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (isValid(i)) {
                System.out.println(i);
                count++;
            }
        }
        return count;
    }

    public int count2(int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (isValidPart2(i)) {
                System.out.println(i);
                count++;
            }
        }
        return count;
    }

    boolean isValid(int a) {
        String s = String.valueOf(a);
        assert s.length() == 6;
        char _1 = s.charAt(0);
        char _2 = s.charAt(1);
        char _3 = s.charAt(2);
        char _4 = s.charAt(3);
        char _5 = s.charAt(4);
        char _6 = s.charAt(5);
        boolean order = _1 <= _2 && _2 <= _3 && _3 <= _4 && _4 <= _5 && _5 <= _6;
        if (order) {
            order = _1 == _2 || _2 == _3 || _3 == _4 || _4 == _5 || _5 == _6;
        }
        return order;
    }

    boolean isValidPart2(int a) {
        if (!isValid(a)) {
            return false;
        }
        String s = String.valueOf(a);
        for (int i = 48; i <= 57; i++) {
            int count = StringUtils.countMatches(s, (char) i);
            if (count == 2) {
                return true;
            }
        }
        return false;
    }
}
