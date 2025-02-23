package com.fachriza.iqpuzzlersolver;

import java.util.Scanner;

import com.fachriza.iqpuzzlersolver.lib.GUI.MainFrame;
import com.fachriza.iqpuzzlersolver.puzzle.Game;

public class App {

    public static void main(String[] args) {

        if (args.length == 0) {
            try (Scanner scanner = new Scanner(System.in)) {
                Game game = new Game(scanner);
                game.readConfig();
                game.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equals("GUI")) {
            MainFrame mainFrame = new MainFrame();
            // mainFrame.OpenGUI();
        } else {
            System.err.println("Invalid arguments");
        }

    }
}
