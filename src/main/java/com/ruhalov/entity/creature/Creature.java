package com.ruhalov.entity.creature;

import com.ruhalov.Constants;
import com.ruhalov.Coordinates;
import com.ruhalov.CoordinatesConverter;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;
import com.ruhalov.util.AStarPathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Creature extends Entity {
    private int healthPoint = Constants.HEALTH_POINTS_CREATURE;
    protected final int healthPointForReproduce = Constants.HEALTH_POINTS_FOR_REPRODUCE;
    protected final int costOfReproduction = Constants.COST_OF_REPRODUCTION;
    protected final int benefitsOfFood = Constants.BENEFITS_OF_FOOD;
    protected final AStarPathFinder pathFinder = new AStarPathFinder();

    public Creature(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Creature(Creature parent, World world) {
        if (parent != null) {
            List<Coordinates> emptyNeighbouringCells = world.getEmptyNeighbouringCells(parent.coordinates);
            if (!emptyNeighbouringCells.isEmpty()) {
                int randomElementIndex = ThreadLocalRandom.current()
                        .nextInt(emptyNeighbouringCells.size()) %
                        emptyNeighbouringCells.size();
                this.coordinates = emptyNeighbouringCells.get(randomElementIndex);
            }
        }
    }
    public abstract void makeMove(World world);

    public abstract Creature reproduce(World world);

    public int getHealthPoint() {
        return healthPoint;
    }
    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void die(World world) {
        world.clearCell(coordinates);
        System.out.println("dead");
    }

    public void go(Coordinates coordinatesToMove, World world) {
        world.clearCell(coordinates);
        Coordinates convertedCoordinates = CoordinatesConverter.convertCoordinates(coordinatesToMove, world);
        coordinates.setX(convertedCoordinates.getX());
        coordinates.setY(convertedCoordinates.getY());
        world.setEntity(this);
    }

    protected void goRand(World world) {
        List<Coordinates> emptyNeighbouringCoordinates = world.getEmptyNeighbouringCells(coordinates);
        if (!emptyNeighbouringCoordinates.isEmpty()) {
            int randomElementIndex = ThreadLocalRandom.current()
                                                      .nextInt(emptyNeighbouringCoordinates.size()) %
                                                                emptyNeighbouringCoordinates.size();
            go(emptyNeighbouringCoordinates.get(randomElementIndex), world);
        }
    }
    public <T extends Entity> void eat(T target, World world) {
        setHealthPoint(getHealthPoint() + benefitsOfFood);
        go(target.getCoordinates(), world);
    }

    protected <T extends Entity> void huntForTarget(T target, World world) {
        List<Coordinates> pathToTarget = new ArrayList<>(pathFinder.findPathToTarget(coordinates, target.getCoordinates(), world));
        if(pathToTarget.isEmpty()){
            goRand(world);
        } else {
            Coordinates coordinatesToMove = pathToTarget.get(1);
            go(coordinatesToMove, world);
        }
    }
}
