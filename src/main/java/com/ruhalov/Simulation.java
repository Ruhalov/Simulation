package com.ruhalov;

import com.ruhalov.actions.Actions;
import com.ruhalov.actions.GrowGrass;
import com.ruhalov.actions.MoveAllCreature;
import com.ruhalov.actions.TurnGrassToTrees;
import com.ruhalov.actions.spawn.*;

import java.util.List;
import java.util.stream.Stream;

import static com.ruhalov.Constants.*;

public class Simulation {
    public void start() {
        World world = new World(WORLD_WIDTH, WORLD_HEIGHT);
        List<Actions> initActions = getInitActionsList();
        List<Actions> turnActions = getTurnActionsList();
        for (Actions action : initActions) {
            action.perform(world);
        }
        ConsoleRenderer renderer = new ConsoleRenderer();
        while (true) {
            renderer.render(world);
            for (Actions action : turnActions) {
                action.perform(world);
            }
        }
    }

    private List<Actions> getInitActionsList() {
        Actions spawnHerbivores = new SpawnHerbivoreAction(SPAWN_RATE_HERBIVORE);
        Actions spawnPredators = new SpawnPredatorAction(SPAWN_RATE_PREDATOR);
        Actions spawnGrass = new SpawnGrassAction(SPAWN_RATE_GRASS);
        Actions spawnTrees = new SpawnTreeAction(SPAWN_RATE_TREE);
        Actions spawnRock = new SpawnRockAction(SPAWN_RATE_ROCK);
        return Stream.of(spawnGrass, spawnPredators, spawnHerbivores, spawnTrees, spawnRock).toList();
    }

    private List<Actions> getTurnActionsList() {
        Actions moveAll = new MoveAllCreature();
        Actions growGrass = new GrowGrass();
        Actions turnToTrees = new TurnGrassToTrees();
        return Stream.of(moveAll, growGrass, turnToTrees).toList();
    }
}
