package com.ruhalov.actions;

import com.ruhalov.World;
import com.ruhalov.entity.objects.Grass;

import java.util.List;

public class TurnGrassToTrees extends Actions {
    @Override
    public void perform(World world) {
        for (Grass grass : List.copyOf(world.getEntitiesByClass(Grass.class))) {
            grass.turnToTree(world);
        }
    }
}
