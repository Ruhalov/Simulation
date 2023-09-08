package com.ruhalov;

import com.ruhalov.entity.Entity;

import java.util.HashMap;

public class World {
    private final int width;
    private final int height;
    private final HashMap<Coordinates, Entity> world;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.world = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Coordinates> getNeighboringCoordinates(Coordinates coordinates) {
        ArrayList<Coordinates> neighbors = new ArrayList<>(4);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (coordinates.getX() + j == coordinates.getX() &&
                        coordinates.getY() + i != coordinates.getY()) {

                    Coordinates convertedCoordinates = CoordinatesConverter.convertCoordinates(
                            new Coordinates(coordinates.getX() + j, coordinates.getY() + i), this);
                    neighbors.add(convertedCoordinates);
                } else if (coordinates.getX() + j != coordinates.getX() &&
                        coordinates.getY() + i == coordinates.getY()) {

                    Coordinates convertedCoordinates = CoordinatesConverter.convertCoordinates(
                            new Coordinates(coordinates.getX() + j, coordinates.getY() + i), this);
                    neighbors.add(convertedCoordinates);
                }
            }
        }
        return neighbors;
    }

    public void setEntity(Entity entity) {
        world.put(entity.getCoordinates(), entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return world.get(coordinates);
    }

    public void clearCell(Coordinates coordinates) {
        world.remove(coordinates);
    }

    public boolean isEmptyCell(Coordinates coordinates) {
        return !world.containsKey(coordinates);
    }
}