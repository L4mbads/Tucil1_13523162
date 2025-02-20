package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.List;
import java.util.ArrayList;

import com.fachriza.iqpuzzlersolver.lib.type.Point;

public class Block {

    private List<Point> coordinates;
    private final byte ID;
    private List<List<Point>> variants = new ArrayList<>();

    public Block(byte ID) {
        this.ID = ID;
        coordinates = new ArrayList<Point>();
    }

    public void addCoordinates(int x, int y) {
        coordinates.add(new Point(x, y));
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public byte getID() {
        return ID;
    }

    public Block getMirroredX() {
        Block flipped = new Block(ID);
        for (Point point : coordinates) {
            flipped.addCoordinates(-point.x, point.y);
        }
        return flipped;
    }

    public Block getMirroredY() {
        Block flipped = new Block(ID);
        for (Point point : coordinates) {
            flipped.addCoordinates(point.x, -point.y);
        }
        return flipped;
    }

    public Block getMirroredXY() {
        Block flipped = new Block(ID);
        for (Point point : coordinates) {
            flipped.addCoordinates(point.y, point.x);
        }
        return flipped;
    }

    public void setVariant(int idx) {
        coordinates = variants.get(idx);
    }

    public void generateVariants() {
        variants.add(coordinates);
        List<Point> variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(point.y, point.x));
        }
        variants.add(variant);
        variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(-point.x, point.y));
        }
        variants.add(variant);
        variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(point.y, -point.x));
        }
        variants.add(variant);
        variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(point.x, -point.y));
        }
        variants.add(variant);
        variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(-point.y, point.x));
        }
        variants.add(variant);
        variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(-point.x, -point.y));
        }
        variants.add(variant);
        variant = new ArrayList<Point>();
        for (Point point : coordinates) {
            variant.add(new Point(-point.y, -point.x));
        }
        variants.add(variant);

    }
}
