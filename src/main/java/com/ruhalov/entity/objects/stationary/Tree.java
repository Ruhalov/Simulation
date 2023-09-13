package com.ruhalov.entity.objects.stationary;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;
import com.ruhalov.entity.objects.Grass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    private int age = 0;

    public void growGrass(World world) {
        int ageForGrow = 5;
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
