package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.objects.Grass;


public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove(World world) {
        huntsForTarget(Grass.class, world);
    }
}
