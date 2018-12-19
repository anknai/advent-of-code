package org.ankur.advent2017.test;

import org.ankur.advent2017.D01InverseCaptcha;
import org.junit.Test;

import static org.junit.Assert.*;

public class D01InverseCaptchaTest {

    private D01InverseCaptcha inverseCaptcha = new D01InverseCaptcha();

    private final static String INPUT = "2017_01_captcha.txt";

    @Test
    public void captcha() {
        int captcha = inverseCaptcha.captcha(INPUT);
        assertEquals("Captcha is ", 1136, captcha);
    }

    @Test
    public void halfwayCaptcha() {
        int captcha = inverseCaptcha.halfwayCaptcha(INPUT);
        assertEquals("Half way captcha is ", 1092, captcha);
    }
}
