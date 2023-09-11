package com.ruhalov.actions.spawn;

import com.ruhalov.Coordinates;
import com.ruhalov.entity.objects.stationary.Tree;

public class SpawnTreeAction extends SpawnEntitiesAction<Tree>{
    public SpawnTreeAction(int spawnRate) {
        super(spawnRate);
    }

    @Override
    protected Tree spawnEntity(Coordinates coordinates) {
        return new Tree(coordinates);
    }
}
