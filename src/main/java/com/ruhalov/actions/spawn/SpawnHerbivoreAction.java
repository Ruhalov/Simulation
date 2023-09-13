package com.ruhalov.actions.spawn;

import com.ruhalov.Coordinates;
import com.ruhalov.entity.creature.Herbivore;

public class SpawnHerbivoreAction extends SpawnEntitiesAction<Herbivore> {

    public SpawnHerbivoreAction(int spawnRate) {
        super(spawnRate);
    }

    @Override
    protected Herbivore spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }
}
