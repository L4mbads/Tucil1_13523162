package com.fachriza.iqpuzzlersolver.puzzle;

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
}
