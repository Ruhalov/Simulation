package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.World;

import java.util.List;


public class Predator extends Creature {
    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    public Predator(Predator parent, World world) {
        super(parent, world);
    }

    @Override
    public void makeMove(World world) {
        if (getHealthPoint() == 0) {
            die(world);
            return;
        } else if (getHealthPoint() >= healthPoinForReproduce) {
            world.setEntity(reproduce(world));
            setHealthPoint(getHealthPoint() - 10);
            return;
        }
        List<Herbivore> targets = world.getEntitiesByClass(Herbivore.class);
        if (targets.isEmpty()) {
            goRand(world);
        } else {
            Herbivore target = selectNearestTarget(targets, world);
            if (pathFinder.manhattanDistance(coordinates, target.getCoordinates(), world) == 1) {
                eat(target, world);
                setHealthPoint(getHealthPoint() + 8);
            } else {
                huntForTarget(target, world);
            }
        }
        setHealthPoint(getHealthPoint() - 1);
    }

    @Override
    public Creature reproduce(World world) {
        return new Predator(this, world);
    }
}

