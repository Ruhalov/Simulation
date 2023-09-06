package com.ruhalov.entity.creature;

import com.ruhalov.Coordinates;
import com.ruhalov.entity.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int healthPoint;

    public Creature(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract void makeMove();
}
