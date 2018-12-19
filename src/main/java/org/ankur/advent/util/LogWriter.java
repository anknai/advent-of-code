package org.ankur.advent.util;

public class LogWriter {

    private LogWriter() {

    }

    public static void logSameLine(String string) {
        System.out.print(string);
    }

    public static void log(String string) {
        System.out.println(string);
    }

    public static void newLine() {
        System.out.println();
    }
}
