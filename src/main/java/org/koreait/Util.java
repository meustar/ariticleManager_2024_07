package org.koreait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Util {

    private static Scanner sc;

    public static String getNow() {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatedNow;
    }

    public static void init() {

        sc = new Scanner(System.in);

    }

    public static void close() {
        sc.close();
    }

    public static Scanner getScanner() {
        return sc;
    }
}