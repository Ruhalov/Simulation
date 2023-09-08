package com.ruhalov.action;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.creature.Creature;

import java.util.Set;


public class moveAllCreature extends Actions {
    @Override
    public void perform(World world) {
        for (Coordinates coordinates : Set.copyOf(world.getAllEntityCoordinates())) {
            if (world.getEntity(coordinates) instanceof Creature creature) {
                creature.makeMove(world);
            }
        }
    }
}

