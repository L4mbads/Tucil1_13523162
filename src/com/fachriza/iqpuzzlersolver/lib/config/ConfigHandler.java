package com.fachriza.iqpuzzlersolver.lib.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigHandler {
    Path filePath;

    public ConfigHandler(String fileName) throws FileNotFoundException {
        filePath = Paths.get("config", fileName);
        if (!Files.exists(filePath))
            throw new FileNotFoundException(String.join(" ", filePath.toString(), "not found!"));

    }

}
