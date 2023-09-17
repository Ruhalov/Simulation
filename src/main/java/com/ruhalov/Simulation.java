package com.ruhalov;

import com.ruhalov.actions.Actions;
import com.ruhalov.actions.GrowGrass;
import com.ruhalov.actions.MoveAllCreature;
import com.ruhalov.actions.TurnGrassToTrees;
import com.ruhalov.actions.spawn.*;
import com.ruhalov.entity.Entity;
import com.ruhalov.entity.creature.Herbivore;
import com.ruhalov.entity.creature.Predator;
import com.ruhalov.entity.objects.Grass;
import com.ruhalov.entity.objects.stationary.Rock;
import com.ruhalov.entity.objects.stationary.Tree;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        World world = new World(15, 15);

        Actions moveAll = new MoveAllCreature();
        Actions growGrass = new GrowGrass();
        Actions turnToTrees = new TurnGrassToTrees();

        Actions spawnHerbivores = new SpawnHerbivoreAction(5);
        Actions spawnPredators = new SpawnPredatorAction(4);
        Actions spawnGrass = new SpawnGrassAction(6);
        Actions spawnTrees = new SpawnTreeAction(3);
        Actions spawnRock = new SpawnRockAction(4);

        List<Actions> initActions = new ArrayList<>();
        initActions.add(spawnGrass);
        initActions.add(spawnPredators);
        initActions.add(spawnHerbivores);
        initActions.add(spawnTrees);
        initActions.add(spawnRock);

        for (Actions action : initActions) {
            action.perform(world);
        }

        ConsoleRenderer renderer = new ConsoleRenderer();

        while (true) {
            renderer.render(world);
            growGrass.perform(world);
            moveAll.perform(world);
            turnToTrees.perform(world);
            Thread.sleep(1000);
            System.out.print("----------------------------------\n");

        }
    }
}
