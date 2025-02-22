package com.fachriza.iqpuzzlersolver;

import com.fachriza.iqpuzzlersolver.lib.SaveHandler;
import com.fachriza.iqpuzzlersolver.lib.config.ConfigHandler;
import com.fachriza.iqpuzzlersolver.puzzle.Solver;

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

        Solver solver = new Solver(configHandler.getConfig());
        solver.solve();

        System.out.println("");
        System.out.println(solver.getResult());

        SaveHandler.promptSave(solver);

    }
}
