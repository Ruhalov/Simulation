package com.ruhalov.actions.spawn;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.actions.Actions;
import com.ruhalov.entity.Entity;

import java.util.concurrent.ThreadLocalRandom;


public abstract class SpawnEntitiesAction<T extends Entity> extends Actions {
    private int spawnRate;

    public SpawnEntitiesAction(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    @Override
    public void perform(World world) {
        while (spawnRate != 0) {
            int randomXCoordinate = ThreadLocalRandom.current().nextInt(world.getWidth() ) % (world.getWidth());
            int randomYCoordinate = ThreadLocalRandom.current().nextInt(world.getHeight()) % (world.getHeight());
            Coordinates randomCoordinates = new Coordinates(randomXCoordinate, randomYCoordinate);
            if (world.isEmptyCell(randomCoordinates)) {
                T entity = spawnEntity(randomCoordinates);
                world.setEntity(entity);
                spawnRate--;
            }
        }
    }

    protected abstract T spawnEntity(Coordinates coordinates);
}
