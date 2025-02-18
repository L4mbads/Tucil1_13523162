package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.List;
import java.util.ArrayList;

import com.fachriza.iqpuzzlersolver.lib.type.Point;

public class Block {

    private List<Point> coordinates;
    private final byte ID;

    public Block(byte ID) {
        this.ID = ID;
        coordinates = new ArrayList<Point>();
    }

    public void addCoordinates(int x, int y) {
        coordinates.add(new Point(x, y));
    }

    public byte getID() {
        return ID;
    }
}
