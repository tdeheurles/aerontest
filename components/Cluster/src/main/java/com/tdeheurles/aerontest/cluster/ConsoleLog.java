package com.tdeheurles.aerontest.cluster;

public class ConsoleLog {

    private static void log(String message, String themeColor) {
        System.out.println(themeColor + message + ConsoleColors.RESET);
    }

    public static void main_3(String message){
        log(message, ConsoleTheme.MAIN3);
    }

    public static void main_2(String message){
        log(message, ConsoleTheme.MAIN2);
    }

    public static void main_1(String message){
        log(message, ConsoleTheme.MAIN1);
    }

    public static void main_0(String message){
        log(message, ConsoleTheme.MAIN0);
    }

    public static void success_0(String message){
        log(message, ConsoleTheme.SUCCESS0);
    }

    public static void error_0(String message){
        System.out.println(ConsoleTheme.ERROR0 + message + ConsoleColors.RESET);
    }

    public static void warning_0(String message){
        log(message, ConsoleTheme.WARNING0);
    }
}
