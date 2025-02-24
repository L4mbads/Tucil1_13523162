package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.Scanner;

import com.fachriza.iqpuzzlersolver.lib.SaveHandler;
import com.fachriza.iqpuzzlersolver.lib.config.Config;
import com.fachriza.iqpuzzlersolver.lib.config.ConfigHandler;
import com.fachriza.iqpuzzlersolver.puzzle.Solver;

public class Game {
    private Scanner scanner;
    private ConfigHandler configHandler;
    private Solver solver;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }

    public Config getConfig() {
        return configHandler.getConfig();
    }

    public Solver getSolver() {
        return solver;
    }

    public void readConfig() {
        try {
            System.out.print("Masukkan nama file: ");
            configHandler = new ConfigHandler(scanner.nextLine());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void readConfig(String fileName) {
        try {
            configHandler = new ConfigHandler(fileName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void start() {
        try {
            solver = new Solver(configHandler.getConfig());
            solver.solve();

            System.out.println("");
            System.out.println(solver.getResult());

            if (scanner != null)
                SaveHandler.promptSave(scanner, solver);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
