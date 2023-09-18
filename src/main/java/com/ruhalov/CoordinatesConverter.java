package com.ruhalov;

import static java.lang.Math.floorMod;


public abstract class CoordinatesConverter {
    public static Coordinates convertCoordinates(Coordinates coordinates, World world) {  //TODO: maybe not static?
        return (new Coordinates(convertCoordinate(coordinates.getX(), world.getWidth()),
                convertCoordinate(coordinates.getY(), world.getHeight())));
    }
    private static int convertCoordinate(int coordinate, int maxCoordinateValue) {
        if (coordinate > maxCoordinateValue - 1) {
            return coordinate % maxCoordinateValue;
        } else if (coordinate < 0) {
            return floorMod(coordinate, maxCoordinateValue);
        }
        return coordinate;
    }
}