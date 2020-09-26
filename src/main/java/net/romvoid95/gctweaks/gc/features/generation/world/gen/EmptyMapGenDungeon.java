package net.romvoid95.gctweaks.gc.features.generation.world.gen;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.Random;

public class EmptyMapGenDungeon extends MapGenDungeon {

    public EmptyMapGenDungeon() {
        super(null);
        System.out.println("AAAH");
    }



    @Override
    public synchronized boolean generateStructure(World worldIn, Random randomIn, ChunkPos chunkCoord) {
        return false;
    }

    @Override
    public boolean canSpawnStructureAtCoords (int chunkX, int chunkZ) {

        return false;
    }

}
