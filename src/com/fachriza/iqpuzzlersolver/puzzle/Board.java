package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.ArrayList;
import java.util.List;

import com.fachriza.iqpuzzlersolver.lib.type.Point;
import com.fachriza.iqpuzzlersolver.lib.Color;

public class Board {
    private byte[][] grid;
    private final int height;
    private final int width;
    private boolean isColorEnabled = true;

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

    public void setColor(boolean val) {
        isColorEnabled = val;
    }

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

    public boolean placeBlock(int centerX, int centerY, Block block, int variantIdx) {

        List<Point> placed = new ArrayList<Point>();
        setElement(centerX, centerY, block.getID());
        for (Point points : block.getCoordinates(variantIdx)) {
            int posX = centerX + points.x;
            int posY = centerY + points.y;

            if (!isCoordinateValid(posX, posY) || getElement(posX, posY) != 0) {
                setElement(centerX, centerY, (byte) 0);
                for (Point point : placed) {
                    setElement(point.x, point.y, (byte) 0);
                }
                return false;
            }

            setElement(posX, posY, block.getID());
            placed.add(new Point(posX, posY));
        }
        return true;
    }

    public void removeBlock(int centerX, int centerY, Block block, int variantIdx) {
        setElement(centerX, centerY, (byte) 0);
        for (Point points : block.getCoordinates(variantIdx)) {
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
                    if (isColorEnabled) {
                        sb.append(Color.colorize(String.valueOf((char) elem), Color.colorTable[elem - 65]));
                    } else {
                        sb.append((char) elem);
                    }
                } else if (elem == -1) {
                    sb.append(" ");
                } else {
                    sb.append(".");
                }
            }
            sb.append(System.lineSeparator());

        }
        return sb.toString();
    }
}
