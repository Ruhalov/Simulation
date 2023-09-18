package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.objects.Grass;


public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    public Herbivore(Herbivore parent, World world) {
        super(parent, world);
    }

    @Override
    public Creature reproduce(World world) {
        return new Herbivore(this, world);
    }

    @Override
    public void makeMove(World world) {
        if (getHealthPoint() == 0) {
            die(world);
        } else if (getHealthPoint() >= healthPointForReproduce) {
            world.setEntity(reproduce(world));
            setHealthPoint(getHealthPoint() - costOfReproduction);
        } else {
            Grass target = pathFinder.selectNearestTarget(coordinates, Grass.class, world);
            if (target == null) {
                goRand(world);
            } else {
                if (pathFinder.manhattanDistance(coordinates, target.getCoordinates(), world) == 1) {
                    eat(target, world);
                } else {
                    huntForTarget(target, world);
                }
            }
            setHealthPoint(getHealthPoint() - 1);
        }
    }
}
