package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.World;


public class Predator extends Creature {
    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove(World world) {
        huntsForTarget(Herbivore.class, world);
    }
}

