package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;

public class Predator extends Creature {
    public Predator(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove() {
        coordinates.setY(coordinates.getY() + 1);
    }
}
