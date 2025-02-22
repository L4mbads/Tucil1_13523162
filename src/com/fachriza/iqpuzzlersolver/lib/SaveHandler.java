package com.fachriza.iqpuzzlersolver.lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.fachriza.iqpuzzlersolver.lib.config.Config;
import com.fachriza.iqpuzzlersolver.puzzle.Solver;

public class SaveHandler {

    public static void promptSave(Solver solver) {
        Path filePath;
        Config config = solver.getConfig();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Save result?: ");
            String res;
            do {
                res = scanner.nextLine().toLowerCase();
            } while (!res.equals("y") && !res.equals("n"));

            if (res.equals("n"))
                return;

            System.out.print("Masukkan nama file (tanpa format): ");
            String fileName = scanner.nextLine();
            filePath = Paths.get("test", fileName + ".txt");

            if (!Files.exists(filePath))
                Files.createFile(filePath);

            try (FileWriter fw = new FileWriter(filePath.toFile(), StandardCharsets.UTF_8);
                    BufferedWriter writer = new BufferedWriter(fw)) {
                config.getBoard().setColor(false);
                writer.write(solver.getResult());
                System.out.println("File written successfully!");
                ImageGenerator.generateImage(config, fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
