package com.ruhalov.actions;

import com.ruhalov.World;
import com.ruhalov.entity.creature.Creature;

import java.util.List;


public class MoveAllCreature extends Actions {
    @Override
    public void perform(World world) {
        for (Creature creature : List.copyOf(world.getEntitiesByClass(Creature.class))) {
            if (world.getAllEntities().contains(creature)) {
                creature.makeMove(world);
            }
        }
    }
}

