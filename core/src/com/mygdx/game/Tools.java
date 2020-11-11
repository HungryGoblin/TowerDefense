package com.mygdx.game;

import com.badlogic.gdx.math.Polygon;

import java.io.File;

public class Tools {

    public static float randomValue(float minBound, float upperBound) {
        return (int) (Math.random() * (upperBound - minBound) + minBound);
    }

    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return (file.getAbsoluteFile().exists() && file.getAbsoluteFile().isFile());
    }

    public static float getDiagonal(float width, float height) {
        return (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }

    public static Polygon getPolygon3 (float x, float y, float height, float width) {
        Polygon triangle = new Polygon(new float[]{x, y, x + height, width / 2 + y, x + height, y - width / 2});
        return triangle;
    }

}
