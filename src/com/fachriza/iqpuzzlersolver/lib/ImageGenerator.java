package com.fachriza.iqpuzzlersolver.lib;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.fachriza.iqpuzzlersolver.lib.config.Config;
import com.fachriza.iqpuzzlersolver.lib.Color;

public class ImageGenerator {

    private static Map<Color.Colors, java.awt.Color> colorMap = new HashMap<>();

    static {
        colorMap.put(Color.Colors.BLACK, new java.awt.Color(0, 0, 0)); // Black
        colorMap.put(Color.Colors.RED, new java.awt.Color(205, 0, 0)); // Red
        colorMap.put(Color.Colors.GREEN, new java.awt.Color(0, 205, 0)); // Green
        colorMap.put(Color.Colors.YELLOW, new java.awt.Color(205, 205, 0)); // Yellow
        colorMap.put(Color.Colors.BLUE, new java.awt.Color(0, 0, 238)); // Blue
        colorMap.put(Color.Colors.MAGENTA, new java.awt.Color(205, 0, 205)); // Magenta
        colorMap.put(Color.Colors.CYAN, new java.awt.Color(0, 205, 205)); // Cyan
        colorMap.put(Color.Colors.WHITE, new java.awt.Color(229, 229, 229)); // White (Light Gray)
        colorMap.put(Color.Colors.BRIGHT_BLACK, new java.awt.Color(127, 127, 127)); // Bright Black (Dark Gray)
        colorMap.put(Color.Colors.BRIGHT_RED, new java.awt.Color(255, 0, 0)); // Bright Red
        colorMap.put(Color.Colors.BRIGHT_GREEN, new java.awt.Color(0, 255, 0)); // Bright Green
        colorMap.put(Color.Colors.BRIGHT_YELLOW, new java.awt.Color(255, 255, 0)); // Bright Yellow
        colorMap.put(Color.Colors.BRIGHT_BLUE, new java.awt.Color(92, 92, 255)); // Bright Blue
        colorMap.put(Color.Colors.BRIGHT_MAGENTA, new java.awt.Color(255, 0, 255)); // Bright Magenta
        colorMap.put(Color.Colors.BRIGHT_CYAN, new java.awt.Color(0, 255, 255)); // Bright Cyan
        colorMap.put(Color.Colors.BRIGHT_WHITE, new java.awt.Color(180, 180, 180)); // Bright White
        colorMap.put(Color.Colors.UNDER_BLACK, new java.awt.Color(0, 205, 205)); // Cyan
        colorMap.put(Color.Colors.UNDER_RED, new java.awt.Color(229, 229, 229)); // White (Light Gray)
        colorMap.put(Color.Colors.UNDER_GREEN, new java.awt.Color(127, 127, 127)); // Bright Black (Dark Gray)
        colorMap.put(Color.Colors.UNDER_YELLOW, new java.awt.Color(255, 0, 0)); // Bright Red
        colorMap.put(Color.Colors.UNDER_BLUE, new java.awt.Color(0, 255, 0)); // Bright Green
        colorMap.put(Color.Colors.UNDER_MAGENTA, new java.awt.Color(255, 255, 0)); // Bright Yellow
        colorMap.put(Color.Colors.UNDER_CYAN, new java.awt.Color(92, 92, 255)); // Bright Blue
        colorMap.put(Color.Colors.UNDER_WHITE, new java.awt.Color(255, 0, 255)); // Bright Magenta
        colorMap.put(Color.Colors.BACK_RED, new java.awt.Color(90, 0, 0)); // Bright Cyan
        colorMap.put(Color.Colors.BACK_GREEN, new java.awt.Color(0, 90, 0)); // Bright White
    }

    public static void generateImage(Config config, String fileName) {
        int width = config.getWidth() * 100;
        int height = config.getHeight() * 100;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();

        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < config.getHeight(); i++) {
            for (int j = 0; j < config.getWidth(); j++) {
                int elem = config.getBoard().getElement(j, i);
                if (elem < 65)
                    continue;
                g.setColor(colorMap.get(Color.colorTable[elem - 65]));
                g.fillOval(j * 100 + 0, i * 100 + 0, 101, 101);

            }
        }

        g.dispose();

        try {
            File outputFile = Paths.get("test", fileName + ".png").toFile();
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
