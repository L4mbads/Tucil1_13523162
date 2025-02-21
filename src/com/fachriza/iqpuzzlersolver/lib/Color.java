package com.fachriza.iqpuzzlersolver.lib;

import java.util.Map;

public class Color {

    private static final String RESET = "\033[0m";

    public static enum Colors {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        MAGENTA,
        CYAN,
        WHITE,
        BRIGHT_BLACK,
        BRIGHT_RED,
        BRIGHT_GREEN,
        BRIGHT_YELLOW,
        BRIGHT_BLUE,
        BRIGHT_MAGENTA,
        BRIGHT_CYAN,
        BRIGHT_WHITE,
        UNDER_BLACK,
        UNDER_RED,
        UNDER_GREEN,
        UNDER_YELLOW,
        UNDER_BLUE,
        UNDER_MAGENTA,
        UNDER_CYAN,
        UNDER_WHITE,
        BACK_RED,
        BACK_GREEN
    }

    public static Color.Colors[] colorTable = {
            Color.Colors.BLACK,
            Color.Colors.RED,
            Color.Colors.GREEN,
            Color.Colors.YELLOW,
            Color.Colors.BLUE,
            Color.Colors.MAGENTA,
            Color.Colors.CYAN,
            Color.Colors.WHITE,
            Color.Colors.BRIGHT_BLACK,
            Color.Colors.BRIGHT_RED,
            Color.Colors.BRIGHT_GREEN,
            Color.Colors.BRIGHT_YELLOW,
            Color.Colors.BRIGHT_BLUE,
            Color.Colors.BRIGHT_MAGENTA,
            Color.Colors.BRIGHT_CYAN,
            Color.Colors.BRIGHT_WHITE,
            Color.Colors.UNDER_BLACK,
            Color.Colors.UNDER_RED,
            Color.Colors.UNDER_GREEN,
            Color.Colors.UNDER_YELLOW,
            Color.Colors.UNDER_BLUE,
            Color.Colors.UNDER_MAGENTA,
            Color.Colors.UNDER_CYAN,
            Color.Colors.UNDER_WHITE,
            Color.Colors.BACK_RED,
            Color.Colors.BACK_GREEN
    };

    private static Map<Colors, String> ColorMap = Map.ofEntries(
            Map.entry(Colors.BLACK, "\033[0;30m"),
            Map.entry(Colors.RED, "\033[0;31m"),
            Map.entry(Colors.GREEN, "\033[0;32m"),
            Map.entry(Colors.YELLOW, "\033[0;33m"),
            Map.entry(Colors.BLUE, "\033[0;34m"),
            Map.entry(Colors.MAGENTA, "\033[0;35m"),
            Map.entry(Colors.CYAN, "\033[0;36m"),
            Map.entry(Colors.WHITE, "\033[0;37m"),
            Map.entry(Colors.BRIGHT_BLACK, "\033[1;30m"),
            Map.entry(Colors.BRIGHT_RED, "\033[1;31m"),
            Map.entry(Colors.BRIGHT_GREEN, "\033[1;32m"),
            Map.entry(Colors.BRIGHT_YELLOW, "\033[1;33m"),
            Map.entry(Colors.BRIGHT_BLUE, "\033[1;34m"),
            Map.entry(Colors.BRIGHT_MAGENTA, "\033[1;35m"),
            Map.entry(Colors.BRIGHT_CYAN, "\033[1;36m"),
            Map.entry(Colors.BRIGHT_WHITE, "\033[1;37m"),
            Map.entry(Colors.UNDER_BLACK, "\033[1;30m"),
            Map.entry(Colors.UNDER_RED, "\033[1;31m"),
            Map.entry(Colors.UNDER_GREEN, "\033[1;32m"),
            Map.entry(Colors.UNDER_YELLOW, "\033[1;33m"),
            Map.entry(Colors.UNDER_BLUE, "\033[1;34m"),
            Map.entry(Colors.UNDER_MAGENTA, "\033[1;35m"),
            Map.entry(Colors.UNDER_CYAN, "\033[1;36m"),
            Map.entry(Colors.UNDER_WHITE, "\033[1;37m"),
            Map.entry(Colors.BACK_RED, "\033[41m"),
            Map.entry(Colors.BACK_GREEN, "\033[42m"));

    public static String colorize(String string, Colors color) {
        return String.join("", ColorMap.get(color), string, RESET);
    }

}
