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
            for (int j = 0; j < world.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(j, i);
                int numOfEntity = world.getAllEntities().size();
                for (Entity ent : world.getAllEntities()) {
                    if (ent.getCoordinates().equals(coordinates)) {
                        if (ent instanceof Predator) {
                            System.out.print("\033[0;100m\uD83D\uDFE5");
                            System.out.print("\033[0m");
                        } else if (ent instanceof Herbivore) {
                            System.out.print("\033[0;100m\uD83D\uDFE8");
                            System.out.print("\033[0m");
                        } else if (ent instanceof Grass) {
                            System.out.print("\033[0;100m\uD83D\uDFE2");
                            System.out.print("\033[0m");
                        } else if (ent instanceof Rock) {
                            System.out.print("\033[0;100m⚫");
                            System.out.print("\033[0m");
                        } else if (ent instanceof Tree) {
                            System.out.print("\033[0;100m\uD83D\uDFE4");
                            System.out.print("\033[0m");
                        }
                    } else {
                        numOfEntity--;
                        if (numOfEntity == 0) {
                            System.out.print("\033[0;100m⬜");
                            System.out.print("\033[0m");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
