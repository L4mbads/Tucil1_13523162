package com.fachriza.iqpuzzlersolver.lib;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
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

    public static void generateImage(Config config, String fileName) {
        int size = 100;
        int width = config.getWidth() * size;
        int height = config.getHeight() * size;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();

        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, width, height);

        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        for (int i = 0; i < config.getHeight(); i++) {
            for (int j = 0; j < config.getWidth(); j++) {
                int elem = config.getBoard().getElement(j, i);
                if (elem < 65)
                    continue;
                int sphereX = j * size;
                int sphereY = i * size;
                int diameter = size + 1;
                g.setColor(Color.colorMap.get(Color.colorTable[elem - 65]));
                g.fillOval(sphereX, sphereY, diameter, diameter);
                if (elem == 65 || elem == 73 || elem == 81) {
                    g.setColor(java.awt.Color.WHITE);
                } else {
                    g.setColor(java.awt.Color.BLACK);
                }

                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(".");
                int textHeight = fm.getAscent();

                int textX = sphereX + (diameter - textWidth) / 2 - 5;
                int textY = sphereY + (diameter + textHeight) / 2 - 5;

                g.drawString(String.valueOf((char) elem), textX, textY);
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
