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
        DARK_GRAY,
        LIGHT_BLACK,
        LIGHT_RED,
        LIGHT_GREEN,
        LIGHT_YELLOW,
        LIGHT_BLUE,
        LIGHT_MAGENTA,
        LIGHT_CYAN,
        LIGHT_WHITE,
        ORANGE,
        PINK
    }

    private static Map<Colors, String> ColorMap = Map.ofEntries(
            Map.entry(Colors.BLACK, "\033[0;30m"),
            Map.entry(Colors.RED, "\033[0;31m"),
            Map.entry(Colors.GREEN, "\033[0;32m"),
            Map.entry(Colors.YELLOW, "\033[0;33m"),
            Map.entry(Colors.BLUE, "\033[0;34m"),
            Map.entry(Colors.MAGENTA, "\033[0;35m"),
            Map.entry(Colors.CYAN, "\033[0;36m"),
            Map.entry(Colors.WHITE, "\033[0;37m"),
            Map.entry(Colors.BRIGHT_BLACK, "\033[0;90m"),
            Map.entry(Colors.BRIGHT_RED, "\033[0;91"),
            Map.entry(Colors.BRIGHT_GREEN, "\033[0;92m"),
            Map.entry(Colors.BRIGHT_YELLOW, "\033[0;93m"),
            Map.entry(Colors.BRIGHT_BLUE, "\033[0;94m"),
            Map.entry(Colors.BRIGHT_MAGENTA, "\033[0;95m"),
            Map.entry(Colors.BRIGHT_CYAN, "\033[0;96m"),
            Map.entry(Colors.BRIGHT_WHITE, "\033[0;97m"),
            Map.entry(Colors.DARK_GRAY, "\033[38;5;8m"),
            Map.entry(Colors.LIGHT_RED, "\033[38;5;9m"),
            Map.entry(Colors.LIGHT_GREEN, "\033[38;5;10m"),
            Map.entry(Colors.LIGHT_YELLOW, "\033[38;5;11m"),
            Map.entry(Colors.LIGHT_BLUE, "\033[38;5;12m"),
            Map.entry(Colors.LIGHT_MAGENTA, "\033[38;5;13m"),
            Map.entry(Colors.LIGHT_CYAN, "\033[38;5;14m"),
            Map.entry(Colors.LIGHT_WHITE, "\033[38;5;15m"),
            Map.entry(Colors.ORANGE, "\033[38;5;208m"),
            Map.entry(Colors.PINK, "\033[38;5;205m"));

    public static String colorize(String string, Colors color) {
        return String.join("", ColorMap.get(color), string, RESET);
    }

}
