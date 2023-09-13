package com.ruhalov.entity.objects;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;
import com.ruhalov.entity.objects.stationary.Tree;

public class Grass extends Entity {
    private int age = 0;
    public Grass(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void turnToTree(World world) {
        int ageForTransformToTree = 9;
        if (age == ageForTransformToTree) {
            world.clearCell(coordinates);
            Tree tree = new Tree(coordinates);
            world.setEntity(tree);
            age = 0;
        } else {
            age++;
        }
    }
}
