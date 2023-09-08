package com.ruhalov;

import static java.lang.Math.floorMod;


public abstract class CoordinatesConverter {
    public static Coordinates convertCoordinates(Coordinates coordinates, World world) {  //TODO: maybe not static?
        return (new Coordinates(convertXCoordinate(coordinates.getX(), world.getWidth()),
                                convertYCoordinate(coordinates.getY(), world.getHeight())));
    }

    public static int convertYCoordinate(int y, int height) {
        if (y > height - 1) {
            return y % height;
        } else if (y < 0) {
            return floorMod(y, height);
        }
        return y;
    }

    public static int convertXCoordinate(int x, int width) {
        if (x > width - 1) {
            return x % width;
        } else if (x < 0) {
            return floorMod(x, width);
        }
        return x;
    }
}