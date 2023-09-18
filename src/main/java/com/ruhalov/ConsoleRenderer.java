package com.ruhalov;

import com.ruhalov.entity.Entity;
import com.ruhalov.entity.creature.Herbivore;
import com.ruhalov.entity.creature.Predator;
import com.ruhalov.entity.objects.Grass;
import com.ruhalov.entity.objects.stationary.Rock;
import com.ruhalov.entity.objects.stationary.Tree;

public class ConsoleRenderer {
    public void render(World world) {
        for (int i = 0; i < world.getHeight(); i++) {
            System.out.print(Constants.ANSI_WHITE_BACKGROUND);
            for (int j = 0; j < world.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);
                int numOfEntity = world.getAllEntities().size();
                for (Entity ent : world.getAllEntities()) {
                    if (ent.getCoordinates().equals(coordinates)) {
                        if (ent instanceof Predator) {
                            System.out.print(Constants.PREDATOR_SYMBOL);
                        } else if (ent instanceof Herbivore) {
                            System.out.print(Constants.HERBIVORE_SYMBOL);
                        } else if (ent instanceof Grass) {
                            System.out.print(Constants.GRASS_SYMBOL);
                        } else if (ent instanceof Rock) {
                            System.out.print(Constants.ROCK_SYMBOL);
                        } else if (ent instanceof Tree) {
                            System.out.print(Constants.TREE_SYMBOL);
                        }
                    } else {
                        numOfEntity--;
                        if (numOfEntity == 0) {
                            System.out.print(Constants.EMPTY_CELL_SYMBOL);
                        }
                    }
                }
            }

            System.out.println(Constants.ANSI_RESET);
        }
        System.out.println();
    }
}
