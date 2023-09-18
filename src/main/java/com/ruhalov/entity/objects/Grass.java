package com.ruhalov.entity.objects;

import com.ruhalov.Constants;
import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;
import com.ruhalov.entity.objects.stationary.Tree;

public class Grass extends Entity {
    private final int ageForTransformToTree = Constants.STEPS_FOR_GROW_GRASS_NEAR_TREE;
    private int age = 0;

    public Grass(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void turnToTree(World world) {
        if (age == ageForTransformToTree) {
            world.clearCell(coordinates);
            world.setEntity(new Tree(coordinates));
            age = 0;
        } else {
            age++;
        }
    }
}
