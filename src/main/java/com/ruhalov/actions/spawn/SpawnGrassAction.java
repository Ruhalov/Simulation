package com.ruhalov.actions.spawn;

import com.ruhalov.Coordinates;
import com.ruhalov.entity.objects.Grass;

public class SpawnGrassAction extends SpawnEntitiesAction<Grass> {
    public SpawnGrassAction(int spawnRate) {
        super(spawnRate);
    }

    @Override
    protected Grass spawnEntity(Coordinates coordinates) {
        return new Grass(coordinates);
    }
}
