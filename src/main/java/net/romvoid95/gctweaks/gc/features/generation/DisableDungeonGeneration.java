package net.romvoid95.gctweaks.gc.features.generation;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.romvoid95.gctweaks.gc.features.generation.world.gen.EmptyMapGenDungeon;
import net.romvoid95.gctweaks.base.Feature;

public class DisableDungeonGeneration extends Feature {
    private static boolean disableDungeonGeneration;

    @Override
    public String[] category () {
        return new String[] { "worldgen" };
    }

    @Override
    public String comment () {
        return "Ability to disable dungeon generation";
    }

    @Override
    public void syncConfig (Configuration config, String[] category) {
        disableDungeonGeneration = config
                .get(category[0], "disableDungeonGeneration", false, "Set to true if you want to disable GC dungeon generation.")
                .getBoolean();
    }

    @Override
    public boolean usesEvents() {
        return true;
    }

    @SubscribeEvent
    public void onMapGen (InitMapGenEvent event) {
        if (disableDungeonGeneration) {
            new EmptyMapGenDungeon();
            System.out.println("testAAA");
        }
    }

}
