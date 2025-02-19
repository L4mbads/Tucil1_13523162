package com.fachriza.iqpuzzlersolver.puzzle;

import com.fachriza.iqpuzzlersolver.lib.Color;
import com.fachriza.iqpuzzlersolver.lib.type.Point;

public class Board {
    private byte[][] grid;

    public Board(int height, int width) {
        grid = new byte[height][width];
    }

    public int getElement(int x, int y) {
        return grid[x][y];
    }

    public void setElement(int x, int y, byte val) {
        grid[x][y] = val;
    }

    private Color.Colors[] colorTable = {
            Color.Colors.BLACK,
            Color.Colors.RED,
            Color.Colors.GREEN,
            Color.Colors.YELLOW,
            Color.Colors.BLUE,
            Color.Colors.MAGENTA,
            Color.Colors.CYAN,
            Color.Colors.WHITE,
            Color.Colors.BRIGHT_BLACK,
            Color.Colors.BRIGHT_RED,
            Color.Colors.BRIGHT_GREEN,
            Color.Colors.BRIGHT_YELLOW,
            Color.Colors.BRIGHT_BLUE,
            Color.Colors.BRIGHT_MAGENTA,
            Color.Colors.BRIGHT_CYAN,
            Color.Colors.BRIGHT_WHITE,
            Color.Colors.DARK_GRAY,
            Color.Colors.LIGHT_BLACK,
            Color.Colors.LIGHT_RED,
            Color.Colors.LIGHT_GREEN,
            Color.Colors.LIGHT_YELLOW,
            Color.Colors.LIGHT_BLUE,
            Color.Colors.LIGHT_MAGENTA,
            Color.Colors.LIGHT_CYAN,
            Color.Colors.LIGHT_WHITE,
            Color.Colors.ORANGE,
            Color.Colors.PINK
    };

    public boolean placeBlock(int centerX, int centerY, Block block) {

        for (Point points : block.getCoordinates()) {
            int posX = centerX + points.x;
            int posY = centerY + points.y;

            if ((posX < 0 || posX >= grid[0].length || posY < 0 || posY >= grid.length)
                    || getElement(posX, posY) != 0) {
                removeBlock(centerX, centerY, block);
                return false;
            }

            setElement(posX, posY, block.getID());
        }

        System.out.println(this);
        return true;
    }

    public void removeBlock(int centerX, int centerY, Block block) {
        for (Point points : block.getCoordinates()) {
            int posX = centerX + points.x;
            int posY = centerY + points.y;

            if ((posX >= 0 && posX < grid[0].length && posY >= 0 && posY < grid.length)
                    && (getElement(posX, posY) == 0 || getElement(posX, posY) == block.getID())) {
                setElement(posX, posY, (byte) 0);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (byte[] col : grid) {
            for (byte elem : col) {
                if (elem >= 65 && elem <= 90) {
                    System.out.print(Color.colorize(String.valueOf((char) elem), colorTable[elem - 65]));
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");

        }
        return sb.toString();
    }
}
