package net.rom.gctweaks.galacticraftchanges.asteroids;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;

public class BiomeProviderGctAsteroids extends BiomeProviderSpace {
	@Override
	public Biome getBiome () {
		return BiomeGctAsteroids.asteroid;
	}

}
