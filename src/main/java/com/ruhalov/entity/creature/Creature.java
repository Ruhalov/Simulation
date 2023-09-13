package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.CoordinatesConverter;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;
import com.ruhalov.util.AStarPathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Creature extends Entity {
    private int speed;
    protected final int healthPoinForReproduce = 18;
    private int healthPoint = 10;
    protected final AStarPathFinder pathFinder = new AStarPathFinder();;

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
        go(target.getCoordinates(), world);
    }

    public abstract Creature reproduce(World world);
    protected <T extends Entity> List<Coordinates> findPathToTarget(T target, World world) {
        return new ArrayList<>(pathFinder.findPathToTarget(coordinates, target.getCoordinates(), world));
    }

    protected <T extends Entity> void huntForTarget(T target, World world) {
        List<Coordinates> pathToTarget = findPathToTarget(target, world);
        if(pathToTarget.isEmpty()){
            goRand(world);
        } else {
            Coordinates coordinatesToMove = pathToTarget.get(1);
            go(coordinatesToMove, world);
        }
    }

    public <T extends Entity> T selectNearestTarget(List<T> entities, World world) {
        TreeMap<Integer, T> distancesToTarget = new TreeMap<>();
        for (T entity : entities) {
            int distance = pathFinder.manhattanDistance(coordinates, entity.getCoordinates(), world);
            distancesToTarget.put(distance, entity);
        }
        return distancesToTarget.get(distancesToTarget.firstKey());
    }

    public void go(Coordinates coordinatesToMove, World world) {
        world.clearCell(coordinates);
        Coordinates convertedCoordinates = CoordinatesConverter.convertCoordinates(coordinatesToMove, world);
        coordinates.setX(convertedCoordinates.getX());
        coordinates.setY(convertedCoordinates.getY());
        world.setEntity(this);
    }
}
