package org.ankur.advent2017.test;

import org.ankur.advent2017.D01InverseCaptcha;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class D01InverseCaptchaTest {

    private D01InverseCaptcha inverseCaptcha = new D01InverseCaptcha();

    private final static String INPUT = "2017_01_captcha.txt";

    @Test
    void captcha() {
        int captcha = inverseCaptcha.captcha(INPUT);
        assertEquals(1136, captcha);
    }

    @Test
    void halfwayCaptcha() {
        int captcha = inverseCaptcha.halfwayCaptcha(INPUT);
        assertEquals(1092, captcha);
    }
}
