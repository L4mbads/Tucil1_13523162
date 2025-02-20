package com.fachriza.iqpuzzlersolver;

import java.text.DecimalFormat;

import com.fachriza.iqpuzzlersolver.lib.SaveHandler;
import com.fachriza.iqpuzzlersolver.lib.config.ConfigHandler;
import com.fachriza.iqpuzzlersolver.puzzle.Solver;
import com.fachriza.iqpuzzlersolver.puzzle.Solver.SolverState;

public class App {
    private final static DecimalFormat df = new DecimalFormat("0.0000");

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
        Solver.SolverState solverState = solver.solve();
        switch (solverState) {
            case SolverState.SUCCESS:
                System.out.println("Success");
                break;

            case SolverState.FAIL_NO_SOLUTION:
                System.out.println("No solution");
                break;

            case SolverState.FAIL_OVER_PIECE:
                System.out.println("Too much blocks");
                break;

            default:
                break;
        }

        System.out.println(configHandler.getConfig().getBoard());
        System.out.println(" Time: " + df.format(solver.getTimeElapsedInNs() * 0.000001) + " ms");
        System.out.println("Cases: " + solver.cases);

        SaveHandler.promptSave(configHandler.getConfig(), solver);

    }
}
