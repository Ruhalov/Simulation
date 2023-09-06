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

    public HashMap<Coordinates, Entity> getWorld() {
        return world;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        world.put(coordinates, entity);
    }
}
