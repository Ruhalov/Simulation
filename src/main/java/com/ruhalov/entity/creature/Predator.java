package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.World;


public class Predator extends Creature {
    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    public Predator(Predator parent, World world) {
        super(parent, world);
    }

    @Override
    public Creature reproduce(World world) {
        return new Predator(this, world);
    }

    @Override
    public void makeMove(World world) {
        if (getHealthPoint() == 0) {
            die(world);
        } else if (getHealthPoint() >= healthPointForReproduce) {
            world.setEntity(reproduce(world));
            setHealthPoint(getHealthPoint() - costOfReproduction);
        } else {
            Herbivore target = pathFinder.selectNearestTarget(coordinates, Herbivore.class, world);
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