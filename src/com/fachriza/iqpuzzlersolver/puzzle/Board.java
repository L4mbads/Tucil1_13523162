package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.ArrayList;
import java.util.List;

import com.fachriza.iqpuzzlersolver.lib.type.Point;
import com.fachriza.iqpuzzlersolver.lib.Color;

public class Board {
    private byte[][] grid;
    private final int height;
    private final int width;

    public Board(int N, int M) {
        height = N;
        width = M;
        grid = new byte[height][width];
    }

    public int getElement(int x, int y) {
        return grid[y][x];
    }

    public void setElement(int x, int y, byte val) {
        grid[y][x] = val;
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

    public boolean isSolved() {
        for (byte[] col : grid) {
            for (byte elem : col) {
                if (elem == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCoordinateValid(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public boolean placeBlock(int centerX, int centerY, Block block) {

        List<Point> placed = new ArrayList<Point>();
        for (Point points : block.getCoordinates()) {
            int posX = centerX + points.x;
            int posY = centerY + points.y;

            if (!isCoordinateValid(posX, posY) || getElement(posX, posY) != 0) {
                // removeBlock(centerX, centerY, block);
                // while (!placed.isEmpty()) {
                // Point point = placed.removeFirst();
                // setElement(point.x, point.y, (byte) 0);
                // }
                for (Point point : placed) {
                    setElement(point.x, point.y, (byte) 0);
                }
                return false;
            }

            setElement(posX, posY, block.getID());
            placed.add(new Point(posX, posY));
        }
        // System.out.println(this);
        return true;
    }

    public void removeBlock(int centerX, int centerY, Block block) {
        for (Point points : block.getCoordinates()) {
            int posX = centerX + points.x;
            int posY = centerY + points.y;

            if (isCoordinateValid(posX, posY)
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
                    // System.out.print((char) elem);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");

        }
        return sb.toString();
    }
}
