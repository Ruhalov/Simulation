package com.ruhalov.actions.spawn;

import com.ruhalov.Coordinates;
import com.ruhalov.entity.creature.Predator;

public class SpawnPredatorAction extends SpawnEntitiesAction<Predator>{

    public SpawnPredatorAction(int spawnRate) {
        super(spawnRate);
    }

    @Override
    protected Predator spawnEntity(Coordinates coordinates) {
        return new Predator(coordinates);
    }
}
