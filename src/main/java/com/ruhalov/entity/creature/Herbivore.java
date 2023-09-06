package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove() {
        coordinates.setY(coordinates.getY() + 1);
    }
}
