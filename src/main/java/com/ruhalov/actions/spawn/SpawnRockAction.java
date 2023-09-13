package com.ruhalov.actions.spawn;

import com.ruhalov.Coordinates;
import com.ruhalov.entity.objects.stationary.Rock;


public class SpawnRockAction extends SpawnEntitiesAction<Rock> {
    public SpawnRockAction(int spawnRate) {
        super(spawnRate);
    }

    @Override
    protected Rock spawnEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}
