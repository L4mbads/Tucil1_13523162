package com.fachriza.iqpuzzlersolver.lib.type;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.join(", ", String.valueOf(x), String.valueOf(y));
    }
}
