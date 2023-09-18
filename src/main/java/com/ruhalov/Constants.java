package com.ruhalov;


public final class Constants {
    public static final int WORLD_HEIGHT = Integer.parseInt(Configurations.getInstance().getProperty("worldHeight"));
    public static final int WORLD_WIDTH = Integer.parseInt(Configurations.getInstance().getProperty("worldWidth"));
    public static final int SPAWN_RATE_HERBIVORE = Integer.parseInt(Configurations.getInstance().getProperty("spawnRateHerbivore"));
    public static final int SPAWN_RATE_PREDATOR = Integer.parseInt(Configurations.getInstance().getProperty("spawnRatePredator"));
    public static final int SPAWN_RATE_GRASS = Integer.parseInt(Configurations.getInstance().getProperty("spawnRateGrass"));
    public static final int SPAWN_RATE_TREE = Integer.parseInt(Configurations.getInstance().getProperty("spawnRateTree"));
    public static final int SPAWN_RATE_ROCK = Integer.parseInt(Configurations.getInstance().getProperty("spawnRateRock"));
    public static final int HEALTH_POINTS_CREATURE = Integer.parseInt(Configurations.getInstance().getProperty("healthPointsCreatures"));
    public static final int HEALTH_POINTS_FOR_REPRODUCE = Integer.parseInt(Configurations.getInstance().getProperty("healthPointsForReproduce"));
    public static final int STEPS_FOR_TRANSFORM_GRASS_TO_GRASS = Integer.parseInt(Configurations.getInstance().getProperty("stepsForTransformGrassToTree"));
    public static final int STEPS_FOR_GROW_GRASS_NEAR_TREE = Integer.parseInt(Configurations.getInstance().getProperty("stepsForGrowGrassNearTree"));
    public static final int COST_OF_REPRODUCTION = Integer.parseInt(Configurations.getInstance().getProperty("costOfReproduction"));
    public static final int BENEFITS_OF_FOOD = Integer.parseInt(Configurations.getInstance().getProperty("benefitsOfFood"));

    public static final String HERBIVORE_SYMBOL = Configurations.getInstance().getProperty("herbivoreSymbol");
    public static final String PREDATOR_SYMBOL = Configurations.getInstance().getProperty("predatorSymbol");
    public static final String GRASS_SYMBOL = Configurations.getInstance().getProperty("grassSymbol");
    public static final String TREE_SYMBOL = Configurations.getInstance().getProperty("treeSymbol");
    public static final String ROCK_SYMBOL = Configurations.getInstance().getProperty("rockSymbol");
    public static final String EMPTY_CELL_SYMBOL = Configurations.getInstance().getProperty("emptyCellSymbol");

    public static final String ANSI_WHITE_BACKGROUND = Configurations.getInstance().getProperty("backgroundColorANSI");
    public static final String ANSI_RESET = Configurations.getInstance().getProperty("resetBackgroundANSI");
}