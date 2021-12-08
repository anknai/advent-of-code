package org.ankur.advent2021.d08;

import java.util.ArrayList;
import java.util.List;

public class SevenSegmentDisplay {

    private final List<String> number;

    public SevenSegmentDisplay(String[] displays) {
        number = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            number.add("");
        }
        //2,3,5
        List<String> fives = new ArrayList<>();
        //0,6,9
        List<String> sixes = new ArrayList<>();
        for (String d : displays) {
            int length = d.length();
            switch (length) {
                case 2:
                    number.set(1, d);
                    break;
                case 3:
                    number.set(7, d);
                    break;
                case 4:
                    number.set(4, d);
                    break;
                case 7:
                    number.set(8, d);
                    break;
                case 5:
                    fives.add(d);
                    break;
                case 6:
                    sixes.add(d);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + length);
            }
        }
        //9 has two more characters then 4
        char[] four = number.get(4).toCharArray();
        for (String six : sixes) {
            String nine = six;
            for (char c : four) {
                if (six.indexOf(c) == -1) {
                    nine = null;
                    break;
                }
            }
            if (null != nine) {
                number.set(9, six);
            }
        }
        sixes.remove(number.get(9));

        //3 & 5 have one character less than 9, so gives us 2
        String nine = number.get(9);
        for (String five : fives) {
            char[] fivec = five.toCharArray();
            for (char c : fivec) {
                if (nine.indexOf(c) == -1) {
                    number.set(2, five);
                    break;
                }
            }
        }
        fives.remove(number.get(2));

        //6 has one character removed then 5
        for (String six : sixes) {
            for (String five : fives) {
                if (contains(five, six)) {
                    number.set(5, five);
                    number.set(6, six);
                    break;
                }
            }
        }
        sixes.remove(number.get(6));
        fives.remove(number.get(5));

        number.set(0, sixes.get(0));
        number.set(3, fives.get(0));
    }

    public String getDisplay(String string) {
        int length = string.length();
        switch (length) {
            case 2:
                return "1";
            case 3:
                return "7";
            case 4:
                return "4";
            case 7:
                return "8";
            case 5:
            case 6:
                return find(string);
            default:
                throw new IllegalStateException("Unexpected value: " + length);
        }
    }

    private String find(String string) {
        char[] chars = string.toCharArray();
        int length = string.length();
        for (int i = 0, numbersSize = number.size(); i < numbersSize; i++) {
            String str = number.get(i);
            if (length != str.length()) {
                continue;
            }
            boolean found = true;
            for (char c : chars) {
                if (str.indexOf(c) == -1) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return "" + i;
            }
        }
        return "";
    }

    private boolean contains(String shorter, String longer) {
        char[] shortC = shorter.toCharArray();
        for (char c : shortC) {
            if (longer.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
