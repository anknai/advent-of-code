package org.ankur.advent2017;

import static org.ankur.advent.util.FileReader.readFileAsString;

public class D01InverseCaptcha {

    public int captcha(String input) {
        String digits = readFileAsString(input);
        assert digits != null;
        char[] chars = digits.toCharArray();
        int captcha = 0;
        for (int i = 0; i < digits.length(); i ++) {
            int j = i + 1;
            if (i == digits.length() - 1) {
                j = 0;
            }
            if (chars[i] == chars[j]) {
                captcha += Character.getNumericValue(chars[i]);
            }
        }
        return captcha;
    }

    public int halfwayCaptcha(String input) {
        String digits = readFileAsString(input);
        assert digits != null;
        char[] chars = digits.toCharArray();
        int captcha = 0;
        int length = digits.length();
        for (int i = 0; i < length; i ++) {
            int j = i + length / 2;
            if (j > length - 1) {
                j -= length;
            }
            if (chars[i] == chars[j]) {
                captcha += Character.getNumericValue(chars[i]);
            }
        }
        return captcha;
    }
}
