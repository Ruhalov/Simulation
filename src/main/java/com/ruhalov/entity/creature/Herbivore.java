package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.objects.Grass;

import java.util.List;


public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }
    public Herbivore(Herbivore parent, World world){
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
        List<Grass> targets = world.getEntitiesByClass(Grass.class);
        if (targets.isEmpty()) {
            goRand(world);
        } else {
            Grass target = selectNearestTarget(targets, world);
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
        return new Herbivore(this, world);
    }
}
