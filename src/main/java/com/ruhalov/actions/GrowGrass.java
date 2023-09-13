package com.ruhalov.actions;

import com.ruhalov.World;
import com.ruhalov.entity.objects.stationary.Tree;

import java.util.List;

public class GrowGrass extends Actions{

    @Override
    public void perform(World world) {
        for (Tree tree : List.copyOf(world.getEntitiesByClass(Tree.class))) {
            tree.growGrass(world);
        }
    }
}
