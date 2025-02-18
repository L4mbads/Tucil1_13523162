package com.fachriza.iqpuzzlersolver;

import com.fachriza.iqpuzzlersolver.lib.config.ConfigHandler;

public class App {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Configuration file is not provided!");
            return;
        }
        ConfigHandler configHandler = null;
        try {
            configHandler = new ConfigHandler(args[0]);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
    }
}
