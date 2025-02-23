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
                g.setColor(Color.colorMap.get(Color.colorTable[elem - 65]));
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
