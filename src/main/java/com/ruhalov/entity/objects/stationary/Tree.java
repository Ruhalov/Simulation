package com.ruhalov.entity.objects.stationary;

import com.ruhalov.Constants;
import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;
import com.ruhalov.entity.objects.Grass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tree extends Entity {
    private final int ageForGrow = Constants.STEPS_FOR_GROW_GRASS_NEAR_TREE;
    private int age = 0;

    public Tree(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void growGrass(World world) {
        if (age == ageForGrow) {
            List<Coordinates> emptyNeighbouringCells = world.getEmptyNeighbouringCells(coordinates);
            if (!emptyNeighbouringCells.isEmpty()) {
                int randomElementIndex = ThreadLocalRandom.current()
                        .nextInt(emptyNeighbouringCells.size()) %
                        emptyNeighbouringCells.size();
                Grass grass = new Grass(emptyNeighbouringCells.get(randomElementIndex));
                world.setEntity(grass);
                age = 0;
            }
            world.clearCell(coordinates);
        } else {
            age++;
        }
    }
}
