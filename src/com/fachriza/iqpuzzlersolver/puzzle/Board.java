package com.fachriza.iqpuzzlersolver.puzzle;

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
                    System.out.print((char) elem);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");

        }
        return sb.toString();
    }
}
