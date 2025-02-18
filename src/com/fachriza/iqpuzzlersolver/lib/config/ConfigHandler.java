package com.fachriza.iqpuzzlersolver.lib.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import com.fachriza.iqpuzzlersolver.lib.config.Config;
import com.fachriza.iqpuzzlersolver.puzzle.Block;

public class ConfigHandler {
    Path filePath;

    enum ConfigState {
        NUMBERS,
        TYPE,
        CUSTOM,
        BLOCKS,
        FINISHED
    }

    public ConfigHandler(String fileName) throws Exception {
        filePath = Paths.get("config", fileName);
        if (!Files.exists(filePath))
            throw new FileNotFoundException(String.join(" ", filePath.toString(), "not found!"));

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            ConfigState configState = ConfigState.NUMBERS;
            Config config = new Config();
            Block block = null;
            while (configState != ConfigState.FINISHED) {
                line = reader.readLine();
                if (line == null) {
                    if (configState == ConfigState.BLOCKS) {
                        configState = ConfigState.FINISHED;
                    }
                    break;
                }

                switch (configState) {
                    case ConfigState.NUMBERS:
                        String[] strings = line.strip().split(" ");

                        if (strings.length != 3) {
                            throw new Exception("First configuration line is invalid");
                        }

                        for (int i = 0; i < 3; i++) {
                            try {
                                int val = Integer.parseInt(strings[i]);
                                switch (i) {
                                    case 0:
                                        config.setBoardHeight(val);
                                        break;

                                    case 1:
                                        config.setBoardWidth(val);
                                        break;

                                    case 2:
                                        config.setBlockNum(val);
                                        break;
                                    default:
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                throw new Exception("First configuration line is invalid");
                            }
                        }

                        config.build();

                        // System.out.println(config);

                        configState = ConfigState.TYPE;
                        break;

                    case ConfigState.TYPE:
                        // System.out.println(line);
                        line = line.strip();

                        switch (line) {
                            case "DEFAULT":

                                break;

                            case "CUSTOM":

                                break;

                            case "PYRAMID":
                                throw new Exception("PYRAMID configuration is not supported");

                            default:
                                break;
                        }
                        configState = ConfigState.BLOCKS;
                        break;

                    case ConfigState.BLOCKS:
                        // System.out.println(line);
                        break;

                    default:
                        break;
                }

                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
