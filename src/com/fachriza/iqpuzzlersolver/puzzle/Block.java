package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.List;
import java.util.ArrayList;

import com.fachriza.iqpuzzlersolver.lib.type.Point;

public class Block {

    private final byte ID;
    private List<List<Point>> variants = new ArrayList<>();

    public Block(byte ID) {
        this.ID = ID;
    }

    public void addCoordinates(int x, int y) {
        if (variants.isEmpty()) {
            variants.add(new ArrayList<Point>());
        }
        variants.get(0).add(new Point(x, y));
    }

    public int getVariantsNum() {
        return variants.size();
    }

    public List<Point> getCoordinates(int idx) {
        return variants.get(idx);
    }

    public byte getID() {
        return ID;
    }

    private boolean isSymmetrical(List<Point> modified, int offsetX, int offsetY) {
        for (Point point : modified) {
            Point offset = new Point(point.x + offsetX, point.y + offsetY);
            if (offset.x == 0 && offset.y == 0)
                continue;
            boolean containPoints = false;
            for (Point point2 : variants.get(0)) {
                if (offsetX == point2.x && offsetY == point2.y)
                    continue;
                if ((offset.x == point2.x && offset.y == point2.y)) {
                    containPoints = true;
                    break;
                }
            }
            if (!containPoints) {
                return false;
            }
        }
        return true;
    }

    private Point calculateMinMaxOffset(List<Point> coordinates) {
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        for (Point point : coordinates) {
            if (point.x < minX)
                minX = point.x;
            if (point.x > maxX)
                maxX = point.x;
            if (point.y < minY)
                minY = point.y;
            if (point.y > maxY)
                maxY = point.y;
        }
        Point offsets = new Point(minX + maxX, minY + maxY);
        return offsets;
    }

    public void generateVariants() {
        if (variants.isEmpty())
            return;
        List<Point> original = variants.get(0);
        Point offsets = calculateMinMaxOffset(original);
        boolean isSymmetrical = false;

        List<Point> modified = new ArrayList<>();
        for (Point point : original) {
            modified.add(new Point(point.y, point.x));
        }
        if (!isSymmetrical(modified, 0, 0)) {
            variants.add(modified);
        }

        modified = new ArrayList<>();
        for (Point point : original) {
            modified.add(new Point(-point.x, point.y));
        }
        if (!isSymmetrical(modified, offsets.x, 0)) {
            variants.add(modified);
            modified = new ArrayList<>();
            for (Point point : original) {
                modified.add(new Point(point.y, -point.x));
            }
            variants.add(modified);
        } else {
            isSymmetrical = true;
        }

        modified = new ArrayList<>();
        for (Point point : original) {
            modified.add(new Point(point.x, -point.y));
        }
        if (!isSymmetrical(modified, 0, offsets.y)) {
            variants.add(modified);
            modified = new ArrayList<>();
            for (Point point : original) {
                modified.add(new Point(-point.y, point.x));
            }
            variants.add(modified);
        } else {
            isSymmetrical = true;
        }

        if (!isSymmetrical) {
            modified = new ArrayList<>();
            for (Point point : original) {
                modified.add(new Point(-point.x, -point.y));
            }
            if (!isSymmetrical(modified, offsets.x, offsets.y)) {
                variants.add(modified);
                modified = new ArrayList<>();
                for (Point point : original) {
                    modified.add(new Point(-point.y, -point.x));
                }
                variants.add(modified);
            }
        }
    }
}
